package clientServerLogin;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Observable;

public class ClientThread extends Observable implements Runnable {
    /** For reading input from socket */
    private ObjectInputStream br;//BufferedReader br;
    ObjectInputStream b;
    /** For writing output to socket. */
    private ObjectOutputStream pw;//PrintWriter pw;

    /** Socket object representing client connection */

    private Socket socket;
    private boolean running;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        running = false;
        running=true;
        //get I/O from socket
        //try {
        	//b = new ObjectInputStream(socket.getInputStream());
        	//br = new ObjectInputStream(socket.getInputStream());
            /*br = new BufferedReader(
                     new InputStreamReader(
                                           socket.getInputStream()
                                          )
                                   );*/
        	//pw = new ObjectOutputStream(socket.getOutputStream());
            //pw = new PrintWriter(socket.getOutputStream(), true);
            //running = true; //set status
        //}
        /*catch (IOException ioe) {
            throw ioe;
        }*/
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
        String msg = ""; //will hold message sent from client

	//sent out initial welcome message etc. if required...
	try {
       // pw.println("Welcome to Java based Server");
		//pw.writeObject(new String("Welcome to Java based Server"));
		 b = new ObjectInputStream(socket.getInputStream());
		 String []received = (String[]) b.readObject();
		 PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
         /* output.println("Student " + received.getName() + " with age: "
                  + received.getAge() + " has been received");*/
          output.println(received[0]);
          b.close();
          output.close();
          socket.close();
      }
	catch(Exception e){}
	finally{};
     // catch(IOException ioe) {/*System.out.println("eROARE");*/ }
		

	 
        //notify the observers for cleanup etc.
        this.setChanged();              //inherit from Observable
        this.notifyObservers(this);     //inherit from Observable
    }
}