package databaseAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
		
	/**
	 * Searches the result set produced by searching for the clients name and returns all the available client data
	 * 
	 * @return an array containing all the available client data
	 * @throws SQLException
	 */
	private String[] clientInformation(String surname) throws SQLException {
			Statement st = conn.createStatement();
			System.out.println(surname);

			String query = "Select Forenames From Clients Where Surname = '";
			ResultSet rs = st.executeQuery(query + surname + "';");
			
			Set<String> firstNames = new HashSet<String>();
			
			while(rs.next()){
				System.out.println(rs.getString("Forenames"));
				firstNames.add(rs.getString("Forenames"));
			}
			
			//converting the set to an array before it is returned to the form
			String[] returner = (String[]) firstNames.toArray(new String[0]);
			return returner;
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

	@Override
	public Map<String, String> getClientPersonalData(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(driver).newInstance();
	      conn = DriverManager.getConnection(url);
	      System.out.println("Connected to MSSQL Database");
	      
	      Statement st = conn.createStatement();
	      String[] names = name.split("/");
	      
	      String query = "SELECT * FROM Clients WHERE Surname = '";
	      System.out.println(names[0]);
	      System.out.println(names[1]);
	      ResultSet rs = st.executeQuery(query + names[0] + "'"
	      		+ "AND Forenames ='" + names[1] + "';");
	      Map<String,String> pData = new HashMap<String, String>();
	      while(rs.next()){
		      pData.put("Title", rs.getString("Title"));
		      //Removing the time portion of the date object returned by the database before adding it to the map
		      String dob = rs.getString("DOB").substring(0,10);
		      pData.put("DOB", dob);
		      pData.put("HomeTel", rs.getString("HomeTel"));
		      pData.put("WorkTel", rs.getString("BusTel"));
		      pData.put("Mobile", rs.getString("MobTel"));
		      pData.put("HomeAddress1", rs.getString("HomeAddress1"));
		      pData.put("HomeAddress2", rs.getString("HomeAddress2"));
		      pData.put("HomeAddress3", rs.getString("HomeAddress3"));
		      pData.put("HomeAddress4", rs.getString("HomeAddress4"));
		      pData.put("HomeAddress5", rs.getString("HomeAddress5"));
		      pData.put("HomePostCode", rs.getString("HomePostCode"));
		      pData.put("Email", rs.getString("EmailAddress"));
	      }
	      
	      System.out.println(pData.get("DOB"));
		
	      if(!conn.equals(null)){
	    	  conn.close();
	    	  System.out.println("disconnected from mySQL Database");
	      }
		return pData;
	}
}
