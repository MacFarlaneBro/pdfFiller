package pdfFiller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class HelloWorldDirect {
	
	public static final String RESULT = "editedDocument.pdf";
	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		document.open();
		PdfContentByte canvas = writer.getDirectContentUnder();
		writer.setCompressionLevel(0);
		canvas.saveState();
		canvas.beginText();
		canvas.moveText(26, 788);
		canvas.setFontAndSize(BaseFont.createFont(), 12);
		canvas.showText("Hello World");
		canvas.endText();
		canvas.restoreState();
		document.close();
	}
}
