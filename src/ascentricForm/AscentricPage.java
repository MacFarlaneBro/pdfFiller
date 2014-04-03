package ascentricForm;

import java.io.FileOutputStream;
import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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
public abstract class AscentricPage {
	
	public static final String FORM = "AscentricForm.pdf";
	protected PdfReader reader;
	protected PdfStamper stamper;
	protected PdfContentByte canvas;
	protected Client theClient;
	
	protected void stamp(int x, int y, String text){
		System.out.println(text);
		System.out.println(canvas);
		ColumnText
		.showTextAligned(
				canvas, 
				Element.ALIGN_LEFT, 
				new Phrase(text), 
				x, 
				y,
				0);
	}
	
	protected void setUp(int pageNumber) throws IOException, DocumentException {
		if(pageNumber > 1){
		 reader = new PdfReader(pageNumber-1 + FORM);
		} else {
			reader = new PdfReader(FORM);
		}
		System.out.println("pageNumber: "+pageNumber);
		 stamper = new PdfStamper(reader, new FileOutputStream(pageNumber + FORM));
		 canvas = stamper.getOverContent(pageNumber);

	}
	
	public abstract void fillPage(Client theClient) throws IOException, DocumentException;
	
	public void fillPage(MakeClients theClient) throws IOException, DocumentException{}
	
	protected void shutDown() throws DocumentException, IOException {
		
		stamper.close();
		System.out.println("afterStamp");

	}
}
