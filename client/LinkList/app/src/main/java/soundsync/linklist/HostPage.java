package soundsync.linklist;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

//------------------------Spotify----------------------//
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
//-----------------------------------------------------//

import java.util.concurrent.TimeUnit;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/*-------------------Activity called by HOST button from Main----------------------------------*/
public class HostPage extends AppCompatActivity {
    Client client;
    private Button play,pause,back,forward,queue;
    private SeekBar timebar;
    private TextView cur,dur,name,myLobby, lobbyID;
    MusicControler music;
    private Handler hand = new Handler();


    private static final int REQUEST_CODE = 1337;
    Player mPlayer;

    //MusicControler mc = new MusicControler(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_page);
        //client = (Client)getIntent().getSerializableExtra("client");

        /*  NOTE: PUT SONG FILE TO BE PLAYED IN RES/RAW LABELED 'song'  */
       // mediaPlayer = MediaPlayer.create(this, R.raw.song);     //create mediaplayer to play song in res/raw

       // music = MusicControler.getPlayer(HostPage.this);

        play = (Button) findViewById(R.id.PlayButton);            //play = play
        pause = (Button) findViewById(R.id.PauseButton);           //pause = pause
        forward = (Button) findViewById(R.id.SkipFor);          //forward = skipfor
        timebar = (SeekBar) findViewById(R.id.seekBar);         //timebar = seekbar
        cur = (TextView) findViewById(R.id.currentTime);        //cur = current time
        dur = (TextView) findViewById(R.id.duration);           //dur = duration
        name = (TextView) findViewById(R.id.songName);          //name = song info
        myLobby = (TextView)findViewById(R.id.myLobby);
        queue = (Button) findViewById(R.id.hostQueueButton);
        lobbyID = (TextView) findViewById(R.id.lobbyId);

        Intent intent = getIntent();
        String lobby = intent.getStringExtra(EXTRA_MESSAGE);
        myLobby.setText("Hosting Lobby: " + lobby);
        lobbyID.setText("Lobby ID (share with friends): " + Client.getRoomId());










        //disable pause button before playing

        /****runs on play button click****/
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.play();                            //start song
                //mc.play();
                play.setEnabled(false);                           //disable play
                pause.setEnabled(true);                            //enable pause


            }
        });

        /****runs on pause button click****/
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.pause();                            //pause player
                pause.setEnabled(false);                           //disable pause
                play.setEnabled(true);                            //enable play
            }
        });


        /**************RUNS ON SKIP FORWARD BUTTON**********/
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               // music.forward(3000);

            }
        });

        queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HostPage.this, Queue.class);   //create intent to change to PlayPage (to be written)
                startActivity(intent);
            }
        });




    //setup timebar and song title
      //  dur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)music.getDuration())));    //label duration
     //   cur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)music.getCurrentPosition())));          //label current time (only 0 sec for now)
      //  name.setText(String.format("Music courtesy of BenSound"));

      //  timebar.setMax(music.getDuration());
     //   timebar.setProgress(music.getCurrentPosition());
        hand.postDelayed(UpdateSongTime,100);



    }






    public String nextInQueue(){
        return Client.playNext();
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
        //    int startTime = music.getCurrentPosition();
       //     cur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds((long)startTime)));
        //    timebar.setProgress((int)startTime);
          //  hand.postDelayed(this, 100);
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            //music.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void hostpageCancel(View view){ //Cancel button on hostPage

        Intent intent = new Intent(this, Main.class);



        /* working on this... -Vinni
        AlertDialog.Builder quitConfirm = new AlertDialog.Builder(getBaseContext());//Alertbox to confirm close of room
        quitConfirm.setMessage("Are you sure you want to close your lobby?");
        quitConfirm.setCancelable(true);

        quitConfirm.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });

        quitConfirm.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert1 = quitConfirm.create();
        alert1.show();
        */
        startActivity(intent);  //go to Main
    }
}
