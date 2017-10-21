package p2p;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Myclient extends Thread {
public 	void run(){
	try{
	Socket s=new Socket("localhost",5555);  
    DataInputStream din=new DataInputStream(s.getInputStream());  
    DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
      
    String str="",str2="";  
    while(!str.equals("stop")){  
    str=br.readLine();  
    dout.writeUTF(str);  
    dout.flush();  
    str2=din.readUTF();  
    System.out.println("Client2 says: "+str2);  
    }  
      
    dout.close();  
    s.close();  
}
	catch(Exception e2)
	{
		
	}
}
}