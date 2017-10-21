package p2p;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Myserver extends Thread {
	ServerSocket ss; 
	Socket c1;
public void run(){
try {
	 ss =new ServerSocket(5555);
	 c1=new Socket();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	try {
		c1=ss.accept();
	
	
	DataInputStream din=new DataInputStream(c1.getInputStream());  
    DataOutputStream dout=new DataOutputStream(c1.getOutputStream());  
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
      
    String str="",str2="";  
    while(!str.equals("stop")){  
   
		str=din.readUTF();
	  
    System.out.println("client1 says: "+str);  
    str2=br.readLine();  
    dout.writeUTF(str2);  
    dout.flush();  
    }  
    din.close();  
    c1.close();  
    ss.close(); 
	
}
	catch(Exception e1)
	{
		
	}
	
}
}

	

