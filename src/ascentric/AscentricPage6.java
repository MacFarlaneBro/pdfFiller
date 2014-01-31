package ascentric;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class AscentricPage6 extends AscentricPage {

	public static final int PAGENUMBER = 6;
	
	private int firstColumnWidth = 48;
	private int secondColumnWidth = 90;
	private int thirdColumnWidth = 220;
	private int fourthColumnWidth = 412;
	
	public void fillPage() throws IOException, DocumentException {
		setUp(PAGENUMBER);
		boolean[] financialAdviserDetails = {true, true, true, true};
		if(financialAdviserDetails[0]){
			initAdvCharge();
		}
		if(financialAdviserDetails[0]){
			regContribCharge();
		}
		if(financialAdviserDetails[0]){
			tradingCharge();
		}
		if(financialAdviserDetails[0]){
			ongoingAdvCharge();
		}
		shutDown();
	}

	private void ongoingAdvCharge() {
		int firstRowHeight = 404;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		stamp(firstColumnWidth+150, firstRowHeight, "X");
		int secondRowHeight = 368;
		//of investment
		stamp(secondColumnWidth, secondRowHeight, "999");
		//fixed amount
		stamp(thirdColumnWidth, secondRowHeight, "999");
		//A
		stamp(fourthColumnWidth, secondRowHeight+15, "X");
		//B
		stamp(fourthColumnWidth, secondRowHeight-10, "X");	
		//C
		stamp(fourthColumnWidth, secondRowHeight-32, "X");
		//D
		stamp(fourthColumnWidth, secondRowHeight-57, "X");
		
	}

	private void tradingCharge() {
			int firstRowHeight = 487;
			//Tick option selection
			stamp(firstColumnWidth, firstRowHeight , "X");
			//VAT applied?
			stamp(firstColumnWidth+155, firstRowHeight, "X");
			int secondRowHeight = 444;
			//of investment
			stamp(secondColumnWidth, secondRowHeight, "999");
			//A
			stamp(fourthColumnWidth, secondRowHeight+23, "X");
			//B
			stamp(fourthColumnWidth, secondRowHeight, "X");	
	}

	private void regContribCharge() {
		int firstRowHeight = 565;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		stamp(firstColumnWidth+155, firstRowHeight, "X");
		int secondRowHeight = 530;
		//of investment
		stamp(secondColumnWidth, secondRowHeight, "999");

	}

	private void initAdvCharge() {
		int firstRowHeight = 668;
		//Tick option selection
		stamp(firstColumnWidth, firstRowHeight , "X");
		//VAT applied?
		stamp(firstColumnWidth+155, firstRowHeight, "X");
		int secondRowHeight = 620;
		//of investment
		stamp(secondColumnWidth, secondRowHeight, "999");
		//fixed amount
		stamp(thirdColumnWidth, secondRowHeight, "999");
		//A
		stamp(fourthColumnWidth, secondRowHeight+25, "X");
		//B
		stamp(fourthColumnWidth, secondRowHeight, "X");	
		//C
		stamp(fourthColumnWidth, secondRowHeight-25, "X");
	}
}
