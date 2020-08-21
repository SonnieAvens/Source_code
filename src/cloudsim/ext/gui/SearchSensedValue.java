
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;

public class SearchSensedValue extends javax.swing.JFrame {
 public static String un="";
 public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
     String Res,fileName ;
     String FogMngr, Master ;
     public String filz;
      private static List<Cloudlet> cloudletList;
             private static List<Vm> vmlist;
    /**
     * Creates new form Uploadfile
     */
    public SearchSensedValue(String uname) {
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IoT_User Side Process", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Enter Text to search");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton3.setText("Allocate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            
                            SearchData();
                GuiMain gm = new GuiMain();
                gm.setVisible(true);
           
            //this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(SearchSensedValue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
   
    
    private void SearchData()
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
            
             long BEGIN = System.currentTimeMillis();
              
   System.out.println("Start scheduling ...");
   try {

       int num_user=1;
    Calendar calendar = Calendar.getInstance();
    boolean trace_flag = false; 
    CloudSim.init(num_user, calendar, trace_flag);
    Datacenter datacenter0 = AHP.createDatacenter("Datacenter_0");
    DatacenterBroker broker = AHP.createBroker();
    int brokerId = broker.getId();
    vmlist = new ArrayList<Vm>();
    int vmid = 0;
    int mips = 1000;
    long size = 10000; 
    int ram = 512; 
    long bw = 1000;
    int pesNumber = 1; 
    String vmm = "Xen"; 
    Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
    vmlist.add(vm);
    broker.submitVmList(vmlist); 
    cloudletList = new ArrayList<Cloudlet>();    
    LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();    
    int id = 0;
    long length = 100000;
    long fileSize = 100;
    long outputSize = 300;
    UtilizationModel utilizationModel = new UtilizationModelFull();               
    Cloudlet[] cloudlet = new Cloudlet[7];
    int userId=0,cloudlets=0,idShift=0;
    for(int i=0;i<6;i++)
    {
     cloudlet[i] = new Cloudlet(  idShift + i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
     cloudlet[i].setUserId(brokerId);
     cloudlet[i].setVmId(vmid);
    }  
  A a=new A();
        int i,j;
        float[][] resource=new float[3][3];
     float[] eigen1=new float[3]; 
     float[] pv=new float[3];
     float[] f=new float[6]; 
     Scanner sc =new Scanner(System.in);
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       resource[i][j]=(float) (Math.random()*10.0);
      }
     }
     for(j=0;j<3;j++)
     {
      for(i=0;i<3;i++)
      {
       eigen1[j]+=resource[i][j];
      }
     }
     for(j=0;j<3;j++)
      {
       System.out.println(eigen1[j]);
      }
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       resource[j][i]=resource[j][i]/eigen1[i];
      }
     }
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       System.out.println(resource[i][j]);
       
      }
     }
     
     for(i=0;i<3;i++)
     {
      for(j=0;j<3;j++)
      {
       pv[i]+=resource[i][j];
       
      }
      pv[i]=pv[i]/3;
     }

     
     for(j=0;j<3;j++)
      {
       System.out.println(pv[j]);
      }
    
      for(j=0;j<3;j++)
       
      {
       a.jungle();
      }
     System.out.println("Priority Order");
     
     for(j=0;j<6;j++)
      {
       f[j]=0;
      } 
        for(j=0;j<6;j++)
        {
        for(int k=0;k<3;k++)
        {
         
         f[j]+=pv[k] * a.pv1[j][k];
        }
       }
   for(j=0;j<6;j++)
      {
       System.out.println(f[j]);
      }
   int[] ids={0,1,2,3,4,5};
   
   for(j=0;j<6;j++)
   {
    for(i=j+1;i<6;i++)
    {
     if (f[j]<f[i])
     {
      float temp;
      temp=f[j];
      f[j]=f[i];
      f[i]=temp;
      int t;
      t=ids[j];
      ids[j]=ids[i];
      ids[i]=t;
     }
       
    }
   }
   System.out.println("sorted priorities");
   for(i=0;i<6;i++)
   {
    
    System.out.println(f[i]+"\t"+ids[i]);
   }
   
   
    for(i=0;i<6;i++)
    {
     cloudletList.add(cloudlet[ids[i]]);
    }
           
    broker.submitCloudletList(cloudletList);
    
    
    for(i=0;i<6;i++)
    {
     broker.bindCloudletToVm(cloudlet[ids[i]].getCloudletId(),vm.getId());
    }
       
    CloudSim.startSimulation();

    CloudSim.stopSimulation();

    List<Cloudlet> newList = broker.getCloudletReceivedList();
    AHP.printCloudletList(newList);
   
   } 
    catch(Exception ex){}
                
          Scanner scan = new Scanner(System.in);
        SplayTree spt = new SplayTree(); 
   
            System.out.println("\nSplay Tree Operations\n");
           spt.insert(1);                     
           spt.insert(2);                     
           spt.insert(3);                     
           spt.insert(4);                     
           spt.insert(5);                     
           spt.insert(6);                     
           spt.insert(7);                     
           spt.insert(8);                     
           spt.insert(9);                     
           spt.insert(10);                     
           spt.insert(11);                     
           spt.insert(12);                     
           spt.insert(13);                     
           spt.insert(14);                     
           spt.insert(15);                     
           spt.insert(16);                     
           spt.insert(17);                     
           spt.insert(8);                     
           spt.insert(19);                     
           spt.insert(20);                     
           spt.insert(21);                     
           spt.insert(22);                     
           spt.insert(23);                     
           spt.insert(24);                     
           spt.insert(25);                     
           spt.insert(26);                     
           spt.insert(27);                     
           spt.insert(28);                     
           spt.insert(29);                     
           spt.insert(30);                                          
                System.out.println("Search result : "+ spt.search( 15));
              System.out.println("Nodes = "+ spt.countNodes());
              System.out.print("\nPost order : ");              
            spt.postorder();
            System.out.print("\nPre order : ");
            spt.preorder();
            System.out.print("\nIn order : ");
            spt.inorder();     
         try{            
            BufferedReader br=new BufferedReader(new FileReader("ontology.txt"));            
            String line="";            
            while((line=br.readLine())!=null)
            {
                String data=line;                
                if(data.contains( jTextField1.getText()))
                {
                System.out.println("Search Result is :- "+data);
                }
            }
            br.close();             
            }catch(Exception ex){}         
            JOptionPane.showMessageDialog(null,"The received tasks from the IoT users are entered into queue1 and scheduled by using Policy-based AHP scheduling. ");
            System.out.println("The received tasks from the IoT users are entered into queue1 and scheduled by using Policy-based AHP scheduling. ");
            System.out.println("The fog is composed of Class-based Splay tree algorithm, using which the requested service will be in search. ");
            System.out.println(". ");
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
            java.util.logging.Logger.getLogger(SearchSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchSensedValue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchSensedValue("Task_Manager").setVisible(true);
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
