package clientServerLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import business.AdminOperations;
import clientServer.Server;
import databaseOperations.MysqlConnect;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerNew implements Observer {

	private ServerSocket socket;
    private int port;
    private boolean listening; //status for listening
    
	private AdminOperations admOp;
	
    public ServerNew(){

        this.port = 5555; //default port
        this.listening = false;
    }
    
    public void startServer() {
        if (!listening) {
        	System.out.println("Serverul s-a conectat");
        	try{
        		//(new ServerSocket(port)).close();
        	socket = new ServerSocket(port);
        	
        	MysqlConnect m=new MysqlConnect();
        	m.connect();
        	admOp=new AdminOperations();
        	
        	this.listening = true;
        	System.out.println("Serverul  asteapta clienti");
        	}
        	catch(Exception ex)
        	{
        		System.out.println("Nu se poate crea server-ul");
        	}      
        }
    }
    
    public void runServer()
    {
    	startServer();
    	while(listening==true)
    	{
    		try{
    		Socket client = socket.accept();// wait for client to connect
    		
    		
    		ObjectInputStream b = new ObjectInputStream(client.getInputStream());
    		String[] received = (String[]) b.readObject();//(String[]) b.readObject();
    		
    		//face operatii in functie de stringul primit
    		int rezultat=admOp.verifyString(received);
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            //output.println(received[0]+" "+received[1]+" "+received[2]);//[0]+" "+received[1]+received[2]);
            output.println(String.valueOf(rezultat));
            
            b.close();
            output.close();
            client.close();
    		}
    		catch(Exception ex)
    		{
    			System.out.println("Eroare acceptare clienti");
    		
    		}
    	}
    }
    public void update(Observable observable, Object object) {
        //notified by observables, do cleanup here//
        //this.clients.removeElement(observable);
    	System.out.println("Update server"+observable.getClass().getName().toString());
    }
    
    /*public void stopServer() {
        try {
            if((socket.isClosed())==false)
            	socket.close();
        }
        catch (IOException ioe) {
            //unable to close ServerSocket
        }
        
      listening=false;
    }*/
    
    public static void main(String args[])
    {
    	ServerNew server=new ServerNew();
    	server.runServer();
    }
}
