package controller;

import view.ViewRegistry;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.Command;
import model.Connection;
import model.Packet;
import model.Packet.Param;

/**
 * Client implementation of Switchboard.
 * 
 * @author Steven Zuchowski
 */
public class ClientSwitchboard extends Switchboard{

	public void process(Packet p) {
		ViewRegistry.getView(
				Hub.getSwitchboard().getConversation(Integer.valueOf(p.getParam(Packet.Param.CONVERSATION_ID)))
		).postMessage(p);
	}
	
	public void connectTo(String host){
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
	
	public void disconnectFrom(Connection c){
		Packet bye = new Packet(Packet.Action.COMMAND);
		bye.putParam(Param.COMMAND, Command.TERMINATE);
		c.send(bye);
		removeConnection(c);
	}
	
}
