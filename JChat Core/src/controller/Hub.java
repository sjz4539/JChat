package controller;

import model.Packet;

/**
 * Inspects packets and directs them to the proper handling class.
 * Requires an implementation of multiple components, creating a
 * split between client/server design.
 * 
 * @author Steven Zuchowski
 */
public abstract class Hub {

	private static Switchboard switchboard;
	private static IControlRoom controlRoom;
	private static ResponseRegistry registry;

	public static void init(Switchboard sb, IControlRoom cr){
		switchboard = sb;
		controlRoom = cr;
		registry = new ResponseRegistry();
	}
	
	public static void direct(Packet p){
		switch(p.getType()){
			case COMMAND:
				controlRoom.process(p);
				break;
			case MESSAGE:
				switchboard.process(p);
				break;
			case RESPONSE:
				registry.process(p);
				break;
			default:
				//illegal packet type, send a response informing the client.
				break;
		}
	}
	
	public static Switchboard getSwitchboard(){
		return switchboard;
	}
	
	public static ResponseRegistry getRegistry(){
		return registry;
	}
	
}
