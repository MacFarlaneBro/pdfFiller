package ascentricClientDetails;

public class OngoingAdviserCharge extends Charge {

	private int annualFixedAmount;
	private boolean collectives;
	private boolean cash;
	private boolean stocksAndShares;
	private boolean nonCustodyAssets;
	
	public int getAnnualFixedAmount() {
		return annualFixedAmount;
	}
	public void setAnnualFixedAmount(int annualFixedAmount) {
		this.annualFixedAmount = annualFixedAmount;
	}
	public boolean isCollectives() {
		return collectives;
	}
	public void setCollectives(boolean collectives) {
		this.collectives = collectives;
	}
	public boolean isCash() {
		return cash;
	}
	public void setCash(boolean cash) {
		this.cash = cash;
	}
	public boolean isStocksAndShares() {
		return stocksAndShares;
	}
	public void setStocksAndShares(boolean stocksAndShares) {
		this.stocksAndShares = stocksAndShares;
	}
	public boolean isNonCustodyAssets() {
		return nonCustodyAssets;
	}
	public void setNonCustodyAssets(boolean nonCustodyAssets) {
		this.nonCustodyAssets = nonCustodyAssets;
	}
}
