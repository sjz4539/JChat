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

	private Set<User> users;
	private int id;
	private Connection con;
	
	public Conversation(Connection c){
		this(Hub.getSwitchboard().getConversationId(), c);
	}
	
	public Conversation(Connection c, Set<User> initialUsers){
		this(Hub.getSwitchboard().getConversationId(), c, initialUsers);
	}
	
	public Conversation(int cid, Connection c){
		id = cid;
		con = c;
		users = Collections.synchronizedSet(new TreeSet<User>());
	}
	
	public Conversation(int cid, Connection c, Set<User> initialUsers){
		id = cid;
		con = c;
		users = Collections.synchronizedSet(new TreeSet<User>());
		setUsers(initialUsers);
	}

	public void addUser(User u){
		users.add(u);
	}
	
	public void removeUser(User u){
		users.remove(u);
	}
	
	public Set<User> getUsers(){
		return users;
	}
	
	public void setUsers(Set<User> newUsers){
		users.clear();
		for(User u : newUsers){
			users.add(u);
		}
	}
	
	public int getId(){
		return id;
	}
	
	public Connection getConnection(){
		return con;
	}

}
