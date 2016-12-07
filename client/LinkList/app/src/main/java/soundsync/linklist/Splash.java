package soundsync.linklist;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ProgressBar;
import android.os.Handler;
import android.graphics.*;
import android.view.*;
import java.lang.Object;
import android.support.v4.app.FragmentActivity;



public class Splash extends AppCompatActivity {

    public Client client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* progress bar on splash page :: in progress
        final int PROGRESS = 0x1;

        ProgressBar mProgress;
        final int mProgressStatus = 0;

        Handler mHandler = new Handler();


        protected void onCreate(Bundle icicle) {
            super.onCreate(icicle);

            mProgress = (ProgressBar) findViewById(R.id.progress_bar);

            // Start lengthy operation in a background thread
            new Thread(new Runnable() {
                public void run() {
                    while (mProgressStatus < 100) {
                        //mProgressStatus = doWork();

                        // Update the progress bar

                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgress.setProgress(mProgressStatus);
                            }
                        });
                    }
                }
            }).start();
        }
        */



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
