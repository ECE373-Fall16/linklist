package soundsync.linklist;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class Queue extends AppCompatActivity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        client = Client.getClient();
      /*  String[] songNames;


        ArrayList<String> list = new ArrayList<String>();
        list.add("Song 1");
        list.add("Song 2");
        list.add("Song 3");


        QueueAdapter adapter = new QueueAdapter(list, this);


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);   */
        int listSize = client.getListSize();

        ArrayList<String> songName = new ArrayList<>();
        ArrayList<String> artist = new ArrayList<>();
        ArrayList<String> album = new ArrayList<>();
        if(listSize>0) {
            for (int i = 0; i <= listSize; i++) {
                System.out.println("[PlayPage]For loop index: "+i);
                songName.add(client.getSongName(i));
                artist.add(client.getSongArtist(i));
                album.add(client.getSongAlbum(i));

            }

            SearchAdapter adapter = new SearchAdapter(songName, artist, album, Queue.this, 1);


            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }

    }
}
