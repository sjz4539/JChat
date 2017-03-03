package view;

import java.util.HashMap;

import model.Connection;
import model.Conversation;

public abstract class ViewRegistry {

	private static HashMap<Conversation, ConversationView> conversationViews = new HashMap<Conversation, ConversationView>();
	
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
