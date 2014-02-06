package databaseAccess;

import java.sql.SQLException;

public class DBStarter {
	
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		ChinookDB db = new ChinookDB();
		db.open();
		db.getFirstLineOfArtist();
		db.close();
		
	}

}
