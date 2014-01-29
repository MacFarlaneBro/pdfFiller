package pdfFiller;

import java.io.IOException;

import com.itextpdf.text.DocumentException;



public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		FillForm formfiller = new Ascentric();
		
		formfiller.setUp("AscentricForm.pdf", "John");
		
		formfiller.fillPersonalDetails();
				
		formfiller.fillNatInsure();
		
		formfiller.fillContactDetails();
		
		formfiller.shutDown();
		
	}

}
