package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class ChinookDB extends Database{
	
	public ChinookDB(){
	    dbName = "chinook";//The database name is not necessary for connection to the database
	    userName = "root"; 
	}
	
	public void makeConnection(){
	      try {
	      Class.forName(driver).newInstance();
	      Connection conn = DriverManager.getConnection(url + dbName,userName,password);
	      System.out.println("Connected to mySQL Database");
	      
	      ResultSet rs=conn.getMetaData().getSchemas();
	      while(rs.next()) {
	         System.out.println(rs.getString(1));
	      }
	      	      
	      conn.close();
	      System.out.println("Disconnected from mySQL Database");
	      

	      } catch (Exception e) {
	    	  e.printStackTrace();
	      } finally {
	      }
	}

	@Override
	public ResultSet getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

}
