package ascentricForm;

import java.io.File;
import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;

/*
 * This Class is used to collate the necessary information on each client to successfully fill in an ascentric form
 * the information collected from SQL is then used by each of the pages in turn to from 
 */
public class AscentricForm{
	
	private AscentricPage page;
	
	/*
	 * Fills the page chosen by the calling class
	 * @return String address of the form with corresponding page filled
	 */
	public void fillIt(MakeClients theClient, File file) throws IOException, DocumentException {
		
		page = new AscentricPage1();
		
		page.fillPage(theClient.getFirstClient());
		
		//If a second Client exists then the relevant information is filled in.
		if(theClient.getSecondClient()!=null){
			page = new AscentricPage2();
			page.fillPage(theClient.getSecondClient());
		} else {
			page = new AscentricPage2();
			page.fillPage(new Client("second"));
		}
		
		page = new AscentricPage3();
		page.fillPage(theClient.getFirstClient());//Filling in the separate access rights applying to each of the applicants if
		if(theClient.getSecondClient()!= null){//they exist
			page.fillPage(theClient.getSecondClient());
		}
		if(theClient.getJointAccount()!= null){
			page.fillPage(theClient.getJointAccount());
		}
		
		page = new AscentricPage4();
		page.fillPage(theClient);
		
		page = new AscentricPage5();
		page.fillPage(theClient);
		
		page = new AscentricPage6();
		page.fillPage(theClient);
		
		page = new AscentricPage7();
		page.fillPage(theClient.getFirstClient());
		
		page = new AscentricPage8();
		page.fillPage(theClient);
		
		System.out.println("finished printing");
	}

}
