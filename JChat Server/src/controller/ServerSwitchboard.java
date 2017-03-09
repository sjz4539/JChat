package controller;

import java.util.Random;

import model.BiMap;
import model.Command;
import model.Connection;
import model.Packet;
import model.Packet.Action;
import model.Packet.Param;

/**
 * Server implementation of Switchboard.
 * Relays message packets to their destinations
 * and tracks id<->connection mappings.
 * 
 * @author Steven Zuchowski
 */
public class ServerSwitchboard extends Switchboard{
	
	private static BiMap<String, Connection> idMap = new BiMap<String, Connection>();
	private static Random rand = new Random();
	
	public void process(Packet p) {
		//attach the sender's username to this packet
		p.putParam(Param.SENDER, getConnectionId(p.getSource()));
		
		//determine whether this packet has a destination or recipient
		if(p.hasParam(Param.DESTINATION) && this.hasConversation(p.getParam(Param.DESTINATION))){
			//get a list of usernames to forward this packet to
			//forward it to each one
			for(String id : this.getConversation(p.getParam(Param.DESTINATION)).getUsers()){
				if(containsConnectionId(id)){
					getConnection(id).send(p);
				}
			}
		}else if(p.hasParam(Param.RECIPIENT)){
			//get the connection for the specified user
			//forward the packet
			if(containsConnectionId(p.getParam(Param.RECIPIENT))){
				getConnection(p.getParam(Param.RECIPIENT)).send(p);
			}
		}
		
	}

	public void addConnection(Connection cn){
		//add the connection to our list and register it with a username
		super.addConnection(cn);
		register(cn);
		
		//inform the new user their username has been set
		Packet p = new Packet(Action.COMMAND);
		p.putParam(Param.COMMAND, Command.USERNAME_SET);
		p.putParam(Param.USERNAME, getConnectionId(cn));
		cn.send(p);
	}
	
	public static String convsToString(){
		StringBuffer buf = new StringBuffer();
		for(String id : Hub.getSwitchboard().conversations.keySet()){
			buf.append(id);
			buf.append(";");
		}
		return buf.toString();
	}
	
	/**
	 * Registers a Connection to a generated id.
	 * @param cn A Connection to register.
	 */
	public static void register(Connection cn){
		register(cn, genId());
	}
	
	/**
	 * Registers a Connection to the specified id.
	 * @param cn A Connection to register.
	 * @param id The id to register the Connection to.
	 */
	public static void register(Connection cn, String un){
		idMap.putA(un, cn);
	}
	
	/**
	 * Removes a specified Connection id and any Connection it
	 * maps to from the registry.
	 * 
	 * @param id A Connection id
	 */
	public static void deregister(String id){
		idMap.removeA(id);
	}
	
	/**
	 * Removes a Connection and any id it maps to
	 * from the registry.
	 * 
	 * @param cn A Connection
	 */
	public static void deregister(Connection cn){
		idMap.removeB(cn);
	}
	
	public static Connection getConnection(String id){
		 return idMap.getA(id);
	}
	
	public static boolean containsConnectionId(String id){
		return idMap.containsA(id);
	}
	
	public static String getConnectionId(Connection cn){
		return idMap.getB(cn);
	}
	
	public static boolean containsConnection(Connection cn){
		return idMap.containsB(cn);
	}
	
	private static String genId(){
		
		String id;
		
		do{
			//burn a few digits
			rand.nextInt();
			rand.nextInt();
			rand.nextInt();
			
			//get the next 8 ints and convert to characters
			id = new StringBuilder()
					.append("rando_")
					.append(Integer.toString(rand.nextInt(100000000)))
					.toString();
		}while(containsConnectionId(id));
		
		return id;
	}
	
}
