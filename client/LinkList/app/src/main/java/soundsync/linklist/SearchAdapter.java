package soundsync.linklist;

import android.content.Context;
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
    Client client = Client.getClient();
   // private Button queueClick;

    public SearchAdapter(List<Track> tracklist, Context context){
        this.list = list;
        this.context = context;
        this.trackList = tracklist;
    }

    public SearchAdapter(List<Track> tracklist, Context context, int type){
        this.list = list;
        this.context = context;
        this.trackList = tracklist;
        this.type = type;
    }

    @Override
    public int getCount(){
        return trackList.size();
    }

    @Override
    public Object getItem(int pos){
        return trackList.get(pos);
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
        final Button queueClick = (Button)view.findViewById(R.id.addToQueue) ;
        TextView songName = (TextView)view.findViewById(R.id.queueSongName);
        TextView artist = (TextView)view.findViewById(R.id.subText);
        TextView album = (TextView)view.findViewById(R.id.albumText);

        songName.setText(trackList.get(position).name);
        artist.setText(trackList.get(position).artists.get(0).name);
        album.setText(trackList.get(position).album.name);

        queueClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.makeSong(trackList.get(position).uri);
                queueClick.setEnabled(false);
            }
        });

        return view;
    }
}
