package ascentricClientDetails;

public class InitialAdviserCharge extends Charge{
	
	private String fixedAmount;
	private boolean appCashLump;
	private boolean appCashTransfer;
	private boolean appStockTransfers;
	
	public String getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(String string) {
		this.fixedAmount = string;
	}
	public boolean isAppCashLump() {
		return appCashLump;
	}
	public void setAppCashLump(boolean appCashLump) {
		this.appCashLump = appCashLump;
	}
	public boolean isAppCashTransfer() {
		return appCashTransfer;
	}
	public void setAppCashTransfer(boolean appCashTransfer) {
		this.appCashTransfer = appCashTransfer;
	}
	public boolean isAppStockTransfers() {
		return appStockTransfers;
	}
	public void setAppStockTransfers(boolean appStockTransfers) {
		this.appStockTransfers = appStockTransfers;
	}
	
}
