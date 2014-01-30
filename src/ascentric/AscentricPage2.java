package ascentric;

import java.io.IOException;

import pdfFiller.AscentricPage;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;

public class AscentricPage2 extends AscentricPage1  implements AscentricPage{
	
	protected String fileName;
	protected int pageNumber = 2;	
	
	public AscentricPage2(String fileName){
		super(fileName);
		this.fileName = fileName;
		pageNumber = 2;
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
		setUp(pageNumber);
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
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 540, 674, 0);
		}
		
	}
	
}