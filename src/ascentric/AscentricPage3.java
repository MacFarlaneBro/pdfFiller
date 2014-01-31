package ascentric;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class AscentricPage3 extends AscentricPage{
	
	private String joint = "joint";
	private int jointAccWidth = 527;

	private String secondApp = "secondApp";
	private int secondAppWidth = jointAccWidth - 63;
	
	private String singleApp = "singleApp";
	private int singleAppWidth = jointAccWidth - 126;

	//These three strings and the strings above need to be sorted when I integrate
	//the sql as they are just tester stand-ins.
	private String onAcc = joint;
	private int noAccDepth = 677;
	
	private String enqOnly = secondApp;
	private int enqOnlyDepth = 657;
	
	private String tradAcc = singleApp;
	private int tradAccDepth = 637;
	
	private int famGroupDepth = 566;
	
	private int pageNumber = 3;
	private int firstSingleDepth = 424;
	private int giaDepth = 309;
	private int wrapWidth = 127;
	private int sasDepth = giaDepth-22;
	private int thirdPartyWidth = 50;
	private int thirdPartyDepth = 228;
	

	@Override
	public void fillPage() throws IOException, DocumentException {
		setUp(pageNumber);
		accessRights();
		familyGroups(true, true);
		firstOrSingle();
		thirdParty();
		shutDown();
	}

	private void thirdParty() {
		//Third Party Name
		stamp(thirdPartyWidth, thirdPartyDepth, "Dave");
		//Amount to be received
		stamp(thirdPartyWidth+150, thirdPartyDepth, "99");
		//source of funds
		stamp(thirdPartyWidth+200, thirdPartyDepth, "cheques");
		//Advisory Wrapper
		stamp(thirdPartyWidth+300, thirdPartyDepth, "99");
		//Discretionary Wrapper
		stamp(thirdPartyWidth+400, thirdPartyDepth, "99");

	}

	private void familyGroups(boolean moreThan1, boolean ascLink) {
		
		if(moreThan1) stamp(jointAccWidth, famGroupDepth, "X");
		if(ascLink){
			stamp(jointAccWidth, famGroupDepth-23, "X");
			stamp(315, famGroupDepth-67, "920292");
		}
		
	}

	private void accessRights() {
		onlineAccess(onAcc);
		enquiryOnly(enqOnly);
		tradingAccess(tradAcc);
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

	private void firstOrSingle() {
		//fill platform account name field
		stamp(315, firstSingleDepth , "Roger Rabbit");
		
		//indicates which wrappers the client wishes to open
		wrapper(true, true);
	}

	private void wrapper(boolean gia, boolean sas) {
		if(gia)fillWrappers(1);
		if(sas)fillWrappers(2);
		
	}

	private void fillWrappers(int lineNumber) {
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

}
