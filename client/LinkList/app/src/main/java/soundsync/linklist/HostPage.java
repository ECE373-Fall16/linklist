package soundsync.linklist;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*-------------------Activity called by HOST button from Main----------------------------------*/
public class HostPage extends AppCompatActivity {

    private Button b1,b2;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_page);

        /*  NOTE: PUT SONG FILE TO BE PLAYED IN RES/RAW LABELED 'song'  */
        mediaPlayer = MediaPlayer.create(this, R.raw.song);     //create mediaplayer to play song in res/raw
        b1 = (Button) findViewById(R.id.PlayButton);            //b1 = play
        b2 = (Button) findViewById(R.id.PauseButton);           //b2 = pause
        b2.setEnabled(false);                                   //disable pause button before playing

        /****runs on play button click****/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();                            //start song
                b1.setEnabled(false);                           //disable play
                b2.setEnabled(true);                            //enable pause
            }

        });

        /****runs on pause button click****/
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();                            //pause player
                b2.setEnabled(false);                           //disable pause
                b1.setEnabled(true);                            //enable play
            }
        });
    }

}
