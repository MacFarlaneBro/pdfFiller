package ascentricClientDetails;

public class ClientFactory {
	
	private Client firstClient;
	private Client secondClient;
	private Client jointAccount;
	private Client nonSpecifiedInfo;
	private String clientNumber;
	
	public ClientFactory(String clientNumber){
		this.clientNumber = clientNumber;
	}
	
	public Client getFirstClient() {
		firstClient = new Client("first");
		return firstClient;
	}
	
	public Client getSecondClient() {
		secondClient = new Client("second");
		return secondClient;
	}
	
	public Client getJointAccount() {
		jointAccount = new Client("joint");
		return jointAccount;
	}	
	
}
