package ascentricClientDetails;

public class ProductDetails{
	
	private String platformAccountNameChoice;
	private Wrapper generalInvestmentAccount;
	private Wrapper stocksAndSharesISA;
	
	private String thirdPartyProductAccounts;
	private int amountToBeReceived;
	private String sourceOfFunds;
	private boolean advisoryWrapper;
	private boolean discretionaryWrapper;
	
	public String getPlatformAccountNameChoice() {
		return platformAccountNameChoice;
	}
	public void setPlatformAccountNameChoice(String platformAccountNameChoice) {
		this.platformAccountNameChoice = platformAccountNameChoice;
	}
	public Wrapper getGeneralInvestmentAccount() {
		return generalInvestmentAccount;
	}
	public void makeGeneralInvestmentAccount() {
		generalInvestmentAccount = new Wrapper();
	}
	public void setGeneralInvestmentAccount(Wrapper generalInvestmentAccount) {
		this.generalInvestmentAccount = generalInvestmentAccount;
	}
	public Wrapper getStocksAndSharesISA() {
		return stocksAndSharesISA;
	}
	public void makeStocksAndSharesISA() {
		stocksAndSharesISA = new Wrapper();
	}
	public void setStocksAndSharesISA(Wrapper stocksAndSharesISA) {
		this.stocksAndSharesISA = stocksAndSharesISA;
	}
	public String getThirdPartyProductAccounts() {
		return thirdPartyProductAccounts;
	}
	public void setThirdPartyProductAccounts(String thirdPartyProductAccounts) {
		this.thirdPartyProductAccounts = thirdPartyProductAccounts;
	}
	public int getAmountToBeReceived() {
		return amountToBeReceived;
	}
	public void setAmountToBeReceived(int amountToBeReceived) {
		this.amountToBeReceived = amountToBeReceived;
	}
	public String getSourceOfFunds() {
		return sourceOfFunds;
	}
	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
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
	public boolean isStocksAndSharesISA() {
		if(stocksAndSharesISA != null){
			return true;
		} else {
			return false;
		}
	}
	public boolean isGeneralInvestmentAccount() {
		if(generalInvestmentAccount != null){
			return true;
		} else {
			return false;
		}
	}

}
