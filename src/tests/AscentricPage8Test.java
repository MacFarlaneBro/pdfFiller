package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricPage9;

import com.itextpdf.text.DocumentException;

public class AscentricPage8Test {
	
	AscentricPage9 thePage = new AscentricPage9();
	ClientHolder theClient = ClientHolder.getInstance();
	
	
	@Before
	public void setUp(){
		theClient.makeNewFirstClient();
		theClient.makeNewSecondClient();
		
		theClient.getFirstClient().getIndividualDetails().setForename("Dave");
		theClient.getFirstClient().getIndividualDetails().setSurname("Davison");
		theClient.getSecondClient().getIndividualDetails().setForename("Dave");
		theClient.getSecondClient().getIndividualDetails().setSurname("Davison");
		theClient.getFirstClient().getfinancialAdviserDetails().setDate("09294828");
	}

	@Test
	public void testPage7() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}
}
