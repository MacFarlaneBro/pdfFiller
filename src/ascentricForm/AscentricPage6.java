package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Charge;
import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricClientDetails.InitialAdviserCharge;
import ascentricClientDetails.MakeClients;
import ascentricClientDetails.OngoingAdviserCharge;
import ascentricClientDetails.TradingCharge;

import com.itextpdf.text.DocumentException;

public class AscentricPage6 extends AscentricPage {

	public static final int PAGENUMBER = 6;
	
	private int firstColumnWidth = 48;
	private int secondColumnWidth = 90;
	private int thirdColumnWidth = 220;
	private int fourthColumnWidth = 412;
	
	public void fillPage(MakeClients theClient) throws IOException, DocumentException {
		setUp(PAGENUMBER);
		FinancialAdviserDetails fad = theClient.getFirstClient().getfinancialAdviserDetails();
		if(fad.getInitAdviser()!= null){
			initAdvCharge((InitialAdviserCharge) fad.getInitAdviser());
		}
		if(fad.getRegContributions()!= null){
			regContribCharge(fad.getRegContributions());
		}
		if(fad.getTradingCharge()!= null){
			tradingCharge((TradingCharge)fad.getTradingCharge());
		}
		if(fad.getOngoingAdviser()!= null){
			ongoingAdvCharge((OngoingAdviserCharge) fad.getOngoingAdviser());
		}
		shutDown();
	}

	private void ongoingAdvCharge(OngoingAdviserCharge oac) {
		int firstRowHeight = 404;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		if(oac.isVatApplied()){
			stamp(firstColumnWidth+150, firstRowHeight, "X");
		}
		int secondRowHeight = 368;
		//of investment
		stamp(secondColumnWidth, secondRowHeight,"" + oac.getOfInvestment());
		//fixed amount
		stamp(thirdColumnWidth, secondRowHeight,"" + oac.getAnnualFixedAmount());
		//A
		if(oac.isCollectives()){
			stamp(fourthColumnWidth, secondRowHeight+15, "X");
		}
		//B
		if(oac.isCash()){
			stamp(fourthColumnWidth, secondRowHeight-10, "X");
		}
		//C
		if(oac.isStocksAndShares()){
			stamp(fourthColumnWidth, secondRowHeight-32, "X");
		}
		//D
		if(oac.isNonCustodyAssets()){
			stamp(fourthColumnWidth, secondRowHeight-57, "X");
		}	
	}

	private void tradingCharge(TradingCharge charge) {
		int firstRowHeight = 487;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		if(charge.isVatApplied()){
			stamp(firstColumnWidth+155, firstRowHeight, "X");
		}
		int secondRowHeight = 444;
		//of investment
		stamp(secondColumnWidth, secondRowHeight,"" + charge.getOfInvestment());
		//A
		if(charge.isSingleFundBuyTrades()){
			stamp(fourthColumnWidth, secondRowHeight+23, "X");
		}
		//B
		if(charge.isSwitchFundBuyTrades()){
			stamp(fourthColumnWidth, secondRowHeight, "X");	
		}
	}

	private void regContribCharge(Charge charge) {
		int firstRowHeight = 565;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		if(charge.isVatApplied()){
			stamp(firstColumnWidth+155, firstRowHeight, "X");
		}
		int secondRowHeight = 530;
		//of investment
		stamp(secondColumnWidth, secondRowHeight, "" + charge.getOfInvestment());

	}

	private void initAdvCharge(InitialAdviserCharge iac) {
		int firstRowHeight = 668;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		if(iac.isVatApplied()){
			stamp(firstColumnWidth+155, firstRowHeight, "X");
		}
		int secondRowHeight = 620;
		//of investment
		stamp(secondColumnWidth, secondRowHeight,"" + iac.getOfInvestment());
		//fixed amount
		stamp(thirdColumnWidth, secondRowHeight,"" + iac.getFixedAmount());
		//A
		if(iac.isAppCashLump()){
			stamp(fourthColumnWidth, secondRowHeight+25, "X");
		}
		//B
		if(iac.isAppCashTransfer()){
			stamp(fourthColumnWidth, secondRowHeight, "X");	
		}
		//C
		if(iac.isAppStockTransfers()){
			stamp(fourthColumnWidth, secondRowHeight-25, "X");
		}
	}

	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}
}
