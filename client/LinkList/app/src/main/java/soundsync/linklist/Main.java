package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//DO YOU SEE THIS

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
