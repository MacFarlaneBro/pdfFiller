package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.IndividualDetails;
import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;
import ascentricForm.AscentricPage3;

import com.itextpdf.text.DocumentException;

public class AscentricPage3Test {
	
	
	ClientHolder theClient = new ClientHolder();
	AscentricPage3 thePage = new AscentricPage3();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		theClient.makeNewFirstClient();
		theClient.makeNewSecondClient();
		theClient.makeNewJointClient();
		
		IndividualDetails fid = theClient.getFirstClient().getIndividualDetails();
		IndividualDetails sid = theClient.getSecondClient().getIndividualDetails();
		IndividualDetails jid = theClient.getJointAccount().getIndividualDetails();

		setPersonalDetails(fid);
		setPersonalDetails(sid);
		setPersonalDetails(jid);
		
		fid.setExistingAccount(true);
		fid.setClientRef("38472hdeh83");
		fid.setFamilyGroup(true);
		
		ProductDetails fpd = theClient.getFirstClient().getProductDetails();
		setProductDetails(fpd);
		
		fpd.setThirdPartyProductAccounts("Dooleys");
		fpd.setAmountToBeReceived(839834);
		fpd.setSourceOfFunds("Cheque");
		fpd.setAdvisoryWrapper(true);
		fpd.setDiscretionaryWrapper(true);
		
		fillPage();
	}
	
	private void setPersonalDetails(IndividualDetails id) {
		id.setOnlineAccess(true);
		id.setEnquiryOnly(true);
		id.setTradingAccess(true);
	}
	
	private void setProductDetails(ProductDetails fpd){
		fpd.setPlatformAccountNameChoice("The great noon one");
		Wrapper gia = new Wrapper();
		gia.setCash(45243);
		gia.setSourceOfFunds("Cheque");
		gia.setCashToBeTransferred(848473);
		gia.setAssetsToBeReregistered(83937);
		gia.setReserverAccount("4849458");
		gia.setAdvisoryWrapper(true);
		gia.setDiscretionaryWrapper(true);
		fpd.setGeneralInvestmentAccount(gia);
		
		Wrapper sas = new Wrapper();
		sas.setCash(436426);
		sas.setSourceOfFunds("BACS");
		sas.setCashToBeTransferred(3984384);
		sas.setAssetsToBeReregistered(839382);
		sas.setReserverAccount("Account");
		sas.setAdvisoryWrapper(true);
		sas.setDiscretionaryWrapper(true);
		fpd.setStocksAndSharesISA(sas);
	}
	
	@Test//There is a bug here whereby if the fillpage method is called on
	//more than one method in a row then the imprint of all but the last
	//client are wiped for some reason
	public void fillPage() throws IOException, DocumentException{
		System.out.println(theClient.getFirstClient().getProductDetails().getGeneralInvestmentAccount().getCash());
		thePage.fillPage(theClient);

	}

}
