package ascentric;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class AscentricPage1 extends AscentricPage{
	
	public static final int PAGENUMBER = 1;
		
	protected int natInsureDepth = 403;
	protected int detailDepth = 500;
	protected int dobDepth = 440;
	protected int firstRow = 100;
	protected int contactDepth = 500;
	protected int contactWidth = 370;
	protected int tickDepth = 560;
	protected int addInfoRow = 45;
	protected int tinDepth = 127;
	protected int passInfo = 75;
	protected int cliRef = 295;
	protected int usPersonDepth = 305;
	protected int natInYN = 332;

	public void fillPage() throws IOException, DocumentException {
		setUp(PAGENUMBER);
    	tickBox("singleApp");
    	tickBox("twoApp");
    	tickBox("joint");
		fillPersonalDetails();	
		fillNatInsure();
		fillContactDetails();
		natInsurance(false);
		usPerson(false);
		String[] info = {"123456", "English", "Ukraine", "12826294291234322842", "London", "Guinea-Bissau"};
		additionalInfo(info);
		shutDown();
	}
	
	private void tickBox(String s){
		if(s.equals("singleApp")){
			stamp(193, tickDepth, "X");
		} else if(s.equals("twoApp")){
			stamp(275, tickDepth, "X");
		} else if(s.equals("joint")){
			stamp(354, tickDepth, "X");
		}
	}
	
	/*
	 * Fills in the selected form with the details of the selected client
	 */
	protected void fillPersonalDetails() {
		String dob = "12345678";
		int extraDistance = 0;
		//Title
		stamp(firstRow, detailDepth, "Mr");
		//First Name
		stamp(firstRow, detailDepth-20, "Bob");
		//Surname
		stamp(firstRow, detailDepth-40, "Hoskins");
		
		//Date of Birth stamping loop
		stamp(firstRow, dobDepth, "" + dob.charAt(0));
		for(int i = 1; i < dob.length(); i++){
			if(i == 2 || i == 4){
				extraDistance+=35;
			} else {
				extraDistance+=20;
			}
			stamp(firstRow + extraDistance, dobDepth, "" + dob.charAt(i));
		}
	}
	
	protected void fillNatInsure(){
		//This string is also just to test positioning, I will obviously need to generalise it when I hook up the SQL
		String nin = "45673456J";
		stamp(firstRow, natInsureDepth, "" + nin.charAt(0));
		for(int i = 1; i < nin.length(); i++){
			if(i %2 == 0 && i < 9){
				stamp(firstRow+=23, natInsureDepth, "" + nin.charAt(i));
			} else {
				stamp(firstRow+=20, natInsureDepth, "" + nin.charAt(i));
			}
		}
	}
	
	protected void fillContactDetails() {
		for(int i = 0 ; i <= 7; i++){
			stamp(contactWidth, contactDepth - (i*20), "07197165900");
		}
	}

	protected void natInsurance(boolean natIn){
		if(!natIn){
			stamp(272, natInYN, "X");
		}
	}
	
	protected void additionalInfo(String[] info) {
		
		if(info[0]!= null){
			//ClientReference
			stamp(addInfoRow, cliRef, "X");
		}
		//Nationality
		stamp(addInfoRow, cliRef-33, info[1]);
		//Domiciled
		stamp(addInfoRow, cliRef-67, info[2]);
		//PassportCity
		stamp(150, passInfo+20, info[4]);
		//Passport Country
		stamp(150, passInfo, info[5]);		
		//TaxInfoNumber input with loop in separate method
		if(info[3]!= null){
			tinNumber(info[3]);
		}
	}
	
	protected void tinNumber(String tin) {
		for(int i = 0; i < tin.length(); i++){
			stamp(addInfoRow+(i*12)+1, tinDepth, "" + tin.charAt(i));
		}
		
	}

	protected void usPerson(boolean b) {
		if(b){
			stamp(403, usPersonDepth, "X");
		} else {
			stamp(440, usPersonDepth, "X");
		}		
	}
}
