package engg.project.hrms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class Hrmlogin extends AppCompatActivity {

    Button blogin;
    TextView tid;
    EditText tpass;

    String uid1 = "";
    String uphno1 = "";
    String upass1 = "";
    String[] uid = new String[1000];
    String[] uphno = new String[1000];
    String[] upass = new String[1000];
    int count = 0;
    ProgressDialog pd;

    Sheets service = null;
    String spid = "";
    String range = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hrmlogin);

        tid = (TextView) findViewById(R.id.tid);
        tpass = (EditText) findViewById(R.id.tpass);
        blogin = (Button) findViewById(R.id.blogin);

        blogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                uid1 = tid.getText().toString();
                upass1 = tpass.getText().toString();

                pd = new ProgressDialog(Hrmlogin.this);
                pd.setMessage("Please wait for Server to Respond");
                pd.setTitle("Authenticating");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();

                new MyAsyncTask().execute("HRM");
            }
        });
    }

    public void connect() throws GeneralSecurityException {
        try {
            Credential credential = getCredentials();
            service = new Sheets.Builder(new NetHttpTransport(),
                    getJsonFactory(),
                    credential)
                    .setApplicationName("Ashvini1")
                    .build();
            credential.refreshToken();
            System.out.println("welcome " + credential.getAccessToken());

            spid = "109Wx6NcKoWuFCZtI2nmcNgf-2Sq8XQOoRhicudwaKDs";
            range = "A:D";

            ValueRange response = service.spreadsheets().values()
                    .get(spid, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            count = values.size();
            System.out.println("welcome Count ----->   " + count);
            int ii = 0;
            if (values == null || values.size() == 0) {
                System.out.println("welcome No data found.");
            } else {

                for (List row : values) {

                    if (ii > 0) {
                        uid[ii] = row.get(0).toString();
                        uphno[ii] = row.get(2).toString();
                        upass[ii] = row.get(3).toString();
                    }
                    ii = ii + 1;

                }
            }
        } catch (IOException ex) {

        }

    }

    private JsonFactory getJsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }


    public Credential getCredentials() throws GeneralSecurityException, IOException {

        System.out.println("welcome 2");
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(baseDir + File.separator + "hrms.p12");

        File f = new File(getCacheDir() + "/hrms.p12");
        if (!f.exists()) {
            InputStream is = getAssets().open("hrms.p12");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer);
            fos.close();

        }
        System.out.println("welcome " + file.getAbsoluteFile());

        List<String> SCOPES_ARRAY
                = Arrays.asList(SheetsScopes.SPREADSHEETS);

        Credential credential = null;

        credential = new GoogleCredential.Builder()
                .setTransport(new NetHttpTransport())
                .setJsonFactory(getJsonFactory())
                .setServiceAccountId("hrms-445@hrms-190011.iam.gserviceaccount.com")
                .setServiceAccountScopes(SCOPES_ARRAY)
                .setServiceAccountPrivateKeyFromP12File(file)
                .build();
        credential.refreshToken();
        System.out.println("welcome token ----->   " + credential.getAccessToken());

        return credential;
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
        @Override
        protected Double doInBackground(String... params) {
            try {
                connect();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Double result) {
            int cc = 0;
            for (int i = 1; i < count; i++) {
                if (uid[i].equals(uid1) && upass[i].equals(upass1)) {
                    SQLiteDatabase db = openOrCreateDatabase("hrms", MODE_PRIVATE, null);
                    db.execSQL("DROP TABLE IF EXISTS signupinfo");
                    db.execSQL("CREATE TABLE IF NOT EXISTS signupinfo(uid VARCHAR,uphno VARCHAR);");
                    db.execSQL("INSERT INTO signupinfo (uid,uphno) VALUES ('" + uid1 +
                            "','" + uphno1 + "');");

                    cc = 1;
                }
            }
            if (cc == 1) {
                Intent i1 = new Intent(Hrmlogin.this, Hrmmenu.class);
                Hrmlogin.this.startActivity(i1);
                Hrmlogin.this.finish();
            } else {
                Toast toast = Toast
                        .makeText(
                                Hrmlogin.this,
                                "PLEASE ENTER ID AND PASSWORD CORRECTLY OR CREATE A NEW ONE USING SIGNUP",
                                Toast.LENGTH_LONG);
                toast.show();
            }
            pd.dismiss();
        }

        protected void onProgressUpdate(Integer... progress) {

        }
    }
}
