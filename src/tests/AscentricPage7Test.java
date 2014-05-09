package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ConfirmationDetails;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricForm.AscentricPage7;

import com.itextpdf.text.DocumentException;

public class AscentricPage7Test {
	
	AscentricPage7 thePage = new AscentricPage7();
	ClientHolder theClientHolder = ClientHolder.getInstance();
	Client theClient;
	
	@Before
	public void setUp(){
		theClientHolder.makeNewFirstClient();
		theClient = theClientHolder.getFirstClient();
		ConfirmationDetails con = theClient.getConfirmationDetails();
		
		fillDetails(con);
		
	}

	private void fillDetails(ConfirmationDetails con) {
		con.setName("James Woods");
		con.setCurrentAddress("23 Nassdfe road:Bignely:England");
		con.setCurrentPostCode("NE3 777");
		con.setDob("09121954");
		con.setPreviousAddress("49 Bummer road:YourMum:IsLovely");
		con.setPreviousPostCode("ME3 IOP");
		con.setMoneyLaunderingCheck(true);
		con.setClientIdentityCheck(true);

	}
	
	@Test
	public void testPage7() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}

}
