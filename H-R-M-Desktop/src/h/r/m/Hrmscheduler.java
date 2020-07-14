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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Hrmscheduler extends javax.swing.JFrame {

    Sheets service = null;
    String spid = "";
    String range = "";
    int count = 0;
    String[] day = new String[1000];
    String[] first = new String[1000];
    String[] second = new String[1000];
    String[] third = new String[1000];
    String[] fourth = new String[1000];
    String[] fifth = new String[1000];
    int r = 0;

    public Hrmscheduler() {
        initComponents();

        bremove.setEnabled(false);
        bsave.setEnabled(false);
        lname.setMultipleSelections(false);
        this.setSize(1042, 768);
        this.setTitle("HUMAN RESOURCE MANAGEMENT");

        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);

        cbday.add("Monday");
        cbday.add("Tuesday");
        cbday.add("Wednesday");
        cbday.add("Thursday");
        cbday.add("Friday");
        cbday.add("Saturday");

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

            spid = "1kx0UCqGJSAcrrxiDf8Xu_Q2cwc92nsHTlhR-wynOumY";
            range = "A:F";

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
                        lname.add(row.get(0).toString());
                        day[i] = row.get(0).toString();
                        first[i] = row.get(1).toString();
                        second[i] = row.get(2).toString();
                        third[i] = row.get(3).toString();
                        fourth[i] = row.get(4).toString();
                        fifth[i] = row.get(5).toString();
                    }
                    i = i + 1;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Hrmscheduler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmscheduler.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        t5 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();
        cbday = new java.awt.Choice();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        lname = new java.awt.List();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        bremove = new javax.swing.JButton();
        bsave = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        bnew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("WEEK SCHEDULER HOUR WISE MASTER");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 40, 880, 40);

        jLayeredPane2.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("First:-");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(50, 60, 180, 30);

        t2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(t2);
        t2.setBounds(250, 100, 260, 30);

        t4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(t4);
        t4.setBounds(250, 180, 260, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Second:-");
        jLayeredPane2.add(jLabel5);
        jLabel5.setBounds(30, 100, 200, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Week Day:-");
        jLayeredPane2.add(jLabel8);
        jLabel8.setBounds(60, 20, 170, 30);

        t1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(t1);
        t1.setBounds(250, 60, 260, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Fourth:-");
        jLayeredPane2.add(jLabel9);
        jLabel9.setBounds(30, 180, 200, 30);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Fifth:-");
        jLayeredPane2.add(jLabel10);
        jLabel10.setBounds(0, 220, 230, 30);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Third:-");
        jLayeredPane2.add(jLabel11);
        jLabel11.setBounds(40, 140, 190, 30);

        t5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(t5);
        t5.setBounds(250, 220, 260, 30);

        t3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(t3);
        t3.setBounds(250, 140, 260, 30);

        cbday.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(cbday);
        cbday.setBounds(250, 20, 220, 30);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(20, 110, 590, 280);

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
        lname.setBounds(10, 10, 270, 340);

        getContentPane().add(jLayeredPane3);
        jLayeredPane3.setBounds(620, 110, 290, 360);

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

        bsave.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bsave.setText("Save");
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bsave);
        bsave.setBounds(290, 20, 110, 30);

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
        jLayeredPane1.setBounds(20, 400, 590, 70);

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

        dimensionRange.setStartIndex(r);
        dimensionRange.setEndIndex(r + 1);

        dimensionRange.setSheetId(spreadsheet.getSheets().get(0).getProperties().getSheetId());
        dimensionRange.setSheetId(spreadsheet.getSheets().get(0).getProperties().getSheetId());
        dimensionRange.setSheetId(spreadsheet.getSheets().get(0).getProperties().getSheetId());
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
        JOptionPane.showMessageDialog(new Hrmlogin(), "Data deleted Successfully");
        this.dispose();
        this.setVisible(false);

}//GEN-LAST:event_bremoveActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed

        try {
            List<List<Object>> writeData = new ArrayList<>();

            List<Object> dataRow = new ArrayList<>();
            dataRow.add(cbday.getSelectedItem());
            dataRow.add(t1.getText());
            dataRow.add(t2.getText());
            dataRow.add(t3.getText());
            dataRow.add(t4.getText());
            dataRow.add(t5.getText());
            writeData.add(dataRow);

            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");

            service.spreadsheets().values()
                    .append(spid, range, vr)
                    .setValueInputOption("RAW")
                    .execute();
            lname.add(cbday.getSelectedItem());
            JOptionPane.showMessageDialog(new Hrmscheduler(), "DATA SAVED SUCCESSFULLY");
        } catch (Exception ex) {
            Logger.getLogger(Hrmscheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_bsaveActionPerformed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_bexitActionPerformed

    private void bnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnewActionPerformed
        // TODO add your handling code here:
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        cbday.requestFocus();
        t1.requestFocus();
        bremove.setEnabled(false);
        bsave.setEnabled(true);
}//GEN-LAST:event_bnewActionPerformed

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed
        bremove.setEnabled(true);
        r = lname.getSelectedIndex();
        cbday.select(day[r + 1]);
        t1.setText(first[r + 1]);
        t2.setText(second[r + 1]);
        t3.setText(third[r + 1]);
        t4.setText(fourth[r + 1]);
        t5.setText(fifth[r + 1]);
    }//GEN-LAST:event_lnameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Hrmscheduler().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bexit;
    private javax.swing.JButton bnew;
    private javax.swing.JButton bremove;
    private javax.swing.JButton bsave;
    private java.awt.Choice cbday;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private java.awt.List lname;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    // End of variables declaration//GEN-END:variables
}