package databaseAccess;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ChinookDB extends Database{
	
	public ChinookDB(){
	    dbName = "chinook";//The database name is not necessary for connection to the database
	    userName = "root"; 
	}
		
	public String getFirstLineOfArtist() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet returner = stmt.executeQuery("SELECT *"
				+ "FROM ARTIST where ArtistId = 1");
		
		String artist = null;
		while(returner.next()){
			 artist = returner.getString("NAME");
			 System.out.println(artist);
		}
		return artist;
	}

	public void open() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(driver).newInstance();
	    conn = DriverManager.getConnection(url + dbName,userName,password);
	    System.out.println("Connected to mySQL Database");
	}

	//This needs to be the last thing done, the result set is not a java object but rather a link retained to a relation
	//created within the database, therefore when the connection is closed, the result set is lost, as such it seems it would
	//be best to do all the work with the result set object in the Database package and then export the necessary information
	//in an array or a list or something.
	public void close() throws SQLException {
		
		conn.close();
		System.out.println("closed");
	}

	public String[] fetchInfoUsingID(int idNumber) throws SQLException {
		
		try {
			open();		
			conn.createStatement();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *"
					+ "FROM ARTIST where ArtistId =" + idNumber);
			
			
			String[] moon = new String[(rs.last() ? rs.getRow(): 0)];
					
			for(int i = 0; i < moon.length; i++){
				moon[i] = rs.getString("Name");
			}

			return moon;			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	@Override
	public int fetchInfoUsingName(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		open();
		Statement stmt = conn.createStatement();
		String query = "SELECT *"
				+ "FROM ARTIST "
				+ "where NAME =" + name;
		rs = stmt.executeQuery(query);
		int returner = 0;
		while(rs.next()){
			returner = rs.getInt("ArtistId");
		}
		return returner;
	}


}
