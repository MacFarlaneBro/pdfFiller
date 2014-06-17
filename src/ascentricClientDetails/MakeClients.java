package ascentricClientDetails;

public interface MakeClients {

	/*
	 * Returns the singleton first client, if no client yet exists, it instantiates the singleton
	 * then returns it
	 */
	public Client getFirstClient();
	
	/*
	 * Returns the singleton second client, if no client yet exists, it instantiates the singleton
	 * then returns it
	 */
	public Client getSecondClient();

	/*
	 * Returns the singleton joint account, if no client yet exists, it instantiates the singleton
	 * then returns it
	 */
	public Client getJointAccount();

}