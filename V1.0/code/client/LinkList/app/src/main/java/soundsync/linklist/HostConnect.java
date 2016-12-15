package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlrpc.android.XMLRPCClient;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class HostConnect extends AppCompatActivity {
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_connect);
        client = Client.getClient();

    }

    public void hostCancel(View view){//cancel button on hostConnect
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);  //go to Main
    }



    public void hostPlay(View view){
        EditText lobby = (EditText) findViewById(R.id.lobbyName);
        EditText size = (EditText) findViewById(R.id.lobbySize);
        if (lobby.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Lobby Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(size.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Room Size", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            client.sendLobbyInfo(lobby.getText().toString(), Integer.parseInt(size.getText().toString()));
        }

        String message = lobby.getText().toString();

        Intent intent = new Intent(this, HostPage.class);
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);  //go to HostPage (muisc player page)
        finish();               //kill this page after leaving
        lobby.setText("");
        size.setText("");
    }
}
