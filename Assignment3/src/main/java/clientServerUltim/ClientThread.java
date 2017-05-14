package clientServerUltim;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Observable;

import business.AdminOperations;
import gui.Admin;

public class ClientThread extends Observable implements Runnable {
    
	private BufferedReader br;
    ObjectInputStream b;
    private PrintWriter pw;
    private Socket socket;
    private boolean running;
    
    public static int id=0;
    AdminOperations admOp;
    
    public ClientThread(Socket socket)
    {
    	id++;
        this.socket = socket;
        admOp=new AdminOperations();
        running = false;

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
           // throw ioe;
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

    public void run() 
    {
        String msg [];
        System.out.println("Acest clientThread e "+this.toString()+" "+String.valueOf(running));
        try {
	        pw.println("Welcome to Java based Server");
        	}
	    catch(Exception ioe) { }
        try 
        {
        	while(running)
        	{System.out.println("Acest clientThread e "+this.toString()+" "+String.valueOf(running));
        		try
        		{   msg=(String[])b.readObject();
        			int rez=admOp.verifyString(msg);
        			/*if(rez==0)
        				{Admin adm=new Admin();}*/
        		 	pw.println(String.valueOf(rez));
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
               // running = false;
        }
   
        //notify the observers for cleanup etc.
        this.setChanged();              
        this.notifyObservers(this);     
    }
}
