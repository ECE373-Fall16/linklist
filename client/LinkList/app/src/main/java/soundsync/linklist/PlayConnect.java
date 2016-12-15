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
        EditText editText = (EditText) findViewById(R.id.lobbyName);

        if(editText.getText().toString().equals("")){
            Toast.makeText(this, "Enter Room ID", Toast.LENGTH_SHORT).show();
            return;
        }


        String message = client.joinRoom(Integer.parseInt(editText.getText().toString()));

        if(message.equals("There aint no rooms you rogue agent, host something instead")) {
            Toast.makeText(this, "There ain't no rooms you rogue agent", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(message.equals("not connected to server")){
            Toast.makeText(this, "This doesn't look like anything to me\n(server connection error)", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println("joined room: "+message);

        Intent intent = new Intent(this, PlayPage.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        editText.setText("");
        startActivity(intent);  //go to PlayPage (to add songs to playlist)
        finish();               //kill this page on leaving
    }

    //cancel button on PlayConnect
    public void playCancel(View view){
        Intent playIntent = new Intent(this, Main.class);
        startActivity(playIntent);  //go to Main
        finish();                   //kill this page on leaving
    }
}
