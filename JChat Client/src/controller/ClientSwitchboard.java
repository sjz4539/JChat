package controller;

import view.ViewRegistry;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.Command;
import model.Connection;
import model.Conversation;
import model.Packet;
import model.Packet.Param;

/**
 * Client implementation of Switchboard.
 * 
 * @author Steven Zuchowski
 */
public class ClientSwitchboard extends Switchboard{

	private static HashMap<Conversation, Connection> convMap = new HashMap<Conversation, Connection>();
	
	public void process(Packet p) {
		ViewRegistry.getView(
				Hub.getSwitchboard().getConversation(p.getParam(Packet.Param.DESTINATION))
		).postMessage(p);
	}
	
	/**
	 * Maps a Conversation to a Connection for later lookup.
	 * Should be called when a Conversation is joined.
	 * @param cv A newly-joined Conversation
	 * @param cn The Connection used to communicate with the specified Conversation
	 */
	public static void register(Conversation cv, Connection cn){
		convMap.put(cv, cn);
	}
	
	/**
	 * Destroys a Conversation->Connection mapping.
	 * Should be called when a Conversation is exited.
	 * @param cv The Conversation that was exited
	 */
	public static void deregister(Conversation cv){
		convMap.remove(cv);
	}
	
	/**
	 * @param cv A Conversation
	 * @return The Connection used to communicate with the specified Conversation
	 */
	public static Connection getConnectionFor(Conversation cv){
		return convMap.get(cv);
	}
	
	/**
	 * Attempts to open a Connection with a server instance.
	 * @param host The IP address or hostname where the server can be located.
	 */
	public static void connectTo(String host){
		//try to form a new connection using the default port and host string supplied
		//on failure, put up an alert.
		try {
			Connection newCon = new Connection(new Socket(host, Connection.PORT_NUMBER));
			Hub.getSwitchboard().addConnection(newCon);
			// throw this at the GUI to make a new element for it
			
		} catch (UnknownHostException e) {
			new Alert(AlertType.ERROR, "The specified host could not be found.", ButtonType.OK).show();
			e.printStackTrace();
		} catch (IOException e) {
			new Alert(AlertType.ERROR, "Unable to connect to the specified host:\n" + e.getMessage(), ButtonType.OK).show();
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes a specified Connection.
	 * @param c The Connection to be closed
	 */
	public static void disconnectFrom(Connection c){
		c.shutdown();
		Hub.getSwitchboard().removeConnection(c);
	}
	
}
