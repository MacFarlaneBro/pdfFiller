package ascentricForm;

import java.io.FileOutputStream;
import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
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
	private BaseFont bf;

	
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
				new Phrase(text.toUpperCase(),new Font(bf, 10)), 
				x, 
				y,
				0);
		}
	}
	
	/**
	 * Overloading of the above stamp method to simplify the printing of individual characters
	 * @param x - The distance in pixels from the left margin of the page
	 * @param y - The distance in pixels from the bottom of the page
	 * @param text - The char to be printed at the specified x and y location
	 */
	protected void stamp(int x, int y, char text){
	ColumnText
	.showTextAligned(
			canvas, 
			Element.ALIGN_LEFT, 
			new Phrase((text + "").toUpperCase(),new Font(bf, 10)), 
			x, 
			y,
			0);
	}
	
	/**
	 * This stamp method includes a textsize field and is used in cases where the string tends to be much longer
	 * thank the form field it is intended for e.g. the email field in the individual details section
	 * @param x
	 * @param y
	 * @param text
	 * @param textSize
	 */
	protected void stamp(int x, int y, String text, int textSize){
		if(text == null
				|| text.equals("null")){ // if null print nothing
			System.out.println("Not printed: " + text);
		} else {			
			System.out.println("Printed: " + text);
		ColumnText
		.showTextAligned(
				canvas, 
				Element.ALIGN_LEFT, 
				new Phrase(text.toUpperCase(),new Font(bf, textSize)), 
				x, 
				y,
				0);
		}
	}
	
	/**
	 * Prepares the relevant pdf page as indicated by the pagenumber for filling by reading it into the file 
	 * output stream
	 * @param pageNumber - The number of the page to be filled
	 * @throws IOException
	 * @throws DocumentException
	 */
	protected void setUp(int pageNumber) throws IOException, DocumentException {
		this.pageNumber = pageNumber;
		if(pageNumber > 1){
			reader = new PdfReader("temp/" + (pageNumber-1) + FORM);
		} else {
			reader = new PdfReader("temp/" + FORM);
		}
		System.out.println(pageNumber);
		stamper = new PdfStamper(reader, new FileOutputStream("temp/" + pageNumber + FORM));
		canvas = stamper.getOverContent(pageNumber);
		bf = BaseFont.createFont();
	}
	
	public abstract void fillPage(Client theClient) throws IOException, DocumentException;
	public abstract void fillPage(MakeClients theClient) throws IOException, DocumentException;
	
	/**
	 * Closes the access of the application to the page once it has been filled
	 * @throws DocumentException
	 * @throws IOException
	 */
	protected void shutDown() throws DocumentException, IOException {
		stamper.close();
	}
	
	public String getFileName() {
		return pageNumber + FORM;	
	}
	
	/**
	 * If there is no data to be filled on the current page then this method is used to skip
	 * over it
	 * @param pageNumber - The page to be skipped over
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void skipPage(int pageNumber) throws IOException, DocumentException {
		setUp(pageNumber);
		shutDown();
		
	}
}
