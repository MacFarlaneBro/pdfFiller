package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.IndividualDetails;
import ascentricForm.AscentricPage1;
import ascentricForm.AscentricPage2;

import com.itextpdf.text.DocumentException;

public class AscentricPage2Test {
	
	ClientHolder theClient = new ClientHolder();
	AscentricPage2 thePage = new AscentricPage2();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		theClient.makeNewSecondClient();
		IndividualDetails spd = theClient.getSecondClient().getIndividualDetails();	
		setPersonalDetails(spd);
	}

	private void setPersonalDetails(IndividualDetails id) {
		id.setAddress("23 St Gabriels rd:Willesden:London");
		id.setPostcode("NW3 2UD");
		id.setHomeNumber("079928472");
		id.setWorkNumber("0998274452");
		id.setMobileNumber("9338273928");
		id.setClientRef("99099");
		id.setCity("London");
		id.setCountry("United Kingdom");
		id.setDob("25091989");
		id.setDomiciled("Great Britain");
		id.setTitle("Mr");
		id.setSurname("Brodie");
		id.setForename("Charlie");
		id.setNationalInsuranceNumber("348483834");
		id.setEmail("rabidwolves911@hotmail.com");
		id.setUsPerson(true);
		id.setNationality("Scottish");
		id.setTin("12826294291234322842");
		id.setSameDetails(true);
	}

	@Test
	public void fillPage() throws IOException, DocumentException{
		thePage.fillPage(theClient.getSecondClient());
	}

}
