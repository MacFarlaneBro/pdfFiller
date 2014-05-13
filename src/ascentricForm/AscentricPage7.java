package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.ConfirmationDetails;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;

public class AscentricPage7 extends AscentricPage{
	
	public static final int PAGENUMBER = 7;

	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		setUp(PAGENUMBER);
		
		ConfirmationDetails con = theClient.getConfirmationDetails();
		fillPrivateIndividual(con);
		fillConfirmation(con, theClient.getfinancialAdviserDetails());
		
		shutDown();
	}

	private void fillPrivateIndividual(ConfirmationDetails con) {
		
		int firstIndividualColumnWidth = 110;
		int privateIndividualHeight = 518;
		int secondIndividualColumnWidth = 380;
		
		//Name of individual
		stamp(firstIndividualColumnWidth, privateIndividualHeight-=20, con.getName());
		//Individual's current address
		if(con.getCurrentAddress() != null && con.getCurrentAddress().length() > 2){
			String[] address = con.getCurrentAddress().split(":");
			
			for(String line: address)
			{
				stamp(firstIndividualColumnWidth, privateIndividualHeight-=20, line);
			}
		}
		//Postcode
		stamp(firstIndividualColumnWidth, privateIndividualHeight-=20, con.getCurrentPostCode());
		privateIndividualHeight-=23;
		//Date of Birth
		String dob = con.getDob();
		if(dob!= null && dob.length() > 3){
			stamp(firstIndividualColumnWidth,
					privateIndividualHeight,
					dob.charAt(0));
			int extraDistance = 0;
			for(int i = 1; i < dob.length(); i++)
			{
				if(i == 2 || i == 4){
					extraDistance+=30;
				} else {
					extraDistance+=20;
				}
				stamp(firstIndividualColumnWidth + extraDistance, privateIndividualHeight, dob.charAt(i));
			}
		}
		
		//Resetting the row height prior to printing out the second column
		privateIndividualHeight = 520;
		
		//Individuals previous address
		if(con.getCurrentAddress() != null && con.getCurrentAddress().length() > 2){
			String[] address = con.getPreviousAddress().split(":");
			for(String line: address)
			{
				stamp(secondIndividualColumnWidth, privateIndividualHeight-=20, line);
			}
			privateIndividualHeight-=21;
			//Post code
			stamp(secondIndividualColumnWidth, privateIndividualHeight, con.getPreviousPostCode());
		}
	}


	private void fillConfirmation(ConfirmationDetails con,
			FinancialAdviserDetails fad) {
		
		int confirmationHeight = 307;
		int firstConfirmationColumnWidth = 47;
		int secondConfirmationColumnWidth = 314;
		int firstAdvRow = 227;
		int secondAdvRow = 182;
		int thirdAdvRow = 142;
		
		//Joint Money Laundering Tick Box
		if(con.isMoneyLaunderingCheck()) stamp(firstConfirmationColumnWidth, confirmationHeight, "X");
		
		//Client Identity Tick Box
		if(con.isClientIdentityCheck()) stamp(firstConfirmationColumnWidth, confirmationHeight-29, "X");
		
		//Firm Name
		stamp(firstConfirmationColumnWidth, firstAdvRow, "Master Adviser");

		//FCA Firm Reference Number
		stamp(firstConfirmationColumnWidth, secondAdvRow, fad.getFcaFirmNumber());
		//Registered Individual
		stamp(firstConfirmationColumnWidth, thirdAdvRow, fad.getRegisteredIndividual());
		//FCA Individual Reference Number
		stamp(secondConfirmationColumnWidth, firstAdvRow, fad.getFcaIndividualReferenceNumber());	
		//Date
		String dob = fad.getDate();
		if(dob!= null){
			stamp(secondConfirmationColumnWidth+=55,
					thirdAdvRow,
					dob.charAt(0));
			int extraDistance = 0;
			for(int i = 1; i < dob.length(); i++)
			{
				if(i == 2 || i == 4){
					extraDistance+=35;
				} else {
					extraDistance+=20;
				}
				stamp(secondConfirmationColumnWidth + extraDistance, thirdAdvRow, dob.charAt(i));
			}
		}
	}

	@Override
	public void fillPage(MakeClients theClient) throws IOException,
			DocumentException {}
}
