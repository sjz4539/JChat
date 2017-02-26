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
		REQUESTID, USERNAME, MESSAGE, CONVERSATION_ID, COMMAND, RESPONSE_CODE
	}
	
	public static final String RESPONSE_OK = "OK";
	public static final String RESPONSE_MISSING_PARAM = "MISSING_PARAM";
	public static final String RESPONSE_INVALID_PARAM = "INVALID_PARAM";
	
	public static final String USERNAME_TAKEN = "USERNAME_TAKEN";
	
	public static final String INVALID_CONVERSATION = "INVALID_CONVERSATION";
	
	private Action type;
	private Map<Param, String> params;
	private Connection source;
	
	public Packet(Action t){
		type = t;
		params = new HashMap<Param, String>();
		//loop through the whole packet map for this packet type
		//grab params out of the map and put them in the array
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
	
}
