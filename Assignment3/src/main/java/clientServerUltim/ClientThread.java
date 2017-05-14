package clientServerUltim;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Observable;

public class ClientThread extends Observable implements Runnable {
    /** For reading input from socket */
    private BufferedReader br;
    ObjectInputStream b;

    /** For writing output to socket. */
    private PrintWriter pw;

    /** Socket object representing client connection */

    private Socket socket;
    private boolean running;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        running = false;
        //get I/O from socket
        try {
            br = new BufferedReader(
                     new InputStreamReader(
                                           socket.getInputStream()
                                          )
                                   );
            
            pw = new PrintWriter(socket.getOutputStream(), true);
            b = new ObjectInputStream(socket.getInputStream());
            running = true; //set status
        }
        catch (IOException ioe) {
            throw ioe;
        }
    }
	
    /** 
     *Stops clients connection
     */

    public void stopClient()
    {
        try {
		this.socket.close();
        }catch(IOException ioe){ };
    }

    public void run() {
        String msg [];//= ""; //will hold message sent from client
        try {
	        pw.println("Welcome to Java based Server");
        	}
	    catch(Exception ioe) { }

	  //start listening message from client//
        try {
        	while(running)
        	{
        		try
        		{   msg=(String[])b.readObject();
        		 	pw.println(msg[0]);
        		 	System.out.println(msg[0]);
        		 	if(msg[0].equalsIgnoreCase("quit"))running=false;
        		 	
        		}
        		catch(Exception ex)
        		{
        			//System.out.println("Eroare receptare mesaj Client Thread");
        		}
        	}
        		if(running==false)
        		{
        			try {
        	            this.socket.close();
        	            System.out.println("Closing connection");
        	        } catch (IOException ioe) { }
        		}
            }
       catch (Exception ioe)
        {
                running = false;
        }
   
        //notify the observers for cleanup etc.
        this.setChanged();              
        this.notifyObservers(this);     
    }
}
