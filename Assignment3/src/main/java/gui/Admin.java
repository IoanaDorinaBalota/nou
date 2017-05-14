package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.AdminOperations;
import clientServerUltim.ClientForm;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

public class Admin {

	private static JFrame frmAdmin;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JPasswordField passwordField;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static JTextField textField_9;
	private JTextField textField_10;
	private static JPasswordField passwordField_1;
	private AdminOperations admOp;
	private JTable table;
	private JPanel panelVizualizeaza;
	private JTextField textField_11;
	private String tableCnp;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.setResizable(false);
		frmAdmin.setTitle("ADMIN");
		frmAdmin.setBounds(100, 100, 958, 370);
		frmAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdmin.setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 50, 0, 50));
		menuBar.setBorderPainted(false);
		frmAdmin.setJMenuBar(menuBar);
		
		JMenuItem mntmVizualizeazaAngajat = new JMenuItem("Vizualizeaza angajat");
		menuBar.add(mntmVizualizeazaAngajat);
		
		
		JMenuItem mntmEditeazaAngajat = new JMenuItem("Editeaza angajat");
		menuBar.add(mntmEditeazaAngajat);
		
		JMenuItem mntmAdaugaAngajat = new JMenuItem("Adauga angajat");
		menuBar.add(mntmAdaugaAngajat);
		
		JMenuItem mntmLogout = new JMenuItem("Logout("+Login.username+")");
		menuBar.add(mntmLogout);
		frmAdmin.getContentPane().setLayout(null);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, -32, 940, 325);
		frmAdmin.getContentPane().add(tabbedPane);
		
		final JPanel panelAdauga = new JPanel();
		tabbedPane.addTab("New tab", null, panelAdauga, null);
		panelAdauga.setLayout(null);
		
		JLabel lblNume = new JLabel("Name");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNume.setBounds(12, 33, 133, 16);
		panelAdauga.add(lblNume);
		
		JLabel lblIdentityCardNumber = new JLabel("Identity Card Number");
		lblIdentityCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdentityCardNumber.setBounds(12, 74, 164, 16);
		panelAdauga.add(lblIdentityCardNumber);
		
		JLabel lblPNC = new JLabel("PNC");
		lblPNC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPNC.setBounds(12, 116, 133, 16);
		panelAdauga.add(lblPNC);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRole.setBounds(12, 179, 133, 16);
		panelAdauga.add(lblRole);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(12, 145, 133, 16);
		panelAdauga.add(lblAddress);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(12, 212, 133, 16);
		panelAdauga.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(12, 252, 133, 16);
		panelAdauga.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(193, 31, 226, 22);
		panelAdauga.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(193, 72, 226, 22);
		panelAdauga.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(193, 114, 226, 22);
		panelAdauga.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(193, 143, 226, 22);
		panelAdauga.add(textField_3);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"admin", "doctor", "secretary"}));
		comboBox.setBounds(193, 177, 226, 22);
		panelAdauga.add(comboBox);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(193, 210, 226, 22);
		panelAdauga.add(textField_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 250, 226, 22);
		panelAdauga.add(passwordField);
		
		JButton btnAdauga = new JButton("ADAUGA");
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//admOp.insertEmployee(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), comboBox.getSelectedItem().toString(),textField_4.getText(), passwordField.getText());
			
			//String msg[]=new String[]{"adaugaAngajat",textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),comboBox.getSelectedItem().toString(),textField_4.getText(),passwordField.getText()};
			try{
				//connect("localhost",5555);
				String msg[]=new String[]{"adaugaAngajat",textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),comboBox.getSelectedItem().toString(),textField_4.getText(),passwordField.getText()};
				ClientForm.sendMessage(msg);
				}
			catch(Exception ex)
			{
				System.out.println("Eroare trimitere mesaj adauga angajat");
				ex.printStackTrace();
			}
			}
		});
		btnAdauga.setBounds(433, 142, 97, 25);
		panelAdauga.add(btnAdauga);
		
		final JPanel panelEditeaza = new JPanel();
		tabbedPane.addTab("New tab", null, panelEditeaza, null);
		panelEditeaza.setLayout(null);
		
		JLabel lblAlegeAngajat = new JLabel("Alege angajat");
		lblAlegeAngajat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlegeAngajat.setBounds(12, 13, 151, 26);
		panelEditeaza.add(lblAlegeAngajat);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(170, 16, 229, 22);
		panelEditeaza.add(comboBox_1);
		
		JLabel lblCnp = new JLabel("PNC");
		lblCnp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCnp.setBounds(12, 52, 142, 22);
		panelEditeaza.add(lblCnp);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(12, 81, 142, 16);
		panelEditeaza.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Identity Card Number");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 118, 142, 16);
		panelEditeaza.add(lblNewLabel);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPnc.setBounds(12, 158, 142, 16);
		panelEditeaza.add(lblPnc);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress_1.setBounds(12, 187, 142, 16);
		panelEditeaza.add(lblAddress_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 237, 142, 16);
		panelEditeaza.add(lblNewLabel_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword_1.setBounds(12, 266, 142, 16);
		panelEditeaza.add(lblPassword_1);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(170, 53, 229, 22);
		panelEditeaza.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(170, 81, 229, 22);
		panelEditeaza.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(170, 116, 229, 22);
		panelEditeaza.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(170, 156, 229, 22);
		panelEditeaza.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(170, 185, 229, 22);
		panelEditeaza.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(170, 235, 229, 22);
		panelEditeaza.add(textField_10);
		textField_10.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(170, 264, 229, 22);
		panelEditeaza.add(passwordField_1);
		
		JButton btnEditeaza = new JButton("EDITEAZA");
		btnEditeaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				admOp.updateEmployee(textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText(), textField_9.getText(), textField_11.getText(), textField_10.getText(), passwordField_1.getText());
			}
		});
		btnEditeaza.setBounds(423, 136, 97, 25);
		panelEditeaza.add(btnEditeaza);
		
		JLabel lblRole_1 = new JLabel("Role");
		lblRole_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRole_1.setBounds(12, 216, 142, 16);
		panelEditeaza.add(lblRole_1);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(170, 203, 229, 22);
		panelEditeaza.add(textField_11);
		
		JPanel panelSterge = new JPanel();
		tabbedPane.addTab("New tab", null, panelSterge, null);
		panelSterge.setLayout(null);
		
		panelVizualizeaza = new JPanel();
		tabbedPane.addTab("New tab", null, panelVizualizeaza, null);
		panelVizualizeaza.setLayout(null);
		
		
		JButton btnSterge = new JButton("STERGE");
		btnSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableCnp!=null)
				{
					admOp.delete(tableCnp);
					table.getSelectionModel().clearSelection();
					tabbedPane.add(panelVizualizeaza);
					admOp.loadEmployee(table);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Selectati un angajat", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnSterge.setBounds(626, 66, 97, 25);
		panelVizualizeaza.add(btnSterge);
		
	
		
		table = new JTable();
		table.setBounds(75, 128, 225, 106);
		
		table.setVisible(true);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) 
	        {
	        	if(table.getSelectedRow()!=-1){
	        	tableCnp=table.getValueAt(table.getSelectedRow(), 2).toString();
	        	System.out.println(tableCnp);}
	        	else tableCnp=null;
	        }});
		//panelVizualizeaza.add(table);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 13, 600, 400);
		
		scrollPane.setViewportView(table);
		panelVizualizeaza.add(scrollPane);
		
		tabbedPane.setVisible(false);
		mntmVizualizeazaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.removeAll();
				tabbedPane.setVisible(true);
				admOp.loadEmployee(table);
				tabbedPane.add(panelVizualizeaza);
				
			}
		});
		
		
		mntmEditeazaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.removeAll();
				tabbedPane.setVisible(true);
			
				String []employee=admOp.loadEmployee();
				for(int i=0;i<employee.length;i++)
					comboBox_1.addItem(employee[i]);
				tabbedPane.add(panelEditeaza);
			}});
		mntmAdaugaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.removeAll();
				tabbedPane.setVisible(true);
				tabbedPane.add(panelAdauga);
			}});
		
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()!=-1)
				{
					String text=comboBox_1.getSelectedItem().toString();
					String cnp=text.substring(text.length()-13);
					System.out.println(cnp);
					String[] info=admOp.loadEmployee(cnp);
					textField_5.setText(info[2]);
					textField_6.setText(info[0]);
					textField_7.setText(info[1]);
					textField_8.setText(info[2]);
					textField_9.setText(info[3]);
					textField_11.setText(info[4]);
					textField_10.setText(info[5]);
					passwordField_1.setText(info[6]);
					//textField_11.setText(info[6]);
				}
			}});
	}
	
	
	public static void clear()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		passwordField.setText("");
	}
	
	
}
