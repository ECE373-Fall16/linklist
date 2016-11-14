package soundsync.linklist;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/*-------------------Activity called by HOST button from Main----------------------------------*/
public class HostPage extends AppCompatActivity {

    private Button play,pause,back,forward;
    private SeekBar timebar;
    private TextView cur,dur,name;
    private MediaPlayer mediaPlayer;
    private Handler hand = new Handler();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_page);


        /*  NOTE: PUT SONG FILE TO BE PLAYED IN RES/RAW LABELED 'song'  */
        mediaPlayer = MediaPlayer.create(this, R.raw.song);     //create mediaplayer to play song in res/raw
        play = (Button) findViewById(R.id.PlayButton);            //play = play
        pause = (Button) findViewById(R.id.PauseButton);           //pause = pause
        back = (Button) findViewById(R.id.SkipBack);               //back = skipback
        forward = (Button) findViewById(R.id.SkipFor);          //forward = skipfor
        timebar = (SeekBar) findViewById(R.id.seekBar);         //timebar = seekbar
        cur = (TextView) findViewById(R.id.currentTime);        //cur = current time
        dur = (TextView) findViewById(R.id.duration);           //dur = duration
        name = (TextView) findViewById(R.id.songName);          //name = song info



        pause.setEnabled(false);                                   //disable pause button before playing

        /****runs on play button click****/
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();                            //start song
                play.setEnabled(false);                           //disable play
                pause.setEnabled(true);                            //enable pause

                name.setText(String.format("Music courtesy of BenSound"));

                timebar.setMax(mediaPlayer.getDuration());
                timebar.setProgress(mediaPlayer.getCurrentPosition());
                hand.postDelayed(UpdateSongTime,100);
            }
        });

        /****runs on pause button click****/
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();                            //pause player
                pause.setEnabled(false);                           //disable pause
                play.setEnabled(true);                            //enable play
            }
        });

        /**************RUNS ON SKIP BACK BUTTON**********/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int t = 0;
                t = mediaPlayer.getCurrentPosition() - 3000;       //skip back 3 second
                if(t<0){t=0;}                                      //don't have negative time
                mediaPlayer.seekTo(t);                              //seek to new time
            }
        });

        /**************RUNS ON SKIP FORWARD BUTTON**********/
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int t = 0;
                t = (mediaPlayer.getCurrentPosition() + 3000)%mediaPlayer.getDuration();       //skip forward 3 second
                /*mod song length so we don't try play past the end of the song
                *This will cause song to repeat*/
                mediaPlayer.seekTo(t);                              //seek to new time

            }
        });


        dur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getDuration())));    //label duration
        cur.setText(String.format("%d sec", mediaPlayer.getCurrentPosition()));          //label current time (only 0 sec for now)


    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            int startTime = mediaPlayer.getCurrentPosition();
            cur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getCurrentPosition())));
            timebar.setProgress((int)startTime);
            hand.postDelayed(this, 100);
        }
    };

}
