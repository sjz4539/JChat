package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a conversation between multiple users.
 * Same class is used for an open room, a private room,
 * and a private messaging session.
 * 
 * @author Steven Zuchowski
 *
 */
public class Conversation {

	private Set<User> users;
	private int id;
	
	public Conversation(int cid){
		id = cid;
		users = Collections.synchronizedSet(new TreeSet<User>());
	}
	
	public Conversation(ArrayList<User> initialUsers){
		for(User u : initialUsers){
			users.add(u);
		}
	}
	
	public void addUser(User u){
		users.add(u);
	}
	
	public Set<User> getUsers(){
		return users;
	}
	
	public int getId(){
		return id;
	}

}
