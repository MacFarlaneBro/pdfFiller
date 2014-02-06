package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import databaseAccess.ChinookDB;

public class chinookDbTest {
	
	ChinookDB db;
	
	@Before
	public void setUp(){
		db = new ChinookDB();
	}
	
	@After
	public void breakDown(){
		db = null;
	}

	
	@Test
	public void valueTest() throws SQLException{
		String acdc = "AC/DC";
		String result = db.getFirstLineOfArtist();
		
		assertEquals(result,acdc);
	}
	
}
