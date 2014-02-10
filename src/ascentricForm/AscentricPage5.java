package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.ClientFactory;

import com.itextpdf.text.DocumentException;

public class AscentricPage5 extends AscentricPage {

	private static final int PAGENUMBER = 5;
	
	private int firstJointDepth = 578;
	private int secondDepth = 400;
	private int accDetailsWidth = 45;
	private int bankDetailsWidth = 370;

	
	public void fillPage(ClientFactory theClient) throws IOException, DocumentException {
		boolean secondSame = false;
		setUp(PAGENUMBER);
		//Sets the data to fill in the first applicant account details
		accDetails(firstJointDepth);
		//this little stamp selects whether the second applicants details
		//are the same as those in the first.
		secondApp(secondSame);
		incomePayment();
		shutDown();
	}

	private void incomePayment() {
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

	private void accDetails(int depth) {
		//as with other such arrays scattered about the code, these are stand ins
		//for positional purposes until the SQL is working
		int[] accountNumber = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] sortCode = {1,2,3,4,5,6};
		String[] bankDetails = {"Natwest", "23 Geroge st", "oppenham", "Surrey", "NE4 TGH"};
		//Names of account holders
		stamp(accDetailsWidth, depth, "Jenny");
		stamp(accDetailsWidth, depth+ 20, "Jenny");
		accountNumber(accountNumber, depth-37);
		sortCode(sortCode, depth-70);
		bankDetails(bankDetails, depth+19);
	}

	private void secondApp(boolean secondSame) {
		if(secondSame){
			stamp(bankDetailsWidth-28, secondDepth+57, "X");
		} else {
			accDetails(secondDepth);
		}
	}
	
	private void bankDetails(String[] bankDetails, int depth) {
		for(int i = 0; i < 5; i++){
			stamp(bankDetailsWidth, depth -(i*20), bankDetails[i]);
		}
	}

	private void sortCode(int[] sortCode, int depth) {
		int tempWidth = accDetailsWidth-20;
		for(int i = 0; i <= 5; i++){
			stamp(tempWidth+=22, depth, ""+ sortCode[i]);
		}		
	}

	private void accountNumber(int[] accountNumber, int depth) {
		int tempWidth = accDetailsWidth-20;
		for(int i = 0; i < 8; i++){
			stamp(tempWidth+=21, depth, ""+ accountNumber[i]);
		}
		
	}
}
