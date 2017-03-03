package view;

import model.Connection;
import model.Conversation;

public class TreeItemContainer {

	private Connection cn;
	private Conversation cv;
	
	public TreeItemContainer(Connection c){
		cn = c;
	}
	
	public TreeItemContainer(Conversation c){
		cv = c;
	}

	public Connection getConnection(){
		return cn;
	}
	
	public Conversation getConversation(){
		return cv;
	}
	
}
