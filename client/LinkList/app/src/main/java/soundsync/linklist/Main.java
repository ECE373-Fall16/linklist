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

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
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
        String accessToken = MusicControler.getAccessToken();

        //////

        setContentView(R.layout.activity_main);

     /*   SpotifyApi api = new SpotifyApi();

// Most (but not all) of the Spotify Web API endpoints require authorisation.
// If you know you'll only use the ones that don't require authorisation you can skip this step
        api.setAccessToken(accessToken);

        SpotifyService spotify = api.getService();

        spotify.searchTracks("Ocean Man");


        spotify.searchTracks("Ocean Man", new SpotifyCallback<TracksPager>() {
            @Override
            public void success(TracksPager tracksPager, Response response) {
                Log.d("Oceaanman", tracksPager.tracks.items.);
            }

            @Override
            public void failure(SpotifyError error) {
                Log.d("couldn't find oceanman", error.toString());
            }
        });

        spotify.getAlbum("2dIGnmEIy1WZIcZCFSj6i8", new Callback<Album>() {
            @Override
            public void success(Album album, Response response) {
                Log.d("Album success", album.name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Album failure", error.toString());
            }
        }); */

//        client = Client.getClient();
      // if(client==null){
        //    Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
        //}

        if(client.getClient()==null)
            Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
        if(client.getClient()!=null)
            Toast.makeText(this, "Connection Success", Toast.LENGTH_SHORT).show();


       /* //--------------FROM SPOTIFY-------------//
>>>>>>> c97a05c60acdd47237ec2f91a1859685dbf91936
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
