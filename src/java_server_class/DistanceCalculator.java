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

import java.util.*;
import java.lang.*;
import java.io.*;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import static java.util.Collections.list;

class DistanceCalculator
        
{
    
	public void dist (Double Lat, Double Long) throws IOException {
          //  int hola = 0;
                try {
            Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath  Where your DB2 Driver is located");
            e.printStackTrace();
            
        }
    //    System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        ArrayList<String> resultados;
     
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
//        
//            System.out.println("Dame tu actual Longitud:");          
//            String Longitud = br.readLine();
//            double Longd = Double.parseDouble(Longitud);    
//            
//            InputStreamReader isr1 = new InputStreamReader(System.in);
//            BufferedReader br1 = new BufferedReader(isr1);
//        
//            System.out.println("Dame tu actual Latitud:");          
//            String Latitud = br1.readLine();
//            double Latid = Double.parseDouble(Latitud);
            
            String query = "SELECT LONGITUD, LATITUD FROM TASTY.REST";
            ArrayList<String> resul = Consultaval(conn, query);
            
//            String query1 = "SELECT LATITUD FROM TASTY.REST";
//            ArrayList<String> resula = Consultaval(conn, query1);
        //   System.out.print(resul + "\n");
        Information.resInfo.clear();
           for(int i = 0; i < resul.size(); i++) {
               
               String row = resul.get(i);           
           String[] rows = row.split("_");
           
           String longe = rows[0];
           String lat = rows[1];
           double longi = Double.parseDouble(longe);
           double lati = Double.parseDouble(lat);
           
           double resultado = distance(lati, longi, Lat, Long, "K");
               if (resultado <= 5)
                {
                    
                    //System.out.println("Este restaurante está a " +resultado + " Kilometros, cerca de ti:");
                   String query1 = "SELECT NOMBRE_RESTAURANTE,IP,LONGITUD,LATITUD FROM TASTY.REST WHERE LONGITUD = '"+longi+"' AND LATITUD = '"+lati+"'";                   
                   Information.resInfo.add(Consulta(conn, query1));// = Consulta(conn, query1);
                 //   System.out.println("Hey I am ");
                    System.out.println(Information.resInfo); 
                  
//  return resultados;
//                    //System.out.println("El restaurante: " +resultados + ",está cerca de ti\n");                    
//                    System.out.println(resultados);  
                    
                    
                }
                else
                {
                    //System.out.println("no entro nunca");
                    //hola = 0;
                  //  System.out.println("Este restaurante está a fuera de rango\n");
                } 
           
           
//           System.out.print(rows[0] + "\n");
//           System.out.print(rows[1] + "\n");
           }
           
         //  System.out.print(resul + "\n");
            
          //  System.out.println(resul.get(1));
          //  System.out.println(resul.get(2));
            
            
//            for(int i = 0; i < resul.size(); i++) {
//              //  for(int k = 0; k < resula.size(); k++){
//                    
//               String longe = resul.get(1);
//               String lat = resul.get(2);
//               double longi = Double.parseDouble(longe);
//               double lati = Double.parseDouble(lat);
//               
//               System.out.print(longi + "\n");
//               System.out.print(lati + "\n");               
//               
////               String lat = resula.get(i);
////               double lati = Double.parseDouble(lat);
//               
//           //    System.out.print(lati + "\n");
//               
////               double resultado = distance(lati, longi, Latid, Longd, "K");
////               if (resultado <= 5)
////                {
////                    System.out.println("Este restaurante está a " +resultado + " Kilometros, cerca de ti\n");
////                }
////                else
////                {
////                    System.out.println("Este restaurante está a fuera de rango\n");
////                }               
//                //System.out.print(longi + "\n");
//            }
            
            
            
	//	System.out.println(distance(20.65256, -103.43958, 20.63981, -103.43133, "M") + " Miles\n");
//                double resultado = distance(20.65256, -103.43958, 20.62964, -103.40588, "K");
//                if (resultado <= 5)
//                {
//                    System.out.println("Este restaurante está a " +resultado + " Kilometros, cerca de ti\n");
//                }
//                else
//                {
//                    System.out.println("Este restaurante está a fuera de rango\n");
//                }
//                
                } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
            
        } 
		//System.out.println(distance(20.65256, -103.43958, 20.63981, -103.43133, "K") + " Kilometers\n");
	//return resultados;	//System.out.println(distance(20.65256, -103.43958, 20.63981, -103.43133, "N") + " Nautical Miles\n");
	}
//        public static String Consultaval  (Connection c, String q) throws SQLException{
//       
//        PreparedStatement pstmt = null;
//        ResultSet rset=null;
//        boolean found=false;
//        String season = null;
//       ArrayList<String> rowData = new ArrayList<String>();
////       String listas = null;
////       int count = 0;
////       ResultSetMetaData md = rset.getMetaData();
////            int count = md.getColumnCount();
//        //    List rowData = null;
//          //  List<List<String>> tableData;
//         //   tableData = new ArrayList<List<String>>();
//        
//        pstmt=c.prepareStatement(q);
//            rset=pstmt.executeQuery();
//         
//            if(rset!=null)
//            {
//                   
//                while(rset.next())
//                
//                    found=true;
//                season = rset.getString(1); 
//               // int idVal = rset.getInt ("id");
////                String send = rset.getString(1);
////                rowData.add(send);
//                  //  ArrayList<String> list = new ArrayList<String>();
//                    //ArrayList<String> result = new ArrayList<String>();
//                    //ClickInfo clickInfo = new ClickInfo();
//                 //   list.add(send);                 
////                for (int i = 1; i <= count; i++) {
////                    rowData.add(rset.getString(i));
////                }
//                //tableData.add(rowData);
//                  //  return list;
//                  
//                }
//            
//            if (found ==false)
//            {
//                System.out.println("No Information Found");
//            }
//                 return season;      
//           // return list;
//    }
         public static ArrayList<String> Consultaval  (Connection c, String q) throws SQLException{
       
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        boolean found=false;
        //String season = null;
        ArrayList<String> season = new ArrayList<String>();
        
        pstmt=c.prepareStatement(q);
            rset=pstmt.executeQuery();
         
            if(rset!=null)
            {
                   
                while(rset.next())
                {
                    found=true;
                    String longitud = rset.getString(1);
                    String latitud = rset.getString(2);
                    season.add(longitud + "_" + latitud);
                }
            }
            if (found ==false)
            {
                System.out.println("No Information Found");
            }
                       
            return season;
    }
          public static ArrayList<String> Consulta  (Connection c, String q) throws SQLException{
       
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        boolean found=false;
        ArrayList<String> allre = new ArrayList<String>();
        
        pstmt=c.prepareStatement(q);
            rset=pstmt.executeQuery();
         
            if(rset!=null)
            {
                   
                while(rset.next())
                {
                    found=true;
                    String NombRest = rset.getString(1);
                    String ip = rset.getString(2);
                    String longitud = rset.getString(3);
                    String latitud = rset.getString(4);
                    allre.add(NombRest + "_" + ip + "_" + longitud + "_" + latitud);
                }
            }
            if (found ==false)
            {
                System.out.println("No Information Found");
            }
                       
            return allre;
    }

	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
