package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PlayConnect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_connect);
    }

    public void playPage(View view){
        Intent intent = new Intent(this, PlayPage.class);   //HostPage.java TO BE WRITTEN
        EditText editText = (EditText) findViewById(R.id.lobbyName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        editText.setText("");
        startActivity(intent);  //go to HostPage
    }
}
