package ascentricForm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;

public class AscentricPage8 extends AscentricPage {

	private static final int PAGENUMBER = 8;
	private int app1Width = 50;
	private int app2Width = 320;
	private Date date;
	private SimpleDateFormat sdf;

	public void fillPage(MakeClients theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		date = new Date();
		sdf = new SimpleDateFormat("dd/mm/yyyy");
		
		applicant(theClient.getFirstClient(), app1Width);
		
		if(theClient.getSecondClient()!= null){
			applicant(theClient.getSecondClient(), app2Width);
		}
		shutDown();
	}

	private void applicant(Client client, int clientWidth) {
		int nameHeight = 415;
		stamp(clientWidth, nameHeight, client.getIndividualDetails().getForename() 
				+ "" + client.getIndividualDetails().getSurname());
		int sigHeight = 365;
		stamp(clientWidth, sigHeight, client.getIndividualDetails().getForename() 
				+ "" + client.getIndividualDetails().getSurname());
		int dateHeight = 335;
		int temp = clientWidth+50;
		String theDate =
				sdf.format(date);
		stamp(temp, dateHeight, theDate.charAt(0) + "");
		for(int i = 1; i < theDate.length(); i++){
			
			if(theDate.charAt(i) == '/'){
				temp+=16;
			} else {
				stamp(temp+=20, dateHeight, theDate.charAt(i) + "");
			}
		}
	}

	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}
	

}
