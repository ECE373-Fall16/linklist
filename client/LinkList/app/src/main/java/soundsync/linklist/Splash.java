package soundsync.linklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    public Client client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    //super.run();
                    client = Client.getClient();
                    if(client==null){
                        Splash.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Splash.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Splash error: " + e);
                } finally {

                    Intent i = new Intent(Splash.this,
                            Main.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        welcomeThread.start();
    }
}
