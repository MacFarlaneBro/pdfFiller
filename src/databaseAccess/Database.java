package databaseAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database implements GetDatabase{
	
	protected String url =  "jdbc:mysql://localhost:3306/";
	protected String driver = "com.mysql.jdbc.Driver";
	protected String dbName;
	protected String userName;
	protected String password;
	protected Connection conn;
	protected DatabaseMetaData md;
		
	public boolean makeConnection() throws SQLException{
		
      try {
      Class.forName(driver).newInstance();
      conn = DriverManager.getConnection(url + dbName,userName,password);
      System.out.println("Connected to mySQL Database");
      md = conn.getMetaData();
      
      showTables();
      getDatabase();
      
      if(!conn.equals(null)){
    	  conn.close();
    	  System.out.println("disconnected from mySQL Database");
    	  return true;
      }
      
      } catch (Exception e) {
    	  e.printStackTrace();
      }
      
	return false;
}
		
	public void showTables() throws SQLException{
		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) {
		  System.out.println(rs.getString(3));
		}
	}

		@Override
		public ResultSet getDatabase(){
			
			return null;
		}
}
