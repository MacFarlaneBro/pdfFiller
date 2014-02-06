package databaseAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ChinookDB extends Database{
	
	public ChinookDB(){
	    dbName = "chinook";//The database name is not necessary for connection to the database
	    userName = "root"; 
	}
		
	public void viewTable(Connection con, String dbName) throws SQLException{
		Statement stmt = null;
	    String query =
	        "select *" +
	        "from " + dbName + ".ARTISTS";

	    try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String coffeeName = rs.getString("COF_NAME");
	            int supplierID = rs.getInt("SUP_ID");
	            float price = rs.getFloat("PRICE");
	            int sales = rs.getInt("SALES");
	            int total = rs.getInt("TOTAL");
	            System.out.println(coffeeName + "\t" + supplierID +
	                               "\t" + price + "\t" + sales +
	                               "\t" + total);
	        }
	    } catch (SQLException e ) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}

	public String getFirstLineOfArtist() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet returner = stmt.executeQuery("SELECT *"
				+ "FROM ARTIST"
				+ "WHERE ARTIST = 'ACDC';");
		
		String returnerman = returner.toString();
		System.out.println(returnerman);
		return dbName;
	}


}
