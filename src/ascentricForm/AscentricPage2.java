package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.IndividualDetails;

import com.itextpdf.text.DocumentException;

public class AscentricPage2 extends AscentricPage1{
	
	public static final int PAGENUMBER = 2;	
	
	public AscentricPage2(){
		super();
		natInsureDepth+=176;
		detailDepth +=175;
		dobDepth+=177;
		contactDepth+=151;
		tinDepth+=168;
		cliRef+=168;
		passInfo += 150;
		natInYN +=165;
		usPersonDepth +=148;
	}
	
	@Override
	public void fillPage(Client theClient) throws IOException, DocumentException{
		setUp(PAGENUMBER);
		IndividualDetails id = theClient.getIndividualDetails();
		fillPersonalDetails(id);	
		if(id.getNationalInsuranceNumber()==null){
			natInsurance(false);
		} else {
			fillNatInsure(id.getNationalInsuranceNumber());
		}
		if(id.isSameDetails()){
			sameDetails(id.isSameDetails());
		} else {
			fillContactDetails(id);
		}
		usPerson(id.isUsPerson());
		additionalInfo(id);
		shutDown();
		
	}

	private void sameDetails(boolean b) {
		if(b){
			stamp(540, 674, "X");
		}	
	}
	
}