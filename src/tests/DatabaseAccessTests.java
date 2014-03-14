package tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import databaseAccess.MSSQLDatabase;

public class DatabaseAccessTests {

	private MSSQLDatabase db;

	@Before
	public void setUp(){
		db = MSSQLDatabase.getDatabaseConnector();
	}
	
	@After
	public void breakDown(){
		
	}

	@Test
	public void fetchFirstNameTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String[] results = db.fetchInfoUsingName("Brodie");
		System.out.println(results[2]);
		assertEquals("Douglas", results[2]);
	}
	
	@Test
	public void getClientPersonalDataTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Map<String, String> results = db.getClientPersonalData("Brodie/Douglas");
		System.out.println(results.get("Title"));
		assertEquals("1959-10-04", results.get("DOB"));
	}

}
