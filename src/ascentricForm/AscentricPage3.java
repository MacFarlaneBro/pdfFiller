package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientInformation;
import ascentricClientDetails.IndividualDetails;

import com.itextpdf.text.DocumentException;

public class AscentricPage3 extends AscentricPage{
	
	private int jointAccWidth = 527;
	private int secondAppWidth = jointAccWidth - 63; 
	private int singleAppWidth = jointAccWidth - 126;

	private int noAccDepth = 677;
	private int enqOnlyDepth = 657;	
	private int tradAccDepth = 637;
	private int famGroupDepth = 566;
	
	private int pageNumber = 3;
	protected int firstSingleDepth = 424;
	protected int giaDepth = 309;
	protected int wrapWidth = 127;
	protected int sasDepth = 287;
	protected int thirdPartyWidth = 50;
	protected int thirdPartyDepth = 228;
	
	private String joint = "joint";
	private String singleApp = "single";
	

	public void fillPage(Client theClient) throws IOException, DocumentException {
		this.theClient = theClient;
		setUp(pageNumber);
		accessRights(theClient.getIndividualDetails());
		familyGroups(true, true);
		firstOrSingle();
		thirdParty();
		shutDown();
	}

	private void familyGroups(boolean moreThan1, boolean ascLink) {
		
		if(moreThan1) stamp(jointAccWidth, famGroupDepth, "X");
		if(ascLink){
			stamp(jointAccWidth, famGroupDepth-23, "X");
			stamp(315, famGroupDepth-67, "920292");
		}
		
	}
	
	//Checks the access rights that each client possesses then passes the client type through to a stamping method
	//to fill in the various tickboxes.
	private void accessRights(IndividualDetails id) {
		if(id.isOnlineAccess()){//if the client has online access rights
			onlineAccess(theClient.getClientType());
		}
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
		} else if(tradAcc.equals(singleApp)){
			stamp(singleAppWidth, tradAccDepth, "X");
		} else {
			stamp(secondAppWidth, tradAccDepth, "X");
		}
	}
	
	//fills in the enquiry only checkboxes
	private void enquiryOnly(String enqOnly) {
		if(enqOnly.equals(joint)){
			stamp(jointAccWidth, enqOnlyDepth, "X");
		} else if(enqOnly.equals(singleApp)){
			stamp(singleAppWidth, enqOnlyDepth, "X");
		} else {
			stamp(secondAppWidth, enqOnlyDepth, "X");
		}
	}
	
	//fills in the online access checkboxes
	private void onlineAccess(String onAcc) {
		if(onAcc.equals(joint)){
			stamp(jointAccWidth, noAccDepth, "X");
		} else if(onAcc.equals(singleApp)){
			stamp(singleAppWidth, noAccDepth, "X");
		} else {
			stamp(secondAppWidth, noAccDepth, "X");
		}
	}

	protected void firstOrSingle() {
		//fill platform account name field
		stamp(315, firstSingleDepth , "Roger Rabbit");
		
		//indicates which wrappers the client wishes to open
		wrapper(true, true);
	}

	protected void wrapper(boolean gia, boolean sas) {
		if(gia)fillWrappers(1);
		if(sas)fillWrappers(2);
		
	}

	protected void fillWrappers(int lineNumber) {
		int yAxis;
		if(lineNumber ==1){
			yAxis = giaDepth;
		} else {
			yAxis = sasDepth ;
		}
		//Wrappers
		stamp(wrapWidth, yAxis, "X");
		//Cash
		stamp(wrapWidth+30, yAxis, "999");
		//Source of funds
		stamp(wrapWidth+85, yAxis, "999");
		//Transfer/Approx
		stamp(wrapWidth+150, yAxis, "999");
		//Transfer/Assets
		stamp(wrapWidth+215, yAxis, "999");
		//Reserve Account
		stamp(wrapWidth+275, yAxis, "999");
		//AdvWrapper
		stamp(wrapWidth+350, yAxis, "X");
		//DiscWrapper
		stamp(wrapWidth+395, yAxis, "X");
	}

	protected void thirdParty() {
		//Third Party Name
		stamp(thirdPartyWidth, thirdPartyDepth, "Dave");
		//Amount to be received
		stamp(thirdPartyWidth+150, thirdPartyDepth, "99");
		//source of funds
		stamp(thirdPartyWidth+200, thirdPartyDepth, "cheques");
		//Advisory Wrapper
		stamp(thirdPartyWidth+340, thirdPartyDepth, "X");
		//Discretionary Wrapper
		stamp(thirdPartyWidth+440, thirdPartyDepth, "X");

	}
}
