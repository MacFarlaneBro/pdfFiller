package ascentricClientDetails;

public class BankAccountDetails{
	
	private String accountHolderNames;
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
	
	private int paymentFromDeposit;
	private String depositPayTiming;
	private String regWithdrawlWrappers;
	private String startDate;
	
	public String getAccountHolderNames() {
		return accountHolderNames;
	}
	public void setAccountHolderNames(String accountHolderNames) {
		this.accountHolderNames = accountHolderNames;
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
	public void setPayTiming(String payTiming) {
		this.payTiming = payTiming;
	}
	public int getPaymentFromDeposit() {
		return paymentFromDeposit;
	}
	public void setPaymentFromDeposit(int paymentFromDeposit) {
		this.paymentFromDeposit = paymentFromDeposit;
	}
	public String getDepositPayTiming() {
		return depositPayTiming;
	}
	public void setDepositPayTiming(String depositPayTiming) {
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

}
