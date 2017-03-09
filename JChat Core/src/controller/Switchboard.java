package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Connection;
import model.Conversation;
import model.Packet;
import model.User;

/**
 * Processes and directs messages to their intended destinations.
 * Tracks open connections and conversations.
 * 
 * @author Steven Zuchowski
 */
public abstract class Switchboard{
	
	public static final int CONNECTION_TIMEOUT = 2000;

	//map of ids -> conversations
	protected HashMap<String, Conversation> conversations;

	//list of connections
	protected ArrayList<Connection> connections;
	
	public Switchboard(){
		conversations = new HashMap<String, Conversation>();
		connections = new ArrayList<Connection>();
	}
	
	public abstract void process(Packet p);
	
	public void addConnection(Connection cn){
		connections.add(cn);
	}
	
	public void removeConnection(Connection cn){
		connections.remove(cn);
	}
	
	public void addConversation(Conversation cv){
		conversations.put(cv.getId(), cv);
	}
	
	public Conversation getConversation(String id){
		return conversations.get(id);
	}
	
	public boolean hasConversation(String id){
		return conversations.containsKey(id);
	}
	
	public void removeConversation(Conversation cv){
		conversations.remove(cv.getId());
	}
	
	public Conversation removeConversation(String id){
		return conversations.remove(id);
	}
	
	public void shutdown(){
		for(Connection cn : connections){
			cn.shutdown();
		}
	}
		
}
