package ascentric;

import java.io.FileOutputStream;
import java.io.IOException;

import pdfFiller.AscentricPage;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class AscentricPage1 implements AscentricPage{
	
	protected PdfReader reader;
	protected PdfStamper stamper;
	protected PdfContentByte canvas;
	protected String client;
	protected String output;
		
	protected int natInsureDepth = 403;
	protected int detailDepth = 500;
	protected int dobDepth = 440;
	protected int firstRow = 100;
	protected int contactDepth = 500;
	protected int contactWidth = 370;
	protected int tickDepth = 560;
	protected int addInfoRow = 45;
	protected int tinDepth = 127;
	protected int pageNumber = 1;
	protected int passInfo = 75;
	protected int cliRef = 295;
	protected int usPerson = 453;
	protected int natInYN = 332;
	
	public AscentricPage1(String client){
		this.client = client;
	}
	
	public String fillPage() throws IOException, DocumentException {
		setUp(pageNumber);
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
		return shutDown();
	}
	
	private void tickBox(String s){
		if(pageNumber == 1){
			if(s.equals("singleApp")){
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 193, tickDepth, 0);
			} else if(s.equals("twoApp")){
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 275, tickDepth, 0);
			} else if(s.equals("joint")){
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 354, tickDepth, 0);
			}
		}
	}
	
	/*
	 * Fills in the selected form with the details of the selected client
	 */
	protected void fillPersonalDetails() {
		
		ColumnText.showTextAligned(canvas,
				Element.ALIGN_LEFT, new Phrase("Mr"),firstRow,detailDepth, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Hoskins"), firstRow, detailDepth-20, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Bob"), firstRow, detailDepth-40, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("1"), firstRow, dobDepth, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("2"), 120, dobDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("3"), 155, dobDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("4"), 175, dobDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("5"), 210, dobDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("6"), 230, dobDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("7"), 250, dobDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("8"), 270, dobDepth, 0);
	}
	
	protected void fillNatInsure(){
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("4"), firstRow, natInsureDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("5"), 120, natInsureDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("6"), 145, natInsureDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("7"), 165, natInsureDepth, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("3"), 185, natInsureDepth, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("4"), 205, natInsureDepth, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("5"), 230, natInsureDepth, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("6"), 250, natInsureDepth, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("J"), 273, natInsureDepth, 0);
	}
	
	protected void fillContactDetails() {
		for(int i = 0 ; i <= 7; i++){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("07917165900"),contactWidth, contactDepth - (i*20), 0);
		}
	}
		
	protected void setUp(int pageNumber) throws IOException, DocumentException {
		 reader = new PdfReader(FORM);
		 output = client + FORM;
		 stamper = new PdfStamper(reader, new FileOutputStream(output));
		 canvas = stamper.getOverContent(pageNumber);
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
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 403, usPerson, 0);
		} else {
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 440, usPerson, 0);
		}
		
	}
	
	protected String shutDown() throws DocumentException, IOException {
		stamper.close();		
		return output;
	}

}
