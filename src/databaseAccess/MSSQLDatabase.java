package databaseAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MSSQLDatabase implements GetDatabase{
	
	protected String url =  "jdbc:sqlserver://localhost;databaseName=Main1st;integratedSecurity=true";
	protected String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected String dbName;
	protected String userName;
	protected String password;
	protected Connection conn;
	protected DatabaseMetaData md;
	protected ResultSet rs;
		//Hooray!
	private void getPersonalDetails(String Name) throws SQLException{
		
      try {
	      Class.forName(driver).newInstance();
	      conn = DriverManager.getConnection(url);
	      System.out.println("Connected to MSSQL Database");
	      md = conn.getMetaData();
	      
	      showTables();
	      clientInformation();
	      
	      if(!conn.equals(null)){
	    	  conn.close();
	    	  System.out.println("disconnected from mySQL Database");
	      }
      } catch (Exception e) {
    	  e.printStackTrace();
      }
}
		
		private void clientInformation() throws SQLException {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * From Clients Where Surname = 'Brodie';");
			while(rs.next()){
				System.out.println(rs.getString("ForeNames"));
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
		public String[] fetchInfoUsingName(String Name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			getPersonalDetails(Name);
			return null;
		}
}
