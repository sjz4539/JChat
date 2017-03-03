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
	
	//simple conversation id counter
	private int lastcid = 0;

	//map of ids -> conversations
	private HashMap<Integer, Conversation> conversations;

	//list of connections
	private ArrayList<Connection> connections;
	
	public Switchboard(){
		conversations = new HashMap<Integer, Conversation>();
		connections = new ArrayList<Connection>();
	}
	
	public abstract void process(Packet p);
	
	public void addConnection(Connection c){
		connections.add(c);
	}
	
	public void removeConnection(Connection c){
		connections.remove(c);
	}
	
	public void addConversation(Conversation c){
		conversations.put(c.getId(), c);
	}
	
	public Conversation getConversation(int id){
		return conversations.get(id);
	}
	
	public void removeConversation(Conversation c){
		conversations.remove(c.getId());
	}
	
	public int getConversationId(){
		lastcid++;
		return lastcid;
	}
	
	public void sendMessage(String s, Conversation c){
		
	}
	
	public void shutdown(){
		for(Connection c : connections){
			c.shutdown();
		}
	}
	
	
	
}
