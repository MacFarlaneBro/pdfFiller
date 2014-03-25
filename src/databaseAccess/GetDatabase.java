package databaseAccess;

import java.sql.SQLException;
import java.util.Map;

public interface GetDatabase {
	
	/**
	 * fetches the all the first names which share the surname provided as input
	 * 
	 * @param surame the surname of the client required
	 * @return an array containing all the first names which share the entered surname
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public String[] fetchInfoUsingName(String surname) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    /**
     * fetches all the personal information required to fill the form, using the first and surnames of the client
     * 
     * @param name the combined first and surname of the client, separated by '/'
     * @return map containing all the necessary information to fill out the clients required personal info on the form
	 * @throws SQLException 
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
	public Map<String, String> getClientPersonalData(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, String> getPartnerPersonalData(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;


}
