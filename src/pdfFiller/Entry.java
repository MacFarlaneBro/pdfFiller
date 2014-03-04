package pdfFiller;

import java.io.IOException;
import java.sql.SQLException;

import ascentricClientDetails.ClientFactory;
import ascentricClientDetails.MakeClients;
import ascentricForm.AscentricForm;

import com.itextpdf.text.DocumentException;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException, SQLException{
		
		MakeClients theClient = new ClientFactory();
				
		AscentricForm ascentric = new AscentricForm();
		
		
		ascentric.fillIt(theClient);
		
	}
}
