package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectPlay(View view){                      //method called on "play button" click
        Intent intent = new Intent(this, PlayPage.class);   //create intent to change to PlayPage (to be written)
        startActivity(intent);  //go to PlayPage
    }

    public void selectHost(View view){
        Intent intent = new Intent(this, HostPage.class);   //HostPage.java TO BE WRITTEN
        startActivity(intent);  //go to HostPage
    }
}
