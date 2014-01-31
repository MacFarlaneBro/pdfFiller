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
	private int giaDepth = 308;
	private int wrapWidth = 100;

	@Override
	public void fillPage() throws IOException, DocumentException {
		setUp(pageNumber);
		accessRights();
		familyGroups(true, true);
		firstOrSingle();
		shutDown();
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
		if(gia)fill(1);
		if(sas)fill(2);
		
	}


	private void fill(int lineNumber) {
		String[] filler = {"X", "999", "999", "999", "999", "999", "999", "999", "999"};
		if(lineNumber ==1){
			for(int i = 0; i < 7; i++){
				stamp(wrapWidth+= 30, giaDepth, filler[i]);
			}
		}
		
	}
	
}
