package ascentric;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class AscentricPage2 extends AscentricPage1{
	
	public static final int PAGENUMBER = 2;	
	
	public AscentricPage2(){
		super();
		natInsureDepth+=176;
		detailDepth +=175;
		dobDepth+=177;
		contactDepth+=151;
		tinDepth+=168;
		cliRef+=168;
		passInfo += 150;
		natInYN +=165;
		usPersonDepth +=148;
	}
	
	@Override
	public void fillPage() throws IOException, DocumentException{
		setUp(PAGENUMBER);
		fillPersonalDetails();	
		fillNatInsure();
		fillContactDetails();
		natInsurance(false);
		usPerson(false);
		String[] info = {"123456", "Dummy", "Ukraine", "12826294291234322842", "London", "Guinea-Bissau"};
		additionalInfo(info);
		sameDetails(true);
		shutDown();
		
	}

	private void sameDetails(boolean b) {
		if(b){
			stamp(540, 674, "X");
		}	
	}
	
}