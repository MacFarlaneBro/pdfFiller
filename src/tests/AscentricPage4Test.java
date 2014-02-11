package tests;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;
import ascentricForm.AscentricPage3;
import ascentricForm.AscentricPage4;

import com.itextpdf.text.DocumentException;

public class AscentricPage4Test {
	
	ClientHolder theClient = new ClientHolder();
	AscentricPage4 thePage = new AscentricPage4();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		theClient.makeNewSecondClient();
		theClient.makeNewJointClient();

		ProductDetails spd = theClient.getSecondClient().getProductDetails();
		ProductDetails jpd = theClient.getJointAccount().getProductDetails();
		
		setProductDetails(spd);
		setProductDetails(jpd);
		
		fillPage();
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
	
	@Test
	public void fillPage() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}

}
