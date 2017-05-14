package business;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseOperations.EmployeeTable;
import databaseOperations.MysqlConnect;
import gui.Admin;

public class AdminOperations {
	
	private DataValidation dv;
	private EmployeeTable empl;
	
	public AdminOperations ()
	{
		dv=new DataValidation();
		empl=new EmployeeTable(MysqlConnect.connect());
	}
	public int verifyString(String []msg)
	{
		if(msg[0].equalsIgnoreCase("login"))
		{
			return login(msg[1],msg[2]);
		}
		else if(msg[0].equalsIgnoreCase("adaugaAngajat"))
		{
			return insertEmployee(msg[1],msg[2],msg[3],msg[4],msg[5],msg[6],msg[7]);
		}
		return -1;
	}
	
	public int login(String username,String pass)
	{
		String role=null;//=null;
		ResultSet rs=empl.selectOneEmployee(username, pass);
		try
		{
			while(rs.next())
			{
				role=new String(rs.getString(5));
				System.out.println(role);
			}
			
			System.out.println(role);
			if(role.equalsIgnoreCase("admin"))
			{
				JOptionPane.showMessageDialog(null,"Logare ADMIN", "succes",JOptionPane.INFORMATION_MESSAGE);
				//Admin adm=new Admin();
				return 0;
			}
			else if(role.equalsIgnoreCase("doctor"))
			{
				JOptionPane.showMessageDialog(null,"Logare DOCTOR", "succes",JOptionPane.INFORMATION_MESSAGE);
				return 1;
			}
			else if(role.equalsIgnoreCase("secretary"))
			{
				JOptionPane.showMessageDialog(null,"Logare SECRETARY", "succes",JOptionPane.INFORMATION_MESSAGE);
				return 2;
			}
			else 
			{
				JOptionPane.showMessageDialog(null,"EROARE logare:functie necunoscuta", "error",JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			
			//LoginClient.
		}
		catch(Exception ex)
		{
			System.out.println("ERoare date introduse login");
			JOptionPane.showMessageDialog(null,"EROARE logare:date necunoscute", "error",JOptionPane.ERROR_MESSAGE);
			return -1;
			
		}
	}
	public int insertEmployee(String name,String identityCardNumber,String PNC,String address,String role,String username,String password)
	{
		if(dv.validateName(name))
		{
			if(dv.validateIdentityCardNumber(identityCardNumber))
			{
				if(dv.validatePNC(PNC))
				{
					if(dv.validatePassword(password))
					{
								int rez=empl.insertEmployee(name, identityCardNumber, PNC, address,role,username, password);
								if(rez==1)
									{
									JOptionPane.showMessageDialog(null,"Eroare adaugare angajat", "ERROR",JOptionPane.ERROR_MESSAGE);
										//Admin.clear();
									//return -1;
									}
								else 
									{
									JOptionPane.showMessageDialog(null,"Angajatul a fost adaugat", "SUCCES",JOptionPane.INFORMATION_MESSAGE);
									Admin.clear();
									return 0;
									
									}
					}
					else
						{
						JOptionPane.showMessageDialog(null,"Parola incorecta", "ERROR",JOptionPane.ERROR_MESSAGE);
						}//return -1;}
				}
				else
					{
					JOptionPane.showMessageDialog(null,"PNC incorect", "ERROR",JOptionPane.ERROR_MESSAGE);
					}//return -1;}
			}
			else 
				{
				JOptionPane.showMessageDialog(null,"Identity Card NUmber incorect", "ERROR",JOptionPane.ERROR_MESSAGE);
				}//return -1;}
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"Nume incorect", "ERROR",JOptionPane.ERROR_MESSAGE);//return -1;
		}
	return -1;
}
	
	public String[] loadEmployee()
	{
		ResultSet rs=null;
		rs=empl.selectEmployee();
		ArrayList<String> arr=new ArrayList<String>();
		
		try
		{
		while(rs.next())
		{
			arr.add(rs.getString(1)+" "+rs.getString(3));
		}
	
		}
		catch(Exception e)
		{
			System.out.println("eroare employee transaction select employee");
			
		}
		
		String []sir=new String[arr.size()];
		for(int i=0;i<sir.length;i++)
			sir[i]=arr.get(i);
		Arrays.sort(sir);
		return sir;
		
	}
	
	public String[] loadEmployee(String pnc)
	{
		ResultSet rs=null;
		rs=empl.selectAllInfoEmployee(pnc);
		ArrayList<String> arr=new ArrayList<String>();
		int i=0;
		try
		{
		while(rs.next())
		{
			arr.add(rs.getString(1));
			arr.add(rs.getString(2));
			arr.add(rs.getString(3));
			arr.add(rs.getString(4));
			arr.add(rs.getString(5));
			arr.add(rs.getString(6));
			arr.add(rs.getString(7));
			//i++;
		}
	
		}
		catch(Exception e)
		{
			System.out.println("eroare employee transaction select employee");
			
		}
		
		String []sir=new String[arr.size()];
		for(int j=0;j<arr.size();j++)
			sir[j]=arr.get(j);
		
		return sir;
		
	}
	
	public void loadEmployee(JTable table)
	{
		ResultSet rs=empl.selectEmployee();
		int index=0;
		try
		{
			while(rs.next())
			{
				System.out.println(rs.getString(1));
				index++;
			}
			rs.beforeFirst();
		}
		catch(Exception e)
		{
			System.out.println("Eroare");
		}

		String[] columnNames = {"Name",
                "Identity card Number",
                "PNC",
                "Address",
                "Role",
                "Username",
                "Passwword"};
		Object[][] data=new Object[index][7];
		try{
		int i=0;
			while(rs.next())
			{data[i][0]=rs.getString(1);
			data[i][1]=rs.getString(2);
			data[i][2]=rs.getString(3);
			data[i][3]=rs.getString(4);
			data[i][4]=rs.getString(5);
			data[i][5]=rs.getString(6);
			data[i][6]=rs.getString(7);
			i++;
				
			}
		/*for(int i=0;i<index;i++)
		{
			
			data[i][0]=rs.getString(1);
			data[i][1]=rs.getString(2);
			data[i][2]=rs.getString(2);
			data[i][3]=rs.getString(3);
			data[i][4]=rs.getString(4);
			data[i][5]=rs.getString(5);
			data[i][6]=rs.getString(6);
		}
		*/}
		catch(Exception ex)
		{
			System.out.println("Eroare tabel");
		}

		table.setModel(new DefaultTableModel(data,columnNames){
				@Override
			       public boolean isCellEditable(int row, int column) {
	           return false; //So I make every cell non-editable
				}});
	}
	
	public void updateEmployee(String oldPnc,String name,String identityCardNumber,String PNC,String address,String role,String username,String password)
	{
		if(dv.validateName(name))
		{
			if(dv.validateIdentityCardNumber(identityCardNumber))
			{
				if(dv.validatePNC(PNC))
				{
					if(dv.validatePassword(password))
					{
								int rez=empl.updateEmoployeeInfo(name, identityCardNumber, PNC, address,role,username, password,oldPnc);
								if(rez==1)
									{
									JOptionPane.showMessageDialog(null,"Eroare modificare angajat", "ERROR",JOptionPane.ERROR_MESSAGE);
										//Admin.clear();
									}
								else 
									{
									JOptionPane.showMessageDialog(null,"Angajatul a fost modificat", "SUCCES",JOptionPane.INFORMATION_MESSAGE);
									Admin.clear();
									}
					}
					else
						JOptionPane.showMessageDialog(null,"Parola incorecta", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null,"PNC incorect", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else 
				JOptionPane.showMessageDialog(null,"Identity Card NUmber incorect", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"Nume incorect", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void delete(String cnp)
	{
		if(dv.validatePNC(cnp))
		{
			empl.deleteEmployee(cnp);
			JOptionPane.showMessageDialog(null,"Angajatul a fost sters", "Succes",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"CNP incorect", "ERROR",JOptionPane.ERROR_MESSAGE);
	}
}
