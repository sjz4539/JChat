package model;

/**
 * Storage class for command strings
 * 
 * @author Steven Zuchowski
 */
public abstract class Command {

	//server->client, you need to set your username
	//client->server, please set my username
	public static final String SET_USERNAME = "SET_USERNAME";
	
	//client->server, send a list of public conversations
	public static final String CONVERSATION_LIST = "CONVERSATION_LIST";
	
	//client->server, send a list of users in a specific conversation
	public static final String USER_LIST = "USER_LIST"; 
	
	//either, I am closing this connection
	public static final String TERMINATE = "TERMINATE";
	
}
