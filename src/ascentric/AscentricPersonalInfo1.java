package ascentric;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class AscentricPersonalInfo1 implements PersonalInfo{
	
	private PdfReader reader;
	private PdfStamper stamper;
	private PdfContentByte canvas;
	private String fileName;
		
	private int natInsureDepth = 403;
	private int detailDepth = 500;
	private int dobDepth = 440;
	private int firstRow = 100;
	private int contactDepth = 500;
	private int contactWidth = 370;
	private int tickDepth = 560;
	private int addInfoRow = 45;
	private float tinDepth = 127;
	private int pageNumber = 1;

	public AscentricPersonalInfo1(String fileName){
		this.fileName = fileName;
	}
	
	public void tickBox(String s){
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
	
	public void fillPersonalDetails() {
		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Mr"), firstRow, detailDepth, 0);
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
	
	public void fillNatInsure(){
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
	
	public void fillContactDetails() {
		for(int i = 0 ; i <= 7; i++){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("07917165900"),contactWidth, contactDepth - (i*20), 0);
		}
	}
		
	public void setUp(String form, String client) throws IOException, DocumentException {
		 reader = new PdfReader(form);
		 fileName = "John" + form;
		 stamper = new PdfStamper(reader, new FileOutputStream(fileName));
		 canvas = stamper.getOverContent(pageNumber);
	}

	public void natInsurance(boolean natIn){
		if(!natIn){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("5"), 272, 332, 0);		
		}
	}
	
	public void additionalInfo(String[] info) {
		
		if(info[0]!= null){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), addInfoRow, 295, 0);
		}
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[1]), addInfoRow, 262, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[2]), addInfoRow, 228, 0);
		
		//PassportCity
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[4]), 150, 95, 0);
		//Passport Country
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[5]), 150, 75, 0);
		
		//TaxInfoNumber input with loop in separate method
		if(info[3]!= null){
			tinNumber(info[3]);
		}
	}
	
	private void tinNumber(String tin) {
		for(int i = 0; i < tin.length(); i++){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("" + tin.charAt(i)),addInfoRow+(i*12)+1,  tinDepth, 0);
		}
		
	}

	public void usPerson(boolean b) {
		if(b){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 403, 305, 0);
		} else {
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 440, 305, 0);
		}
		
	}
	
	public String shutDown() throws DocumentException, IOException {
		stamper.close();		
		return fileName;
	}


}
