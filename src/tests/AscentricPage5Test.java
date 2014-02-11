package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.Client;
import ascentricClientDetails.ClientFactory;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.MakeClients;
import ascentricForm.AscentricForm;
import ascentricForm.AscentricPage5;

public class AscentricPage5Test {
	
	ClientHolder theClient = new ClientHolder();
	AscentricPage5 thePage = new AscentricPage5();
	
	@Before
	public void setUp() throws IOException, DocumentException{
		theClient.makeNewFirstClient();
		theClient.makeNewSecondClient();
		BankAccountDetails fbd = theClient.getFirstClient().getBankAccountDetails();
		BankAccountDetails sbd = theClient.getSecondClient().getBankAccountDetails();
		setBankAccountDetails(fbd);
		setBankAccountDetails(sbd);
	}
	
	private void setBankAccountDetails(BankAccountDetails bd) {
		bd.setAccountHolderNames("Daniel Orthodox");
		bd.setBankAccountNumber("2949291");
		bd.setBankAddress("23 Sutton road, Dumblefor, Yorkshire");
		bd.setBankName("Natwest");
		bd.setBankPostCode("YWD 4XP");
		bd.setBranchSortCode("602847");
		bd.setDepositPayTiming("3934234");
		bd.setLeaveInIncomeAccount(true);
		bd.setNatIncomeWrappers("Turnabout");
		bd.setNoIncomeWithdrawl(false);
		bd.setPaymentFromDeposit(999);
		bd.setPayTiming("38439");
		bd.setRegWithdrawlWrappers("These are wrappers");
		bd.setStartDate("30092014");
		bd.setWithdrawNaturalIncome(true);
		
	}

	@Test
	public void fillPage() throws IOException, DocumentException{
		thePage.fillPage(theClient);
	}

}
