package clientServerUltim;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import javax.swing.JTextField;

import business.AdminOperations;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import gui.Admin;
import gui.Admin.*;

public class ClientForm extends Observable implements Runnable {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
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
    //private PrintWriter pw;
    private ObjectOutputStream pw;

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
    private String hostName="localhost";//default host name

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm window = new ClientForm();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ClientForm() {
		
		connected=false;
		try{
		connect("localhost",5555);
		} 
		catch(Exception ex)
		{
			System.out.println("Eroare conectare cllient");
		}
		//connect("localhost",5555);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(27, 37, 160, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(27, 88, 160, 16);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(180, 35, 183, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 86, 183, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
				String msg[]=new String[]{"login",textField.getText(),textField_1.getText()};
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				sendMessage(msg);
				//Socket s = new Socket("localhost", 5000);
				/*ObjectOutputStream p = new ObjectOutputStream(socket.getOutputStream());
				p.writeObject(msg);
		        p.flush();

		        // Here we read the details from server
		        BufferedReader response = new BufferedReader(new InputStreamReader(
		                socket.getInputStream()));
		        String raspuns=response.readLine().toString();
		        if(raspuns.equalsIgnoreCase("0"))
		        {
		        	Admin adm=new Admin();
		        }
		        System.out.println("The server respond: " + raspuns);
		        p.close();
		        response.close();
		        socket.close();*/
				
				}
				catch(Exception ex)
				{
					System.out.println("Eroare trimitere mesaj login");
					ex.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(90, 148, 97, 25);
		frame.getContentPane().add(btnLogin);
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                disconnect();
                e.getWindow().dispose();
            }
        });
	}
	
	
	public void connect(String hostName, int port) throws IOException {
        if(!connected)
        {
	     this.hostName = hostName;
           this.port = port;
           socket = new Socket(hostName,port);
           //get I/O from socket
           br = new BufferedReader(new         InputStreamReader(socket.getInputStream()));
          // pw = new PrintWriter(socket.getOutputStream(),true);
           pw = new ObjectOutputStream(socket.getOutputStream());
		   connected = true;
           //initiate reading from server...
           Thread t = new Thread(this);
           t.start(); //will call run method of this class
        }
    }
	public void sendMessage(String []msg) throws IOException
    {
		if(connected) {
			System.out.println("intra send"+msg[0]);
	        //pw.println(msg);
			pw.writeObject(msg);
        } else throw new IOException("Not connected to server");
    }

    public void disconnect() {
		if(socket != null && connected)
        {String []mesaj=new String[]{"quit"};
        System.out.println("*"+mesaj[0]+"*");
        try{
        	sendMessage(mesaj);
        }
        catch(Exception exx)
        {
        	System.out.println("Eroare trimitere mesj quit");
        }
          try {
        	  
        	  //sendMessage(mesaj);
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
	   String msg = ""; //holds the msg recieved from server
         try {
            while(connected && (msg = br.readLine())!= null)
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

	
	
}
