package model;

import java.util.HashMap;

/**
 * Keeps a record of request ids awaiting responses 
 * and processes those when they arrive.
 * 
 * @author Steven Zuchowski
 */
public class ResponseRegistry {

	private HashMap<Integer, ResponseHandler> handlers;
	
	public ResponseRegistry(){
		handlers = new HashMap<Integer, ResponseHandler>();
	}
	
	/**
	 * Assigns a handler to a request ID.
	 * @param id A request ID. Must be unique.
	 * @param rh A RequestHandler object to execute when a response to
	 * the specified request ID is received.
	 */
	public void register(int id, ResponseHandler rh){
		handlers.put(id, rh);
	}
	
	/**
	 * Unassigns any handler assigned to a specified request ID.
	 * @param id A request ID.
	 */
	public void deregister(int id){
		handlers.remove(id);
	}
	
	/**
	 * Executes any existing handler registered with the given request ID.
	 * @param p A response-type packet that contains a request ID.
	 */
	public void process(Packet p){
		if(handlers.containsKey(p.getParam(Packet.Param.REQUEST_ID))){
			handlers.get(p.getParam(Packet.Param.REQUEST_ID)).handle(p);
		}
	}
	
}
