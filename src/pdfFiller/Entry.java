package pdfFiller;

import java.io.IOException;
import java.sql.SQLException;

import ascentricClientDetails.ClientInformation;
import ascentricForm.AscentricForm;

import com.itextpdf.text.DocumentException;

import databaseAccess.ChinookDB;
import databaseAccess.GetDatabase;

public class Entry {

	
	public static void main(String[] args) throws DocumentException, IOException, SQLException{
		
		GetDatabase db = new ChinookDB();
		ClientInformation theClient = new ClientInformation("Bob Harris");
		
		String[] formInfo = db.fetchInfoUsingID(58);
				
		for(int i = 0; i < formInfo.length; i++){
			System.out.println(formInfo[i]);
			i++;
		}
		
		AscentricForm ascentric = new AscentricForm();
		
		
		ascentric.fillIt(theClient);
		
	}
}
