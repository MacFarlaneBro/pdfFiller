package tests;

import org.junit.Before;

import ascentricClientDetails.Charge;
import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricClientDetails.InitialAdviserCharge;
import ascentricClientDetails.OngoingAdviserCharge;
import ascentricClientDetails.TradingCharge;
import ascentricForm.AscentricPage6;

public class AscentricPage6Test {
	
	AscentricPage6 thePage = new AscentricPage6();
	ClientHolder theClient = new ClientHolder();
	
	@Before
	public void setUp(){
		theClient.makeNewFirstClient();
		Client firstClient = theClient.getFirstClient();
		
		FinancialAdviserDetails fad = firstClient.getfinancialAdviserDetails();
		
		setUpClient(fad);
	}

	private void setUpClient(FinancialAdviserDetails fad) {
		InitialAdviserCharge init = new InitialAdviserCharge();
		fad.setInitAdviser(init);
		Charge reg = new Charge();
		fad.setRegContributions(reg);
		TradingCharge tradingCharge = new TradingCharge();
		fad.setTradingCharge(tradingCharge);
		OngoingAdviserCharge ongoingAdviser = new OngoingAdviserCharge();
		fad.setOngoingAdviser(ongoingAdviser);
		
	}

}
