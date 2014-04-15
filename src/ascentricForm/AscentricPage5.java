package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.Client;
import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;

public class AscentricPage5 extends AscentricPage {

	private static final int PAGENUMBER = 5;
	
	private int firstJointDepth = 578;
	private int secondDepth = 400;
	private int accDetailsWidth = 45;
	private int bankDetailsWidth = 370;

	
	public void fillPage(MakeClients theClient) throws IOException, DocumentException {
		
		setUp(PAGENUMBER);
		
		if(theClient.getJointAccount() == null){//If the account is not joint use the first client information
			accDetails(theClient.getFirstClient().getBankAccountDetails(), "first");
		} else {
			accDetails(theClient.getJointAccount().getBankAccountDetails(), "joint");
		}
		
		//this little stamp selects whether the second applicants details
		//are the same as those in the first.
		System.out.println("Second Might be Null");
		if(theClient.getSecondClient()!=null){
			System.out.println("secondNotNull");
			secondApp(theClient.getSecondClient().getBankAccountDetails());
		}
		
		incomePayment(theClient.getFirstClient().getBankAccountDetails());
		shutDown();
	}
	
	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		// TODO Auto-generated method stub
		
	}

	private void incomePayment(BankAccountDetails bad) {
		//Natural Income Payment Instructions
		int firstColumnDepth = 270;
		
		if(bad.isNoIncomeWithdrawl()){
			stamp(225, firstColumnDepth-20, "X");
		}
		if(bad.isLeaveInIncomeAccount()){
			stamp(225, firstColumnDepth-40, "X");
		}
		if(bad.isWithdrawNaturalIncome()){
			stamp(225, firstColumnDepth-60, "X");
		}
		

		//National Income Payment Wrappers
		stamp(95, 170, bad.getNatIncomeWrappers());
		int firstRowWidth = 10;
		if(bad.getPayTiming().equals("Quarterly")){
			firstRowWidth+=65;
		}
		if(bad.getPayTiming().equals("HalfYearly")){
			firstRowWidth+=130;
		}
		if(bad.getPayTiming().equals("Annually")){
			firstRowWidth+=195;
		}
		stamp(firstRowWidth+=65, 148, "X");
		//Regular Withdrawal Instructions
		int rwiDepth = 254;
		//Amount
		stamp(482, rwiDepth, bad.getRegWithdrawalAmount());
		//TimeFrame
		int secondRowWidth = 282;

		//Regular WithdrawalWrappers
		System.out.println(bad.getDepositPayTiming());
		if(bad.getDepositPayTiming().equals("Monthly")){
			secondRowWidth+=65;
		}
		if(bad.getDepositPayTiming().equals("Quarterly")){
			secondRowWidth+=130;
		}
		if(bad.getDepositPayTiming().equals("HalfYearly")){
			secondRowWidth+=195;
		}
		if(bad.getDepositPayTiming().equals("Annually")){
			secondRowWidth+=260;
		}
		stamp(370, 208, bad.getRegWithdrawlWrappers());
		stamp(secondRowWidth, 229, "X");
		
		
		secondRowWidth = 350;
		//StartDate
		if(bad.getStartDate()!= null && bad.getStartDate().length() >= 4){
		String startDate = bad.getStartDate();
		System.out.println(startDate);
			for(int i = 0; i < 8; i++){
				if(i == 2 ||i == 4){
					stamp(secondRowWidth+=35, 188,""+startDate.charAt(i));
				} else {
					stamp(secondRowWidth+=20, 188,""+startDate.charAt(i));
				}
			}
		}
	}

	private void accDetails(BankAccountDetails bad, String ct) {
		int depth;
		if(ct.equals("first")){
			depth = firstJointDepth;
		} else {
			depth = secondDepth;
		}
		//Names of account holders
		stamp(accDetailsWidth, depth+20, bad.getAccountHolderName1());
		if(bad.getAccountHolderName2()!= null){
			stamp(accDetailsWidth, depth, bad.getAccountHolderName2());
		}
		accountNumber(bad.getBankAccountNumber(), depth-37);
		if(bad.getBranchSortCode()!= null && bad.getBranchSortCode().length()> 2){
			sortCode(bad.getBranchSortCode(), depth-70);
		}
		bankDetails(bad, depth+19);
	}

	private void secondApp(BankAccountDetails bad) {
		if(bad.hasSameDetails()){//Checks to see whether the second applicants bank details are the same as the first
			stamp(bankDetailsWidth-28, secondDepth+57, "X");
		} else {
			accDetails(bad, "second");
		}
	}
	
	private void bankDetails(BankAccountDetails bad, int depth) {
		//Bank Name
		stamp(bankDetailsWidth, depth, bad.getBankName());
		//Split the bank address into lines and then print them out
		String[] bankAddress = bad.getBankAddress().split(":");
		for(int i = 0; i < bankAddress.length; i++){
			stamp(bankDetailsWidth, depth-20 -(i*20), bankAddress[i]);
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
