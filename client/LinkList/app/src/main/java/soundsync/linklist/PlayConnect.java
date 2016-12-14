package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlrpc.android.XMLRPCClient;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PlayConnect extends AppCompatActivity {
    Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_connect);
        client = Client.getClient();
    }

    public void playPage(View view){
        Intent intent = new Intent(this, PlayPage.class);
        EditText editText = (EditText) findViewById(R.id.lobbyName);


        String message = Client.joinRoom(Integer.parseInt(editText.getText().toString()));
        if(message=="There aint no rooms you rogue agent, host something instead") {
            Toast.makeText(this, "No Lobbies available", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra(EXTRA_MESSAGE, message);
        editText.setText("");
        startActivity(intent);  //go to HostPage
    }
    public void playCancel(View view){//cancel button on PlayConnect
        Intent playIntent = new Intent(this, Main.class);
        startActivity(playIntent);  //go to Main
    }
}
