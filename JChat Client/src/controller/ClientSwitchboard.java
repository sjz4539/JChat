package controller;

import model.Switchboard;
import view.ViewRegistry;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

import model.Connection;
import model.Hub;
import model.Packet;

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

	public void connect(SocketAddress addr) throws IOException{
		Socket sock  = new Socket();
		sock.connect(addr, CONNECTION_TIMEOUT);
		
		addConnection(new Connection(sock));
		// throw this at the GUI to make a new element for it
	}
	
}
