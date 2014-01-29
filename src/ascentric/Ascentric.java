package ascentric;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

/*
 * This Class is used to collate the necessary information on each client to successfully fill in an ascentric form
 * the information collected from SQL is then used by each of the pages in turn to from 
 */
public class Ascentric{
	
	private PersonalInfo pageInPlay;
	
	public void fillIt() throws IOException, DocumentException{
		
		personalInfo("JohnFile");
		
		String altered = pageInPlay.shutDown();
		
	}

    private void personalInfo(String fileName) throws IOException, DocumentException{
    	

    	pageInPlay = new AscentricPersonalInfo1(fileName);
    	
    	pageInPlay.setUp("AscentricForm.pdf", "John");
    	
    	pageInPlay.tickBox("singleApp");
    	pageInPlay.tickBox("twoApp");
    	pageInPlay.tickBox("joint");
    	
		pageInPlay.fillPersonalDetails();
				
		pageInPlay.fillNatInsure();
		
		pageInPlay.fillContactDetails();
		
		pageInPlay.natInsurance(false);
		
		pageInPlay.usPerson(false);
		
		String[] info = {"123456", "English", "Ukraine", "12826294291234322842", "London", "Guinea-Bissau"};
		
		pageInPlay.additionalInfo(info);
		
		PersonalInfo jam = new AscentricPersonalInfo2(fileName);
    }
    
    
    
}
