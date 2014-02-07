package ascentricClientDetails;

public class Wrapper {
	
	private int cash;
	private String sourceOfFunds;
	private int cashToBeTransferred;
	private int assetsToBeReregistered;
	private String reserverAccount;
	private boolean advisoryWrapper;
	private boolean discretionaryWrapper;
	
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getSourceOfFunds() {
		return sourceOfFunds;
	}
	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
	}
	public int getCashToBeTransferred() {
		return cashToBeTransferred;
	}
	public void setCashToBeTransferred(int cashToBeTransferred) {
		this.cashToBeTransferred = cashToBeTransferred;
	}
	public int getAssetsToBeReregistered() {
		return assetsToBeReregistered;
	}
	public void setAssetsToBeReregistered(int assetsToBeReregistered) {
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
