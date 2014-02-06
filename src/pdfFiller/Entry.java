package pdfFiller;

import java.io.IOException;
import java.sql.ResultSet;

import ascentric.AscentricForm;

import com.itextpdf.text.DocumentException;

import databaseAccess.ChinookDB;
import databaseAccess.GetDatabase;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException{
		
		GetDatabase db = new ChinookDB();
		
		ResultSet formInfo = db.getDatabase("AC/DC");
		
		AscentricForm ascentric = new AscentricForm();
		
		ascentric.fillIt("Bob Hoskins");
		
	}
}
