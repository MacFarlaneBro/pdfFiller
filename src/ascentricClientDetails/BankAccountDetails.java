package ascentricClientDetails;

public class BankAccountDetails{
	
	private String accountHolderName1;
	private String accountHolderName2;
	private String bankAccountNumber;
	private String branchSortCode;
	private String bankName;
	private String bankAddress;
	private String bankPostCode;
	
	private boolean noIncomeWithdrawl;
	private boolean leaveInIncomeAccount;
	private boolean withdrawNaturalIncome;
	private String natIncomeWrappers;
	private String payTiming;
	
	private String paymentFromDepositAccount;
	private String paymentFromReserveAccount;
	private String depositPayTiming;
	private String regWithdrawlWrappers;
	private String startDate;
	private boolean sameDetails;
	private String regWithdrawalAmount;
	
	public String getAccountHolderName1() {
		return accountHolderName1;
	}
	public void setAccountHolderName1(String accountHolderName1) {
		this.accountHolderName1 = accountHolderName1;
	}
	public String getAccountHolderName2() {
		return accountHolderName2;
	}
	public void setAccountHolderName2(String accountHolderName2) {
		this.accountHolderName2 = accountHolderName2;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBranchSortCode() {
		return branchSortCode;
	}
	public void setBranchSortCode(String branchSortCode) {
		this.branchSortCode = branchSortCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankPostCode() {
		return bankPostCode;
	}
	public void setBankPostCode(String bankPostCode) {
		this.bankPostCode = bankPostCode;
	}
	public boolean isNoIncomeWithdrawl() {
		return noIncomeWithdrawl;
	}
	public void setNoIncomeWithdrawl(boolean noIncomeWithdrawl) {
		this.noIncomeWithdrawl = noIncomeWithdrawl;
	}
	public boolean isLeaveInIncomeAccount() {
		return leaveInIncomeAccount;
	}
	public void setLeaveInIncomeAccount(boolean leaveInIncomeAccount) {
		this.leaveInIncomeAccount = leaveInIncomeAccount;
	}
	public boolean isWithdrawNaturalIncome() {
		return withdrawNaturalIncome;
	}
	public void setWithdrawNaturalIncome(boolean withdrawNaturalIncome) {
		this.withdrawNaturalIncome = withdrawNaturalIncome;
	}
	public String getNatIncomeWrappers() {
		return natIncomeWrappers;
	}
	public void setNatIncomeWrappers(String natIncomeWrappers) {
		this.natIncomeWrappers = natIncomeWrappers;
	}
	public String getPayTiming() {
		return payTiming;
	}
	public void setNatIncomePayTiming(String payTiming) {
		this.payTiming = payTiming;
	}
	public String getDepositPayTiming() {
		return depositPayTiming;
	}
	public void setRegWithdrawalPayTiming(String depositPayTiming) {
		this.depositPayTiming = depositPayTiming;
	}
	public String getRegWithdrawlWrappers() {
		return regWithdrawlWrappers;
	}
	public void setRegWithdrawlWrappers(String regWithdrawlWrappers) {
		this.regWithdrawlWrappers = regWithdrawlWrappers;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public boolean hasSameDetails() {
		return sameDetails;
	}
	public void setSameDetails(boolean same){
		this.sameDetails = same;
	}
	public String getRegWithdrawalAmount() {
		return regWithdrawalAmount;
	}
	public void setRegWithdrawalAmount(String regw){
		this.regWithdrawalAmount = regw;
	}
	public void setPaymentTiming(String paymentTiming) {
		this.payTiming = paymentTiming;	
	}
	public String getPaymentFromDepositAccount() {
		return paymentFromDepositAccount;
	}
	public void setPaymentFromDepositAccount(String paymentFromDepositAccount) {
		this.paymentFromDepositAccount = paymentFromDepositAccount;
	}
	public String getPaymentFromReserveAccount() {
		return paymentFromReserveAccount;
	}
	public void setPaymentFromReserveAccount(String paymentFromReserveAccount) {
		this.paymentFromReserveAccount = paymentFromReserveAccount;
	}

}
