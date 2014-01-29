package formTests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import ascentric.AscentricPersonalInfo1;
import com.itextpdf.text.DocumentException;

public class AscentricTest {
	
	private AscentricPersonalInfo1 asc;
	
	@Before
	public void setUp(){
		asc = new AscentricPersonalInfo1("a");
	}
	

	@Test
	public void setUpTest_formNotNull() throws IOException, DocumentException {
		
		asc.setUp("asc", "james");
		
	}
	
	@Test public void setUpTest_clientNotNull(){
		
		
	}

}
