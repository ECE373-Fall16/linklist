package soundsync.linklist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.spotify.sdk.android.player.Metadata;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by Chris on 11/16/16.
 */

public class SearchAdapter extends BaseAdapter implements ListAdapter{
    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    private List<Track> trackList;

    private int type=0;
    private ArrayList<String> Name;
    private ArrayList<String> Artist;
    private ArrayList<String> Album;


    Client client = Client.getClient();
   // private Button queueClick;

    public SearchAdapter(List<Track> tracklist, Context context){
        this.list = list;
        this.context = context;
        this.trackList = tracklist;
    }

    public SearchAdapter(ArrayList<String> name, ArrayList<String> artist, ArrayList<String> album, Context context, int type){
        Name = name;
        Artist = artist;
        Album = album;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount(){
        if(type==0)
            return trackList.size();
        else
            return Name.size();

    }

    @Override
    public Object getItem(int pos){
        if(type==0)
            return trackList.get(pos);
        else
            return Name.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(type==0)
                view = inflater.inflate(R.layout.layout_search_item, null);
            else
                view = inflater.inflate(R.layout.layout_viewqueue_item, null);
        }

        TextView songName = (TextView)view.findViewById(R.id.queueSongName);
        TextView artist = (TextView)view.findViewById(R.id.subText);
        TextView album = (TextView)view.findViewById(R.id.albumText);
        if(type!=0){
            songName.setText(Name.get(position));
            artist.setText(Artist.get(position));
            album.setText(Album.get(position));
        }

        else {
            final Button queueClick = (Button)view.findViewById(R.id.addToQueue);
            final Track track = trackList.get(position);
            songName.setText(track.name);
            artist.setText(track.artists.get(0).name);
            album.setText(track.album.name);


            queueClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ret = client.makeSong(track.uri, track.name, track.artists.get(0).name, track.album.name);
                    if (ret == 0) {
                        queueClick.setEnabled(false);
                        Log.d("[searchAdapter]makSong", "Made song");
                    } else if (ret == 1) {
                        Log.d("[searchAdapter]makeSong", "XMLRPC error");
                    } else if (ret == 3) {
                        Log.d("[searchAdapter]makeSong", "not in room error");
                    }
                }
            });
        }

        return view;
    }
}
