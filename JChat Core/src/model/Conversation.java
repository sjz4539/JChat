package model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import controller.Hub;

/**
 * Represents a conversation between multiple users.
 * Same class is used for an open room, a private room,
 * and a private messaging session.
 * 
 * @author Steven Zuchowski
 *
 */
public class Conversation {

	private Set<String> users;
	private String id;

	public Conversation(String cid){
		id = cid;
		users = Collections.synchronizedSet(new TreeSet<String>());
	}
	
	public Conversation(String cid, Set<String> initialUsers){
		this(cid);
		setUsers(initialUsers);
	}

	public void addUser(String un){
		users.add(un);
	}
	
	public void removeUser(String un){
		users.remove(un);
	}
	
	public Set<String> getUsers(){
		return users;
	}
	
	public String getUsersString(){
		StringBuffer buf = new StringBuffer();
		for(String u : users){
			buf.append(u);
			buf.append(";");
		}
		return buf.toString();
	}
	
	public void setUsers(Set<String> newUsers){
		users.clear();
		for(String un : newUsers){
			users.add(un);
		}
	}
	
	public String getId(){
		return id;
	}

}
