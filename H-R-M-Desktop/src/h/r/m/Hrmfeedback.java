package h.r.m;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Hrmfeedback extends javax.swing.JFrame {

    DefaultTableModel model;
    Sheets service = null;
    String spid = "";
    String range = "";
    int count = 0;
    String[] name = new String[1000];
    String[] date = new String[1000];
    String[] details = new String[1000];

    public Hrmfeedback() {
        initComponents();

        this.setSize(1042, 768);
        this.setTitle("HUMAN RESOURCE MANAGEMENT");
        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);

        model = new DefaultTableModel();
        model.addColumn("Teacher");
        model.addColumn("Date");
        model.addColumn("Details");
        table.setModel(model);
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

            spid = "1NSPR9ZvCMcSHr3J5sRYmXhxf5tRdany87cs_cLP3M9A";
            range = "A:C";

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
                        name[i] = row.get(0).toString();
                        date[i] = row.get(1).toString();
                        details[i] = row.get(2).toString();
                    }
                    i = i + 1;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Hrmfeedback.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Hrmfeedback.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        bfetch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
		bexit = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("View Feedback info");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 30, 880, 40);

        jLayeredPane2.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        table.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        table.setForeground(new java.awt.Color(255, 0, 102));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table);

		
		bexit.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        bexit.setText("Fetch Teacher Feedback Info");
        bexit.setText("Exit");//The button will have string text Exit
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });
		
		jLayeredPane2.add(bexit);
        bexit.setBounds(440, 30,420, 29);
		
        jLayeredPane2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 870, 320);

        bfetch.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        bfetch.setText("Fetch Teacher Feedback Info");
        bfetch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfetchActionPerformed(evt);
            }
        });
        jLayeredPane2.add(bfetch);
        bfetch.setBounds(10, 30, 420, 29);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(20, 110, 890, 420);

        jLabel1.setBackground(new java.awt.Color(153, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download-2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 1042, 768);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void bexitActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);;
	}
    private void bfetchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfetchActionPerformed

        try {
            connect();
            for (int i1 = 1; i1 < count; i1++) {
                String[] request = {name[i1], date[i1], details[i1]};
                model.addRow(request);
            }
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_bfetchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hrmfeedback().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bfetch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable table;
	private javax.swing.JButton bexit;
    // End of variables declaration//GEN-END:variables

}
