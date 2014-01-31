package ascentric;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class AscentricPage3 extends AscentricPage{
	
	private String joint = "joint";
	private int jointAccWidth;

	private String secondApp = "secondApp";
	private int secondAppWidth;
	
	private String singleApp = "singleApp";
	private int singleAppWidth;

	//These three strings and the strings above need to be sorted when I integrate
	//the sql as they are just tester stand-ins.
	private String onAcc = joint;
	private int onAccDepth = 600;
	
	private String enqOnly = secondApp;
	private int enqOnlyDepth = 580;
	
	private String tradAcc = singleApp;
	private int tradAccDepth = 560;
	
	private int pageNumber = 3;

	@Override
	public void fillPage() throws IOException, DocumentException {
		setUp(pageNumber);
		accessRights();
		
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
		} else if(onAcc.equals(singleApp)){
			stamp(singleAppWidth, tradAccDepth, "X");
		} else {
			stamp(secondAppWidth, tradAccDepth, "X");
		}
	}
	
	//fills in the enquiry only checkboxes
	private void enquiryOnly(String enqOnly) {
		if(onAcc.equals(joint)){
			stamp(jointAccWidth, enqOnlyDepth, "X");
		} else if(onAcc.equals(singleApp)){
			stamp(singleAppWidth, enqOnlyDepth, "X");
		} else {
			stamp(secondAppWidth, enqOnlyDepth, "X");
		}
	}
	
	//fills in the online access checkboxes
	private void onlineAccess(String onAcc) {
		if(onAcc.equals(joint)){
			stamp(jointAccWidth, onAccDepth, "X");
		} else if(onAcc.equals(singleApp)){
			stamp(singleAppWidth, onAccDepth, "X");
		} else {
			stamp(secondAppWidth, onAccDepth, "X");
		}
	}
	
}
