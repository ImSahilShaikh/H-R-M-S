package engg.project.hrmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hrmmenu1 extends AppCompatActivity {
    Button bsend, bget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hrmmenu1);

        bsend = (Button) findViewById(R.id.bsend);
        bget = (Button) findViewById(R.id.bget);
        
        bsend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i1 = new Intent(Hrmmenu1.this, Hrmleave.class);
                Hrmmenu1.this.startActivity(i1);


            }
        });

        bget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i1 = new Intent(Hrmmenu1.this, Hrmleave1.class);
                Hrmmenu1.this.startActivity(i1);


            }
        });
    }
}
