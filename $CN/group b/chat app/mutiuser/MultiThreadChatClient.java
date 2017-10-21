import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class MultiThreadChatClient implements Runnable{
	static Socket clientSocket = null;
    static PrintWriter os = null;
    static BufferedReader is = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;
    
    public static void main(String[] args) {
	
	// The default port	
	
	int port_number=7686;
        String host="localhost";
	
	if (args.length < 2)
	    {
		System.out.println("Usage: java MultiThreadChatClient  \n"+
				   "Now using host="+host+", port_number="+port_number);
	    }
	    else {
		host=args[0];
		port_number=Integer.valueOf(args[1]).intValue();
	    }
	// Initialization section:
	// Try to open a socket on a given host and port
	// Try to open input and output streams
	try {
            clientSocket = new Socket(host, port_number);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintWriter(clientSocket.getOutputStream(),true);
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host "+host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "+host);
        }
	
	// If everything has been initialized then we want to write some data
	// to the socket we have opened a connection to on port port_number 
	
        if (clientSocket != null && os != null && is != null) {
            try {
		
		// Create a thread to read from the server
		
                new Thread(new MultiThreadChatClient()).start();
		
		while (!closed) {
                    os.println(inputLine.readLine()); 
                }
		
		// Clean up:
		// close the output stream
		// close the input stream
		// close the socket
		
		os.close();
		is.close();
		clientSocket.close();   
            } 
                catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

@SuppressWarnings("deprecation")
public void run() {		
	String responseLine;
	
	// Keep on reading from the socket till we receive the "Bye" from the server,
	// once we received that then we want to break.
	try{ 
	    while ((responseLine = is.readLine()) != null) {
		System.out.println(responseLine);
		if (responseLine.indexOf("*** Bye") != -1) break;
	    }
            closed=true;
	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }
}