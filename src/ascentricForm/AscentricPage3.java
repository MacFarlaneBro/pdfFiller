package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.IndividualDetails;
import ascentricClientDetails.MakeClients;
import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;

import com.itextpdf.text.DocumentException;

public class AscentricPage3 extends AscentricPage{
	
	private static final int PAGENUMBER = 3;
	private int jointAccWidth = 527;
	private int secondAppWidth = jointAccWidth - 63; 
	private int singleAppWidth = jointAccWidth - 126;

	private int noAccDepth = 677;
	private int enqOnlyDepth = 657;	
	private int tradAccDepth = 637;
	private int famGroupDepth = 566;
	
	protected int firstSingleDepth = 424;
	protected int giaDepth = 309;
	protected int wrapWidth = 127;
	protected int sasDepth = 287;
	protected int thirdPartyWidth = 50;
	protected int thirdPartyDepth = 228;
	
	private String joint = "joint";
	private String singleApp = "single";
	private String second = "second";
	

	public void fillPage(Client theClient) throws IOException, DocumentException {
		this.theClient = theClient;
		IndividualDetails id = theClient.getIndividualDetails();
		ProductDetails pd = theClient.getProductDetails();
		accessRights(id);
		familyGroups(id.isFamilyGroup(),
				id.isExistingAccount());
		if(theClient.getClientType().equals("single")){
			System.out.println("The client type equalled first");
			applicantWrapperInfo(pd);
			thirdParty(pd);
		}		
	}

	private void familyGroups(boolean moreThan1, boolean ascLink) {
		
		if(moreThan1) stamp(jointAccWidth, famGroupDepth, "X");
		if(ascLink){
			stamp(jointAccWidth, famGroupDepth-23, "X");
			stamp(315, famGroupDepth-67, theClient.getIndividualDetails().getClientRef());
		}
		
	}
	
	//Checks the access rights that each client possesses then passes the client type through to a stamping method
	//to fill in the various tickboxes.
	private void accessRights(IndividualDetails id) {
		if(id.isOnlineAccess()){//if the client has online access rights
			onlineAccess(theClient.getClientType());
		}//cdsa
		if(id.isEnquiryOnly()){//if the client has enquiry only rights
			enquiryOnly(theClient.getClientType());
		}
		if(id.isTradingAccess()){//if the client has trading access rights
			tradingAccess(theClient.getClientType());
		}
	}
	
	//fills in the trading Access checkboxes
	private void tradingAccess(String tradAcc) {
		if(tradAcc.equals(joint)){
			stamp(jointAccWidth, tradAccDepth, "X");
		} 
		if(tradAcc.equals(singleApp)){
			stamp(singleAppWidth, tradAccDepth, "X");
		} 
		if(tradAcc.equals(second )) {
			stamp(secondAppWidth, tradAccDepth, "X");
		}
	}
	
	//fills in the enquiry only checkboxes
	private void enquiryOnly(String enqOnly) {
		if(enqOnly.equals(joint)){
			stamp(jointAccWidth, enqOnlyDepth, "X");
		}
		if(enqOnly.equals(singleApp)){
			stamp(singleAppWidth, enqOnlyDepth, "X");
		} 
		if(enqOnly.equals(second)) {
			stamp(secondAppWidth, enqOnlyDepth, "X");
		}
	}
	
	//fills in the online access checkboxes
	private void onlineAccess(String onAcc) {
		if(onAcc.equals(joint)){
			stamp(jointAccWidth, noAccDepth, "X");
		} 
		if(onAcc.equals(singleApp)){
			stamp(singleAppWidth, noAccDepth, "X");
		} 
		if(onAcc.equals(second)) {
			stamp(secondAppWidth, noAccDepth, "X");
		}
	}

	protected void applicantWrapperInfo(ProductDetails pd) {
		//fill platform account name field
		stamp(315, firstSingleDepth , pd.getPlatformAccountNameChoice());
		
		//indicates which wrappers the client wishes to open
		wrapper(pd.getGeneralInvestmentAccount(), pd.getStocksAndSharesISA());
	}

	protected void wrapper(Wrapper generalInvestment, Wrapper stocksAndShares) {
		if(generalInvestment!= null)fillWrappers(generalInvestment, true);
		if(stocksAndShares!= null)fillWrappers(stocksAndShares, false);
		
	}

	protected void fillWrappers(Wrapper wrapper, boolean isGeneralInvestment) {
		int yAxis;
		if(isGeneralInvestment){
			yAxis = giaDepth;
		} else {
			yAxis = sasDepth ;
		}
		//Wrappers
		stamp(wrapWidth, yAxis, "X");
		//Cash
		stamp(wrapWidth+25, yAxis,"" + wrapper.getCash());
		//Source of funds
		stamp(wrapWidth+77, yAxis, wrapper.getSourceOfFunds());
		//Transfer/Approx
		stamp(wrapWidth+150, yAxis, "" + wrapper.getCashToBeTransferred());
		//Transfer/Assets
		stamp(wrapWidth+212, yAxis,"" + wrapper.getAssetsToBeReregistered());
		//Reserve Account
		stamp(wrapWidth+260, yAxis, wrapper.getReserverAccount());
		
		//AdvWrapper
		if(wrapper.isAdvisoryWrapper()){
			stamp(wrapWidth+350, yAxis, "X");
		}
		
		//DiscWrapper
		if(wrapper.isDiscretionaryWrapper()){
			stamp(wrapWidth+395, yAxis, "X");
		}
	}

	protected void thirdParty(ProductDetails pd) {
		//Third Party Name
		stamp(thirdPartyWidth, thirdPartyDepth, pd.getThirdPartyProductAccounts());
		//Amount to be received
		stamp(thirdPartyWidth+150, thirdPartyDepth,"" + pd.getAmountToBeReceived());
		//source of funds
		stamp(thirdPartyWidth+200, thirdPartyDepth, pd.getSourceOfFunds());
		
		//Advisory Wrapper
		if(pd.isAdvisoryWrapper()){
			stamp(thirdPartyWidth+340, thirdPartyDepth, "X");
		}
		//Discretionary Wrapper
		if(pd.isDiscretionaryWrapper()){
			stamp(thirdPartyWidth+440, thirdPartyDepth, "X");
		}

	}
	
	/**
	 * As information from all three potential clients is necessary on this page and the printing mechanism
	 * wipes the page every time setUp is run, this additional fillPage method is used
	 * 
	 * @param theClient
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void fillPage(MakeClients theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		
		Client[] clients = {theClient.getFirstClient(),
				theClient.getSecondClient(),
				theClient.getJointAccount()};
		for(Client current:clients){
			if(current != null) fillPage(current);
		}
		shutDown();
	}
}
