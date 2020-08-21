
package cloudsim.ext.gui;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class UploadSensedValue extends javax.swing.JFrame {
 public static String un="";
 public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
     String Res,fileName ;
     String FogMngr, Master ;
     public String filz;
    
    public UploadSensedValue(String uname) {
        un=uname;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IoT_Device Side Process", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Enter The Sensed Value");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton3.setText("Allocate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            
            
                SaveData();
                GuiMain gm = new GuiMain();
                gm.setVisible(true);
           
            //this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(UploadSensedValue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    
   
    
    private void SaveData()
    { 
        Connection connect =  ConnectDB();
        try 
        {
            stmt = connect.createStatement();
            String sql1 = "SELECT * FROM `registeration` WHERE `Username`='"+un+"' ";
            rs = stmt.executeQuery(sql1);
            while (rs.next()){
                Res = rs.getString("Username");
                JOptionPane.showMessageDialog(null,"User task is allocated: "+ Res);
                
                String file = jLabel5.getText();
                fileName = file.substring(file.lastIndexOf('\\')+1, file.length());
                System.out.println("User task "+fileName+" is allocated " + Res);
                String desFile = null;
                try 
                {
                    desFile = new File(".").getCanonicalPath() + "\\Task_Allocation\\" +fileName;
                    Files.copy(Paths.get(file),Paths.get(desFile),
                    StandardCopyOption.COPY_ATTRIBUTES,StandardCopyOption.REPLACE_EXISTING); 
                } catch (IOException e1) {
                     e1.printStackTrace();
                }
            }
            rs.close();
            String sql = "INSERT INTO task_alloc " + "(UserName,FogNode,Task) " + "VALUES ('" + un + "','"+ Res +"','" + jTextField1.getText() +"') ";
            stmt.execute(sql);
            jLabel5.setText("");
             long BEGIN = System.currentTimeMillis();
 
        Qlearning obj = new Qlearning();
 
        obj.run();
        obj.printResult();
         
        long END = System.currentTimeMillis();
        System.out.println("Time: " + (END - BEGIN) / 1000.0 + " sec.");
        
         try{
            
            BufferedReader br=new BufferedReader(new FileReader("ontology.txt"));
            
            String line="";
            String Newline="";
            while((line=br.readLine())!=null)
            {
                String data=line;
                Newline=Newline+line+"\n";
                if(data.contains("</owl:someValuesFrom>"))
                {
                    String ss="<owl:someValuesFrom><owl:Class rdf:about=http:\\purl.oclc.org/NET/ssnx/qu/dim#";
                Newline=Newline+ ss + jTextField1.getText() +"\"/>\n";
                Newline=Newline+"</owl:someValuesFrom>\n";
                }
            }
            br.close();
              String FILENAME = "H:\\Scheduling_Heterogeneous_IoT_Tasks\\ontology.txt";                      
                                                    BufferedWriter bw = null;
                                                        FileWriter fw = null;
                                                            fw = new FileWriter(FILENAME);
                                                                bw = new BufferedWriter(fw);
                                                                 bw.write(Newline);
                                                                 bw.close();
            }catch(Exception ex){}
         
            JOptionPane.showMessageDialog(null,"Task Allocation and Queue Process is Completed based on the Double Q-learning algorithm");
            System.out.println("Task Allocation and Queue Process is Completed based on the Double Q-learning algorithm");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
       }
       try{
            if(stmt != null) 
            {
               stmt.close();
               connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection ConnectDB() {
        try {
            String url = "jdbc:mysql://localhost/taskscheduling";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            return con; 
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   
            return null;
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UploadSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadSensedValue("Task_Manager").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
