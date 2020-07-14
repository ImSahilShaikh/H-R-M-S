package engg.project.hrmt;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Hrmleave extends AppCompatActivity {
    Button bstore;
    TextView tid, tdate;
    EditText tdetails;
    int count = 0;
    String hid = "", hdate = "", hdetails = "";
    ProgressDialog pd;
    Sheets service = null;
    String spid = "";
    String range = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hrmleave);

        tid = (TextView) findViewById(R.id.tid);
        tdate = (TextView) findViewById(R.id.tdate);
        tdetails = (EditText) findViewById(R.id.tdetails);

        bstore = (Button) findViewById(R.id.bstore);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        hdate = df.format(c.getTime());

        tdate.setText(hdate);

        try {
            SQLiteDatabase db = openOrCreateDatabase("hrmt", MODE_PRIVATE, null);
            Cursor cr = db.rawQuery("Select * from signupinfo", null);
            cr.moveToFirst();


            if (cr != null) {

                do {

                    tid.setText(cr.getString(0));
                    hid = cr.getString(0);

                } while (cr.moveToNext());
            }
        } catch (Exception e) {

        }

        bstore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hdetails=tdetails.getText().toString();
                pd = new ProgressDialog(Hrmleave.this);
                pd.setMessage("Savinging Leave Request ------ > ");
                pd.setTitle("Wait ");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                new MyAsyncTask().execute("ODM");

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

            spid = "1G66d1dLH2YYk7h_Npy9l_FJnD1UWjjDUILskhrHubhg";
            range = "A:D";

            ValueRange response = service.spreadsheets().values()
                    .get(spid, range)
                    .execute();
            List<List<Object>> values = response.getValues();

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

                List<List<Object>> writeData = new ArrayList<>();

                List<Object> dataRow = new ArrayList<>();
                dataRow.add(hid);
                dataRow.add(hdate);
                dataRow.add(hdetails);
                dataRow.add("NEW");
                writeData.add(dataRow);

                ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");

                service.spreadsheets().values()
                        .append(spid, range, vr)
                        .setValueInputOption("RAW")
                        .execute();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Double result) {
            Toast toast = Toast
                    .makeText(
                            Hrmleave.this,
                            "LEAVE REQUEST INFO STORED SUCCESSFULLY",
                            Toast.LENGTH_LONG);
            toast.show();
            pd.dismiss();
        }

        protected void onProgressUpdate(Integer... progress) {

        }

    }
}
