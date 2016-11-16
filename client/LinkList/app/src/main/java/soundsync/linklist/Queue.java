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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        String[] songNames;


        ArrayList<String> list = new ArrayList<String>();
        list.add("Song 1");
        list.add("Song 2");
        list.add("Song 3");


        QueueAdapter adapter = new QueueAdapter(list, this);


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
