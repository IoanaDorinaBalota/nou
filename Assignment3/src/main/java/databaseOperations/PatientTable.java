package databaseOperations;

import java.sql.Connection;
import java.sql.*;

public class PatientTable {
	
	private Connection myConnection=null;
	private final static String dbName="patient";
	
	private final static String updatePatientInfoStatement =
			"UPDATE patient" +
			" set name = ?, identityCardNumber = ?, personalNumericalCode = ?,birthDate=?, address=?" +
			" where personalNumericalCode = ?";
	
	private final static String deletePatientStatement =
			"DELETE from patient" +
			" where personalNumericalCode = ?";
	
	private final static String insertPatientStatement =
			"INSERT into patient values (?,?,?,?,?)";
	private final static String selectPatientStatement =
			"SELECT name, personalNumericalCode from patient";
	private final static String selectAllPatientStatement =
			"SELECT *from patient where personalNumericalCode=?";
	
	private final static String selectAll =
			"SELECT * from patient";
	
	/*public ClientTableGateway()
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
	public PatientTable(Connection mysql)
	{
		myConnection=mysql;
	}
	
	public void updatePatientInfo(String name, String identityCardNumber,String PNC,Date date,String address,String oldPNC)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
		updateStatement = myConnection.prepareStatement(sql0);
		updateStatement.executeUpdate();
		
		
		updateStatement = myConnection.prepareStatement(updatePatientInfoStatement);
		updateStatement.setString(1, name);
		updateStatement.setString(2, identityCardNumber);
		updateStatement.setString(3, PNC);
		updateStatement.setDate(4, date);
		updateStatement.setString(5, address);
		updateStatement.setString(6, oldPNC);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();

		} 
		catch (Exception e) 
		{
			System.out.println("ERoare update PATIENT");
			e.printStackTrace();
		} 

	}
	
	public void deletePatient(String PNC)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
		updateStatement = myConnection.prepareStatement(sql0);
		updateStatement.executeUpdate();
		
		
		updateStatement = myConnection.prepareStatement(deletePatientStatement);
		updateStatement.setString(1, PNC);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();

		} 
		catch (Exception e) 
		{
			System.out.println("ERoare DELETE clients");
			e.printStackTrace();
		} 
	}
	
	public void insertPatient(String name,String identityCardNumber,String personalNumericalCode,Date date,String address)
	{
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(insertPatientStatement);
			updateStatement.setString(1, name);
			updateStatement.setString(2, identityCardNumber);
			updateStatement.setString(3, personalNumericalCode);
			updateStatement.setDate(4, date);
			updateStatement.setString(5, address);
			updateStatement.executeUpdate();
		}
		catch (Exception e) 
		{
			System.out.println("ERoare INSERT clients");
			e.printStackTrace();
		} 
	}
	
	public ResultSet selectPatient()
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(selectPatientStatement);
			rs=updateStatement.executeQuery();
			
			/*while(rs.next())
			  {
				   
			        System.out.println(rs.getString(1)+ "	" + rs.getString(2));
			  }*/
		}
		catch (Exception e) 
		{
			System.out.println("ERoare INSERT clients");
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet selectAll()
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(selectAll);
			rs=updateStatement.executeQuery();
			
			/*while(rs.next())
			  {
				   
			        System.out.println(rs.getString(1)+ "	" + rs.getString(2));
			  }*/
		}
		catch (Exception e) 
		{
			System.out.println("ERoare INSERT clients");
			e.printStackTrace();
		} 
		return rs;
	}
	
	
	public ResultSet selectAllPatient(String PNC)
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(selectAllPatientStatement);
			updateStatement.setString(1, PNC);
			rs=updateStatement.executeQuery();
			
			/*while(rs.next())
			  {
				   
			        System.out.println(rs.getString(1)+ "	" + rs.getString(2));
			  }*/
		}
		catch (Exception e) 
		{
			System.out.println("ERoare select cclient gateway clients");
			e.printStackTrace();
		} 
		return rs;
	}
	public void delete()
	{
		ResultSet rs=null;
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";

		try {
			updateStatement = myConnection.prepareStatement(sql0);
			updateStatement.executeUpdate();
			
			updateStatement = myConnection.prepareStatement("delete  from patient");
			updateStatement.executeUpdate();
			
			updateStatement = myConnection.prepareStatement(sql1);
			updateStatement.executeUpdate();
		}
		catch (Exception e) 
		{
			System.out.println("ERoare select cclient gateway clients");
			e.printStackTrace();
		} 
	
	}
	

}
