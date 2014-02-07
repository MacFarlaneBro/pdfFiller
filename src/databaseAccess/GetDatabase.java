package databaseAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetDatabase {
	
	/**
	 * fetches an array of strings containing all values matching the relevant ID number
	 * @param idNumber The ID number of the relevant data
	 * @return An array containing all relevant data
	 */
	public String[] fetchInfoUsingID(int idNumber) throws SQLException;

	/**
	 * fetches the ID number associated with a specific name
	 * @param Name the identfiying name of the data required
	 * @return the ID number associated with the name
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int fetchInfoUsingName(String Name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;


}
