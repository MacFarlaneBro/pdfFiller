package ascentricForm;

import java.io.FileOutputStream;
import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 
 * @author charliebrodie
 *
 * This is the default template for all the remaining ascentric pages, containing the fundamentals necessary to fill
 * in all the forms with none of the implementation details for doing so.
 * 
 * Each of the pages contains a series of integer values ending in either width, indicating their distance from the left margin of the page
 * or depth, indicating their distance from the bottom of the page.
 */
public abstract class AscentricPage{
	
	public static final String FORM = "AscentricForm.pdf";
	protected PdfReader reader;
	protected PdfStamper stamper;
	protected PdfContentByte canvas;
	protected Client theClient;
	protected int pageNumber;
	
	/**
	 * Prints text onto the pdf document specified by the FORM field and the page specified by the pagenumber field
	 * @param x - The distance in pixels from the left margin of the page
	 * @param y - The distance in pixels from the bottom of the page
	 * @param text - The text to be printed at the specified x and y location
	 */
	protected void stamp(int x, int y, String text){
		if(text == null
				|| text.equals("null")){ // if null print nothing
			System.out.println("Not printed: " + text);
		} else {			
			System.out.println("Printed: " + text);
		ColumnText
		.showTextAligned(
				canvas, 
				Element.ALIGN_LEFT, 
				new Phrase(text), 
				x, 
				y,
				0);
		}
	}
	
	/**
	 * Overloading of the above stamp method to remove the costly concatenation of a null string prior to char printing
	 * @param x - The distance in pixels from the left margin of the page
	 * @param y - The distance in pixels from the bottom of the page
	 * @param text - The char to be printed at the specified x and y location
	 */
	protected void stamp(int x, int y, char text){
	ColumnText
	.showTextAligned(
			canvas, 
			Element.ALIGN_LEFT, 
			new Phrase(text + ""), 
			x, 
			y,
			0);
	}
	
	protected void stamp(int x, int y, String text, int textSize){
		PdfContentByte tempCanvas = canvas;
		tempCanvas.setFontAndSize(
				(new Font(
						FontFamily.HELVETICA))
						.getBaseFont()
						, textSize);
		if(text == null
				|| text.equals("null")){ // if null print nothing
			System.out.println("Not printed: " + text);
		} else {			
			System.out.println("Printed: " + text);
		ColumnText
		.showTextAligned(
				tempCanvas, 
				Element.ALIGN_LEFT, 
				new Phrase(text), 
				x, 
				y,
				0);
		}
	}
	
	protected void setUp(int pageNumber) throws IOException, DocumentException {
		this.pageNumber = pageNumber;
		if(pageNumber > 1){
			reader = new PdfReader("temp/" + (pageNumber-1) + FORM);
		} else {
			reader = new PdfReader("temp/" + FORM);
		}
		 stamper = new PdfStamper(reader, new FileOutputStream("lib/" + pageNumber + FORM));
		 canvas = stamper.getOverContent(pageNumber);
	}
	
	public abstract void fillPage(Client theClient) throws IOException, DocumentException;
	public abstract void fillPage(MakeClients theClient) throws IOException, DocumentException;
		
	protected void shutDown() throws DocumentException, IOException {
		
		stamper.close();
	}

	public String getFileName() {
		return pageNumber + FORM;	
	}

	public void skipPage(int pageNumber) throws IOException, DocumentException {
		setUp(pageNumber);
		shutDown();
		
	}
}
