
package cloudsim.ext.gui;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class DB_Form1 {
  public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
   public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/taskscheduling";
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "");
        return con;
    }
  
   public static int addAcount(String name, String email, String umode, String username, String pwd,int key) throws ClassNotFoundException, SQLException {
        Connection con1 = getConnection();
     
        stmt = con1.createStatement();
        String sql = "INSERT INTO `fogdeduplication`.`user` ( `name`,`email`,`usermode`,`uname`, `pwd`,`key`) VALUES ( '"+name+"', '"+email+"','"+umode+"','"+username+"', '"+pwd+"','"+key+"');";
        System.out.println(sql);
      int res=  stmt.executeUpdate(sql);
      return res;
    }
    public static int addservice(String user,int key, String task, String fognode, String filesize, String encfile,int rand,String sign,String filePath ) throws ClassNotFoundException, SQLException {
        Connection con1 = getConnection();
     
        stmt = con1.createStatement();
        String sql = "INSERT INTO `fogdeduplication`.`task` ( `user`,`userkey`, `task`,`fog_node`,`filesize`,`encrypfile`,`pesudo_random`,`signature`,`filestatus`) VALUES ( '"+user+"','"+key+"', '"+task+"','"+fognode+"','"+filesize+"','"+ encfile+"','"+ rand+"','"+sign+"','"+filePath+"');";
        System.out.println(sql);
      int res=  stmt.executeUpdate(sql);
      return res;
    }
  public static String addservice(String encfile, String sgn)throws ClassNotFoundException, SQLException
{   
            String chk="";                             
             Connection con1 = getConnection();
             stmt = con1.createStatement();
             String sql = "SELECT `signature` FROM `task` WHERE `encrypfile`='" + encfile +"'";
             rs = stmt.executeQuery(sql);                        
             if (rs.next()) {                     
                 chk = rs.getString("signature");                    
             }          
            else
             {
                 chk=sgn;
             }
             con1.close();
             return chk; 
}
  public static int verfiyrand(String encfile, int rand)throws ClassNotFoundException, SQLException
{   
            int chk;                  
             Connection con1 = getConnection();
             stmt = con1.createStatement();
             String sql = "SELECT `pesudo_random` FROM `task` WHERE `encrypfile`='" + encfile +"'";
             rs = stmt.executeQuery(sql);                        
             if (rs.next()) {      
                     
                 chk = rs.getInt("pesudo_random");                    
             }          
            else
             {
                 chk=rand;
             }
             con1.close();
             return chk; 
}
  public static int addCount() throws ClassNotFoundException, SQLException {
        Connection con1 = getConnection();
     int count=0;
        stmt = con1.createStatement();
        String sql = "SELECT  count(uname) FROM user";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next())  
             count=rs.getInt(1);
        return count ;
    }
 public static boolean VerifycloudFileDuplication(String user,int ukey) throws SQLException, ClassNotFoundException {
        boolean res = false;
        int chk = 0;
        Connection con1 = getConnection();
        stmt = con1.createStatement();
        String sql = "SELECT * FROM `user` WHERE `uname`='"+user+"' and `key`='"+ukey+"'";
               rs = stmt.executeQuery(sql);              
        while (rs.next()) {
           chk=1;            
            }    
         if (chk == 1) {
            res = true;
        } else {
            res = false;
        }
        con1.close();
        return res;
    }
public static boolean VerifyfogDeDuplication(int rand,String sgn) throws SQLException, ClassNotFoundException {
        boolean res = false;
        int chk = 0;
        Connection con1 = getConnection();
        stmt = con1.createStatement();
        String sql = "SELECT * FROM `task` WHERE `pesudo_random`='"+rand+"' or `signature`='"+sgn+"'";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);      
        
        while (rs.next()) {
           chk=1;            
            }    
         if (chk == 1) {
            res = true;
        } else {
            res = false;
        }
        con1.close();
        return res;
    }
public static boolean verfiytheFileDeDuplication(String enc)
{  boolean res = false;
         try {
           
             int chk = 0;
             Connection con1 = getConnection();
             stmt = con1.createStatement();
             String sql = "SELECT * FROM `task` WHERE `encrypfile`='" + enc +"'";
             System.out.println(sql);
             rs = stmt.executeQuery(sql);
                        
             while (rs.next()) {
                 chk=1;                 
             }        
             
             if (chk == 1) {
                 res = true;
             } else {
                 res = false;
             }
             con1.close();
           
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(DB_Form1.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(DB_Form1.class.getName()).log(Level.SEVERE, null, ex);
         }
           return res;
    
}
       public static boolean VerifyLogin(String uname,String pwd) throws SQLException, ClassNotFoundException, IOException {
        boolean res = false;
        int chk = 0;
        Connection con1 = getConnection();
        stmt = con1.createStatement();
        String sql = "SELECT * FROM `registeration` WHERE `Username`='"+uname+"' and `Password`='"+pwd+"' ";
       // System.out.println(sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
              chk=1;
              String Res = rs.getString("FogNode");
              String FILENAMES = "H:\\Scheduling_Heterogeneous_IoT_Tasks\\ViewFog.txt";                       
                       BufferedWriter ba = null;
                       FileWriter fa = null;
                       fa = new FileWriter(FILENAMES);
                       ba = new BufferedWriter(fa);
                       ba.write(""+Res+"\n");
                       ba.close();
                       fa.close(); 
            //JOptionPane.showMessageDialog(null,"FogNode:"+ Res);
        }
        if (chk == 1) {
            res = true;
            
        } else {
            res = false;
        }
        con1.close();
        return res;
    }
}
