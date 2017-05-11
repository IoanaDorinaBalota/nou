package clientServerLogin;

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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	
	//CLIENT
	private Socket socket;
	private ObjectInputStream br;//BufferedReader br;
	private PrintWriter pw;//ObjectOutputStream pw;//PrintWriter pw;
	private boolean connected;
	private int port=5555; //default port
	private String hostName="127.0.0.1";//"localhost";//default host name
	
	ObjectOutputStream p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		connected=false;
		initialize();
		/*connected=false;
		try{
		connect("localhost",5555);
		connected=true;
		initialize();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Serverul nu e pornit", "ERROR",JOptionPane.ERROR_MESSAGE);
		}*/
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 86, 183, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//connect("localhost",5555);
				try{
					connect("localhost",5555);
					String []msg=new String[]{"login","mere","ana"};
					sendMessage(msg);
				//sendMessage(textField.getText()+" "+textField_1.getText()+"login");
				JOptionPane.showMessageDialog(null,"Clientul a trimis mesajul", "success",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception ex)
				{
					System.out.println("Eroare trimitere mesaj login");
				}
			}
		});
		btnLogin.setBounds(90, 148, 97, 25);
		frame.getContentPane().add(btnLogin);
	}
	
	
	
	
	
	public void connect(String hostName, int port) {//throws IOException {
        if(!connected)
        {
           System.out.println("Login Connecting to " + hostName + " on port " + port);
	       this.hostName = hostName;
           this.port = port;
           try{
           socket = new Socket(hostName,port);
           System.out.println("Conecatre cu succes login");
           }
           catch(Exception ex)
           {
        	   System.out.println("Nu pot face un socket client login");
           }
        	try{
          // br = new ObjectInputStream(socket.getInputStream());//new BufferedReader(new         InputStreamReader(socket.getInputStream()));
          // pw = new PrintWriter(socket.getOutputStream(),true);//new ObjectOutputStream(socket.getOutputStream());//new PrintWriter(socket.getOutputStream(),true);
        		p = new ObjectOutputStream(socket.getOutputStream());
        		System.out.println("Conectat" );
           }
            catch(IOException e) {
            	System.out.println("Exceptie buffer citire scriere");
            	e.printStackTrace();
            }
		   connected = true;
           //initiate reading from server...
           //Thread t = new Thread(this);
          // t.start(); //will call run method of this class
           System.out.println("ajunge ");
        }
    }

    public void sendMessage(String[] msg) throws IOException
    {
		if(connected) {
	        //pw.println(msg);//writeObject(new String(msg));//println(msg);
	        //pw.flush();
			p.writeObject((String[])(msg));//new Student(name, age, mark));
	        p.flush();
	        BufferedReader response = new BufferedReader(new InputStreamReader(
	                socket.getInputStream()));
	        System.out.println("The server respond: " + response.readLine());
	        p.close();
	        response.close();
	        disconnect();
        } else throw new IOException("Login Not connected to server");
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

    /*public void run() {
	   String msg = "mesah"; //holds the msg recieved from server
         try {
            while(connected && (msg=br.readLine())!= null)
            {
			 System.out.println("Server:"+msg);
			 //notify observers//
			 this.setChanged();
              	 this.notifyObservers(msg);
            }
         }
         catch(IOException ioe) { }
         finally { connected = false; }
    }*/

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
