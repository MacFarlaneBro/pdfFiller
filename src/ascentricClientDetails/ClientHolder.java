package ascentricClientDetails;

public class ClientHolder implements MakeClients{
	
	private Client firstClient;
	private Client secondClient;
	private Client jointAccount;
	private static ClientHolder instance;
	private MakeClients makeClients;
	
	static{
		instance = new ClientHolder();
	}
	
	private ClientHolder(){
		 makeClients = new ClientFactory();
	}
	
	public static ClientHolder getInstance(){
		return instance;
	}

	private void makeNewFirstClient(){
		firstClient = makeClients.getFirstClient();
	}
	
	private void makeNewSecondClient(){
		secondClient = makeClients.getSecondClient();
	}
	
	private void makeNewJointAccount(){
		jointAccount = makeClients.getJointAccount();
	}

	public Client getFirstClient() {
		if(firstClient == null){
			makeNewFirstClient();
		}
		return firstClient;
	}
	
	public Client getSecondClient() {
		if(secondClient == null){
			makeNewSecondClient();
		}
		return secondClient;
	}

	public Client getJointAccount() {
		if(jointAccount == null){
			makeNewJointAccount();
		}
		return jointAccount;
	}
}
