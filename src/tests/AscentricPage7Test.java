package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricForm.AscentricPage8;

import com.itextpdf.text.DocumentException;

public class AscentricPage7Test {
	
	AscentricPage8 thePage = new AscentricPage8();
	ClientHolder theClientHolder = ClientHolder.getInstance();
	Client theClient;
	
	
	@Before
	public void setUp(){
		theClientHolder.makeNewFirstClient();
		theClient = theClientHolder.getFirstClient();
		FinancialAdviserDetails fad = theClient.getfinancialAdviserDetails();
		
		fillDetails(fad);
		
	}

	private void fillDetails(FinancialAdviserDetails fad) {
		fad.setFcaFirmNumber("839483");
		fad.setRegisteredIndividual("Douglas Brodie");
		fad.setFaceToFaceContact(true);
		fad.setDate("25091989");
	}
	
	@Test
	public void testPage7() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}
}
