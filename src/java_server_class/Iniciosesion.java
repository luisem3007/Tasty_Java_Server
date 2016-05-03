/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_server_class;

/**
 *
 * @author Administrator
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import static tastyserver.Registro.Consulta;


public class Iniciosesion {
    
    public static boolean Consulta (Connection c, String q) throws SQLException{
       
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        boolean found=false;
        
        pstmt=c.prepareStatement(q);
            rset=pstmt.executeQuery();
         
            if(rset!=null)
            {
                   
                while(rset.next())
                {
                    found=true;
                
                }
            }
            if (found ==false)
            {
            //    System.out.println("No Information Found");
            }
            return found;
    }
    public static String Consultaval  (Connection c, String q) throws SQLException{
       
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        boolean found=false;
        String season = null;
        
        pstmt=c.prepareStatement(q);
            rset=pstmt.executeQuery();
         
            if(rset!=null)
            {
                   
                while(rset.next())
                {
                    found=true;
                    season = rset.getString(1); 
                }
            }
            if (found ==false)
            {
                System.out.println("No Information Found");
            }
                       
            return season;
    }
    
     public static boolean Actualizar (Connection c, String q) throws SQLException{
       
        Statement stmt;
        Connection con;
        PreparedStatement pstmt = null;
     
        stmt = c.createStatement();      
        boolean found =  stmt.execute(q);
   
            return found;
    }
    
     public void verificar () throws IOException {
         try {
            Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath  Where your DB2 Driver is located");
            e.printStackTrace();
            return;
        }
       // System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset=null;
     
        try {
            conn = DriverManager.getConnection("jdbc:db2://localhost:50000/TASTY","ADMINISTRATOR","EvitarePadre");
            if (conn != null)
            {
            //    System.out.println("DB2 Database Connected");
            }
            else
            {
                System.out.println("Db2 connection Failed ");
            }
           
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Bienvenido a Inicio de Sesión de Tasty");    
            System.out.println("Ingresa tu usuario:");
            String inputUser = br.readLine();
            String UinputUser = inputUser.toUpperCase();    
            String query = "SELECT USUARIO FROM TASTY.REST WHERE upper(USUARIO) = '"+UinputUser+"'";
            boolean found = Consulta(conn, query);           
            
                if (found)  
                {                    
                    new Iniciosesion().verificarpass(UinputUser);
                 }
                else  
                {
                    System.out.println("El usuario que ingresaste no existe, por favor verifica e intenta de nuevo");                    
                   // new TastyServer().inicio();
                }           
         
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
            return;
        }
        
     }
     
     public void verificarpass (String UinputUser) throws IOException {
         try {
            Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath  Where your DB2 Driver is located");
            e.printStackTrace();
            return;
        }
     //   System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset=null;
     
        try {
            conn = DriverManager.getConnection("jdbc:db2://localhost:50000/TASTY","ADMINISTRATOR","EvitarePadre");
            if (conn != null)
            {
           //     System.out.println("DB2 Database Connected");
            }
            else
            {
                System.out.println("Db2 connection Failed ");
            }
            
//            InputStreamReader isr = new InputStreamReader(System.in);
//            BufferedReader br = new BufferedReader(isr);
//            System.out.println("Bienvenido "+UinputUser+"");
//            System.out.println("Por favor, ingresa tu contraseña");
//            String passUser = br.readLine();
          //  new Iniciosesion().validarpass(UinputUser,passUser);
            
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
            return;
        }
        
     }
     public int validarpass (String UinputUser, String passUser) throws IOException {
               int answer = 0;

         try {
            Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath  Where your DB2 Driver is located");
            e.printStackTrace();
            //return;
        }
       // System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset=null;
     
        try {
            conn = DriverManager.getConnection("jdbc:db2://localhost:50000/TASTY","ADMINISTRATOR","EvitarePadre");
            if (conn != null)
            {
             //   System.out.println("DB2 Database Connected");
            }
            else
            {
                System.out.println("Db2 connection Failed ");
            }
            
//            InputStreamReader isr = new InputStreamReader(System.in);
//            BufferedReader br = new BufferedReader(isr);
//            System.out.println("Bienvenido a "+UinputUser+"");
//            System.out.println("Por favor, ingresa tu contraseña");
//            String passUser = br.readLine();
//            new Iniciosesion().validarpass();
                       
            UinputUser = UinputUser.toUpperCase();
            String query = "SELECT PASSWORD FROM TASTY.REST WHERE upper(USUARIO) = '"+UinputUser+"'";
               String resultado = Consultaval(conn, query);   
               if (resultado.equals(passUser))
               {
                 //  System.out.println("Inicio de sesión exitoso");
                 //  new Iniciosesion().ip(UinputUser);
                 answer = 1;
                  // new Loggeado().sesionin(UinputUser);
               }
               else
               {
                   
                   answer = 0;
               }
               
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
            //return answer;
        }
        return answer;
        
     }
     public void ip (String UinputUser, String IP) throws IOException {
         try {
            Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath  Where your DB2 Driver is located");
            e.printStackTrace();
            return;
        }
   //     System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset=null;
    
        try {
            conn = DriverManager.getConnection("jdbc:db2://localhost:50000/TASTY","ADMINISTRATOR","EvitarePadre");
            if (conn != null)
            {
    //            System.out.println("DB2 Database Connected");
            }
            else
            {
                System.out.println("Db2 connection Failed ");
            }
          
                        
//           System.out.println("User:" + UinputUser);               
//           System.out.println("IP:" + IP);   
           UinputUser = UinputUser.toUpperCase();
           String querys    = "UPDATE TASTY.REST SET IP = '0' WHERE IP = '"+IP+"'";
           String query     = "UPDATE TASTY.REST SET IP =  '"+IP+"' WHERE UPPER(USUARIO) = '"+UinputUser+"'";
           boolean found1 = Actualizar(conn, querys);
            boolean found = Actualizar(conn, query);
            
            
            if (found) {
                
              //  new Actualizar().ip(UinputUser);
            }
            else {
                //System.out.println("IP del Restaurante actualizado exitosamente");               
             //   new Loggeado().sesionin(UinputUser);                
            }
            
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
            return;
        }     
         
     }
     
}
