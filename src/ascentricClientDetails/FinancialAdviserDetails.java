package ascentricClientDetails;

public class FinancialAdviserDetails {
	private Charge initAdviser;
	private Charge regContributions;
	private Charge tradingCharge;
	private Charge ongoingAdviser;
	
	private String firmName = "Master Adviser";
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
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
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
	}
	public String getFcaIndividualReferenceNumber() {
		return fcaIndividualReferenceNumber;
	}
	public void setFcaIndividualReferenceNumber(String fcaIndividualReferenceNumber) {
		this.fcaIndividualReferenceNumber = fcaIndividualReferenceNumber;
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
