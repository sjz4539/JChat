package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Packet implements Serializable{

	private static final long serialVersionUID = -3303366030413162928L;

	public enum Action{
		MESSAGE, COMMAND, RESPONSE;
	}
	
	public enum Param{
		MESSAGE, COMMAND, RESPONSE_CODE, REQUEST_ID, SENDER, RECIPIENT, DESTINATION, USERNAME, CONVERSATION_ID, USER_LIST, CONVERSATION_LIST
	}
	
	private Action type;
	private Map<Param, String> params;
	private Connection source;
	
	public Packet(Action t){
		type = t;
		params = new HashMap<Param, String>();
	}
	
	/**
	 * @return The Packet.Type of this Packet
	 */
	public Action getType(){
		return type;
	}
	
	/**
	 * Associates a string with a Packet.Param and stores it in this Packet.
	 * @param p A Packet.Param
	 * @param s The String to associate with the given Param
	 */
	public void putParam(Param p, String s){
		params.put(p, s);
	}
	
	/**
	 * Associates an int with a Packet.Param and stores it in this Packet as a String.
	 * @param p A Packet.Param
	 * @param s The String to associate with the given Param
	 */
	public void putParam(Param p, int i){
		params.put(p, Integer.toString(i));
	}
	
	/**
	 * Inserts all Param->String pairs in the given map to this Packet.
	 * @param m A Param->String map.
	 */
	public void putParams(Map<Param, String> m){
		for(Param p : m.keySet()){
			params.put(p, m.get(p));
		}
	}
	
	/**
	 * @param p A Packet.Param
	 * @return The string value associated with the given Param within 
	 * this Packet. Returns an empty string if no such value exists.
	 */
	public String getParam(Param p){
		String ret = params.get(p);
		return (ret == null ? "" : ret);
	}
	
	/**
	 * @return The Param->String map in this Packet.
	 */
	public Map<Param, String> getParams(){
		return params;
	}
	
	/**
	 * @param p A Param
	 * @return True if this Packet has a value set for Param p, false otherwise.
	 */
	public boolean hasParam(Param p){
		return params.containsKey(p);
	}
	
	/**
	 * Assigns this packet to a connection.
	 * @param c The source connection this packet was received through.
	 */
	public void setSource(Connection c){
		source = c;
	}
	
	/**
	 * @return The source connection this packet was received through.
	 */
	public Connection getSource(){
		return source;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		if(type == Action.COMMAND){
			buffer.append("Command: ");
		}else if(type == Action.MESSAGE){
			buffer.append("Message: ");
		}else{
			buffer.append("Response: ");
		}
		
		for(Param p : getParams().keySet()){
			switch(p){
				case REQUEST_ID:
					buffer.append("Request ID");
					break;
				case USERNAME:
					buffer.append("Username");
					break;
				case MESSAGE:
					buffer.append("Message");
					break;
				case DESTINATION:
					buffer.append("Destination");
					break;
				case RECIPIENT:
					buffer.append("Recipient");
					break;
				case COMMAND:
					buffer.append("Command");
					break;
				case RESPONSE_CODE:
					buffer.append("Response Code");
					break;
				case SENDER:
					buffer.append("Sender");
					break;
				default:
					break;
			}		
					
			buffer.append("=");
			buffer.append(getParam(p));
			buffer.append(", ");
		}
		
		return buffer.toString();
	}
	
}
