package ascentricClientDetails;

public class ClientInformation {
	
	private Client firstClient;
	private Client secondClient;
	private Client jointAccount;
	
	public ClientInformation(String clientNumber){
		
	}
	
	public Client getFirstClient() {
		firstClient = new Client();
		return firstClient;
	}
	
	public Client getSecondClient() {
		secondClient = new Client();
		return secondClient;
	}
	
	public Client getJointAccount() {
		jointAccount = new Client();
		return jointAccount;
	}	
	
}
