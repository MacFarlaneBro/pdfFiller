package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class Database implements GetDatabase{
	
	protected String url =  "jdbc:mysql://localhost:3306/";
	protected String driver = "com.mysql.jdbc.Driver";
	protected String dbName;
	protected String userName;
	protected String password;
	protected Connection conn;
		
	public void makeConnection() throws SQLException{
		
      try {
      Class.forName(driver).newInstance();
      conn = DriverManager.getConnection(url + dbName,userName,password);
      System.out.println("Connected to mySQL Database");
      	      
      System.out.println("Disconnected from mySQL Database");
      

      } catch (Exception e) {
    	  e.printStackTrace();
      } finally {
          conn.close();
      }
}

		@Override
		public ResultSet getDatabase() {
			// TODO Auto-generated method stub
			return null;
		}
}
