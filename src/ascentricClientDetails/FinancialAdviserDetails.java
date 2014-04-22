package ascentricClientDetails;

public class FinancialAdviserDetails{
	private Charge initAdviser;
	private Charge regContributions;
	private Charge tradingCharge;
	private Charge ongoingAdviser;
	
	private String fcaFirmNumber;
	private String registeredIndividual;
	private String fcaIndividualReferenceNumber;
	
	private String date;
	private boolean faceToFaceContact;
	
	public Charge getInitAdviser() {
		return initAdviser;
	}
	public void setInitAdviser(Charge initAdviser) {
		this.initAdviser = initAdviser;
	}
	public Charge getRegContributions() {
		return regContributions;
	}
	public void setRegContributions(Charge regContributions) {
		this.regContributions = regContributions;
	}
	public Charge getTradingCharge() {
		return tradingCharge;
	}
	public void setTradingCharge(Charge tradingCharge) {
		this.tradingCharge = tradingCharge;
	}
	public Charge getOngoingAdviser() {
		return ongoingAdviser;
	}
	public void setOngoingAdviser(Charge ongoingAdviser) {
		this.ongoingAdviser = ongoingAdviser;
	}
	public String getFcaFirmNumber() {
		return fcaFirmNumber;
	}
	public void setFcaFirmNumber(String fcaFirmNumber) {
		this.fcaFirmNumber = fcaFirmNumber;
	}
	public String getRegisteredIndividual() {
		return registeredIndividual;
	}
	public void setRegisteredIndividual(String registeredIndividual) {
		this.registeredIndividual = registeredIndividual;
		
		switch(registeredIndividual){
		
		case "Mrs Jacalyn Karen Baker": 
			fcaIndividualReferenceNumber = "JKB01086";
			break;	
		case "Mr Douglas David McFarlane Brodie":
			fcaIndividualReferenceNumber = "DDB00002";
			break;
		case "Mr Paul Michael Grant":
			fcaIndividualReferenceNumber = "PMG00013";
			break;
		case "Mr Richard Neil Higham":
			fcaIndividualReferenceNumber = "RNH00010";
			break;
		case "Miss Natalie Victoria Lucy Hull":
			fcaIndividualReferenceNumber = "NVH00005";
			break;
		case "Mr Roy McLoughlin":
			fcaIndividualReferenceNumber = "RXM00129";
			break;
		case "Mr David Gordon Tom":
			fcaIndividualReferenceNumber = "DXT00055";
			break;
		case "Mr Jeremy Bryce Watson":
			fcaIndividualReferenceNumber = "JBW00013";
			break;
		}
	}
	public String getFcaIndividualReferenceNumber() {
		return fcaIndividualReferenceNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isFaceToFaceContact() {
		return faceToFaceContact;
	}
	public void setFaceToFaceContact(boolean faceToFaceContact) {
		this.faceToFaceContact = faceToFaceContact;
	}

}
