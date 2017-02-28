package controller;

import java.util.HashMap;

import model.User;

public class UserRegistry {
	
	private HashMap<User, Integer> users;
	private int uid;
	
	public UserRegistry(){
		users = new HashMap<User, Integer>();
		uid = 0;
	}
	
	/**
	 * Registers a user and assigns them a user ID.
	 * @param u A user to register.
	 * @return The user id for the newly-registered user.
	 */
	public int register(User u){
		users.put(u, uid);
		uid++;
		return users.get(u);
	}
	
	public void deregister(User u){
		users.remove(u);
	}
}
