package soundsync.linklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chris on 11/16/16.
 */

public class QueueAdapter extends BaseAdapter implements ListAdapter{
    private Context context;
    private ArrayList<String> list = new ArrayList<>();

    public QueueAdapter(ArrayList<String> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int pos){
        return list.get(pos);
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
            view = inflater.inflate(R.layout.layout_list_item, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.queueSongName);
        listItemText.setText(list.get(position));

        Button upVote = (Button) view.findViewById(R.id.upVoteButton);
        Button downVote = (Button) view.findViewById(R.id.downVoteButton);

        return view;
    }
}
