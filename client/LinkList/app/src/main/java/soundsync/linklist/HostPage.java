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

import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.client.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/*-------------------Activity called by HOST button from Main----------------------------------*/
public class HostPage extends AppCompatActivity {
    Client client;
    private Button play,pause,back,forward,queue,nextInQueue;
    private SeekBar timebar;
    private TextView name,myLobby, lobbyID, cur, dur;
    MusicControler music;
    private Handler hand = new Handler();
    private boolean playing;


    private static final int REQUEST_CODE = 1337;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_page);
        //client = (Client)getIntent().getSerializableExtra("client");

        /*  NOTE: PUT SONG FILE TO BE PLAYED IN RES/RAW LABELED 'song'  */
       // mediaPlayer = MediaPlayer.create(this, R.raw.song);     //create mediaplayer to play song in res/raw

       // music = MusicControler.getPlayer(HostPage.this);
        music = MusicControler.getPlayer();
        play = (Button) findViewById(R.id.PlayButton);            //play = play
        pause = (Button) findViewById(R.id.PauseButton);           //pause = pause
        //forward = (Button) findViewById(R.id.SkipFor);          //forward = skipfor
        timebar = (SeekBar) findViewById(R.id.seekBar);         //timebar = seekbar
        cur = (TextView) findViewById(R.id.currentTime);        //cur = current time
        dur = (TextView) findViewById(R.id.duration);           //dur = duration
        name = (TextView) findViewById(R.id.songName);          //name = song info
        myLobby = (TextView)findViewById(R.id.myLobby);
        queue = (Button) findViewById(R.id.hostQueueButton);
        lobbyID = (TextView) findViewById(R.id.lobbyId);
        nextInQueue = (Button) findViewById(R.id.SkipFor);

        playing = music.getStatus();        //this tells us if we're playing or not
            //we only update current time if we're playing, but that method gets called ever 100ms
            //so we set playing to getStatus, and call getStatus elsewhere

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
                play.setEnabled(false);                           //disable play
                pause.setEnabled(true);                            //enable pause
               // playing = music.getStatus();        //update playing status
            }
        });

        /****runs on pause button click****/
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.pause();                            //pause player
                pause.setEnabled(false);                           //disable pause
                play.setEnabled(true);//enable play
              //  playing = music.getStatus();            //update playing status
            }
        });

        nextInQueue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                music.playNextSong(client.playNext());
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
       // dur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(music.getDuration())) + "/");    //label duration
       // cur.setText(String.format("%d sec", TimeUnit.MILLISECONDS.toSeconds(music.getCurrentPosition())));          //label current time (only 0 sec for now)
      //  name.setText(String.format("Music courtesy of BenSound"));

//        timebar.setMax((int)music.getDuration());
     //   timebar.setProgress(music.getCurrentPosition());
        hand.postDelayed(UpdateSongTime,100);



    }


//***********************commented out as unnecessary extra step, rplaced by client.playNext()
 /*   public String nextInQueue(){
        return Client.playNext();

    }       */

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if(music.getStatus()) {
                long curTime = music.getCurrentPosition();
                long duration= music.getDuration();
                dur.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(duration),
                        TimeUnit.MILLISECONDS.toSeconds(duration) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))));
                cur.setText(String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(curTime),
                        TimeUnit.MILLISECONDS.toSeconds(curTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(curTime))));


                timebar.setMax((int)duration);
                timebar.setProgress((int)curTime);
            }

           hand.postDelayed(this, 100);
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
