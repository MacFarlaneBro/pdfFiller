package ascentric;

import java.io.FileOutputStream;
import java.io.IOException;

import pdfFiller.AscentricPage;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class AscentricPage1 extends AscentricPage{
	
	public static int pageNumber = 1;
		
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
	
	private void setUp() throws IOException, DocumentException {
		 reader = new PdfReader(FORM);
		 stamper = new PdfStamper(reader, new FileOutputStream(pageNumber + FORM));
		 canvas = stamper.getOverContent(pageNumber);
	}

	public void fillPage() throws IOException, DocumentException {
		setUp();
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
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("07917165900"),contactWidth, contactDepth - (i*20), 0);
		}
	}

	protected void natInsurance(boolean natIn){
		if(!natIn){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 272, natInYN, 0);		
		}
	}
	
	protected void additionalInfo(String[] info) {
		
		if(info[0]!= null){
			//ClientReference
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), addInfoRow, cliRef, 0);
		}
		//Nationality
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[1]), addInfoRow, cliRef-33, 0);
		//Domiciled
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[2]), addInfoRow, cliRef-67, 0);
		//PassportCity
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[4]), 150, passInfo+20, 0);
		//Passport Country
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[5]), 150, passInfo, 0);
		
		//TaxInfoNumber input with loop in separate method
		if(info[3]!= null){
			tinNumber(info[3]);
		}
	}
	
	protected void tinNumber(String tin) {
		for(int i = 0; i < tin.length(); i++){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("" + tin.charAt(i)),addInfoRow+(i*12)+1, tinDepth, 0);
		}
		
	}

	protected void usPerson(boolean b) {
		if(b){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 403, usPersonDepth, 0);
		} else {
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 440, usPersonDepth, 0);
		}
		
	}

}
