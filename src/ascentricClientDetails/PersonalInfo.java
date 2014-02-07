package ascentricClientDetails;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface PersonalInfo {
	
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
	public String shutDown() throws DocumentException, IOException;
	
	/*
	 * Fills the tick boxes about the nature of the form (where applicable)
	 */
	public void tickBox(String string);
	
	/*
	 * Ticks national insurance box (where applicable)
	 * @param natInsurance asks whether nationalInsurance number is present
	 */
	public void natInsurance(boolean b);
	
	/*
	 * Ticks usPerson box (where applicable)
	 * @param b whether the client in question is American
	 */
	public void usPerson(boolean b);

	/*
	 * fills various additional information regarding the client
	 * @param this is just an array of string for the time being, however when the sql database is hooked up this should be cleaned up
	 * and made more data specific
	 */
	public void additionalInfo(String[] info);

}
