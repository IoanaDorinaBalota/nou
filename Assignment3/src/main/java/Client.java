import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Observable;


public class Client extends Observable implements Runnable {

    private Socket socket;
    private BufferedReader br;
    private ObjectOutputStream pw;
    private boolean connected;
    private int port=5555; //default port
    private String hostName="localhost";//default host name
    private static int id=0;

    public Client() {
		connected = false;
		id++;
    }
    public Client(Socket s)
    {
    	this.socket=s;
    	connected=false;
    	id++;
    }

    public void connect(String hostName, int port)
    {
        if(!connected)
        {
        	this.hostName = hostName;
        	this.port = port;
        	try
        	{
        		socket = new Socket(hostName,port);
                br = new BufferedReader(new         InputStreamReader(socket.getInputStream()));
                pw = new ObjectOutputStream(socket.getOutputStream());
                connected = true;
        	}
        	catch(Exception e)
        	{
        		System.out.println("Eroare creare client");
        	}


           Thread t = new Thread(this);
           t.start();
        }
    }

    public void sendMessage(String msg)
    {
		if(connected) 
		{
			try{
			System.out.println("intra send");
			pw.writeObject(new String(msg));
			}
			catch(Exception e)
			{
				System.out.println("Eroare send message client");
			}
        } 
    }

    public void disconnect() 
    {
		if(socket != null && connected)
        {
          try 
          {
			socket.close();
          }
          catch(Exception ioe)
          {
			System.out.println("Nu pot deconecta clientul");
          }
          /*finally {
			this.connected = false;
          }*/
        }
    }
    public void stopClient()
    {
        try {
		this.socket.close();
        }catch(Exception ioe){ };
    }

    public void run() {
	   String msg = ""; 
         try {
            while(connected && (msg = br.readLine())!= null)
            {
			 System.out.println("Server:"+msg);
			 this.setChanged();
             this.notifyObservers(msg);
            }
         }
         catch(Exception ioe)
         {
        	 System.out.println("A aparut o eroare in client run");
         }
        // finally { connected = false; }
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
    public static void main(String[] argv)throws IOException {
        Client c = new Client();
        System.out.println("S-a conectat clientul cu id-ul" +id);
        c.connect("localhost",5555);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        while(!msg.equalsIgnoreCase("quit"))
        {
           msg = br.readLine();
           c.sendMessage(msg);
        }
        c.disconnect();
    }
}