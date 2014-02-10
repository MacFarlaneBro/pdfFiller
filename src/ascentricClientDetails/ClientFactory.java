package ascentricClientDetails;

public class ClientFactory implements MakeClients {
	
	/* (non-Javadoc)
	 * @see ascentricClientDetails.MakeClients#getFirstClient()
	 */
	@Override
	public Client getFirstClient() {
		Client firstClient = new Client("first");
		return firstClient;
	}
	
	/* (non-Javadoc)
	 * @see ascentricClientDetails.MakeClients#getSecondClient()
	 */
	@Override
	public Client getSecondClient() {
		Client secondClient = new Client("second");
		return secondClient;
	}
	
	/* (non-Javadoc)
	 * @see ascentricClientDetails.MakeClients#getJointAccount()
	 */
	@Override
	public Client getJointAccount() {
		Client jointAccount = new Client("joint");
		return jointAccount;
	}	
	
}
