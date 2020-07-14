package engg.project.hrmt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    DonutProgress cpb;
    private Timer timer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpb = (DonutProgress) findViewById(R.id.cpb);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cpb.setProgress(cpb.getProgress() + 10);

                        if (cpb.getProgress() == 100) {
                            timer.cancel();
                            Intent i1 = new Intent(MainActivity.this, Hrmlogin.class);
                            MainActivity.this.startActivity(i1);
                            MainActivity.this.finish();
                        }
                    }
                });
            }
        }, 100, 100);
    }
}
