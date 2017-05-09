package databaseOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.*;

public class MysqlConnect {
    // init database constants
    private static String databaseDriver = "com.mysql.jdbc.Driver";
    private static String databaseURL = "jdbc:mysql://localhost:3306/cabinet";
    private String username = "root";
    private String password = "";
    private static String maxPool = "250";
    private String dbName="cabinet";
    private static Connection connection;
    private static Properties properties;
    
    public MysqlConnect(String dbName,String username,String pass)
    {
    	dbName=dbName;
    	this.username=username;
    	password=pass;
    }
    public MysqlConnect(String dbName,String username)
    {
    	dbName=dbName;
    	this.username=username;
    }
    public MysqlConnect()
    {
    }


    private static Properties getProperties()
    {    // create properties
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "");
            properties.setProperty("MaxPooledStatements", maxPool);
        }
        return properties;
    }


    public static Connection connect()
    {    // connect database
    	MysqlConnect c=new MysqlConnect();
        if (connection == null) {
            try {
            	try{
                Class.forName(databaseDriver);
            	}
            	 catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }
                connection = DriverManager.getConnection(databaseURL, getProperties());
                
            	} 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

   /*
    public void disconnect() 
    { 
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
    
    public String getDBName()
    {
    	return dbName;
    }
    
    public static void disconnect()
    {
    	/*Connection con;
    	try{
    	while((con=DriverManager.getConnection(databaseURL))!=null)
    	{
    		con.close();
    		
    	}
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Eroare deconectare");
    	}*/
    	/*try{
    	connection.close();
    	}
    	catch(Exception ex)
    	{
    		System.out.println("Eroare deconectare");
    	}*/
    	
    }
    

}