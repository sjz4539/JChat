package model;

/**
 * Storage class for response strings.
 * 
 * @author Steven Zuchowski
 */
public class Response {

	//The request was understood and completed successfully
	public static final String OK = "OK";
	
	//The request was missing one or more required parameters
	public static final String MISSING_PARAM = "MISSING_PARAM";
	
	//The request contained one or more invalid parameters
	public static final String INVALID_PARAM = "INVALID_PARAM";
	
	//server->client, the requested username was already taken
	public static final String USERNAME_TAKEN = "USERNAME_TAKEN";
	
	//server->client, the specified conversation ID was invalid
	public static final String INVALID_CONVERSATION = "INVALID_CONVERSATION";
	
}
