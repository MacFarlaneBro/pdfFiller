package pdfFiller;

import java.io.IOException;
import ascentric.*;
import com.itextpdf.text.DocumentException;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Ascentric ascentric = new Ascentric();
		
		String next = ascentric.fillIt(new AscentricPage1("Bob Hoskins"));
		
		ascentric.fillIt(new AscentricPage2(next));
		
	}

}
