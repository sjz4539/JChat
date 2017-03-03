package model;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Responsible for processing packets received by connection threads.
 * This class ensures packets are received from sockets as quickly
 * as possible (rather than have connection threads process
 * packets themselves).
 * 
 * @author Steven Zuchowski
 *
 */
public abstract class Mailroom {

	protected static LinkedBlockingQueue<Packet> packetQueue = new LinkedBlockingQueue<Packet>();
	private static LinkedList<Worker> workers;
	
	public static void setNumWorkers(int numThreads){
		//init our worker list if we need to
		if(workers == null){
			workers = new LinkedList<Worker>();
		}
		
		if(numThreads > workers.size()){
			//start more threads
			while(workers.size() < numThreads){
				workers.add(new Worker());
				workers.getLast().start();
			}
		}else if(numThreads < workers.size()){
			//stop the extra threads
			for(int i = workers.size() -  numThreads; i > 0; i--){
				workers.get(0).shutdown();
			}
			//wake all threads up so the extras will terminate
			packetQueue.notifyAll();
		}
		
	}
	
	public static void deliverPacket(Packet p) throws InterruptedException{
		packetQueue.put(p);
	}
	
	public static void shutdown(){
		for(Worker w : workers){
			w.shutdown();
			w.interrupt();
		}
	}
	
	private static class Worker extends Thread{
		
		private boolean done = false;
		private Packet packet;
		
		public void run(){
			while(!done){
				try {
					packet = null; //clear this first
					packet = packetQueue.take(); //attempt to assign a packet
					if(packet != null){
						//process a packet if we got one
						Hub.direct(packet);
					}
				} catch (InterruptedException e) {
					if(!done){
						e.printStackTrace();
					}
				}
			}
		}
		
		public void shutdown(){
			done = true;
		}
	}
	
}
