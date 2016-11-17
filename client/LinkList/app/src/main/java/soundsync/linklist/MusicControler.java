package soundsync.linklist;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Chris on 11/16/16.
 */

public class MusicControler {
    private MediaPlayer mediaPlayer;
    private Context context;

    public MusicControler(Context context){
        this.context = context;
    }

    public void createPlayer(){
        mediaPlayer = MediaPlayer.create(context, R.raw.song);
    }

    public void changeSong(int id){
        mediaPlayer = MediaPlayer.create(context, id);
    }
}
