package ascentricClientDetails;

public class ClientHolder {
	
	private Client firstClient;
	private Client secondClient;
	private Client jointAccount;
	private Client nonSpecifiedInfo;
	private String clientNumber;
	private static ClientHolder instance;
	
	MakeClients makeClients;
	
	private ClientHolder(){
		 makeClients = new ClientFactory();
	}
	
	public static ClientHolder getInstance(){
		return instance;
	}
	
	static{
		instance = new ClientHolder();
	}
	
	public void makeNewFirstClient(){
		firstClient = makeClients.getFirstClient();
	}
	
	public void makeNewSecondClient(){
		secondClient = makeClients.getSecondClient();
	}
	
	public void makeNewJointClient(){
		jointAccount = makeClients.getJointAccount();
	}

	public Client getFirstClient() {
		return firstClient;
	}

	public void setFirstClient(Client firstClient) {
		this.firstClient = firstClient;
	}

	public Client getSecondClient() {
		return secondClient;
	}

	public void setSecondClient(Client secondClient) {
		this.secondClient = secondClient;
	}

	public Client getJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(Client jointAccount) {
		this.jointAccount = jointAccount;
	}

	public Client getNonSpecifiedInfo() {
		return nonSpecifiedInfo;
	}

	public void setNonSpecifiedInfo(Client nonSpecifiedInfo) {
		this.nonSpecifiedInfo = nonSpecifiedInfo;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public MakeClients getMakeClients() {
		return makeClients;
	}

	public void setMakeClients(MakeClients makeClients) {
		this.makeClients = makeClients;
	}

}
