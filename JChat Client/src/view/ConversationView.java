package view;

import controller.ClientSwitchboard;
import controller.Hub;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import model.Conversation;
import model.Packet;

public class ConversationView extends Group{

	//model instance
	private Conversation con;
	
	//GUI components
	private TextArea messageBoard, inputBox;
	private ScrollPane messageScroll, inputScroll;
	private Button submitButton;
	
	public ConversationView(Conversation c){
		
		BorderPane root = new BorderPane();
		BorderPane inputPane = new BorderPane();
		
		messageBoard = new TextArea();
		inputBox = new TextArea();
		messageScroll = new ScrollPane();
		inputScroll = new ScrollPane();
		submitButton = new Button("Send");
		
		submitButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				sendMessage();
			}
		});
		
		messageScroll.setContent(messageBoard);
		messageScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		messageScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		inputScroll.setContent(inputBox);
		inputScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		inputScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		inputPane.setCenter(inputScroll);
		inputPane.setRight(submitButton);
		
		root.setCenter(messageScroll);
		root.setBottom(inputPane);
		
		getChildren().add(root);
	}
	
	public void postMessage(Packet p){
		messageBoard.appendText(p.getParam(Packet.Param.USERNAME) + ": " + p.getParam(Packet.Param.MESSAGE));
	}
	
	public void sendMessage(){
		Packet message = new Packet(Packet.Action.MESSAGE);
		message.putParam(Packet.Param.MESSAGE, inputBox.getText());
		message.putParam(Packet.Param.DESTINATION, con.getId());
		message.putParam(Packet.Param.USERNAME, "user");
		
		//con.getConnection().send(message);
		//conversations don't store connections
		//users only have one connection to the server
		ClientSwitchboard.getConnectionFor(con).send(message);
		
		inputBox.clear();
	}
	
}
