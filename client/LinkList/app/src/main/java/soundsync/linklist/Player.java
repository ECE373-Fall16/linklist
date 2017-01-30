package soundsync.linklist;

/**
 * Created by Monkeysee on 12/8/2016.
 */

import android.support.annotation.Nullable;

public interface Player {

    void play(String url);

    void pause();

    void resume();

    boolean isPlaying();

    @Nullable
    String getCurrentTrack();

    void release();
}
