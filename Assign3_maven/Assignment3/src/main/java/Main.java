import clientServerLogin.LoginClient;
import clientServerLogin.ServerNew;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("salut");
		ServerNew server=new ServerNew();
		
		server.runServer();
		//server.stopServer();
		//LoginClient client=new LoginClient();
	
		//client.connect(hostName, port);
		
	}

}
