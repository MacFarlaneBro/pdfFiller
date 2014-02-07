package ascentricClientDetails;

public class Client {
	
	private IndividualDetails individualDetails;
	private ProductDetails productDetails;
	private BankAccountDetails bankAccountDetails;
	private FinancialAdviserDetails financialAdviserDetails;
	
    
    public IndividualDetails getIndividualDetails(){
    	individualDetails = new IndividualDetails();
    	return individualDetails;
    }
    
    public BankAccountDetails getBankAccountDetails(){
    	bankAccountDetails = new BankAccountDetails();
    	return bankAccountDetails;
    }
    
    public ProductDetails getProductDetails(){
    	productDetails = new ProductDetails();
    	return productDetails;
    }
    
    public FinancialAdviserDetails getfinancialAdviserDetails(){
    	financialAdviserDetails = new FinancialAdviserDetails();
    	return financialAdviserDetails;
    }

}

