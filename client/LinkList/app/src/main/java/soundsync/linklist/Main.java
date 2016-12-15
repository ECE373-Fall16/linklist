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

public class Main extends AppCompatActivity {
    int i;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //allows network connection in main thread; in future netweork connections should get their own thread...
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        client = Client.getClient();
        System.out.println("Main onCreate");
        if(client==null||client.connectFail==1)
            Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
        else if(client!=null) {
            Toast.makeText(this, "Connection Success", Toast.LENGTH_SHORT).show();
        }

    }

    //onRestart called when back button is pressed
    @Override
    protected void onRestart(){
        super.onRestart();
        client = Client.getClient();
        System.out.println("Main onRestart called");
    }

    /***Onclick for PLAY button***/
    public void selectPlay(View view){
        if(client==null){
            Toast.makeText(this, "Cannot Connect", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, PlayConnect.class);
        startActivity(intent);  //go to PlayPage
    }

    /***Onclick for HOST button***/
    public void selectHost(View view){
        if(client==null){
            Toast.makeText(this, "Cannot Connect", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, HostConnect.class);
        startActivity(intent);  //go to HostPage
    }

    public void tryConnection(View v){
        if(client==null){
            client = Client.getClient();
        }
        if(client==null)
            Toast.makeText(this, "Connection Failure", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Connection Success", Toast.LENGTH_SHORT).show();
    }

}
