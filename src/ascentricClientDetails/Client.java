package ascentricClientDetails;

public class Client {
	
	private Individual individualDetails;
	private Product productDetails;
	private Bank bankAccountDetails;
	private Adviser financialAdviserDetails;
	
    public Client(){
    	
    }
    
    public void fillIndividualDetails(){
    	individualDetails = new IndividualDetails();
    	individualDetails.fill();
    }
    
    public void fillBankAccountDetails(){
    	bankAccountDetails = new BankAccountDetails();
    	bankAccountDetails.fill();
    }
    public void fillProductDetails(){
    	productDetails = new ProductDetails();
    	productDetails.fill();
    }
    public void financialAdviserDetails(){
    	financialAdviserDetails = new FinancialAdviserDetails();
    	financialAdviserDetails.fill();
    }

}

