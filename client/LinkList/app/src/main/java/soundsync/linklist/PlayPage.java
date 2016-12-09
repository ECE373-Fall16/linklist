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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kaaes.spotify.webapi.android.models.Track;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PlayPage extends AppCompatActivity {

    private SeekBar timebar;
    private TextView cur,dur,name,title;
    private MediaPlayer mediaPlayer;
    private Handler hand = new Handler();
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        Intent intent = getIntent();
        String lobby = intent.getStringExtra(EXTRA_MESSAGE);
        //client = (Client)intent.getSerializableExtra("client");


        /*  NOTE: PUT SONG FILE TO BE PLAYED IN RES/RAW LABELED 'song'  */
        //music = MusicControler.getPlayer(PlayPage.this);
        mediaPlayer = mediaPlayer.create(this, R.raw.song);
        timebar = (SeekBar) findViewById(R.id.seekBar);         //timebar = seekbar
        cur = (TextView) findViewById(R.id.currentTime);        //cur = current time
        dur = (TextView) findViewById(R.id.duration);           //dur = duration
        name = (TextView) findViewById(R.id.songName);          //name = song info
        title = (TextView) findViewById(R.id.title);
        //lobbyName = (TextView) findViewById(R.id.hostName);
       // viewQueue = (Button) findViewById(R.id.queue);

        name.setText(String.format("Current Song"));

        title.setText("Connected to lobby: "+lobby);

        dur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getDuration())));    //label duration
        cur.setText(String.format("%d sec", mediaPlayer.getCurrentPosition()));          //label current time (only 0 sec for now)

        mediaPlayer.setVolume(0,0);
        mediaPlayer.start();                            //start song

       // name.setText(String.format("Music courtesy of BenSound"));

        timebar.setMax(mediaPlayer.getDuration());
        timebar.setProgress(mediaPlayer.getCurrentPosition());
        hand.postDelayed(UpdateSongTime,100);

        ArrayList<String> list = new ArrayList<String>();
        list.add("Song 1");
        list.add("Song 2");
        list.add("Song 3");//added more 'songs' to show scrolling
        list.add("Song 4");
        list.add("Song 5");
        list.add("Song 6");
        list.add("Song 7");
        list.add("Song 8");
        list.add("Song 9");
        list.add("Song 10");

        QueueAdapter adapter = new QueueAdapter(list, this);


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            int startTime = mediaPlayer.getCurrentPosition();
            cur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getCurrentPosition())));
            timebar.setProgress((int)startTime);
            hand.postDelayed(this, 100);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)   //stop music player on back button press
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            mediaPlayer.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void goToQueue(View view){
        Intent intent = new Intent(this, Queue.class);
        startActivity(intent);
    }
    public void playPageCancel(View view){
        Intent cancelIntent = new Intent(this, Main.class);
        startActivity(cancelIntent);
    }

    public void playPageSearch(View view){
        Intent searchIntent = new Intent(this, spotifySearch.class);
        startActivity(searchIntent);
    }

}
