package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.CorrespondenceDetails;
import ascentricForm.AscentricPage1;

import com.itextpdf.text.DocumentException;

public class AscentricPage1Test {
	
	ClientHolder theClient = ClientHolder.getInstance();
	AscentricPage1 thePage = new AscentricPage1();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		theClient.makeNewFirstClient();
		setPersonalDetails();
	}

	private void setPersonalDetails() {
		theClient.getFirstClient().setApplicationType("Single Client");
		theClient.getFirstClient().getIndividualDetails().setAddress("23 St Gabriels rd:Willesden:London");
		theClient.getFirstClient().getIndividualDetails().setPostcode("NW2 4DS");
		theClient.getFirstClient().getIndividualDetails().setHomeNumber("079928472");
		theClient.getFirstClient().getIndividualDetails().setWorkNumber("0998274452");
		theClient.getFirstClient().getIndividualDetails().setMobileNumber("9338273928");
		theClient.getFirstClient().getIndividualDetails().setClientRef("99099");
		theClient.getFirstClient().getIndividualDetails().setCity("London");
		theClient.getFirstClient().getIndividualDetails().setCountry("United Kingdom");
		theClient.getFirstClient().getIndividualDetails().setDob("25091989");
		theClient.getFirstClient().getIndividualDetails().setDomiciled("Great Britain");
		theClient.getFirstClient().getIndividualDetails().setTitle("Mr");
		theClient.getFirstClient().getIndividualDetails().setSurname("Brodie");
		theClient.getFirstClient().getIndividualDetails().setForename("Charlie");
		theClient.getFirstClient().getIndividualDetails().setNationalInsuranceNumber(null);
		theClient.getFirstClient().getIndividualDetails().setEmail("rabidwolves911@hotmail.com");
		theClient.getFirstClient().getIndividualDetails().setUsPerson(false);
		theClient.getFirstClient().getIndividualDetails().setNationality("Scottish");
		theClient.getFirstClient().getIndividualDetails().setTin("12826294291234322842");
		CorrespondenceDetails cd = theClient.getFirstClient().getIndividualDetails().getCorrespondenceDetails();
		cd.setFirstAdd("Barnsley");
		cd.setSecondAdd("Hull");
		cd.setPostCode("NW3 2UD");
	}
	
	@Test
	public void fillFirstPage() throws IOException, DocumentException{
		thePage.fillPage(theClient.getFirstClient());
		System.out.println("Page Filled");
	}
}
