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
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class ClientForm extends Observable implements Runnable {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	static int id=0;
	
  
    private Socket socket;
    private BufferedReader br;
    private static ObjectOutputStream pw;
    private static boolean connected;//static boolean connected;
    private int port=5555; //default port
    private String hostName="localhost";//default host name
    private String oldInfo[];
    private JTextField textField_2;
	public static void main(String[] args) {
		ClientForm window = new ClientForm();
		window.frame.setVisible(true);
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm window = new ClientForm();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}


	public ClientForm()
	{
		System.out.println("Intra in constructorul client form");
		connected=false;
		id++;
		oldInfo=new String[]{"unu","doi"};
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 408, 240);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(12, 13, 160, 16);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(12, 55, 160, 16);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setBounds(117, 13, 183, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115, 52, 183, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(77, 127, 97, 25);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		tabbedPane.setEnabledAt(1, false);
		panel_1.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(38, 29, 116, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(64, 87, 97, 25);
		panel_1.add(btnNewButton);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//connect("localhost",5555);
					try{
						connect("localhost",5555);
						} 
						catch(Exception ex)
						{
							System.out.println("Eroare conectare cllient form");
						}
				String msg[]=new String[]{"login",textField.getText(),textField_1.getText()};
				//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				if(msg[1].equals(oldInfo[0])&&msg[2].equals(oldInfo[1]))
					JOptionPane.showMessageDialog(null,"EROARE logare: deja sunteti logat", "error",JOptionPane.ERROR_MESSAGE);
				else
				{		sendMessage(msg);
						oldInfo[0]=msg[1];
						oldInfo[1]=msg[2];
				
				}
				
				}

				catch(Exception ex)
				{
					System.out.println("Eroare trimitere mesaj login");
					ex.printStackTrace();
				}
			}
		});
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
	
	
	public void connect(String hostName, int port) {
        if(connected==false)
        {
        	
        try{
           this.hostName = hostName;
           this.port = port;
           socket = new Socket(hostName,port);
           br = new BufferedReader(new         InputStreamReader(socket.getInputStream()));
           pw = new ObjectOutputStream(socket.getOutputStream());
		   connected = true;
		   System.out.println("Tocmai s-a conectat "+this.toString());
           Thread t = new Thread(this);
           t.start();
        	}
        catch (Exception ex){
        	System.out.println("A aparut o eroare la conectare");}
        }
        else
        	System.out.println("E conectat");
    }
	public static void sendMessage(String msg[])
    {
		
		if(connected==true) {
			try
			{
			System.out.println("intra send"+msg[0]);
			pw.writeObject(msg);
			//br = new BufferedReader(new         InputStreamReader(socket.getInputStream()));
			//System.out.println("nou"+br.readLine());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
        } 
		else System.out.println("Clientul nu e conectat la server");
		//else throw new IOException("Not connected to server");
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
	   System.out.println("Acest clientForm e "+this.toString()+" "+String.valueOf(connected));
         try {
            while(connected && (msg = br.readLine())!= null)
            {
			 System.out.println("Server:"+msg);
			 if(msg.equals("0"))
				 {Admin adm=new Admin();}

			 	 this.setChanged();
              	 this.notifyObservers(msg);
            }
         }
         catch(IOException ioe) { }
         //finally { connected = false; }
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
