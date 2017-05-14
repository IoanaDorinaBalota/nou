import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import clientServerUltim.ClientThread;

public class Server {
	
	private ServerSocket server; 
	private int port;
	private Vector clients;
	private boolean listening;
	
	
	
	public Server(int port)
	{
		this.port=port;
		listening=true;
		clients=new Vector();
		try{
		server=new ServerSocket(port);
		System.out.println("Serverul s-a creat");
		}
		catch(Exception e)
		{
			System.out.println("Eroare creare server");
		}
	}
	
	public void startServer()
	{
		while(listening==true)
		{
			try
			{
				Socket c= server.accept();
				clients.add(new Client(c));
			}
			catch(Exception e)
			
			{
				System.out.println("Eroare acceptare clienti in server");
			}
		}
	}
	
	public void stopServer()
	{
		listening=false;
		java.util.Enumeration e = this.clients.elements();
        while(e.hasMoreElements())
        {
	    Client ct = (Client)e.nextElement();
            ct.stopClient();
        }
	}

}
