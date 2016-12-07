package soundsync.linklist;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ProgressBar;
import android.os.Handler;
import android.graphics.*;
import android.view.*;
import java.lang.Object;
import android.support.v4.app.FragmentActivity;



//---------------------SPOTIFY IMPORTS----------------------//
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
//---------------------------------------------------------//

public class Splash extends Activity implements SpotifyPlayer.NotificationCallback,ConnectionStateCallback  {

    public Client client = null;
    private static final int REQUEST_CODE = 1337;

    Thread welcomeThread = new Thread() {

        @Override
        public void run() {
            try {
                //super.run();
                client = Client.getClient();
                if(client==null){
                    Splash.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Splash.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (Exception e) {
                System.out.println("Splash error: " + e);
            } finally {


                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        welcomeThread.start();



        //--------------FROM SPOTIFY-------------//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        //---------------------------------------//

    }

    private static final String CLIENT_ID = ""; // TODO: remove this?

    private static final String REDIRECT_URI = "soundsync.linklist://callback";

    private Player mPlayer;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(Splash.this);
                        mPlayer.addNotificationCallback(Splash.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("SplashActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Spotify.destroyPlayer(this); //THIS NEEDS TO HAPPEN OR WE HAVE MEMORY LEAKS
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d("SplashActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("SplashActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("SplashActivity", "User logged in");
        try {
            welcomeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(Splash.this,
                Main.class);
        startActivity(i);
        //  mPlayer.playUri(null, "spotify:track:7sSC2ndMmI1qGNbu8UXQuE", 0, 0);

    }

    @Override
    public void onLoggedOut() {
        Log.d("SplashActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(int i) {
        Log.d("SplashActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("SplashActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("SplashActivity", "Received connection message: " + message);
    }

}
