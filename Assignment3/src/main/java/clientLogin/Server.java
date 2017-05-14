package clientLogin;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import business.AdminOperations;
import databaseOperations.MysqlConnect;

public class Server {
	
	static ArrayList<Client> list;
	static ServerSocket server;
	AdminOperations admOp;
    MysqlConnect m;
	int port;
	
	public Server(int port)
	{
		this.port=port;
		admOp=new AdminOperations();
		m=new MysqlConnect();
    	m.connect();
    	
		try{
		server = new ServerSocket(port);
		System.out.println("Server started");
		}
		catch(Exception e)
		{
			System.out.println("Eroare creare server");
		}
	}
	
	public void addClient(Socket t)
	{
		list.add(new Client(t));
        System.out.println("server connected");
        try{
        	
        	
        ObjectInputStream b = new ObjectInputStream(t.getInputStream());
        String received[] = (String[]) b.readObject();
        int rez=admOp.verifyString(received);
        PrintWriter output = new PrintWriter(t.getOutputStream(), true);
        output.println(String.valueOf(rez));
        b.close();
        output.close();
        t.close();
        }
        catch(Exception e)
        {
        	System.out.println("Eroare adugare client nou server");
        }
	}

    public static void main(String[] argv) throws Exception
    {
        Server myServer=new Server(5000);
        while (true)
        {
            Socket t = server.accept();// wait for client to connect
            myServer.addClient(t);
        }

    }
}