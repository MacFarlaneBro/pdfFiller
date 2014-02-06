package databaseAccess;

import java.sql.ResultSet;

public interface GetDatabase {
	
	/**
	 * fetches a database using the jdbc api, generalised to hide encapsulate database extraction function
	 * @return the database to be used
	 */
	public ResultSet getDatabase();

}
