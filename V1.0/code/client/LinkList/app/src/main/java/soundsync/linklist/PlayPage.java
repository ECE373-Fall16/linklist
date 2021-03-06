package soundsync.linklist;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.sdk.android.player.Metadata;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.client.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PlayPage extends AppCompatActivity {

  //  private SeekBar timebar;
    private TextView cur, dur, name, title, queueTitle;
    private ListView listView;
  //  private MediaPlayer mediaPlayer;
//    private Handler hand = new Handler();

    Client client;

    private int ListCounter = 0;
    private ArrayList<Track> queuedTracks = new ArrayList<Track>();

    SpotifyApi api = new SpotifyApi();
    SpotifyService spotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        client = Client.getClient();

        Intent intent = getIntent();
        String lobby = intent.getStringExtra(EXTRA_MESSAGE);

        listView = (ListView) findViewById(R.id.listView);
        name = (TextView) findViewById(R.id.songName);          //name = song info
        title = (TextView) findViewById(R.id.title);
        queueTitle = (TextView) findViewById(R.id.queueTitle);
        //lobbyName = (TextView) findViewById(R.id.hostName);
        // viewQueue = (Button) findViewById(R.id.queue);

        api.setAccessToken(MusicControler.getAccessToken());
        spotify = api.getService();

      //  name.setText(String.format("Current Song"));

        title.setText("Connected to lobby: " + Client.getJoinedRoomName());

//        dur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration())));    //label duration
//        cur.setText(String.format("%d sec", mediaPlayer.getCurrentPosition()));          //label current time (only 0 sec for now)

 //       mediaPlayer.setVolume(0, 0);
  //      mediaPlayer.start();                            //start song

        // name.setText(String.format("Music courtesy of BenSound"));

  //      timebar.setMax(mediaPlayer.getDuration());
   //     timebar.setProgress(mediaPlayer.getCurrentPosition());
        //hand.postDelayed(UpdateSongTime,100);


      /*  private Runnable UpdateSongTime = new Runnable() {
            public void run() {
                int startTime = mediaPlayer.getCurrentPosition();
                cur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getCurrentPosition())));
                timebar.setProgress((int) startTime);
                hand.postDelayed(this, 100);
            }
        };  */

  /*      @Override
        public boolean onKeyDown ( int keyCode, KeyEvent
        event)   //stop music player on back button press
        {
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                mediaPlayer.stop();
            }
            return super.onKeyDown(keyCode, event);
        }   */



    }

    public void onResume(){
        super.onResume();
        refreshList();
    }


    public void goToQueue(View view) {
        Intent intent = new Intent(this, Queue.class);
        startActivity(intent);
    }

    public void playPageCancel(View view) {
        Intent cancelIntent = new Intent(this, Main.class);
        startActivity(cancelIntent);
    }

    public void playPageSearch(View view) {
        Intent searchIntent = new Intent(this, spotifySearch.class);
        startActivity(searchIntent);
    }

    public void listAdd(Track track) {
        queuedTracks.add(ListCounter, track);
        ListCounter++;
    }

    public void refreshList(){
        int listSize = client.getListSize();

        ArrayList<String> songName = new ArrayList<>();
        ArrayList<String> artist = new ArrayList<>();
        ArrayList<String> album = new ArrayList<>();
        if(listSize>0) {
            for (int i = 0; i < listSize; i++) {
                songName.add(client.getSongName(i));
                artist.add(client.getSongArtist(i));
                album.add(client.getSongAlbum(i));
            }

            SearchAdapter adapter = new SearchAdapter(songName, artist, album, PlayPage.this, 1);


            // ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }

    public void refreshList(View v){
        int listSize = client.getListSize();

        ArrayList<String> songName = new ArrayList<>();
        ArrayList<String> artist = new ArrayList<>();
        ArrayList<String> album = new ArrayList<>();
        if(listSize>0) {
            for (int i = 0; i < listSize; i++) {
                System.out.println("[PlayPage]Song #: "+i+"; Song: "+client.getSongName(i));
                songName.add(client.getSongName(i));
                artist.add(client.getSongArtist(i));
                album.add(client.getSongAlbum(i));

            }

            SearchAdapter adapter = new SearchAdapter(songName, artist, album, PlayPage.this, 1);


            // ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }


}