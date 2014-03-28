package ascentricClientDetails;

public class Wrapper {
	
	private String cash;
	private String sourceOfFunds;
	private String cashToBeTransferred;
	private String assetsToBeReregistered;
	private String reserverAccount;
	private boolean advisoryWrapper;
	private boolean discretionaryWrapper;
	
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getSourceOfFunds() {
		return sourceOfFunds;
	}
	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
	}
	public String getCashToBeTransferred() {
		return cashToBeTransferred;
	}
	public void setCashToBeTransferred(String cashToBeTransferred) {
		this.cashToBeTransferred = cashToBeTransferred;
	}
	public String getAssetsToBeReregistered() {
		return assetsToBeReregistered;
	}
	public void setAssetsToBeReregistered(String assetsToBeReregistered) {
		this.assetsToBeReregistered = assetsToBeReregistered;
	}
	public String getReserverAccount() {
		return reserverAccount;
	}
	public void setReserverAccount(String reserverAccount) {
		this.reserverAccount = reserverAccount;
	}
	public boolean isAdvisoryWrapper() {
		return advisoryWrapper;
	}
	public void setAdvisoryWrapper(boolean advisoryWrapper) {
		this.advisoryWrapper = advisoryWrapper;
	}
	public boolean isDiscretionaryWrapper() {
		return discretionaryWrapper;
	}
	public void setDiscretionaryWrapper(boolean discretionaryWrapper) {
		this.discretionaryWrapper = discretionaryWrapper;
	}

}
