package model;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import controller.Mailroom;

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
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		this.start();
	}
	
	public void run(){
		try{
			while(!close){
				try {
					Packet nextPacket = (Packet)(ois.readObject());
					System.out.println("Packet received: " + nextPacket.toString());
					nextPacket.setSource(this);
					Mailroom.deliverPacket(nextPacket);
				} catch (InterruptedException e) {
					if(!close){
						e.printStackTrace();
					}
				} catch (EOFException e){
					e.printStackTrace();
				} catch (SocketException e){
					e.printStackTrace();
					close = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				if(oos != null){
					oos.close();
				}
				if(ois != null){
					ois.close();
				}
			} catch (IOException e) {
				//gg was close
				e.printStackTrace();
			}
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
