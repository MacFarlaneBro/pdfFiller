package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ProductDetails;

import com.itextpdf.text.DocumentException;

/**
 * 
 * @author charliebrodie
 *
 * As this class contains two of the same forms as appear on AscentricPage 3 but with different depths all it needed was changing of the depth
 * values and a reduction in the total number of stamp methods called.
 */
public class AscentricPage4 extends AscentricPage3{
	
	private int pageNumber = 4;
	// the increase in depth required to access the second applicant form
	private int secondApplicantDepthIncrease = 263;
	// the decrease in depth required to access the joint applicant form
	private int jointApplicantDepthDecrease = -300;
	
	
	@Override
	public void fillPage(Client theClient) throws IOException, DocumentException{
		setUp(pageNumber);
		
		//Setting the secondClients product details
		ProductDetails pd = theClient.getProductDetails();
		
		//These three methods fill in the second Applicant product section
		changeDepth(secondApplicantDepthIncrease);
		applicantWrapperInfo(pd);
		thirdParty(pd);
		
		//Setting the joint account product details
		pd = theClient.getProductDetails();
		//These three methods fill in the joint Applicant form section
		changeDepth(jointApplicantDepthDecrease);
		applicantWrapperInfo(pd);
		thirdParty(pd);
		shutDown();
	}
	
	private void changeDepth(int applicant){
		
		thirdPartyDepth += applicant;
		//This is a fairly dreadful workaround whereby as there is no general investment account option for joint accounts
		//The line needs to not exist, therefore it is jettisoned off the page by adding 1000 to its depth
		if(applicant > 0){ 
			giaDepth += applicant;
			firstSingleDepth += applicant;
		} else {
			giaDepth +=1000;
			firstSingleDepth += applicant-15;
		}		
			sasDepth += applicant;
	}
	
	/**
	 * As information from all three potential clients is necessary on this page and the printing mechanism
	 * wipes the page with every run, this additional fillPage method is used
	 * 
	 * @param theClient
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void fillPage(ClientHolder theClient) throws IOException, DocumentException {
		setUp(pageNumber);

		Client[] clients = {theClient.getFirstClient(),
				theClient.getSecondClient(),
				theClient.getJointAccount()};
		for(Client current:clients){
			if(current != null) fillPage(current);
		}
		shutDown();
	}

}
