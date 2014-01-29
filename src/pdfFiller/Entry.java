package pdfFiller;

import java.io.IOException;
import ascentric.Ascentric;
import com.itextpdf.text.DocumentException;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Ascentric ascentric = new Ascentric();
		
		ascentric.fillIt();
		
		
	}

}
