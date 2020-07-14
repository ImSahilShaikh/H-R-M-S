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

public class Hrmstudents extends javax.swing.JFrame {

    Sheets service = null;
    String spid = "";
    String range = "";
    int count = 0;
    String[] name = new String[1000];
    String[] address = new String[1000];
    String[] phno = new String[1000];
    int r = 0;

    public Hrmstudents() {
        initComponents();

        bremove.setEnabled(false);
        bsave.setEnabled(false);
        lname.setMultipleSelections(false);

        this.setSize(1042,768);
        this.setTitle("HUMAN RESOURCE MANAGEMENT");

        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);

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

            spid = "109Wx6NcKoWuFCZtI2nmcNgf-2Sq8XQOoRhicudwaKDs";
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
                        name[i] = row.get(0).toString();
                        address[i] = row.get(1).toString();
                        phno[i] = row.get(2).toString();

                    }
                    i = i + 1;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Hrmstudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmstudents.class.getName()).log(Level.SEVERE, null, ex);
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

        jComboBox1 = new javax.swing.JComboBox();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        tname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        taddress = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tphno = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tpass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        lname = new java.awt.List();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        bremove = new javax.swing.JButton();
        bsave = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        badd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLayeredPane2.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(tname);
        tname.setBounds(220, 30, 260, 30);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText(" Name :-");
        jLayeredPane2.add(jLabel10);
        jLabel10.setBounds(20, 30, 190, 30);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText(" Address :-");
        jLayeredPane2.add(jLabel11);
        jLabel11.setBounds(0, 70, 210, 30);

        taddress.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(taddress);
        taddress.setBounds(220, 70, 350, 30);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(java.awt.Color.white);
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText(" Phone No. :-");
        jLayeredPane2.add(jLabel12);
        jLabel12.setBounds(0, 110, 210, 30);

        tphno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(tphno);
        tphno.setBounds(220, 110, 260, 30);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(java.awt.Color.white);
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText(" Password :-");
        jLayeredPane2.add(jLabel13);
        jLabel13.setBounds(0, 150, 210, 30);

        tpass.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLayeredPane2.add(tpass);
        tpass.setBounds(220, 150, 260, 30);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(20, 110, 590, 210);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ENTER STUDENTS INFORMATION");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 40, 880, 40);

        jLayeredPane3.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lname.setBackground(new java.awt.Color(255, 255, 255));
        lname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lname.setMultipleMode(true);
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
        lname.setBounds(10, 10, 270, 270);

        getContentPane().add(jLayeredPane3);
        jLayeredPane3.setBounds(620, 110, 290, 290);

        jLayeredPane1.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bremove.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bremove.setForeground(java.awt.Color.black);
        bremove.setText("Delete");
        bremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bremoveActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bremove);
        bremove.setBounds(190, 20, 110, 30);

        bsave.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bsave.setForeground(java.awt.Color.black);
        bsave.setText("Save");
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bsave);
        bsave.setBounds(300, 20, 110, 30);

        bexit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bexit.setForeground(java.awt.Color.black);
        bexit.setText("Exit");
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bexit);
        bexit.setBounds(410, 20, 110, 30);

        badd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        badd.setForeground(java.awt.Color.black);
        badd.setText("Add");
        badd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baddActionPerformed(evt);
            }
        });
        jLayeredPane1.add(badd);
        badd.setBounds(80, 20, 110, 30);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(20, 330, 590, 70);

        jLabel1.setBackground(new java.awt.Color(153, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download-2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 1200, 900);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lnameItemStateChanged
}//GEN-LAST:event_lnameItemStateChanged

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed

        bremove.setEnabled(true);
        r = lname.getSelectedIndex();
        tname.setText(name[r + 1]);
        taddress.setText(address[r + 1]);
        tphno.setText(phno[r + 1]);


}//GEN-LAST:event_lnameActionPerformed

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
        JOptionPane.showMessageDialog(new Hrmstudents(), "Data deleted Successfully");
        this.dispose();
        this.setVisible(false);


}//GEN-LAST:event_bremoveActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed

        try {
            List<List<Object>> writeData = new ArrayList<>();

            List<Object> dataRow = new ArrayList<>();
            dataRow.add(tname.getText());
            dataRow.add(taddress.getText());
            dataRow.add(tphno.getText());
            dataRow.add(tpass.getText());
            writeData.add(dataRow);

            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");

            service.spreadsheets().values()
                    .append(spid, range, vr)
                    .setValueInputOption("RAW")
                    .execute();
            lname.add(tname.getText());
            JOptionPane.showMessageDialog(new Hrmstudents(), "DATA SAVED SUCCESSFULLY");
        } catch (Exception ex) {
            Logger.getLogger(Hrmstudents.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_bsaveActionPerformed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bexitActionPerformed

        this.setVisible(false);

}//GEN-LAST:event_bexitActionPerformed

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baddActionPerformed

        tname.setText("");
        taddress.setText("");
        tphno.setText("");
        tpass.setText("");

        tname.requestFocus();
        bremove.setEnabled(false);
        bsave.setEnabled(true);
}//GEN-LAST:event_baddActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Hrmstudents().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton badd;
    private javax.swing.JButton bexit;
    private javax.swing.JButton bremove;
    private javax.swing.JButton bsave;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private java.awt.List lname;
    private javax.swing.JTextField taddress;
    private javax.swing.JTextField tname;
    private javax.swing.JTextField tpass;
    private javax.swing.JTextField tphno;
    // End of variables declaration//GEN-END:variables
}