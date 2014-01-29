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

public class AscentricPage1 implements Page{
	
	private PdfReader reader;
	private PdfStamper stamper;
	private PdfContentByte canvas;
	
	private int natInsureDepth = 403;
	private int dobDepth = 440;
	private int firstRow = 100;
	private int contactDepth = 370;
	private int tickDepth = 560;
	private int addInfoRow = 45;

	
	public void tickBox(String s){
		if(s.equals("singleApp")){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 193, tickDepth, 0);
		} else if(s.equals("twoApp")){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 275, tickDepth, 0);
		} else if(s.equals("joint")){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 354, tickDepth, 0);
		}

	}
	
	public void fillPersonalDetails() {
		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Mr"), firstRow, 500, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Hoskins"), firstRow, 480, 0);		
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Bob"), firstRow, 460, 0);		
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
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("07917165900"),contactDepth, 500 - (i*20), 0);
		}
	}
		
	public void setUp(String form, String client) throws IOException, DocumentException {
		 reader = new PdfReader(form);
		 stamper = new PdfStamper(reader, new FileOutputStream("John" + form));
		 canvas = stamper.getOverContent(1);
	}

	public void natInsurance(boolean natIn){
		if(!natIn){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("5"), 272, 332, 0);		
		}
	}
	
	public void additionalInfo(String[] info) {
		
		if(info[0]!= null){
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[0]), addInfoRow, 295, 0);
		}
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[1]), addInfoRow, 262, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(info[2]), addInfoRow, 228, 0);
	}
	
	public void usPerson(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	public void shutDown() throws DocumentException, IOException {
		stamper.close();		
	}





}
