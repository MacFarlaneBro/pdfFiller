package pdfFiller;

import java.io.IOException;
import ascentric.*;
import com.itextpdf.text.DocumentException;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		AscentricForm ascentric = new AscentricForm();
		
		//Filling page 1
		ascentric.fillIt("Bob Hoskins");
		
	}

}
