package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Processes and directs messages to their intended destinations.
 * Registers conversations and pairs them with connections.
 * 
 * @author Steven Zuchowski
 */
public abstract class Switchboard{
	
	public static final int CONNECTION_TIMEOUT = 2000;

	private HashMap<Integer, Conversation> conversations;
	private HashMap<Conversation, Connection> conversationConnections;
	private ArrayList<Connection> connections;
	
	public Switchboard(){
		conversationConnections = new HashMap<Conversation, Connection>();
		conversations = new HashMap<Integer, Conversation>();
		connections = new ArrayList<Connection>();
	}
	
	public abstract void process(Packet p);

	public void register(Conversation cv, Connection cn) {
		conversations.put(cv.getId(), cv);
		conversationConnections.put(cv, cn);
	}
	
	public void deregister(Conversation cv){
		conversations.remove(cv.getId());
		conversationConnections.remove(cv);
	}
	
	public void addConnection(Connection c){
		connections.add(c);
	}
	
	public Connection getConnection(Conversation cv){
		return conversationConnections.get(cv);
	}
	
	public Conversation getConversation(int id){
		return conversations.get(id);
	}
	
	public void removeConnection(Connection c){
		connections.remove(c);
	}
	
	public void sendMessage(String s, User u, Conversation c){
		
	}
	
	public void shutdown(){
		for(Connection c : connections){
			c.shutdown();
		}
	}
	
	
	
}
