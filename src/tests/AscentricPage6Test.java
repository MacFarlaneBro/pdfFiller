package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.Charge;
import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricClientDetails.InitialAdviserCharge;
import ascentricClientDetails.OngoingAdviserCharge;
import ascentricClientDetails.TradingCharge;
import ascentricForm.AscentricPage6;

import com.itextpdf.text.DocumentException;

public class AscentricPage6Test {
	
	AscentricPage6 thePage = new AscentricPage6();
	ClientHolder theClient = ClientHolder.getInstance();
	
	@Before
	public void setUp(){
		theClient.getFirstClient();
		Client firstClient = theClient.getFirstClient();
		
		FinancialAdviserDetails fad = firstClient.getfinancialAdviserDetails();
		
		setUpClient(fad);

	}

	private void setUpClient(FinancialAdviserDetails fad) {
		
		InitialAdviserCharge init = new InitialAdviserCharge();
		init.setAppCashLump(true);
		init.setAppCashTransfer(true);
		init.setAppStockTransfers(true);
		init.setFixedAmount("fsf2");
		init.setOfInvestment("fsf2");
		init.setVatApplied(true);
		fad.setInitAdviser(init);
		
		Charge reg = new Charge();
		reg.setOfInvestment("fsf2");
		reg.setVatApplied(true);
		fad.setRegContributions(reg);
		
		TradingCharge trad = new TradingCharge();
		trad.setOfInvestment("fsf2");
		trad.setSingleFundBuyTrades(true);
		trad.setSwitchFundBuyTrades(true);
		trad.setVatApplied(true);
		fad.setTradingCharge(trad);
		
		OngoingAdviserCharge ong = new OngoingAdviserCharge();
		ong.setAnnualFixedAmount("dfasf");
		ong.setVatApplied(true);
		ong.setCash(true);
		ong.setCollectives(true);
		ong.setNonCustodyAssets(true);
		ong.setOfInvestment("fsf2");
		ong.setStocksAndShares(true);
		fad.setOngoingAdviser(ong);
	}
	
	@Test
	public void page6Test() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}

}
