package databaseAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database implements GetDatabase{
	
	protected String url =  "jdbc:mysql://localhost:3306/";
	protected String mySqlDriver = "com.mysql.jdbc.Driver";
	protected String msSqlDriver = "com.mysql.jdbc.Driver";
	protected String dbName;
	protected String userName;
	protected String password;
	protected Connection conn;
	protected DatabaseMetaData md;
	protected ResultSet rs;
		
	public void makeConnection() throws SQLException{
		
      try {
	      Class.forName(mySqlDriver).newInstance();
	      conn = DriverManager.getConnection(url + dbName,userName,password);
	      System.out.println("Connected to mySQL Database");
	      md = conn.getMetaData();
	      
	      showTables();
	      
	      
	      if(!conn.equals(null)){
	    	  conn.close();
	    	  System.out.println("disconnected from mySQL Database");
	      }
      
      } catch (Exception e) {
    	  e.printStackTrace();
      }
      
}
		
	public void showTables() throws SQLException{
		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) {
		  System.out.println(rs.getString(3));
		}
	}

		@Override
		public String[] fetchInfoUsingID(int idNumber) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int fetchInfoUsingName(String Name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			return 0;
		}
}
