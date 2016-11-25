package soundsync.linklist;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * controller class for mediaplayer, intended to allow mediaplayer to be controlled across pages.
        */

public class MusicControler {
    private static MusicControler music = null;
    private static MediaPlayer mediaPlayer;
    private static Context context;

    private MusicControler(){

    }

    public static MusicControler getPlayer(Context cont){
        updateContext(cont);
        if(music == null){
            music = new MusicControler();
            createPlayer();
        }
        return music;
    }

    public static void updateContext(Context newCont){
        context = newCont;
    }

    private static void createPlayer(){
        mediaPlayer = MediaPlayer.create(context, R.raw.song);
    }

    public static void play(){
        if(!getStatus())
            mediaPlayer.start();
    }

    public static void pause(){
        if(getStatus())
            mediaPlayer.pause();
    }

    public static void stop(){
        mediaPlayer.stop();
    }

    public static void changeSong(int id){
        mediaPlayer = MediaPlayer.create(context, id);
    }

    public static void forward(int msec){
        int t = 0;
        t = (mediaPlayer.getCurrentPosition() + msec)%mediaPlayer.getDuration();       //skip forward 3 second
                /*mod song length so we don't try play past the end of the song
                *This will cause song to repeat*/
        mediaPlayer.seekTo(t);
    }

    public static void back(int msec){
        int t = 0;
        t = (mediaPlayer.getCurrentPosition() - msec);
        if(t>=0)
            mediaPlayer.seekTo(t);
    }

    public static boolean getStatus(){
        return mediaPlayer.isPlaying();
    }

    public static int getDuration(){
        return mediaPlayer.getDuration();
    }

    public static int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }
}
