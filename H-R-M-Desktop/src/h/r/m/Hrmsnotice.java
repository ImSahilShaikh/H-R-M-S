package h.r.m;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

public class Hrmsnotice extends javax.swing.JFrame {

    JSpinner.DateEditor de;
    String sndate = "";
    Sheets service = null;
    String spid = "";
    String range = "";
    int count = 0;
    String[] date1 = new String[1000];
    String[] details = new String[1000];
    int r = 0;
    Aes aes = new Aes();
    String data = "";

    public Hrmsnotice() {
        initComponents();
        this.setSize(1042, 768);
        de = new JSpinner.DateEditor(ds, "dd/MM/yy");
        ds.setEditor(de);
        this.setTitle("HUMAN RESOURCE MANAGEMENT");

        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);
        bremove.setEnabled(false);
        badd.setEnabled(false);
        lname.setMultipleSelections(false);

        connect();
    }

    public void connect() {
        try {
            Credential credential = getCredentials();
            service = new Sheets.Builder(getHttpTransport(),
                    getJsonFactory(),
                    credential)
                    .setApplicationName("HRM")
                    .build();
            credential.refreshToken();
            System.out.println("" + credential.getAccessToken());

            spid = "1O4svOSakJeMxuL-ry9cpAjM0j649BXMeek12pe1hDFo";
            range = "A:B";

            ValueRange response = service.spreadsheets().values()
                    .get(spid, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            count = values.size();
            System.out.println("Count ----->   " + count);
            int i = 0;
            if (values == null || values.size() == 0) {
                System.out.println("No data found.");
            } else {

                for (List row : values) {
                    if (i > 0) {
                        System.out.println("row " + row.get(1).toString());
                        /*
                        data = aes.decrypt(row.get(0).toString());
                        lname.add(data);
                        data = aes.decrypt(row.get(1).toString());
                        details[i] = data;
                        */
                        lname.add(row.get(0).toString());
                        details[i] = row.get(1).toString();
                        

                    }
                    i = i + 1;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Hrmsnotice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmsnotice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Hrmsnotice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static JsonFactory getJsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }

    private static HttpTransport getHttpTransport()
            throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    public static Credential getCredentials()
            throws GeneralSecurityException, IOException {

        File p12 = new File("c:\\hrms.p12");
        System.out.println(p12.getAbsoluteFile());
        List<String> SCOPES_ARRAY
                = Arrays.asList(SheetsScopes.SPREADSHEETS);

        Credential credential = new GoogleCredential.Builder()
                .setTransport(getHttpTransport())
                .setJsonFactory(getJsonFactory())
                .setServiceAccountId("hrms-445@hrms-190011.iam.gserviceaccount.com")
                .setServiceAccountScopes(SCOPES_ARRAY)
                .setServiceAccountPrivateKeyFromP12File(p12)
                .build();
        credential.refreshToken();
        System.out.println("token ----->   " + credential.getAccessToken());
        return credential;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tdetails = new javax.swing.JTextField();
        ds = new javax.swing.JSpinner();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        lname = new java.awt.List();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        bremove = new javax.swing.JButton();
        badd = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        bnew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("STUDENT'S NOTICE MASTER");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 40, 880, 40);

        jLayeredPane2.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Notice Details :-");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(10, 60, 200, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Date :-");
        jLayeredPane2.add(jLabel8);
        jLabel8.setBounds(40, 20, 170, 30);

        tdetails.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(tdetails);
        tdetails.setBounds(220, 60, 360, 30);

        ds.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ds.setModel(new javax.swing.SpinnerDateModel());
        jLayeredPane2.add(ds);
        ds.setBounds(220, 20, 120, 30);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(20, 110, 590, 120);

        jLayeredPane3.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lname.setBackground(new java.awt.Color(204, 255, 204));
        lname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lnameItemStateChanged(evt);
            }
        });
        lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameActionPerformed(evt);
            }
        });
        jLayeredPane3.add(lname);
        lname.setBounds(10, 10, 270, 180);

        getContentPane().add(jLayeredPane3);
        jLayeredPane3.setBounds(620, 110, 290, 200);

        jLayeredPane1.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bremove.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bremove.setText("Delete");
        bremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bremoveActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bremove);
        bremove.setBounds(180, 20, 110, 30);

        badd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        badd.setText("Save");
        badd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baddActionPerformed(evt);
            }
        });
        jLayeredPane1.add(badd);
        badd.setBounds(290, 20, 110, 30);

        bexit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bexit.setText("Exit");
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bexit);
        bexit.setBounds(400, 20, 110, 30);

        bnew.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bnew.setText("Add");
        bnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnewActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bnew);
        bnew.setBounds(70, 20, 110, 30);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(20, 240, 590, 70);

        jLabel1.setBackground(new java.awt.Color(153, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download-2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 1200, 900);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lnameItemStateChanged
}//GEN-LAST:event_lnameItemStateChanged

    private void bremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bremoveActionPerformed
        Spreadsheet spreadsheet = null;
        try {
            spreadsheet = service.spreadsheets().get(spid).execute();
        } catch (IOException e) {
            System.out.println("Error 1  " + e);
        }
        BatchUpdateSpreadsheetRequest content = new BatchUpdateSpreadsheetRequest();
        Request request = new Request();
        DeleteDimensionRequest deleteDimensionRequest = new DeleteDimensionRequest();
        DimensionRange dimensionRange = new DimensionRange();
        dimensionRange.setDimension("ROWS");
        System.out.println("spid=  " + spid);
        System.out.println("r=  " + r);

        dimensionRange.setStartIndex(r + 1);
        dimensionRange.setEndIndex(r + 2);

        dimensionRange.setSheetId(spreadsheet.getSheets().get(0).getProperties().getSheetId());
        deleteDimensionRequest.setRange(dimensionRange);

        request.setDeleteDimension(deleteDimensionRequest);

        List<Request> requests = new ArrayList<Request>();
        requests.add(request);
        content.setRequests(requests);

        try {
            service.spreadsheets().batchUpdate(spid, content).execute();
        } catch (IOException e) {
            System.out.println("Error 2  " + e);
        } finally {
            dimensionRange = null;
            deleteDimensionRequest = null;
            request = null;
            requests = null;
            content = null;
        }
        JOptionPane.showMessageDialog(new Hrmsnotice(), "Data deleted Successfully");
        this.dispose();
        this.setVisible(false);
}//GEN-LAST:event_bremoveActionPerformed

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baddActionPerformed

        Date date = (Date) ds.getValue();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        sndate = "" + formatter.format(date).trim();
        try {
            List<List<Object>> writeData = new ArrayList<>();

            List<Object> dataRow = new ArrayList<>();
            /*
            data=aes.encrypt(sndate);
            dataRow.add(data);
            data=aes.encrypt(tdetails.getText());
            dataRow.add(data);
            */
            dataRow.add(sndate);
            dataRow.add(tdetails.getText());
            
            writeData.add(dataRow);

            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");

            service.spreadsheets().values()
                    .append(spid, range, vr)
                    .setValueInputOption("RAW")
                    .execute();
             //data=aes.encrypt(sndate);
             //lname.add(data);
            lname.add(sndate);
            JOptionPane.showMessageDialog(new Hrmsnotice(), "DATA SAVED SUCCESSFULLY");
        } catch (Exception ex) {
            Logger.getLogger(Hrmsnotice.class.getName()).log(Level.SEVERE, null, ex);
        }

}//GEN-LAST:event_baddActionPerformed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_bexitActionPerformed

    private void bnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnewActionPerformed
        tdetails.setText("");
        tdetails.requestFocus();
        bremove.setEnabled(false);
        badd.setEnabled(true);
}//GEN-LAST:event_bnewActionPerformed

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed

        bremove.setEnabled(true);
        badd.setEnabled(false);
        r = lname.getSelectedIndex();
        tdetails.setText(details[r + 1]);

    }//GEN-LAST:event_lnameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Hrmsnotice().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton badd;
    private javax.swing.JButton bexit;
    private javax.swing.JButton bnew;
    private javax.swing.JButton bremove;
    private javax.swing.JSpinner ds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private java.awt.List lname;
    private javax.swing.JTextField tdetails;
    // End of variables declaration//GEN-END:variables
}
