package engg.project.hrmt;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

public class Hrmleave1 extends AppCompatActivity {
    private Context context;
    String hid[] = new String[1000];
    String hdate[] = new String[1000];
    String hdetails[] = new String[1000];
    String horder[] = new String[1000];
    int count = 0;
    TableLayout tableLayout;
    ProgressDialog pd;
    String hid1 = "";

    Sheets service = null;
    String spid = "";
    String range = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hrmleave1);

        try {
            SQLiteDatabase db = openOrCreateDatabase("hrmt", MODE_PRIVATE, null);
            Cursor cr = db.rawQuery("Select * from signupinfo", null);
            cr.moveToFirst();

            if (cr != null) {
                do {
                    hid1 = cr.getString(0);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {

        }

        context = this;

        tableLayout = (TableLayout) findViewById(R.id.tablelayout);

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#FF4081"));

        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"DATE", "DETAILS", "ORDER"};
        for (String c : headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(40, 40, 40, 40);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);
        pd = new ProgressDialog(Hrmleave1.this);
        pd.setMessage("Fethcing Leave Request Info ------ > ");
        pd.setTitle("Wait ");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        new Hrmleave1.MyAsyncTask().execute("HRM");
    }

    public void connect() throws GeneralSecurityException {
        try {
            Credential credential = getCredentials();
            service = new Sheets.Builder(new NetHttpTransport(),
                    getJsonFactory(),
                    credential)
                    .setApplicationName("hrms")
                    .build();
            credential.refreshToken();
            System.out.println("welcome " + credential.getAccessToken());

            spid = "1RWRs_aK-On3Icg--3IBwiTObLM71S8UkfGe4svTSivU";
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
                        hid[ii] = row.get(0).toString();
                        hdate[ii] = row.get(1).toString();
                        hdetails[ii] = row.get(2).toString();
                        horder[ii] = row.get(3).toString();
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

            for (int i = 1; i < count; i++) {
                System.out.println("welcome id " + hid[i]);

               if (hid[i].equals(hid1)) {
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText = {hdate[i], hdetails[i], horder[i]};

                    for (String text : colText) {
                        TextView tv = new TextView(Hrmleave1.this);

                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setTextColor(Color.parseColor("#DC143C"));
                        tv.setPadding(10, 10, 10, 10);
                        tv.setText(text);
                        row.addView(tv);
                    }
                    tableLayout.addView(row);
                }
            }
            pd.dismiss();

        }

        protected void onProgressUpdate(Integer... progress) {

        }

    }

}
