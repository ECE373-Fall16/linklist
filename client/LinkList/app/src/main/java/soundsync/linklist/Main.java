package soundsync.linklist;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.xmlrpc.android.XMLRPCClient;

//DO YOU SEE THIS

public class Main extends AppCompatActivity {

    int i;
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///////////override to allow networking in forground
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //////

        setContentView(R.layout.activity_main);





        client = Client.getClient();
      // if(client==null){
        //    Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
        //}

    }

    /***Onclick for PLAY button***/
    public void selectPlay(View view){                      //method called on "play button" click
        Intent intent = new Intent(this, PlayConnect.class);   //create intent to change to PlayPage (to be written)
        startActivity(intent);  //go to PlayPage


    }

    /***Onclick for HOST button***/
    public void selectHost(View view){
        Intent intent = new Intent(this, HostConnect.class);   //HostPage.java TO BE WRITTEN
        startActivity(intent);  //go to HostPage


    }
}
