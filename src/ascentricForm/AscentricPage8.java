package ascentricForm;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class AscentricPage8 extends AscentricPage {

	private static final int PAGENUMBER = 8;
	private int app1Width = 50;
	private int app2Width = 320;

	@Override
	public void fillPage() throws IOException, DocumentException {
		setUp(PAGENUMBER);
		int applicants = 2;
		applicant(app1Width);
		if(applicants > 1){
			applicant(app2Width);
		}
		shutDown();
	}

	private void applicant(int width) {
		int nameHeight = 415;
		stamp(width, nameHeight, "Geoff");
		int sigHeight = 365;
		stamp(width, sigHeight, "Geoff");
		int dateHeight = 335;
		String date = "31012014";
		int temp = width+50;
		stamp(temp, dateHeight, date.charAt(0) + "");
		for(int i = 1; i < date.length(); i++){
			if(i%2 == 0 && i < 5){
				stamp(temp+=35, dateHeight, date.charAt(i) + "");
			} else {
				stamp(temp+=20, dateHeight, date.charAt(i) + "");
			}
		}
	}
	

}
