package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.Client;
import ascentricClientDetails.ClientFactory;
import ascentricClientDetails.MakeClients;
import ascentricForm.AscentricForm;

public class AscentricPage5Test {
	MakeClients theClient = new ClientFactory("Bob Hoskins");
	
	@Before
	public void setUp() throws IOException, DocumentException{
		Client first = theClient.getFirstClient();
		BankAccountDetails bd = first.getBankAccountDetails();
		setBankAccountDetails(bd);
		AscentricForm form = new AscentricForm();
		form.fillIt(theClient);
	}
	
	private void setBankAccountDetails(BankAccountDetails bd) {
		bd.setAccountHolderNames("Daniel");
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
	public void testWrapperExistence(){
		assertNotNull(theClient.getFirstClient().getProductDetails().getStocksAndSharesISA());
	}

}
