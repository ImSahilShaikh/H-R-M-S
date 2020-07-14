package h.r.m;

import java.awt.Dimension;
import java.awt.Color;
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

public class Hrmleave extends javax.swing.JFrame {

    Sheets service = null;
    String spid = "";
    String range = "";
    Sheets service1 = null;
    String spid1 = "";
    String range1 = "";
    int count = 0;
    String[] id = new String[1000];
    String[] date = new String[1000];
    String[] details = new String[1000];
    String[] order = new String[1000];
    int r = 0;

    public Hrmleave() {
        initComponents();

        lname.setMultipleSelections(false);
        this.setSize(1042, 768);
        this.setTitle("HUMAN RESOURCE MANAGEMENT");

        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);

        connect();
        connect1();
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

            spid = "1G66d1dLH2YYk7h_Npy9l_FJnD1UWjjDUILskhrHubhg";
            range = "A:D";

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
                        id[i] = row.get(0).toString();
                        date[i] = row.get(1).toString();
                        details[i] = row.get(2).toString();
                        order[i] = row.get(3).toString();
                    }
                    i = i + 1;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Hrmleave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmleave.class.getName()).log(Level.SEVERE, null, ex);
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

    public void connect1() {
        try {
            Credential credential = getCredentials1();
            service1 = new Sheets.Builder(getHttpTransport1(),
                    getJsonFactory1(),
                    credential)
                    .setApplicationName("HRM")
                    .build();
            credential.refreshToken();
            System.out.println("" + credential.getAccessToken());

            spid1 = "1RWRs_aK-On3Icg--3IBwiTObLM71S8UkfGe4svTSivU";
            range1 = "A:D";

            ValueRange response = service1.spreadsheets().values()
                    .get(spid1, range1)
                    .execute();

        } catch (IOException ex) {
            Logger.getLogger(Hrmleave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmleave.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static JsonFactory getJsonFactory1() {
        return JacksonFactory.getDefaultInstance();
    }

    private static HttpTransport getHttpTransport1()
            throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    public static Credential getCredentials1()
            throws GeneralSecurityException, IOException {

        File p12 = new File("c:\\hrms.p12");
        System.out.println(p12.getAbsoluteFile());
        List<String> SCOPES_ARRAY
                = Arrays.asList(SheetsScopes.SPREADSHEETS);

        Credential credential = new GoogleCredential.Builder()
                .setTransport(getHttpTransport1())
                .setJsonFactory(getJsonFactory1())
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
        tdate = new javax.swing.JTextField();
        torder = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tdetails = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        lname = new java.awt.List();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        bsave = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("LEAVE MANAGEMENT MASTER");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 40, 880, 40);

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Id:-");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(50, 60, 180, 30);

        tdate.setFont(new java.awt.Font("Times New Roman", 1, 18));//
		tdate.setForeground(Color.black);
		jLayeredPane2.add(tdate);
		tdate.setEnabled(false);
        tdate.setBounds(250, 100, 260, 30);

        torder.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(torder);
        torder.setBounds(250, 180, 260, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Date:-");
        jLayeredPane2.add(jLabel5);
        jLabel5.setBounds(30, 100, 200, 30);

        tid.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(tid);
		tid.setEnabled(false);
        tid.setBounds(250, 60, 260, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Order:-");
        jLayeredPane2.add(jLabel9);
        jLabel9.setBounds(30, 180, 200, 30);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Details:-");
        jLayeredPane2.add(jLabel11);
        jLabel11.setBounds(40, 140, 190, 30);

        tdetails.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(tdetails);
		tdetails.setEnabled(false);
        tdetails.setBounds(250, 140, 260, 30);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(20, 110, 590, 280);

        jLayeredPane3.setBackground(new java.awt.Color(255, 255, 255));
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

        bsave.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bsave.setText("Save");
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bsave);
        bsave.setBounds(160, 20, 110, 30);

        bexit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bexit.setText("Exit");
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bexit);
        bexit.setBounds(320, 20, 110, 30);

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

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed

        try {
            List<List<Object>> writeData = new ArrayList<>();

            List<Object> dataRow = new ArrayList<>();
            dataRow.add(tid.getText());
            dataRow.add(tdate.getText());
            dataRow.add(tdetails.getText());
            dataRow.add(torder.getText());
            writeData.add(dataRow);

            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");

            service1.spreadsheets().values()
                    .append(spid1, range1, vr)
                    .setValueInputOption("RAW")
                    .execute();
            JOptionPane.showMessageDialog(new Hrmleave(), "LEAVE REQUEST ANSWERED SUCCESSFULLY");
        } catch (Exception ex) {
            Logger.getLogger(Hrmleave.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_bsaveActionPerformed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_bexitActionPerformed

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed
        r = lname.getSelectedIndex();
        tid.setText(id[r + 1]);
        tdate.setText(date[r + 1]);
        tdetails.setText(details[r + 1]);
        torder.setText(order[r + 1]);
    }//GEN-LAST:event_lnameActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Hrmleave().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify without concerning developers
    private javax.swing.JButton bexit;
    private javax.swing.JButton bsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private java.awt.List lname;
    private javax.swing.JTextField tdate;
    private javax.swing.JTextField tdetails;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField torder;
    // End of variables declaration
}