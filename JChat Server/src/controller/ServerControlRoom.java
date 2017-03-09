package controller;

import model.Command;
import model.Conversation;
import model.Packet;
import model.Packet.Action;
import model.Packet.Param;
import model.Response;

/**
 * Server implementation of ControlRoom.
 * 
 * @author Steven Zuchowski
 */
public class ServerControlRoom implements IControlRoom{

	public void process(Packet p) {
		switch(p.getParam(Param.COMMAND)){
		
			case Command.SET_USERNAME:
				Packet su_response = new Packet(Action.RESPONSE);
				su_response.putParam(Param.REQUEST_ID, p.getParam(Param.REQUEST_ID));
				
				//check for necessary arguments
				if(!p.hasParam(Param.USERNAME)){
					su_response.putParam(Param.RESPONSE_CODE, Response.MISSING_PARAM);
					
				//check to see if this username is taken already
				}else if(ServerSwitchboard.containsConnectionId(p.getParam(Param.USERNAME))){
					su_response.putParam(Param.RESPONSE_CODE, Response.USERNAME_TAKEN);
					
				//update the connection<->username mapping
				}else{
					ServerSwitchboard.register(p.getSource(), p.getParam(Param.USERNAME));
					su_response.putParam(Param.RESPONSE_CODE, Response.OK);
				}
				
				p.getSource().send(su_response);
				break;
				
			case Command.JOIN_CONVERSATION:
				Packet jc_response = new Packet(Action.RESPONSE);
				jc_response.putParam(Param.REQUEST_ID, p.getParam(Param.REQUEST_ID));
				
				if(!p.hasParam(Param.CONVERSATION_ID)){
					jc_response.putParam(Param.RESPONSE_CODE, Response.MISSING_PARAM);
					
				}else{
					if(!Hub.getSwitchboard().hasConversation(p.getParam(Param.CONVERSATION_ID))){
						Hub.getSwitchboard().addConversation(new Conversation(p.getParam(Param.CONVERSATION_ID)));
					}
					
					Conversation cv = Hub.getSwitchboard().getConversation(p.getParam(Param.CONVERSATION_ID));
					cv.addUser(ServerSwitchboard.getConnectionId(p.getSource()));
					
					jc_response.putParam(Param.RESPONSE_CODE, Response.OK);
					jc_response.putParam(Param.USER_LIST, cv.getUsersString());
					
				}
				
				p.getSource().send(jc_response);
				break;
				
			case Command.CONVERSATION_LIST:
				Packet cl_response = new Packet(Action.RESPONSE);
				cl_response.putParam(Param.RESPONSE_CODE, p.getParam(Param.REQUEST_ID));
				cl_response.putParam(Param.CONVERSATION_LIST, ServerSwitchboard.convsToString());
				
				p.getSource().send(cl_response);
				
			case Command.TERMINATE:
				//close the socket this command came through
				break;
				
			default:
				break;
		
		}
	}

}
