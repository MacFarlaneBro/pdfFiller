package pdfFiller;

import java.io.IOException;
import ascentric.*;
import com.itextpdf.text.DocumentException;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Ascentric ascentric = new Ascentric();
		
		//Filling page 1
		String next = ascentric.fillIt(new AscentricPage1("Bob Hoskins"));
		//filling page 2
		next = ascentric.fillIt(new AscentricPage2(next));
		//filling page 3
		next = ascentric.fillIt(new AscentricPage3(next));
		
	}

}
