package pdfFiller;

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
	protected String client;
	protected String output;
	
	protected void stamp(int x, int y, String text){
		ColumnText.showTextAligned(
				canvas, Element.ALIGN_LEFT, new Phrase(text), x, y, 0);
	}
	
	
	protected void setUp(int pageNumber) throws IOException, DocumentException {
		 reader = new PdfReader(FORM);
		 output = client + FORM;
		 stamper = new PdfStamper(reader, new FileOutputStream(output));
		 canvas = stamper.getOverContent(pageNumber);
	}
	
	public abstract String fillPage() throws IOException, DocumentException;

}
