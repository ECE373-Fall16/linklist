package soundsync.linklist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PlayPage extends AppCompatActivity {

    private SeekBar timebar;
    private TextView cur,dur,name,lobbyName;
    private MediaPlayer mediaPlayer;
    private Button viewQueue;
    private Handler hand = new Handler();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        Intent intent = getIntent();
        String lobby = intent.getStringExtra(EXTRA_MESSAGE);


        /*  NOTE: PUT SONG FILE TO BE PLAYED IN RES/RAW LABELED 'song'  */
        mediaPlayer = MediaPlayer.create(this, R.raw.song);     //create mediaplayer to play song in res/raw
        timebar = (SeekBar) findViewById(R.id.seekBar);         //timebar = seekbar
        cur = (TextView) findViewById(R.id.currentTime);        //cur = current time
        dur = (TextView) findViewById(R.id.duration);           //dur = duration
        name = (TextView) findViewById(R.id.songName);          //name = song info
        lobbyName = (TextView) findViewById(R.id.hostName);
        viewQueue = (Button) findViewById(R.id.queue);

        name.setText(String.format("Music courtesy of BenSound"));

        lobbyName.setText(lobby);

        dur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getDuration())));    //label duration
        cur.setText(String.format("%d sec", mediaPlayer.getCurrentPosition()));          //label current time (only 0 sec for now)

        mediaPlayer.setVolume(0,0);
        mediaPlayer.start();                            //start song

       // name.setText(String.format("Music courtesy of BenSound"));

        timebar.setMax(mediaPlayer.getDuration());
        timebar.setProgress(mediaPlayer.getCurrentPosition());
        hand.postDelayed(UpdateSongTime,100);


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
    public boolean onKeyDown(int keyCode, KeyEvent event)
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
}
