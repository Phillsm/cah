package database;

import java.sql.Connection;
import java.sql.DriverManager;

//This calss is for establishing the connection to the Oracle-database.

public class DBConnector {

	  private static String id = "cphtc47";						//Insert ORACLE id and password
	  private static String pw = "cphtc47";
	 
	  public Connection getConnection()
	  {
	    Connection con = null;
	    try 
	    {  
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      con = DriverManager.getConnection(
	          "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat", id,  pw );  
	    }
	    catch (Exception e) 
	    {   
	    	System.out.println("\n*** Remember to insert your Oracle ID and PW in the DBConnector class! ***\n");
	    	System.out.println("eror in DBConnector.getConnection()");
	        System.out.println(e); 	     
	    }    

	    return con;
	  }
	  
	  public void releaseConnection(Connection con)
	  {
	      try{
	          con.close();
	      }
	      catch (Exception e)
	      { System.err.println(e);}
	  }
}
