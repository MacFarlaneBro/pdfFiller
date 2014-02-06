package pdfFiller;

import java.io.IOException;
import java.sql.ResultSet;

import ascentric.*;

import com.itextpdf.text.DocumentException;

import databaseAccess.*;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		GetDatabase db = new ChinookDB();
		
		ResultSet formInfo = db.getDatabase();
		
		AscentricForm ascentric = new AscentricForm();
		
		ascentric.fillIt("Bob Hoskins");
		
	}

}
