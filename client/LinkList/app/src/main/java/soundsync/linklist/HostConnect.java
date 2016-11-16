package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class HostConnect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_connect);
    }


    public void hostPlay(View view){
        Intent intent = new Intent(this, HostPage.class);   //HostPage.java TO BE WRITTEN
        EditText lobby = (EditText) findViewById(R.id.lobbyName);
        EditText size = (EditText) findViewById(R.id.lobbySize);
        String message = lobby.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);  //go to HostPage
        lobby.setText("");
        size.setText("");
    }
}
