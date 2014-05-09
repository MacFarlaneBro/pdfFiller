package ascentricClientDetails;

public class ConfirmationDetails {
	
	private String name;
	private String currentAddress;
	private String currentPostCode;
	private String dob;
	private String previousAddress;
	private String previousPostCode;
	private boolean moneyLaunderingCheck;
	private boolean clientIdentityCheck;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getCurrentPostCode() {
		return currentPostCode;
	}
	public void setCurrentPostCode(String currentPostCode) {
		this.currentPostCode = currentPostCode;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPreviousAddress() {
		return previousAddress;
	}
	public void setPreviousAddress(String previousAddress) {
		this.previousAddress = previousAddress;
	}
	public String getPreviousPostCode() {
		return previousPostCode;
	}
	public void setPreviousPostCode(String previousPostCode) {
		this.previousPostCode = previousPostCode;
	}
	public boolean isMoneyLaunderingCheck() {
		return moneyLaunderingCheck;
	}
	public void setMoneyLaunderingCheck(boolean moneyLaunderingCheck) {
		this.moneyLaunderingCheck = moneyLaunderingCheck;
	}
	public boolean isClientIdentityCheck() {
		return clientIdentityCheck;
	}
	public void setClientIdentityCheck(boolean clientIdentityCheck) {
		this.clientIdentityCheck = clientIdentityCheck;
	}


}
