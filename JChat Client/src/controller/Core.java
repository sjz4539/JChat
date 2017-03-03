package controller;

import java.io.IOException;

import model.Connection;
import model.User;

/**
 * The JChat client core. Program entry point.
 * Initializes program components.
 * 
 * @author Steven Zuchowski
 *
 */
public class Core {
	
	private static final int NUM_WORKER_THREADS = 1;
	public static User me;
	
	public static void init() throws IOException{
		init(Connection.PORT_NUMBER);
	}
	
	public static void init(int port) throws IOException{
		Hub.init(new ClientSwitchboard(), new ControlRoom());
		Mailroom.setNumWorkers(NUM_WORKER_THREADS);
	}
	
	public static void shutdown(){
		Hub.getSwitchboard().shutdown();
		Mailroom.shutdown();
	}
	
}
