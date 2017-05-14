package clientLogin;

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
import java.awt.event.ActionEvent;

import gui.Admin;
import gui.Admin.*;

public class Client extends Observable{

	private JFrame frame;
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
					Client window = new Client();
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
	public Client(Socket socket) {
		this.socket=socket;
		connected=false;
		//connect("localhost",5555);
		//initialize();
	}
	public Client()
	{
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
				//Socket s = new Socket("localhost", 5000);
				ObjectOutputStream p = new ObjectOutputStream(socket.getOutputStream());
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
		        socket.close();
				
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
	}
	
	
	
	
	
	
}
