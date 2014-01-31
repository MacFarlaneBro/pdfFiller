package ascentric;

import java.io.IOException;
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
	public void fillPage() throws IOException, DocumentException{
		setUp(pageNumber);
		
		//These three methods fill in the second Applicant form section
		changeDepth(secondApplicantDepthIncrease);
		firstOrSingle();
		thirdParty();
		
		//These three methods fill in the joint Applicant form section
		changeDepth(jointApplicantDepthDecrease);
		firstOrSingle();
		thirdParty();
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

}
