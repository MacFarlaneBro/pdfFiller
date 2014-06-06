package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;
import ascentricForm.AscentricPage4;

import com.itextpdf.text.DocumentException;

public class AscentricPage4Test {
	
	ClientHolder theClient = ClientHolder.getInstance();
	AscentricPage4 thePage = new AscentricPage4();
	
	@Before
	public void setUp() throws IOException, DocumentException{

		ProductDetails spd = theClient.getSecondClient().getProductDetails();
		
		Wrapper sas = new Wrapper();
		sas.setCash("Cheque");
		sas.setSourceOfFunds("BACS");
		sas.setCashToBeTransferred("Cheque");
		sas.setAssetsToBeReregistered("Cheque");
		sas.setReserverAccount("Account");
		sas.setAdvisoryWrapper(true);
		sas.setDiscretionaryWrapper(true);
		spd.setStocksAndSharesISA(sas);
		
		ProductDetails jpd = theClient.getJointAccount().getProductDetails();
		
		setProductDetails(spd);
		setProductDetails(jpd);

		fillPage();
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
		
		fpd.setThirdPartyProductAccounts("Dooleys");
		fpd.setAmountToBeReceived("Dooleys");
		fpd.setSourceOfFunds("Cheque");
		fpd.setAdvisoryWrapper(true);
		fpd.setDiscretionaryWrapper(true);	
	}
	@Test
	public void fillPage() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}

}
