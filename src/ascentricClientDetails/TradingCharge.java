package ascentricClientDetails;

public class TradingCharge extends Charge {
	
	private boolean singleFundBuyTrades;
	private boolean switchFundBuyTrades;
	
	public boolean isSingleFundBuyTrades() {
		return singleFundBuyTrades;
	}
	public void setSingleFundBuyTrades(boolean singleFundBuyTrades) {
		this.singleFundBuyTrades = singleFundBuyTrades;
	}
	public boolean isSwitchFundBuyTrades() {
		return switchFundBuyTrades;
	}
	public void setSwitchFundBuyTrades(boolean switchFundBuyTrades) {
		this.switchFundBuyTrades = switchFundBuyTrades;
	}
	

}
