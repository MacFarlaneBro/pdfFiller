package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientFactory;
import ascentricForm.AscentricForm;

public class AscentricPageTests {
	ClientFactory theClient = new ClientFactory("Bob Hoskins");
	
	@Before
	public void setUp() throws IOException, DocumentException{
		AscentricForm form = new AscentricForm();
		form.fillIt(theClient);
	}
	
	@Test
	public void testWrapperExistence(){
		assertNotNull(theClient.getFirstClient().getProductDetails().getStocksAndSharesISA());
	}

}
