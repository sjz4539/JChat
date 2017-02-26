package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Represents a client-server connection. Listens for incoming
 * packets and directs them to the Mailroom for processing.
 * 
 * @author Steven Zuchowski
 *
 */
public class Connection extends Thread{

	public static final int PORT_NUMBER = 23456;
	public static final int OPERATION_TIMEOUT = 5000;
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean close = false;
	
	public Connection(Socket s) throws SocketException, IOException{
		socket = s;
		s.setSoTimeout(OPERATION_TIMEOUT);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}
	
	public void run(){
		try{
			while(!close){
				Packet nextPacket = (Packet)(ois.readObject());
				nextPacket.setSource(this);
				try {
					Mailroom.deliverPacket(nextPacket);
				} catch (InterruptedException e) {
					if(!close){
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Packet p){
		synchronized(oos){
			try {
				oos.writeObject(p);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){
		close = true;
		this.interrupt();
	}
	
}
