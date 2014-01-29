package pdfFiller;

import java.io.IOException;
import com.itextpdf.text.DocumentException;


public interface FillForm {
	
	/*
	 * Fills in the selected form with the details of the selected client
	 */
	public void fillPersonalDetails();
	
	/*
	 * Fills the national insurance information of the selected form
	 */
	public void fillNatInsure();
	
	/*
	 * Fills the contact details of the form
	 */
	public void fillContactDetails();
	
	/*
	 * Sets up the form filler in the correct format for the context
	 * @param form - The type of form required for the client
	 * @param clientNumber - Number allowing unique identification of each client
	 */
	public void setUp(String form, String client) throws IOException, DocumentException;
	
	/*
	 * Shuts closes down the form filler application including stopping the pdfstamper, thus creating the new pdf document
	 */
	public void shutDown() throws DocumentException, IOException;

}
