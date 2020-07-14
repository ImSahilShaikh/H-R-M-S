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

public class Hrmteachers extends javax.swing.JFrame {

    Sheets service = null;
    String spid = "";
    String range = "";
    int count = 0;
    String[] name = new String[1000];
    String[] type = new String[1000];
    String[] address = new String[1000];
    String[] phno = new String[1000];
    int r = 0;
    Aes aes = new Aes();
    String data = "";

    public Hrmteachers() {
        initComponents();

        bremove.setEnabled(false);
        bsave.setEnabled(false);
        lname.setMultipleSelections(false);

        cbtype.add("HOD");
        cbtype.add("STAFF");
        this.setSize(1042, 768);
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

            spid = "1WAoj61dD7rk0xGA2qLm4fsfoW51s2fmmqDeudpd8Mt0";
            range = "A:E";

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
                        data = row.get(0).toString();
                        name[i] = data;
                        data = row.get(1).toString();
                        type[i] = data;
                        data = row.get(2).toString();
                        address[i] = data;
                        data = row.get(3).toString();
                        phno[i] = data;
                         */
                        lname.add(row.get(0).toString());
                        name[i] = row.get(0).toString();
                        type[i] = row.get(1).toString();
                        address[i] = row.get(2).toString();
                        phno[i] = row.get(3).toString();

                    }
                    i = i + 1;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Hrmteachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmteachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Hrmteachers.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel14 = new javax.swing.JLabel();
        cbtype = new java.awt.Choice();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        lname = new java.awt.List();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        bremove = new javax.swing.JButton();
        bsave = new javax.swing.JButton();
        bexit = new javax.swing.JButton();
        badd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
		//all variables are initialized

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);//used to close the frame 
        getContentPane().setLayout(null);//layout of the content pane is set as null

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));//layeredPane2 background is set
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // font for textfield for adding name is set
        jLayeredPane2.add(tname);//textfield is added to jLayeredPane2
        tname.setBounds(220, 10, 260, 30);//Bounds are set to the textfield

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18));// font for the label is set
        jLabel10.setForeground(java.awt.Color.white);//color of label is set
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);//alignment of label is set
        jLabel10.setText(" Name :-");//text of label is set
        jLayeredPane2.add(jLabel10);//label is added in Jlayeredpane2
        jLabel10.setBounds(20, 10, 190, 30);//the bounds for label is set

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18));// font for the label is set
        jLabel11.setForeground(java.awt.Color.white);//color of label is set
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);//alignment of label is set
        jLabel11.setText(" Address :-");//text of label is set
        jLayeredPane2.add(jLabel11);//label is added in Jlayeredpane2
        jLabel11.setBounds(0, 90, 210, 30);//the bounds for label is set

        taddress.setFont(new java.awt.Font("Times New Roman", 1, 18));//font of textfield is set
        jLayeredPane2.add(taddress);//textfield is added in Jlayeredpane2
        taddress.setBounds(220, 90, 350, 30);//the bounds for textfield is set

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18));// font for the label is set
        jLabel12.setForeground(java.awt.Color.white);//color of label is set
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);//alignment of label is set
        jLabel12.setText(" Phone No. :-");//text of label is set
        jLayeredPane2.add(jLabel12);//label is added in Jlayeredpane2
        jLabel12.setBounds(0, 130, 210, 30);//the bounds for label is set

        tphno.setFont(new java.awt.Font("Times New Roman", 1, 18));//font of textfield is set
        jLayeredPane2.add(tphno);//textfield is added in Jlayeredpane2
        tphno.setBounds(220, 130, 260, 30);//the bounds for textfield is set

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18));// font for the label is set
        jLabel13.setForeground(java.awt.Color.white);//color of label is set
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);//alignment of label is set
        jLabel13.setText(" Password :-");//text of label is set
        jLayeredPane2.add(jLabel13);//label is added in Jlayeredpane2
        jLabel13.setBounds(0, 170, 210, 30);//the bounds for label is set

        tpass.setFont(new java.awt.Font("Times New Roman", 1, 18));//font of textfield is set
        jLayeredPane2.add(tpass);//textfield is added in Jlayeredpane2
        tpass.setBounds(220, 170, 260, 30);//the bounds for textfield is set

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18));// font for the label is set
        jLabel14.setForeground(java.awt.Color.white);//color of label is set
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);//alignment of label is set
        jLabel14.setText(" Post :-");//text of label is set
        jLayeredPane2.add(jLabel14);//label is added in Jlayeredpane2
        jLabel14.setBounds(0, 50, 210, 30);//the bounds for label is set

        cbtype.setFont(new java.awt.Font("Times New Roman", 0, 14));//font of textfield is set
        jLayeredPane2.add(cbtype);//textfield is added in Jlayeredpane2
        cbtype.setBounds(220, 50, 240, 40);//the bounds for textfield is set

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(20, 110, 590, 210);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36));// font for the label is set
        jLabel6.setForeground(java.awt.Color.white);//color of label is set
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);//alignment of label is set
        jLabel6.setText("ENTER TEACHERS INFORMATION");//text of label is set
        getContentPane().add(jLabel6);//Label is added to the content pane
        jLabel6.setBounds(30, 40, 880, 40);//the bounds for label is set

        jLayeredPane3.setBackground(new java.awt.Color(255, 255, 255));
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download-2.png")));//Image is set inside jLabel1 which has same size as of framesize
        jLabel1.setText("jLabel1");//text inside label is set
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 1200, 900);

        pack();
    }

    private void lnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lnameItemStateChanged
}//GEN-LAST:event_lnameItemStateChanged

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed

        bremove.setEnabled(true);
        r = lname.getSelectedIndex();
        tname.setText(name[r + 1]);
        cbtype.select(type[r + 1]);
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
        JOptionPane.showMessageDialog(new Hrmteachers(), "Data deleted Successfully");
        this.dispose();
        this.setVisible(false);


}//GEN-LAST:event_bremoveActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed

        try {
            List<List<Object>> writeData = new ArrayList<>();

            List<Object> dataRow = new ArrayList<>();
            
            /*
            data=aes.encrypt(tname.getText());
            dataRow.add(data);
            data=aes.encrypt(cbtype.getSelectedItem());
            dataRow.add(data);
            data=aes.encrypt(taddress.getText());
            dataRow.add(data);
            data=aes.encrypt(tphno.getText());
            dataRow.add(data);
            data=aes.encrypt(tpass.getText());
            dataRow.add(data);
            */
            
            dataRow.add(tname.getText());
            dataRow.add(cbtype.getSelectedItem());
            dataRow.add(taddress.getText());
            dataRow.add(tphno.getText());
            dataRow.add(tpass.getText());
            writeData.add(dataRow);

            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");

            service.spreadsheets().values()
                    .append(spid, range, vr)
                    .setValueInputOption("RAW")
                    .execute();
            //data=aes.encrypt(tname.getText());
            //lname.add(data);
            lname.add(tname.getText());
            JOptionPane.showMessageDialog(new Hrmteachers(), "DATA SAVED SUCCESSFULLY");
        } catch (Exception ex) {
            Logger.getLogger(Hrmteachers.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_bsaveActionPerformed

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {

        this.setVisible(false);

}//after exit button is clicked the current frame will be exited i.e its visiblity will be false

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {

        tname.setText("");
        taddress.setText("");
        tphno.setText("");
        tpass.setText("");

        tname.requestFocus();
        bremove.setEnabled(false);
        bsave.setEnabled(true);
}//after clicking add button, all textfields will be initialize to null, save button will be enabled and remove button will be disabled

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Hrmteachers().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify without concerning developers
    private javax.swing.JButton badd;//Button for adding new fields in the spreadsheet
    private javax.swing.JButton bexit;//Button for exiting the teachers registration window
    private javax.swing.JButton bremove;//Button for removing new fields in the spreadsheet
    private javax.swing.JButton bsave;//Button for saving new fields in the spreadsheet
    private java.awt.Choice cbtype;//Choice for selecting the type of the Staff
    private javax.swing.JComboBox jComboBox1;//
    private javax.swing.JLabel jLabel1;//used to set an Icon inside it
    private javax.swing.JLabel jLabel10;//used the label to indicate user to add name 
    private javax.swing.JLabel jLabel11;//used the label to indicate user to add address 
    private javax.swing.JLabel jLabel12;//used the label to indicate user to add phone number 
    private javax.swing.JLabel jLabel13;//used the label to indicate user to add password 
    private javax.swing.JLabel jLabel14;//used the label to indicate user to select post
    private javax.swing.JLabel jLabel6;//Heading displaying enter teachers information
    private javax.swing.JLayeredPane jLayeredPane1;//Buttons are added in this Pane
    private javax.swing.JLayeredPane jLayeredPane2;//various labels and textfields are added in this pane
    private javax.swing.JLayeredPane jLayeredPane3;//used to add list of the registered staff in this pane
    private java.awt.List lname;//atores the list of registered staff
    private javax.swing.JTextField taddress;//textfield to add address
    private javax.swing.JTextField tname;//textfield to add name
    private javax.swing.JTextField tpass;//textfield to add passsword
    private javax.swing.JTextField tphno;//textfield to add phonenumber
    // End of variables declaration
}