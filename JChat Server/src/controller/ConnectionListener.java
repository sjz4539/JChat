package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

import model.Connection;
import model.Hub;
import model.Switchboard;

/**
 * Monitor class that listens for incoming connection requests
 * and creates/registers now Connection instances using the
 * resulting Sockets.
 * 
 * @author Steven Zuchowski
 */
public class ConnectionListener extends Thread{
	
	private boolean stop = false;
	private ServerSocket socket;
	
	public ConnectionListener(int port) throws IOException{
		socket = new ServerSocket(port);
	}
	
	public void run(){
		while(!stop){
			try {
				Hub.getSwitchboard().addConnection(new Connection(socket.accept()));
			} catch (SocketException e){
				if(!stop){
					//error setting timeout on new socket
					e.printStackTrace();
				}
			} catch (IOException e) {
				//error getting i/o streams from new socket
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){
		stop = true;
		try {
			socket.close();
		} catch (IOException e) {
			// socket exploded while closing
			e.printStackTrace();
		}
	}
	
}
