package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricPage8;

import com.itextpdf.text.DocumentException;

public class AscentricPage8Test {
	
	AscentricPage8 thePage = new AscentricPage8();
	ClientHolder theClientHolder = new ClientHolder();
	
	
	@Before
	public void setUp(){
		theClientHolder.makeNewFirstClient();
		theClientHolder.makeNewSecondClient();
		
		theClientHolder.getFirstClient().getIndividualDetails().setForename("Dave");
		theClientHolder.getFirstClient().getIndividualDetails().setSurname("Davison");
		theClientHolder.getSecondClient().getIndividualDetails().setForename("Dave");
		theClientHolder.getSecondClient().getIndividualDetails().setSurname("Davison");
		theClientHolder.getFirstClient().getfinancialAdviserDetails().setDate("09294828");
	}

	@Test
	public void testPage7() throws IOException, DocumentException{
		thePage.fillPage(theClientHolder);
	}
}
