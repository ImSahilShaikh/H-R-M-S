package engg.project.hrmt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Calendar;

public class Hrmmenu extends AppCompatActivity {
    Button bview;
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hrmmenu);
        bview = (Button) findViewById(R.id.bview);

        bview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                PopupMenu popup = new PopupMenu(Hrmmenu.this, bview);

                popup.getMenuInflater()
                        .inflate(R.menu.popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("TEACHER'S NOTICE")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmnotice.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("WEEK SCHEDULER")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmscheduler.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("NOTIFICATION")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmnotify.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("LEAVE MANAGEMENT")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmmenu1.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("ENTER FEEDBACK")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmfeedback.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("EXIT APP")) {
                            Hrmmenu.this.finish();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }
}
