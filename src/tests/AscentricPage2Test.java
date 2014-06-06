package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.CorrespondenceDetails;
import ascentricClientDetails.IndividualDetails;
import ascentricForm.AscentricPage2;

import com.itextpdf.text.DocumentException;

public class AscentricPage2Test {
	
	ClientHolder theClient = ClientHolder.getInstance();
	AscentricPage2 thePage = new AscentricPage2();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		IndividualDetails spd = theClient.getSecondClient().getIndividualDetails();	
		setPersonalDetails(spd);
	}

	private void setPersonalDetails(IndividualDetails id) {
		id.setAddress("20 Nassington rd:London");
		id.setPostcode("NW3 2UD");
		id.setHomeNumber("079928472");
		id.setWorkNumber("0998274452");
		id.setMobileNumber("9338273928");
		id.setClientRef("99099");
		id.setCity("London");
		id.setCountry("United Kingdom");
		id.setDob("12021990");
		id.setDomiciled("Great Britain");
		id.setTitle("Ms");
		id.setSurname("Grant");
		id.setForename("Naomi");
		id.setNationalInsuranceNumber(null);
		id.setEmail("nrg23@sussex.ac.uk");
		id.setUsPerson(true);
		id.setNationality("British");
		id.setTin("12826294291234322842");
		id.setSameDetails(false);
		CorrespondenceDetails cd = id.getCorrespondenceDetails();
		cd.setFirstAdd("20 Nassington Road");
		cd.setSecondAdd("London");
		cd.setPostCode("NW3 2UD");
	}

	@Test
	public void fillPage() throws IOException, DocumentException{
		thePage.fillPage(theClient.getSecondClient());
	}

}
