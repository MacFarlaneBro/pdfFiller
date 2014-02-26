package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.FinancialAdviserDetails;

import com.itextpdf.text.DocumentException;

public class AscentricPage7 extends AscentricPage {

	private static final int PAGENUMBER = 7;
	private static final String FIRMNAME = "Master Adviser";
	private static final String FIRMREF = "593723";
	
	
	public void fillPage(Client theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		tickFacetoFace(theClient.getfinancialAdviserDetails().isFaceToFaceContact());
		declaration(theClient.getfinancialAdviserDetails());
		shutDown();
	}

	private void declaration(FinancialAdviserDetails fad) {
		int formHeight = 667;
		int formWidth = 317;
		
		//Firm Name
		stamp(formWidth, formHeight, FIRMNAME);
		
		//Firm Ref No.
		int temp = formWidth;
		stamp(temp, formHeight - 40, "" + FIRMREF.charAt(0));
		for(int i = 1; i < 6; i++){
			stamp(temp+=20, formHeight-40, FIRMREF.charAt(i) + "");
		}
		
		//Registered Individual
		stamp(formWidth, formHeight-84, fad.getRegisteredIndividual());
		
		//FCA Individual Reference Number
		temp = formWidth;
		stamp(temp, formHeight-126, fad.getFcaIndividualReferenceNumber().charAt(0) + "");
		for(int i = 1; i < 8; i++){
			stamp(temp+=20, formHeight-126, fad.getFcaIndividualReferenceNumber().charAt(i) + "");
		}
		
		//Date
		temp = formWidth+52;
		stamp(temp, formHeight-206, fad.getDate().charAt(0) + "");
		for(int i = 1; i < fad.getDate().length(); i++){
			if(i %2 == 0 && i < 5){
				//stamp(temp+=35, formHeight-206, fad.getDate().charAt(i) + "");
			} else {
				stamp(temp+=20, formHeight-206, fad.getDate().charAt(i) + "");
			}
		}
	}

	private void tickFacetoFace(boolean b) {
			if(b) stamp(271, 477, "X");
	}

}
