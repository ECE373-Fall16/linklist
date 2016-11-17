package soundsync.linklist;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * controller class for mediaplayer, intended to allow mediaplayer to be controlled across pages.
        */

public class MusicControler {
    public MediaPlayer mediaPlayer;
    private Context context;

    public MusicControler(Context context){
        this.context = context;
    }

    public void createPlayer(){
        mediaPlayer = MediaPlayer.create(context, R.raw.song);
    }

    public void play(){
        if(!getStatus())
            mediaPlayer.start();
    }

    public void pause(){
        if(getStatus())
            mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public void changeSong(int id){
        mediaPlayer = MediaPlayer.create(context, id);
    }

    public void forward(int msec){
        int t = 0;
        t = (mediaPlayer.getCurrentPosition() + msec)%mediaPlayer.getDuration();       //skip forward 3 second
                /*mod song length so we don't try play past the end of the song
                *This will cause song to repeat*/
        mediaPlayer.seekTo(t);
    }

    public boolean getStatus(){
        return mediaPlayer.isPlaying();
    }
}
