package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PlayConnect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_connect);
    }

    public void playPage(View view){
        Intent intent = new Intent(this, PlayPage.class);
        EditText editText = (EditText) findViewById(R.id.lobbyName);
        /* Client client = new Client("http://10.0.3.2:8080/RPC2");
        int i = client.createClient();
        if(i==-1){
           Toast.makeText(getApplicationContext(), "Could not connect.", Toast.LENGTH_LONG).show();
        } */

        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        editText.setText("");
        startActivity(intent);  //go to HostPage
    }
}
