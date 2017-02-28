package view;

import java.util.HashMap;

import model.Connection;
import model.Conversation;

public abstract class ViewRegistry {

	private static HashMap<Connection, ConnectionView> connectionViews = new HashMap<Connection, ConnectionView>();
	private static HashMap<Conversation, ConversationView> conversationViews = new HashMap<Conversation, ConversationView>();
	
	public static ConnectionView getView(Connection con){
		if(!connectionViews.containsKey(con)){
			connectionViews.put(con, new ConnectionView(con));
		}
		return connectionViews.get(con);
	}
	
	public static void removeView(Connection con){
		connectionViews.remove(con);
	}
	
	public static ConversationView getView(Conversation con){
		if(!conversationViews.containsKey(con)){
			conversationViews.put(con, new ConversationView(con));
		}
		return conversationViews.get(con);
	}
	
	public static void removeView(Conversation con){
		conversationViews.remove(con);
	}
	
}
