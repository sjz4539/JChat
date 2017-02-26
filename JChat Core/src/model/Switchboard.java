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

	private HashMap<Conversation, Connection> conversations;
	private ArrayList<Connection> connections;
	
	public Switchboard(){
		conversations = new HashMap<Conversation, Connection>();
		connections = new ArrayList<Connection>();
	}

	public void register(Conversation cv, Connection cn) {
		conversations.put(cv, cn);
	}
	
	public void deregister(Conversation cv){
		conversations.remove(cv);
	}
	
	public void addConnection(Connection c){
		connections.add(c);
	}
	
	public Connection getConnection(Conversation cv){
		return conversations.get(cv);
	}
	
	public void shutdown(){
		for(Connection c : connections){
			c.shutdown();
		}
	}
	
	public abstract void process(Packet p);
	
}
