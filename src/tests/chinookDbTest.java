package tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import databaseAccess.ChinookDB;
import databaseAccess.GetDatabase;

public class chinookDbTest {
	
	GetDatabase db;
	
	@Before
	public void setUp(){
		db = new ChinookDB();
	}
	
	@After
	public void breakDown(){
		db = null;
	}

	
	@Test
	public void getFirstLineArtistTest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		ChinookDB db = new ChinookDB();
		db.open();
		String acdc = "AC/DC";
		String result = db.getFirstLineOfArtist();
		System.out.println(result);
		db.close();
		assertEquals(acdc, result);
	}
	
	@Test
	public void artistRetrievalUsingFetchInfoTest() throws SQLException{
		GetDatabase db = new ChinookDB();
		String queen = "Queen";
		String[] rs = db.fetchInfoUsingID(51);
		String result = rs[0];
		assertEquals(queen, result);
	}
	
	@Test
	public void IdRetrievalUsingName() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		int artistID = 110;
		int result = db.fetchInfoUsingName("'Nirvana'");
		
		assertEquals(artistID, result);
	}
	
}
