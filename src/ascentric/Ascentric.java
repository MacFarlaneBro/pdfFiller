package ascentric;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

/*
 * This Class is used to collate the necessary information on each client to successfully fill in an ascentric form
 * the information collected from SQL is then used by each of the pages in turn to from 
 */
public class Ascentric{
	
	private AscentricPage1 pageInPlay;
	
    public void fillPage1() throws IOException, DocumentException{
    	
    	pageInPlay = new AscentricPage1();
    	
    	pageInPlay.setUp("AscentricForm.pdf", "John");
    	
    	pageInPlay.tickBox("singleApp");
    	pageInPlay.tickBox("twoApp");
    	pageInPlay.tickBox("joint");
    	
		pageInPlay.fillPersonalDetails();
				
		pageInPlay.fillNatInsure();
		
		pageInPlay.fillContactDetails();
		
		pageInPlay.natInsurance(false);
		
		pageInPlay.usPerson(true);
		
		String[] info = {"123456", "English", "Ukraine"};
		
		pageInPlay.additionalInfo(info);
		
		pageInPlay.shutDown();
    }
    
    
}
