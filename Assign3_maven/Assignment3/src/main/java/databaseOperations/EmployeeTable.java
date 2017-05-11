package databaseOperations;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeTable {
	
	private Connection myConnection=null;
	//private final static String dbName="clients";
	
	private final static String updateEmployeeInfoStatement =
			"UPDATE employee" +
			" set name = ?, identityCardNumber = ?, personalNumericalCode = ?, address=?, role=?,username=?,passwordE=?" +
			" where personalNumericalCode = ?";
	
	private final static String deleteEmployeeStatement =
			"DELETE from employee" +
			" where personalNumericalCode = ?";
	
	private final static String insertEmployeeStatement =
			"INSERT into employee values (?,?,?,?,?,?,?)";
	private final static String selectEmployeeStatement =
			"SELECT * from employee";
	
	private final static String selectOneEmployeeStatement =
			"SELECT * from employee where passwordE=? and username=?";
	
	private final static String selectAllInfoEmployee =
			"SELECT * from employee where personalNumericalCode=?";
	
	/*public EmployeeTableGateway()
	{
		MysqlConnect mysql=new MysqlConnect();
		try 
		{
			myConnection=mysql.connect();
		}
		catch(Exception e)
		{
			System.out.println("Eroare conectare");
		}
		
	}*/
	public EmployeeTable(Connection mysql)
	{
		myConnection=mysql;
	}
	public int updateEmoployeeInfo(String name, String identityCardNumber,String PNC,String address,String role,String username,String password,String oldPNC)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
		updateStatement = myConnection.prepareStatement(sql0);
		updateStatement.executeUpdate();
		
		
		updateStatement = myConnection.prepareStatement(updateEmployeeInfoStatement);
		updateStatement.setString(1, name);
		updateStatement.setString(2, identityCardNumber);
		updateStatement.setString(3, PNC);
		updateStatement.setString(4, address);
		updateStatement.setString(5, role);
		updateStatement.setString(6, username);
		updateStatement.setString(7, password);
		updateStatement.setString(8, oldPNC);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();
		return 0;

		} 
		catch (Exception e) 
		{
			System.out.println("ERoare update employee");
			e.printStackTrace();
			return 1;
		} 

	}
	
	public void deleteEmployee(String PNC)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
		updateStatement = myConnection.prepareStatement(sql0);
		updateStatement.executeUpdate();
		
		
		updateStatement = myConnection.prepareStatement(deleteEmployeeStatement);
		updateStatement.setString(1, PNC);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();
		
		//HistoryTableGateway a=new AccountsTableGateway();
		//a.deleteAccountsPNC(PNC);
		} 
		catch (Exception e) 
		{
			System.out.println("ERoare DELETE employee");
			e.printStackTrace();
		} 
	}
	
	public int insertEmployee(String name, String identityCardNumber,String PNC,String address,String role,String username,String password)
	{
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(insertEmployeeStatement);
			updateStatement.setString(1, name);
			updateStatement.setString(2, identityCardNumber);
			updateStatement.setString(3, PNC);
			updateStatement.setString(4, address);
			updateStatement.setString(5, role);
			updateStatement.setString(6, username);
			updateStatement.setString(7, password);
			updateStatement.executeUpdate();
			return 0;
			
		}
		catch (Exception e) 
		{
			System.out.println("ERoare INSERT EMPLOYEE");
			e.printStackTrace();
			return 1;
		} 
	}
	
	public ResultSet selectEmployee()
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(selectEmployeeStatement);
			rs=updateStatement.executeQuery();
			
			/*while(rs.next())
			  {
				   
			        System.out.println(rs.getString(1)+ "	" + rs.getString(2));
			  }*/
		}
		catch (Exception e) 
		{
			System.out.println("ERoare select employee");
			e.printStackTrace();
		} 
		return rs;
	}
	public ResultSet selectAllInfoEmployee(String pnc)
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(selectAllInfoEmployee);
			updateStatement.setString(1, pnc);
			rs=updateStatement.executeQuery();
			
			/*while(rs.next())
			  {
				   
			        System.out.println(rs.getString(1)+ "	" + rs.getString(2));
			  }*/
		}
		catch (Exception e) 
		{
			System.out.println("ERoare select all info employee");
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet selectOneEmployee(String name,String pass)
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(selectOneEmployeeStatement);
			updateStatement.setString(1, pass);
			updateStatement.setString(2, name);
			rs=updateStatement.executeQuery();
			
			/*while(rs.next())
			  {
				   
			        System.out.println(rs.getString(1)+ "	" + rs.getString(2));
			  }
			rs.beforeFirst();*/
		}
		catch (Exception e) 
		{
			System.out.println("ERoare select one employee gateway");
			e.printStackTrace();
		} 
		return rs;
	}
	
	public  void updateEmployeeAllInfo(String oldID,String name, String idc,String pnc,String add,String agency,String role,String pass)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
			updateStatement = myConnection.prepareStatement(sql0);
			updateStatement.executeUpdate();
		updateStatement = myConnection.prepareStatement(updateEmployeeInfoStatement);
		updateStatement.setString(1, name);
		updateStatement.setString(2, idc);
		updateStatement.setString(3, pnc);
		updateStatement.setString(4, add);
		updateStatement.setString(5, agency);
		updateStatement.setString(6, role);
		updateStatement.setString(7, pass);
		updateStatement.setString(8, oldID);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();
		} 
		catch (Exception e) 
		{
			System.out.println("ERoare update EMOPLOYEE ACCOUNT");
			e.printStackTrace();
		} 
	}

}
