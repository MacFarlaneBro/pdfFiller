package formTests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pdfFiller.AscentricPage1;

import com.itextpdf.text.DocumentException;

public class AscentricTest {
	
	private AscentricPage1 asc;
	
	@Before
	public void setUp(){
		asc = new AscentricPage1();
		
	}
	

	@Test
	public void setUpTest_formNotNull() throws IOException, DocumentException {
		
		asc.setUp("asc", "james");
		
		String client = asc.getClient();
		
		assertNotNull(client);
	}
	
	@Test public void setUpTest_clientNotNull(){
		
		
	}

}
