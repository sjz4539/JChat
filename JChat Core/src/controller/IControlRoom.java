package controller;

import model.Packet;

/**
 * Processes and executes commands.
 * 
 * @author Steven Zuchowski
 */
public interface IControlRoom {

	public void process(Packet p);
	
}
