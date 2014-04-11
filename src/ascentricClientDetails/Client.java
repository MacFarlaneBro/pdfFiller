package ascentricClientDetails;

public class Client {
	
	private IndividualDetails individualDetails;
	private ProductDetails productDetails;
	private BankAccountDetails bankAccountDetails;
	private FinancialAdviserDetails financialAdviserDetails;
	private String clientType;
	private String appType;
	
    
    public Client(String type) {
		this.clientType = type;
		individualDetails = new IndividualDetails();
    	bankAccountDetails = new BankAccountDetails();
    	productDetails = new ProductDetails();
    	financialAdviserDetails = new FinancialAdviserDetails();
	}

	public IndividualDetails getIndividualDetails(){
    	return individualDetails;
    }
    
    public BankAccountDetails getBankAccountDetails(){
    	return bankAccountDetails;
    }
    
    public ProductDetails getProductDetails(){
    	return productDetails;
    }
    
    public FinancialAdviserDetails getfinancialAdviserDetails(){
    	return financialAdviserDetails;
    }

	public String getClientType() {
		return clientType;
	}

	public void setApplicationType(String appType) {
		this.appType = appType;
	}
	
	public String getApplicationType(){
		return appType;
	}
}

