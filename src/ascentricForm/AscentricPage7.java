package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;

import com.itextpdf.text.DocumentException;

public class AscentricPage7 extends AscentricPage {

	private static final int PAGENUMBER = 7;
	
	public void fillPage(ClientHolder theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		tickFacetoFace();
		declaration();
		shutDown();
	}

	private void declaration() {
		int formHeight = 667;
		int formWidth = 317;
		String numbers = "12345678";
		//Firm Name
		stamp(formWidth, formHeight, "Master Adviser");
		//Firm Ref No.
		int temp = formWidth;
		stamp(temp, formHeight - 40, "1");
		for(int i = 1; i < 6; i++){
			stamp(temp+=20, formHeight-40, numbers.charAt(i) + "");
		}
		//Registered Individual
		stamp(formWidth, formHeight-84, "Douglas Brodie");
		//Firm Ref No.
		temp = formWidth;
		stamp(temp, formHeight-126, "0");
		for(int i = 1; i < 8; i++){
			stamp(temp+=20, formHeight-126, numbers.charAt(i) + "");
		}
		//Date
		String date = "31012014";
		temp = formWidth+52;
		stamp(temp, formHeight-206, "0");
		for(int i = 1; i < date.length(); i++){
			if(i %2 == 0 && i < 5){
				stamp(temp+=35, formHeight-206, numbers.charAt(i) + "");
			} else {
				stamp(temp+=20, formHeight-206, numbers.charAt(i) + "");
			}
		}
	}

	private void tickFacetoFace() {
		stamp(271, 477, "X");
	}

	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}

}
