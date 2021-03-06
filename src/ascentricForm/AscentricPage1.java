package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;
import ascentricClientDetails.CorrespondenceDetails;
import ascentricClientDetails.IndividualDetails;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;

public class AscentricPage1 extends AscentricPage{
	
	public static final int PAGENUMBER = 1;
		
	protected int natInsureDepth = 404;
	protected int detailDepth = 500;
	protected int dobDepth = 440;
	protected int firstRow = 100;
	protected int contactDepth = 500;
	protected int contactWidth = 368;
	protected int corrDepth = 345;
	protected int tickDepth = 560;
	protected int addInfoRow = 45;
	protected int tinDepth = 127;
	protected int passInfo = 75;
	protected int cliRef = 295;
	protected int usPersonDepth = 225;
	protected int natInYN = 326;

	public void fillPage(Client theClient) throws IOException, DocumentException {
		this.theClient = theClient;
		IndividualDetails id = theClient.getIndividualDetails();
		System.out.println("Here");
		setUp(PAGENUMBER);
		fillAppType(theClient);
		fillPersonalDetails(id);	
		if(id.getNationalInsuranceNumber()==null
			|| id.getNationalInsuranceNumber().equals("")){
			natInsurance(false);
		} else {
			fillNatInsure(id.getNationalInsuranceNumber());
		}

		fillContactDetails(id);
		
		fillCorrespondenceAddress(id.getCorrespondenceDetails());

		usPerson(id.isUsPerson());
		additionalInfo(id);
		shutDown();
	}

	protected void fillCorrespondenceAddress(CorrespondenceDetails cd) {
		
		//Address
		stamp(contactWidth, corrDepth, cd.getFirstAdd());
		stamp(contactWidth, corrDepth-20, cd.getSecondAdd());

		//PostCode
		stamp(contactWidth+52, corrDepth-40, cd.getPostCode());
	}

	private void fillAppType(Client theClient) throws IOException, DocumentException{
		if(theClient.getApplicationType().equals("Single Client")){//If a joint account is present
			stamp(193, tickDepth, "X");
		} else if(theClient.getApplicationType().equals("Two Clients")){//If a second client is present
			stamp(275, tickDepth, "X");
		} else {
			stamp(354, tickDepth, "X");
		}
	}
	
	/*
	 * Fills in the selected form with the details of the selected client
	 */
	protected void fillPersonalDetails(IndividualDetails id) {
		
		String dob = id.getDob();
		int extraDistance = 0;
		//Title
		stamp(firstRow, detailDepth, id.getTitle());
		//Surname
		stamp(firstRow, detailDepth-20, id.getSurname());
		//First Name
		stamp(firstRow, detailDepth-40, id.getForename());
		
		
		//Date of Birth stamping loop
		if(dob!= null){
			stamp(firstRow,
					dobDepth,
					dob.charAt(0));
			for(int i = 1; i < dob.length(); i++){
				if(i == 2 || i == 4){
					extraDistance+=35;
				} else {
					extraDistance+=20;
				}
				stamp(firstRow + extraDistance, dobDepth, dob.charAt(i));
			}
		}
	}
	
	protected void fillNatInsure(String nin){
		
		if(nin!= null){
			stamp(firstRow, natInsureDepth, nin.charAt(0));
			for(int i = 1; i < nin.length(); i++){
				if(i %2 == 0 && i < 9){
					stamp(firstRow+=23, natInsureDepth, nin.charAt(i));
				} else {
					stamp(firstRow+=20, natInsureDepth, nin.charAt(i));
				}
			}
		}
	}
	
	protected void fillContactDetails(IndividualDetails id) {
		//Home Telephone Number
		stamp(contactWidth, contactDepth, id.getHomeNumber());
		//Work Telephone Number
		stamp(contactWidth, contactDepth - 20, id.getWorkNumber());
		//Mobile Telephone Number
		stamp(contactWidth, contactDepth - 40, id.getMobileNumber());
		
		contactWidth -= 52;
		//Splitting the address string
		if(id.getAddress()!=null && id.getAddress().length() > 2){
			String[] addressParts = id.getAddress().split(":");

			int addPart = 77;
			//Address
			stamp(contactWidth, contactDepth - addPart, addressParts[0]);
			addPart +=20;
			stamp(contactWidth, contactDepth - addPart, addressParts[1]);
		}
		//Post code
		stamp(contactWidth+52, contactDepth - 117, id.getPostcode());
		
		//E-mail
		stamp(contactWidth+52, contactDepth - 215, id.getEmail(), 9);
	}

	protected void natInsurance(boolean natIn){
		if(!natIn){
			stamp(271, natInYN, "X");
		}
	}
	
	protected void additionalInfo(IndividualDetails id) {
		
		if(id.getClientRef()!=null){
			//ClientReference
			stamp(addInfoRow, cliRef, id.getClientRef());
		}
		//Nationality
		stamp(addInfoRow, cliRef-33, id.getNationality());
		//Domiciled
		stamp(addInfoRow, cliRef-67, id.getDomiciled());
		//PassportCity
		stamp(150, passInfo+20, id.getCity());
		//Passport Country
		stamp(150, passInfo, id.getCountry());		
		//TaxInfoNumber input with loop in separate method
		if(id.getTin()!=null){// if tin isnt null then call the tin creation method
			tinNumber(id.getTin());
		}
	}
	
	protected void tinNumber(String tin) {
		for(int i = 0; i < tin.length(); i++){
			stamp(addInfoRow+(i*12)+1, tinDepth, tin.charAt(i));
		}
		
	}

	protected void usPerson(boolean b) {
		if(b){
			stamp(403, usPersonDepth, "X");
		} else {
			stamp(440, usPersonDepth, "X");
		}		
	}

	@Override
	public void fillPage(MakeClients theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}
}
