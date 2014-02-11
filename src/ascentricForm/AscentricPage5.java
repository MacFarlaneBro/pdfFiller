package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.Client;
import ascentricClientDetails.ClientHolder;

import com.itextpdf.text.DocumentException;

public class AscentricPage5 extends AscentricPage {

	private static final int PAGENUMBER = 5;
	
	private int firstJointDepth = 578;
	private int secondDepth = 400;
	private int accDetailsWidth = 45;
	private int bankDetailsWidth = 370;

	
	public void fillPage(ClientHolder theClient) throws IOException, DocumentException {
		
		setUp(PAGENUMBER);
		
		if(theClient.getFirstClient() != null){//If the account is not joint use the first client information
			accDetails(theClient.getFirstClient().getBankAccountDetails());
		} else {
			accDetails(theClient.getJointAccount().getBankAccountDetails());
		}
		if(theClient.getSecondClient() != null){//If a second client without a joint account is present, fill in their details
			accDetails(theClient.getSecondClient().getBankAccountDetails());
		}
		
		//this little stamp selects whether the second applicants details
		//are the same as those in the first.
		secondApp(theClient.getSecondClient().getBankAccountDetails());
		incomePayment(theClient.getFirstClient().getBankAccountDetails());
		shutDown();
	}
	
	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}

	private void incomePayment(BankAccountDetails bankAccountDetails) {
		//Natural Income Payment Instructions
		int firstColumnDepth = 270;
		for(int i = 0; i < 3; i++){
			stamp(225, firstColumnDepth-= 20, "X");
		}
		//Wrappers
		stamp(95, 170, "A Wrapper");
		int firstRowWidth = 10;
		for(int i = 0; i < 4; i++){
			stamp(firstRowWidth+=65, 148, "X");
		}
		
		//Regular Withdrawal Instructions
		int rwiDepth = 254;
		//Amount
		stamp(482, rwiDepth, "100");
		//TimeFrame
		int secondRowWidth = 280;
		for(int i = 0; i < 4; i++){
			stamp(secondRowWidth+=65, 230, "X");
		}
		//Wrappers
		stamp(370, 208, "A Wrapper");
		
		int[] startDate = {1,5,2,3,5,2,7,2};
		secondRowWidth = 350;
		//StartDate
		for(int i = 0; i < 8; i++){
			if(i == 2 ||i == 4){
				stamp(secondRowWidth+=35, 188, ""+startDate[i]);
			} else {
				stamp(secondRowWidth+=20, 188, ""+startDate[i]);
			}
		}
	}

	private void accDetails(BankAccountDetails bad) {
		
		//Names of account holders
		String[] names = bad.getAccountHolderNames().split(" ");
		stamp(accDetailsWidth, firstJointDepth, names[0]);
		stamp(accDetailsWidth, firstJointDepth+ 20, names[1]);
		accountNumber(bad.getBankAccountNumber(), firstJointDepth-37);
		sortCode(bad.getBranchSortCode(), firstJointDepth-70);
		bankDetails(bad, firstJointDepth+19);
	}

	private void secondApp(BankAccountDetails bad) {
		if(bad.hasSameDetails()){//Checks to see whether the second applicants bank details are the same as the first
			stamp(bankDetailsWidth-28, secondDepth+57, "X");
		} else {
			accDetails(bad);
		}
	}
	
	private void bankDetails(BankAccountDetails bad, int depth) {
		//Bank Name
		stamp(bankDetailsWidth, depth, bad.getBankName());
		//Split the bank address into lines and then print them out
		String[] bankAddress = bad.getBankAddress().split("/");
		for(int i = 0; i < bankAddress.length; i++){
			stamp(bankDetailsWidth, depth -(i*20), bankAddress[i]);
		}
		//Bank postcode
		stamp(bankDetailsWidth, depth-80, bad.getBankPostCode());

	}

	private void sortCode(String sortCode, int depth) {
		int tempWidth = accDetailsWidth-20;
		for(int i = 0; i <= 5; i++){
			stamp(tempWidth+=22, depth, ""+ sortCode.charAt(i));
		}		
	}

	private void accountNumber(String accNum, int depth) {
		int tempWidth = accDetailsWidth-20;
		for(int i = 0; i < accNum.length(); i++){
			stamp(tempWidth+=21, depth, ""+ accNum.charAt(i));
		}
		
	}
}
