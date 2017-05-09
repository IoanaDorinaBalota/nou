package databaseOperations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultationTable {
	
	private Connection myConnection=null;
	private final static String dbName="consultation";
	
	private final static String updateConsultationInfoStatement =
			"UPDATE consultation" +
			" set employeePNC = ?, patientPNC = ?, dateH = ?, duration=?,diagnostic=?,observations=?" +
			" where id = ?";
	
	private final static String deleteConsultationStatement =
			"DELETE from consultation" +
			" where patientPNC= ?";
	
	private final static String insertConsultationStatement =
			"INSERT into consultation values (?,?,?,?,?,?)";
	/*private final static String selectClientStatement =
			"SELECT name, personalNumericalCode from clients";*/
	private final static String selectAllConsultationStatement =
			"SELECT *from consultation where patientPNC=?";
	
	private final static String selectAll =
			"SELECT * from consultation";
	
	public ConsultationTable(Connection mysql)
	{
		myConnection=mysql;
	}
	
	public void updateConsultationInfo(int id, String employeePNC,String patientPNC,Date data,int duration,String diagnostic,String observations)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
		updateStatement = myConnection.prepareStatement(sql0);
		updateStatement.executeUpdate();
		
		
		updateStatement = myConnection.prepareStatement(updateConsultationInfoStatement);
		updateStatement.setString(1, employeePNC);
		updateStatement.setString(2, patientPNC);
		updateStatement.setDate(3,data);
		updateStatement.setInt(4, duration);
		updateStatement.setString(5, diagnostic);
		updateStatement.setString(6, observations);
		updateStatement.setInt(7, id);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();

		} 
		catch (Exception e) 
		{
			System.out.println("ERoare update consultation");
			e.printStackTrace();
		} 
	}
	
	public void deleteConsultation(String PNC)
	{
		PreparedStatement updateStatement = null;
		String sql0="SET FOREIGN_KEY_CHECKS=0";
		String sql1="SET FOREIGN_KEY_CHECKS=1";
		try {
		updateStatement = myConnection.prepareStatement(sql0);
		updateStatement.executeUpdate();
		
		
		updateStatement = myConnection.prepareStatement(deleteConsultationStatement);
		updateStatement.setString(1, PNC);
		updateStatement.execute();
		
		updateStatement = myConnection.prepareStatement(sql1);
		updateStatement.executeUpdate();

		} 
		catch (Exception e) 
		{
			System.out.println("ERoare DELETE consultation");
			e.printStackTrace();
		} 
	}
	
	public void insertConsultation(String employeePNC,String patientPNC,Date data,int duration,String diagnostic,String observations)
	{
		PreparedStatement updateStatement = null;
		try {
			updateStatement = myConnection.prepareStatement(insertConsultationStatement);
			updateStatement.setString(1, employeePNC);
			updateStatement.setString(2, patientPNC);
			updateStatement.setDate(3, data);
			updateStatement.setInt(4, duration);
			updateStatement.setString(5, diagnostic);
			updateStatement.setString(6, observations);
			updateStatement.executeUpdate();
		}
		catch (Exception e) 
		{
			System.out.println("ERoare INSERT consultation");
			e.printStackTrace();
		} 
	}
	
	public ResultSet selectConsultation()
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
			System.out.println("ERoare select all consultation");
			e.printStackTrace();
		} 
		return rs;
	}
}
