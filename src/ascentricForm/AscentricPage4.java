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
	
	private static final int PAGENUMBER = 4;
	private int nameS = 690;
	private int giaS = nameS-120;
	private int sasS = nameS-143;
	private int thirdS = nameS-200;
	
	private int nameJ = 370;
	private int giaJ = nameJ-120;
	private int sasJ = nameJ-143;
	private int thirdJ = nameJ-180;
	
	
	@Override
	public void fillPage(Client theClient) throws IOException, DocumentException{
		//Setting the secondClients product details
		ProductDetails pd = theClient.getProductDetails();
		//These three methods fill in the second Applicant product section
		if(theClient.getClientType().equals("second")){
			sasDepth = sasS;
			giaDepth = giaS;
			firstSingleDepth = nameS;
			thirdPartyDepth = thirdS;
		} else if(theClient.getClientType().equals("joint")){
			sasDepth = sasJ;
			giaDepth = giaJ;
			firstSingleDepth = nameJ;
			thirdPartyDepth = thirdJ;
		}
		applicantWrapperInfo(pd);
		thirdParty(pd);
	}
	
	/**
	 * As information from two potential client states is necessary on this page and the printing mechanism
	 * wipes the page with every run, this additional fillPage method is used
	 * 
	 * @param theClient
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void fillPage(ClientHolder theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		fillPage(theClient.getSecondClient());
		fillPage(theClient.getJointAccount());
		shutDown();
	}

}
