package h.r.m;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Hrmlogin extends javax.swing.JFrame {

    public Hrmlogin() {
        initComponents();
        this.setSize(1042, 768);//frame size is defined
        this.setTitle("HUMAN RESOURCE MANAGEMENT");//frame title is defined
        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();//specifies dimensions of object
        setLocation(sd.width / 2 - this.getWidth() / 2, sd.height / 2 - this.getHeight() / 2);//sets the location of container manually
        //tpass.setEchoChar('*');//the password entered in the textfield will not be visible using this method
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tname = new javax.swing.JTextField();
        tpass = new javax.swing.JPasswordField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        bexit = new javax.swing.JButton();
        bok = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
		//all the variables are initalized above
		
		
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLayeredPane1.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		
		jLabel2.setFont(new java.awt.Font("Arial", 1, 18));//Label for which will acknowledge user for entering the password
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));//The label color will be changed by this method
        jLabel2.setText("Password :-");//Text of the label
        jLayeredPane1.add(jLabel2);//adding the label in the LayeredPane1
        jLabel2.setBounds(20, 60, 120, 40);//the setBounds method defines the actual size of the label in the LayeredPane1

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // Label for which will acknowledge user for entering the username
        jLabel3.setForeground(new java.awt.Color(255,255,255));//the label color will be changed by this method
        jLabel3.setText("User Name :-");//Text of the label
        jLayeredPane1.add(jLabel3);//adding the label in the LayeredPane1
        jLabel3.setBounds(10, 20, 130, 40);//the setBounds method defines the actual size of the label in the LayeredPane1

        tname.setFont(new java.awt.Font("Arial", 1, 12));//Textfield in which the user i.e the admin will enter the username
        tname.setText("H-R-M");//The default text for this textfield is H-R-M
        tname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnameActionPerformed(evt);
            }
        });//Adding ActionListener to this textfield
        jLayeredPane1.add(tname);//adding the textfield which will accept username to the LayeredPane1
        tname.setBounds(140, 30, 210, 30);//Setting the size of the textfield
        
		jLayeredPane1.add(tpass);//Textfield for password is added into Layered Pane
        tpass.setBounds(140, 70, 180, 30);//Size of textfield for entering password is defined

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(330, 270, 390, 126);//LayeredPane1 size defined

        jLayeredPane2.setBackground(new java.awt.Color(255, 0, 0));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bexit.setBackground(new java.awt.Color(255, 255, 255));//Exit button color is defined
        bexit.setFont(new java.awt.Font("Arial", 1, 14)); // Font is selected which will be applied to text inside the button
        bexit.setText("Exit");//The button will have string text Exit
        bexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexitActionPerformed(evt);
            }
        });//ActionListener added or decloared to the exit button
        jLayeredPane2.add(bexit);//exit button is added to layered Pane2
        bexit.setBounds(210, 30, 100, 30);//exit button size is defined 

        bok.setBackground(new java.awt.Color(255, 255, 255));//ok button color is set
        bok.setFont(new java.awt.Font("Arial", 1, 14)); // Font is selected which will be applied to text inside the button
        bok.setText("OK");//The button will have string text OK
        bok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bokActionPerformed(evt);
            }
        });//ActionListener is added or delcared for ok button
        jLayeredPane2.add(bok);//ok button added to layeredPane2
        bok.setBounds(100, 30, 100, 30);//Ok button size is defined

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(330, 410, 390, 80);//LayeredPane2 size defined

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download-2.png")));//Image is set inside jLabel1 which has same size as of framesize
        jLabel1.setText("jLabel1");//The text of label is set
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 0, 1150, 790);//the size of label is set which is same as frame size so that image can be the background of the frame

        pack();
    }//initComponents

    private void bexitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
}//bexitActionPerformed i.e if exit button is toggled the project will end

    private void bokActionPerformed(java.awt.event.ActionEvent evt) {
        if (tpass.getText().equals("1234")) {
            Hrmmenu menu = new Hrmmenu();//if password will be correct then menu will appear
            menu.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(new Hrmlogin(), "Authentication Denied! Please enter valid credentials");//authentication will be failed if password will be incorrect
            tpass.setText("");
            tpass.requestFocus();
        }//else block will show a dialog box to the user
}//This block will provide authentication facility for admin/HOD for the application

    private void tnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnameActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Hrmlogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify without concerning developers
    private javax.swing.JButton bexit;//Exit Button
    private javax.swing.JButton bok;//Ok button
    private javax.swing.JLabel jLabel1;//used for background image icon purpose
    private javax.swing.JLabel jLabel2;//used to acknowledge user to enter password in corresponding textfield
    private javax.swing.JLabel jLabel3;//used to acknowledge user to enter username in corresponding textfiel
    private javax.swing.JLayeredPane jLayeredPane1;//textfields and label will be in this layeredpane
    private javax.swing.JLayeredPane jLayeredPane2;//buttons will be in this layeredpane
    private javax.swing.JTextField tname;//user will enter username in this textfield
    private javax.swing.JPasswordField tpass;//user will enter password in this textfield
    // End of variables declaration
}