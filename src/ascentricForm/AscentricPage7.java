package ascentricForm;

import java.io.IOException;

import ascentricClientDetails.Client;

import com.itextpdf.text.DocumentException;

public class AscentricPage7 extends AscentricPage{
	
	public static final int PAGENUMBER = 7;

	@Override
	public void fillPage(Client theClient) throws IOException,
			DocumentException {
		setUp(PAGENUMBER);
		
	}

}
