/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_server_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class WorkerRunnable implements Runnable{

	    protected Socket clientSocket = null;
	    protected String serverText   = null;

	    public WorkerRunnable(Socket clientSocket, String serverText) {
	        this.clientSocket = clientSocket;
	        this.serverText   = serverText;
	    }

	    @SuppressWarnings("unchecked")
		public void run() {
	        try {
	        	//Get IP
	        	String IPAddress = clientSocket.getInetAddress().toString();
	        	IPAddress=IPAddress.replaceAll("/", "");
	            InputStream input  = clientSocket.getInputStream();
	            OutputStream output = clientSocket.getOutputStream();
	            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	  
	            String inputLine;
	            inputLine = in.readLine();
	            input.toString();
	            output.close();
	            input.close();
	            
	            Information.listA.add(IPAddress+"_"+inputLine);
	            System.out.println(inputLine);
	            
	        } catch (IOException e) {
	            //report exception somewhere.
	            e.printStackTrace();
	        }
	    }}