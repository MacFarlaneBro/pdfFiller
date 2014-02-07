package ascentricClientDetails;

public class Client {
	
	private IndividualDetails individualDetails;
	private ProductDetails productDetails;
	private BankAccountDetails bankAccountDetails;
	private FinancialAdviserDetails financialAdviserDetails;
	
	public IndividualDetails getIndividualDetails() {
		return individualDetails;
	}
	public void setIndividualDetails(IndividualDetails individualDetails) {
		this.individualDetails = individualDetails;
	}
	public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	public BankAccountDetails getBankAccountDetails() {
		return bankAccountDetails;
	}
	public void setBankAccountDetails(BankAccountDetails bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}
	public FinancialAdviserDetails getFinancialAdviserDetails() {
		return financialAdviserDetails;
	}
	public void setFinancialAdviserDetails(
			FinancialAdviserDetails financialAdviserDetails) {
		this.financialAdviserDetails = financialAdviserDetails;
	}

}

