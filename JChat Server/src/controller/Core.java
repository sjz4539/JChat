package controller;

import java.io.IOException;

import model.Connection;
import model.Hub;
import model.Mailroom;

/**
 * The JChat server core. Program entry point.
 * Initializes program components.
 * 
 * @author Steven Zuchowski
 *
 */
public class Core {
	
	private static final int NUM_WORKER_THREADS = 5;
	private static ConnectionListener listener;
	
	public static void init() throws IOException{
		init(Connection.PORT_NUMBER);
	}
	
	public static void init(int port) throws IOException{
		Hub.init(new ServerSwitchboard(), new ControlRoom());
		Mailroom.setNumWorkers(NUM_WORKER_THREADS);
		listener = new ConnectionListener(port);
		listener.start();
	}
	
	public static void shutdown(){
		listener.shutdown();
		Hub.getSwitchboard().shutdown();
		Mailroom.shutdown();
		
	}
	
}
