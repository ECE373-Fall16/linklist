package soundsync.linklist;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class Main extends AppCompatActivity /* implements SpotifyPlayer.NotificationCallback,ConnectionStateCallback */{

    int i;
    Client client;
    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///////////override to allow networking in forground
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //////

        setContentView(R.layout.activity_main);

        client = Client.getClient();
      // if(client==null){
        //    Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
        //}


       /* //--------------FROM SPOTIFY-------------//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        //---------------------------------------// */

    }

    /***Onclick for PLAY button***/
    public void selectPlay(View view){                      //method called on "play button" click
        Intent intent = new Intent(this, PlayConnect.class);   //create intent to change to PlayPage (to be written)
        startActivity(intent);  //go to PlayPage


    }

    /***Onclick for HOST button***/
    public void selectHost(View view){
        Intent intent = new Intent(this, HostConnect.class);   //HostPage.java TO BE WRITTEN
        startActivity(intent);  //go to HostPage


    }

/*******************************SPOTIFY STUFF
    private static final String CLIENT_ID = "e4402cbd73bf4eb1b6571a9659783af2"; // TODO: remove this?

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
                        mPlayer.addConnectionStateCallback(Main.this);
                        mPlayer.addNotificationCallback(Main.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
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
        Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");

      //  mPlayer.playUri(null, "spotify:track:7sSC2ndMmI1qGNbu8UXQuE", 0, 0);

    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(int i) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

            ********************/

}
