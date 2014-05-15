package ascentricForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import ascentricClientDetails.MakeClients;

import com.itextpdf.text.DocumentException;

/*
 * This Class is used to collate the necessary information on each client to successfully fill in an ascentric form
 * the information collected from SQL is then used by each of the pages in turn to from 
 */
public class AscentricForm{
	
	private AscentricPage page;
	
	/*
	 * Fills the page chosen by the calling class
	 * @return String address of the form with corresponding page filled
	 */
	public void fillIt(MakeClients theClient, File file) throws IOException, DocumentException {
		
		page = new AscentricPage1();
		
		page.fillPage(theClient.getFirstClient());
		
		//If a second Client exists then the relevant information is filled in.
		if(theClient.getSecondClient()!=null){
			page = new AscentricPage2();
			page.fillPage(theClient.getSecondClient());
		} else {
			page = new AscentricPage2();
			page.skipPage(2);
		}
		
		page = new AscentricPage3();
		page.fillPage(theClient);//Filling in the separate access rights applying to each of the applicants if
		
		page = new AscentricPage4();
		page.fillPage(theClient);
		
		page = new AscentricPage5();
		page.fillPage(theClient);
		
		page = new AscentricPage6();
		page.fillPage(theClient);
		
		page = new AscentricPage7();
		page.fillPage(theClient.getFirstClient());
		
		page = new AscentricPage8();
		page.fillPage(theClient.getFirstClient());
		
		page = new AscentricPage9();
		page.fillPage(theClient);
		String finishedFile ="temp/" + page.getFileName();
		System.out.println(finishedFile);
		cleanUp(file, finishedFile);
		
		System.out.println("finished printing");
	}
	
	private void cleanUp(File file, String finishedFile) throws IOException {
		FileChannel src = null;
		FileChannel dest = null;
		try {
			
			src = new FileInputStream(finishedFile).getChannel();
			dest = new FileOutputStream(file).getChannel();
			dest.transferFrom(src, 0, src.size());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			src.close();
			dest.close();
		}
	}
}
