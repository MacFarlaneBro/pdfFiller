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
	
	
	ClientHolder theClient = ClientHolder.getInstance();
	AscentricPage3 thePage = new AscentricPage3();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		
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
		fpd.setAmountToBeReceived("Dooleys");
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
		gia.setCash("Cheque");
		gia.setSourceOfFunds("Cheque");
		gia.setCashToBeTransferred("Cheque");
		gia.setAssetsToBeReregistered("Cheque");
		gia.setReserverAccount("4849458");
		gia.setAdvisoryWrapper(true);
		gia.setDiscretionaryWrapper(true);
		fpd.setGeneralInvestmentAccount(gia);
		
		Wrapper sas = new Wrapper();
		sas.setCash("Cheque");
		sas.setSourceOfFunds("BACS");
		sas.setCashToBeTransferred("Cheque");
		sas.setAssetsToBeReregistered("Cheque");
		sas.setReserverAccount("Account");
		sas.setAdvisoryWrapper(true);
		sas.setDiscretionaryWrapper(true);
		fpd.setStocksAndSharesISA(sas);
	}
	
	@Test
	public void fillPage() throws IOException, DocumentException{
		System.out.println(theClient.getFirstClient().getProductDetails().getGeneralInvestmentAccount().getCash());
		thePage.fillPage(theClient);

	}

}
