/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_server_class;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Java_Server_Class {
	static String hostName = "192.168.100.11";
    static int portNumber = 5003;
    static String usuario = "paco";
    static String contraseña = "con";
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MultiThreadedServer server = new MultiThreadedServer(portNumber);
		new Thread(server).start();
		
		
		 	
		while (true)
		{
		try {
		    Thread.sleep(1);
		    
		    
		    if (!(Information.listA.isEmpty()))
		    {
		    	String s = Information.listA.get(0).toString();
	            String[] parts = s.split("_");
	            String IP = parts[0];
			   // System.out.println("IP: "+IP + " Mensaje: " + parts[1] + " Usuario: " + parts[2]);
			    Information.listA.remove(0);
			    
			    
			    //Codigo Base de datos!!
			    
			    
			    
			    //*************************************Termina codigo base de datos
			    if (parts[1].equals("Login"))			    {                              
                                
                                String UinputUser = parts[2];
                                String passUser   = parts[3];
                                
                                int answer = new Iniciosesion().validarpass(UinputUser,passUser);
                                
                                
			    	if (answer  == 1)
			    	{
                                    new Iniciosesion().ip(UinputUser,IP);
                                    
			    		sendMessage(IP,"Login Accepted");
			    	}else 
			    	{sendMessage(IP,"Login dennied");}
			    	
			    }else if (parts[1].equals("getRestaurants"))
                            {                              
                                System.out.println("Entro");
                                String Lats = parts[2];
                                double Lat = Double.parseDouble(Lats);
                                String Lons = parts[3];
                                double Lon = Double.parseDouble(Lons);
                                System.out.println("Lat: " + Lat + " Lon: " + Lon);
                                new DistanceCalculator().dist(Lon,Lat);
                               // System.out.println(resInfo);
                                sendMessage(IP,"begin");
                                System.out.println(Information.resInfo.get(0));
                                
                                for (int i = 0; i < Information.resInfo.size(); i++)
                                {
               
                                    String macaco = Information.resInfo.get(i).toString();
                                    macaco= macaco.replace("[", " ");
                                    
                                     macaco = macaco.replace("]", " ");
                                     System.out.println("macaco value: "+macaco);
                                   
                                    System.out.println(macaco);
                                sendMessage(IP,macaco);
                                    
                                }
                                //System.out.println(resInfo.get(0).toString());

                                /*
                                for (String resInfo1 : resInfo)
                                {
                                
                                sendMessage(IP,resInfo1);
                                 
                              
                                }
                                */
                                  sendMessage(IP,"close");
			    	
			    }else
			    {
			    sendMessage(IP,"begin");
			    
			    //Reemplazar con while *************************************
			    
			    sendMessage(IP,"Restaurant1_192.168.100.1");
			    sendMessage(IP,"Restaurant2_192.168.100.11");
			    sendMessage(IP,"Restaurant3_192.168.100.1");
			    sendMessage(IP,"Restaurant4_192.168.100.1");
			    sendMessage(IP,"Restaurant5_192.168.100.1");
			    
			    //Termino de Reemplazar con while (Cerrar*************************************
			    sendMessage(IP,"close");
			    }
		    }
		    else{
			    
		    }
		    }
		 
		    
		
		
		
		    /*
		    try (
		            Socket echoSocket = new Socket(hostName, portNumber);
		            PrintWriter out =
		                new PrintWriter(echoSocket.getOutputStream(), true);
		            BufferedReader in =
		                new BufferedReader(
		                    new InputStreamReader(echoSocket.getInputStream()));
		            BufferedReader stdIn =
		                new BufferedReader(
		                    new InputStreamReader(System.in));

		        ) 
		        {
		        	out.println("5");
		            }
		        } catch (UnknownHostException e) {
		            System.exit(1);
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to " +
		                hostName);
		            System.exit(1);
		        }
		        
		         */
		catch (InterruptedException e) {
		    e.printStackTrace();
		}
		}
		
		//System.out.println("Stopping Server");
		//server.stop();

	}
	
	
	static public void sendMessage(String IP, String sendMessage){
		  try{
		    	Socket echoSocket = new Socket(IP, portNumber);
	            PrintWriter out =
	                new PrintWriter(echoSocket.getOutputStream(), true);
	            BufferedReader in =
	                new BufferedReader(
	                    new InputStreamReader(echoSocket.getInputStream()));
	            BufferedReader stdIn =
	                new BufferedReader(
	                    new InputStreamReader(System.in));
	            out.println(sendMessage);
		    }catch (UnknownHostException e) {
	            System.err.println("Don't know about host " + IP);
	           
	        } catch (IOException e) {                    
	            System.err.println("Couldn't get I/O for the connection to " +
	                IP);
                    
                }	    }

		}
		
		




