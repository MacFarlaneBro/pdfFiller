package databaseAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MSSQLDatabase implements GetDatabase{
	
	private static MSSQLDatabase instance;
	protected String url =  "jdbc:sqlserver://localhost;databaseName=Main1st;integratedSecurity=true";
	protected String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected String dbName;
	protected String userName;
	protected String password;
	protected Connection conn;
	protected DatabaseMetaData md;
	protected ResultSet rs;
		//Hooray!
	
	public static MSSQLDatabase getDatabaseConnector(){
		return instance;
	}
	
	//Static instantiation block creates single instance on first use of class
	static {
		instance = new MSSQLDatabase();
	}
	
	//Private constructor enforces singleton pattern to prevent multiple simultaneous database connections
	private MSSQLDatabase(){};
	
	public String[][] getPersonalDetails(String name, boolean partner) throws SQLException{
		
      try {
	      Class.forName(driver).newInstance();
	      conn = DriverManager.getConnection(url);
	      System.out.println("Connected to MSSQL Database");
	      md = conn.getMetaData();
	      
	      //Matrix to be returned containing the clients data if available
	      String[][] clientData = new String[2][10];
	      
	      //fill the matrix with the clients data
	      clientData[0] = clientInformation(name);
	  
	      //If the application is for two people then fill the second array in the matrix with the client partner data
	      if(partner){
	      }
	      
	      if(!conn.equals(null)){
	    	  conn.close();
	    	  System.out.println("disconnected from mySQL Database");
	      }
      } catch (Exception e) {
    	  e.printStackTrace();
      }
	return null;
}
		
	/**
	 * Searches the result set produced by searching for the clients name and returns all the available client data
	 * 
	 * @return an array containing all the available client data
	 * @throws SQLException
	 */
	private String[] clientInformation(String surname) throws SQLException {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * From Clients Where Surname = '" + surname + "';");
			
			String[] clientData = new String[10];
			int i = 0;
			while(rs.next()){
				System.out.println(rs.getFetchSize());
				System.out.println(rs.getString("Forenames"));
				clientData[i] = rs.getString("Forenames");
				i++;
				if(i == 10) break;
			}
			return clientData;
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
	public String[] fetchInfoUsingName(String name)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		
		  Class.forName(driver).newInstance();
	      conn = DriverManager.getConnection(url);
	      System.out.println("Connected to MSSQL Database");
	      md = conn.getMetaData();
	      
	      String[] result = clientInformation(name);
		
	      if(!conn.equals(null)){
	    	  conn.close();
	    	  System.out.println("disconnected from mySQL Database");
	      }
		return result;
	}
}
