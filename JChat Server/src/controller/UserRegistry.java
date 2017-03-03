package controller;

import java.util.HashMap;

import model.Connection;

public abstract class UserRegistry {
	
	private static HashMap<String, Connection> userConnections = new HashMap<String, Connection>();

	/**
	 * Registers a user and assigns them a user ID.
	 * @param u A user to register.
	 * @return The user id for the newly-registered user.
	 */
	public static void register(String un, Connection c){
		userConnections.put(un, c);
	}
	
	public static void deregister(String un){
		userConnections.remove(un);
	}
	
	public Connection getConnection(String un){
		 return userConnections.get(un);
	}
}
