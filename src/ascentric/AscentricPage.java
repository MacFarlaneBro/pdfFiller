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

public abstract class AscentricPage {
	
	public static final String FORM = "AscentricForm.pdf";
	protected PdfReader reader;
	protected PdfStamper stamper;
	protected PdfContentByte canvas;
	
	protected void stamp(int x, int y, String text){
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(text), x, y, 0);
	}
	
	protected void setUp(int pageNumber) throws IOException, DocumentException {
		if(pageNumber > 1){
		 reader = new PdfReader(pageNumber-1 + FORM);
		} else {
			reader = new PdfReader(FORM);
		}
		 stamper = new PdfStamper(reader, new FileOutputStream(pageNumber + FORM));
		 canvas = stamper.getOverContent(pageNumber);
	}
	
	public abstract void fillPage() throws IOException, DocumentException;
	
	protected void shutDown() throws DocumentException, IOException {
		
		System.out.println("beforeStamp");
		stamper.close();
		System.out.println("afterStamp");

	}

}
