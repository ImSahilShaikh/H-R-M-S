package engg.project.hrms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

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
                        if (item.getTitle().equals("STUDENT'S NOTICE")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmnotice.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("WEEK SCHEDULER")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmscheduler.class);
                            Hrmmenu.this.startActivity(i1);
                        } else if (item.getTitle().equals("NOTIFICATION")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmnotify.class);
                            Hrmmenu.this.startActivity(i1);
                        } /*else if (item.getTitle().equals("ASSESMENT SHEET")) {
                            Intent i1 = new Intent(Hrmmenu.this, Hrmassesment.class);
                            Hrmmenu.this.startActivity(i1);
                        }*/  else if (item.getTitle().equals("EXIT APP")) {
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
