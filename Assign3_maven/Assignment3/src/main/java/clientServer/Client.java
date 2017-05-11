package clientServer;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;


public class Client extends Observable implements Runnable {

    /**
     * Uses to connect to the server 
     */
    private Socket socket;

    /**
     * For reading input from server. 
     */
    private BufferedReader br;

    /**
     * For writing output to server. 
     */
    private PrintWriter pw;

    /**
     * Status of client. 
     */
    private boolean connected;

    /**
     * Port number of server
     */
     private int port=5555; //default port

    /**
     * Host Name or IP address in String form
     */
    private String hostName="127.0.0.1";//"localhost";//default host name

    public Client() {
		connected = false;
    }

    public void connect(String hostName, int port) {//throws IOException {
        if(!connected)
        {
        	 System.out.println("Connecting to " + hostName + " on port " + port);
	       this.hostName = hostName;
           this.port = port;
           try{
           socket = new Socket(hostName,port);
           }
           catch(Exception ex)
           {
        	   System.out.println("Nu pot face un socket client");
           }
        	try{
           br = new BufferedReader(new         InputStreamReader(socket.getInputStream()));
           pw = new PrintWriter(socket.getOutputStream(),true);
           System.out.println("Conectat" );
           }
            catch(IOException e) {
            	System.out.println("Exceptie buffer citire scriere");
            	e.printStackTrace();
            }
               
               
            
           //get I/O from socket
           //br = new BufferedReader(new         InputStreamReader(socket.getInputStream()));
           //pw = new PrintWriter(socket.getOutputStream(),true);

		   connected = true;
           //initiate reading from server...
           Thread t = new Thread(this);
           t.start(); //will call run method of this class
           System.out.println("ajunge ");
        }
    }

    public void sendMessage(String msg) throws IOException
    {
		if(connected) {
	        pw.println(msg);
        } else throw new IOException("Not connected to server");
    }

    public void disconnect() {
		if(socket != null && connected)
        {
          try {
			socket.close();
          }catch(IOException ioe) {
			//unable to close, nothing to do...
          }
          finally {
			this.connected = false;
          }
        }
    }

    public void run() {
	   String msg = "mesah"; //holds the msg recieved from server
         try {
            while(connected && (msg=br.readLine())!= null)
            {
			 System.out.println("Server:"+msg);
			 //notify observers//
			 this.setChanged();
 //notify+send out recieved msg to Observers
              	 this.notifyObservers(msg);
            }
         }
         catch(IOException ioe) { }
         finally { connected = false; }
    }

    public boolean isConnected() {
		return connected;
    }


    public int getPort(){
            return port;
        }

    public void setPort(int port){
            this.port = port;
        }

    public String getHostName(){
            return hostName;
        }

    public void setHostName(String hostName){
            this.hostName = hostName;
        }

	//testing Client//
    public static void main(String[] argv){//throws IOException {
        Client c = new Client();
        try{
        c.connect("localhost",5555);
        }
        catch(Exception e)
        {
        	System.out.println("Clientul nu se poate conecta");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        while(!msg.equalsIgnoreCase("quit"))
        {
        	try{
           msg = br.readLine();
           c.sendMessage(msg);
        	}
        	catch(Exception e)
        	{
        		System.out.println("Mesajul nu se poate trimite");
        	}
        }
        c.disconnect();
    }
}