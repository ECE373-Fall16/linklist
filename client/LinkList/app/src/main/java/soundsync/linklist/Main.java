package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//DO YOU SEE THIS

public class Main extends AppCompatActivity {
    int i;
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new Client("http://104.196.192.226:8080/RPC2");
        i = client.createClient();
        if(i==-1){
            Toast.makeText(getApplicationContext(), "Could not connect.", Toast.LENGTH_LONG).show();
        }
    }

    /***Onclick for PLAY button***/
    public void selectPlay(View view){                      //method called on "play button" click
        Intent intent = new Intent(this, PlayConnect.class);   //create intent to change to PlayPage (to be written)
        startActivity(intent);  //go to PlayPage
        intent.putExtra("client", client);
    }

    /***Onclick for HOST button***/
    public void selectHost(View view){
        Intent intent = new Intent(this, HostConnect.class);   //HostPage.java TO BE WRITTEN
        startActivity(intent);  //go to HostPage
        intent.putExtra("client", client);
    }
}
