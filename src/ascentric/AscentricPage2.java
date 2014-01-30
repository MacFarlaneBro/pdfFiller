package ascentric;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

public class AscentricPage2 extends AscentricPage1{
	
	protected String fileName;
	public static final int PAGENUMBER = 2;	
	
	public AscentricPage2(String fileName){
		super(fileName);
		this.fileName = fileName;
		natInsureDepth+=176;
		detailDepth +=175;
		dobDepth+=177;
		contactDepth+=151;
		tinDepth+=168;
		cliRef+=168;
		passInfo += 150;
		natInYN +=165;
	}
	
	@Override
	public String fillPage() throws DocumentException, IOException{
		setUp(PAGENUMBER);
		fillPersonalDetails();	
		fillNatInsure();
		fillContactDetails();
		natInsurance(false);
		usPerson(false);
		String[] info = {"123456", "Dummy", "Ukraine", "12826294291234322842", "London", "Guinea-Bissau"};
		additionalInfo(info);
		sameDetails(true);
		return shutDown();
	}

	private void sameDetails(boolean b) {
		if(b){
			stamp(540, 674, "X");
		}	
	}
	
}