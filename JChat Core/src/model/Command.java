package model;

/**
 * Storage class for command strings
 * 
 * @author Steven Zuchowski
 */
public abstract class Command {

	//client->server, please set my username
	public static final String SET_USERNAME = "SET_USERNAME";
	
	//server->client, your username has been changed
	public static final String USERNAME_SET = "USERNAME_SET";
	
	//client->server, send a list of public conversations
	public static final String CONVERSATION_LIST = "CONVERSATION_LIST";
	
	//client->server, send a list of users in a specific conversation
	public static final String USER_LIST = "USER_LIST"; 
	
	//client->server, I would like to join a specified conversation
	public static final String JOIN_CONVERSATION = "JOIN_CONVERSATION";
	
	//either, I am closing this connection
	public static final String TERMINATE = "TERMINATE";
	
}
