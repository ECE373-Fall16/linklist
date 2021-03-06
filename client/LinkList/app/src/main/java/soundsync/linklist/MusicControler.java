package soundsync.linklist;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.Settings;

//------------------------Spotify----------------------//
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
//-----------------------------------------------------//

/**
 * controller class for mediaplayer, intended to allow mediaplayer to be controlled across pages.
        */

public class MusicControler {
    private static MusicControler music = null;
    private static Player mediaPlayer;
    private static Context context;
    private static String accessToken;

    private MusicControler(){

    }

    public static void makePlayer(Player player, String accessToken){
        mediaPlayer = player;
        MusicControler.accessToken = accessToken;

    }

    public static MusicControler getPlayer(){
        //updateContext(cont);
      /*  if(music == null){
            music = new MusicControler();
            //createPlayer();
        }   */
        return music;
    }

    public static String getAccessToken(){
        return accessToken;
    }

    public static void updateContext(Context newCont){
        context = newCont;
    }

   /* private static void createPlayer(){
        mediaPlayer = MediaPlayer.create(context, R.raw.song);
    }   */

    public static void play(){
      //  if(!getStatus())
        final Player.OperationCallback callback = new Player.OperationCallback() {
            @Override
            public void onSuccess() {
                System.out.println("it went OK");
            }

            @Override
            public void onError(Error error) {
                System.out.println("we fucked it");
            }
        };
        mediaPlayer.resume(callback);
    }

      public static void pause(){
          final Player.OperationCallback callback = new Player.OperationCallback() {
              @Override
              public void onSuccess() {
                  System.out.println("it went OK");
              }

              @Override
              public void onError(Error error) {
                    System.out.println("we fucked it");
              }
          };
          mediaPlayer.pause(callback);
    }

    public static void playNextSong(String string){
        mediaPlayer.playUri(null,string, 0, 0);
    }
/*
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
                *This will cause song to repeat
        mediaPlayer.seekTo(t);
    }

    public static void back(int msec){
        int t = 0;
        t = (mediaPlayer.getCurrentPosition() - msec);
        if(t>=0)
            mediaPlayer.seekTo(t);
    }
*/
    public static boolean getStatus(){
        return mediaPlayer.getPlaybackState().isPlaying;
    }

    public static long getDuration(){
        return mediaPlayer.getMetadata().currentTrack.durationMs;
    }

    public static long getCurrentPosition(){
        return mediaPlayer.getPlaybackState().positionMs;
    }
}
