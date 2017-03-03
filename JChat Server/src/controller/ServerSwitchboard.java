package controller;

import model.Command;
import model.Connection;
import model.Packet;
import model.Packet.Param;

/**
 * Server implementation of Switchboard.
 * 
 * @author Steven Zuchowski
 */
public class ServerSwitchboard extends Switchboard{

	public void process(Packet p) {
		
	}
	
	public void addConnection(Connection c){
		super.addConnection(c);
	}

}
