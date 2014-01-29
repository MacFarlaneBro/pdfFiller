package pdfFiller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class HelloWorldColumn {
	
	public static final String RESULT = "editedDocument.pdf";
	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		document.open();
		writer.setCompressionLevel(0);
		Phrase hello = new Phrase("Hello World");
		PdfContentByte canvas = writer.getDirectContentUnder();
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, hello, 36, 788, 0);
		document.close();
	}
}

