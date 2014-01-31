package pdfFiller;

import java.io.IOException;
import ascentric.*;
import com.itextpdf.text.DocumentException;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		Ascentric ascentric = new Ascentric();
		
		//Filling page 1
		ascentric.fillIt("Bob Hoskins");
		
	}

}
