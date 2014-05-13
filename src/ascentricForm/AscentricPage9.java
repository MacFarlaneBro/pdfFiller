package ascentricForm;

import java.io.IOException;
import ascentricClientDetails.Client;
import ascentricClientDetails.MakeClients;
import com.itextpdf.text.DocumentException;

public class AscentricPage9 extends AscentricPage {

	private static final int PAGENUMBER = 9;
	private int app1Width = 50;
	private int app2Width = 320;

	public void fillPage(MakeClients theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		
		applicant(theClient.getFirstClient(), app1Width);
		
		if(theClient.getSecondClient()!= null){
			applicant(theClient.getSecondClient(), app2Width);
		}
		shutDown();
	}

	private void applicant(Client client, int clientWidth) {
		int nameHeight = 413;
		stamp(clientWidth, nameHeight, client.getIndividualDetails().getForename() 
				+ " " + client.getIndividualDetails().getSurname());
	}

	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}
	
}
