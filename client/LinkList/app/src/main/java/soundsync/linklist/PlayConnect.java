package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayConnect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_connect);
    }

    public void playPage(View view){
        Intent intent = new Intent(this, PlayPage.class);   //HostPage.java TO BE WRITTEN
        startActivity(intent);  //go to HostPage
    }
}
