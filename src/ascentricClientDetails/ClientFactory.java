package ascentricClientDetails;

public class ClientFactory implements MakeClients {
	
	/* (non-Javadoc)
	 * @see ascentricClientDetails.MakeClients#getFirstClient()
	 */
	@Override
	public Client getFirstClient() {
		
		return new Client("single");
	}
	
	/* (non-Javadoc)
	 * @see ascentricClientDetails.MakeClients#getSecondClient()
	 */
	@Override
	public Client getSecondClient() {
		
		return new Client("second");
	}
	
	/* (non-Javadoc)
	 * @see ascentricClientDetails.MakeClients#getJointAccount()
	 */
	@Override
	public Client getJointAccount() {
		
		return new Client("joint");
	}	
	
}
