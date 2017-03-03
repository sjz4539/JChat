package view;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Connection;
import model.Conversation;

public class ConnectionTree extends TreeView<ConnectionTree.Container>{
	
	private HashMap<Connection, TreeItem<Container>> connections;
	private HashMap<Conversation, TreeItem<Container>> conversations;
	
	private TreeItem<Container> root = new TreeItem<Container>();
	
	public ConnectionTree(){
		setCellFactory(new Callback<TreeView<ConnectionTree.Container>, TreeCell<ConnectionTree.Container>>(){
			public TreeCell<Container> call(TreeView<Container> param) {
				return new ContainerView();
			}
		});
		this.setRoot(root);
	}
	
	public void addConnection(Connection cn){
		connections.put(cn, new TreeItem<Container>(new Container(cn)));
		root.getChildren().add(connections.get(cn));
	}
	
	public void removeConnection(Connection cn){
		root.getChildren().remove(connections.get(cn));
		connections.remove(cn);
	}
	
	public void addConversation(Conversation cv, Connection cn){
		conversations.put(cv, new TreeItem<Container>(new Container(cv)));
		root.getChildren().get(root.getChildren().indexOf(cn)).getChildren().add(conversations.get(cv));
	}
	
	public void removeConversation(Conversation cv, Connection cn){
		root.getChildren().get(root.getChildren().indexOf(cn)).getChildren().remove(conversations.get(cv));
		conversations.remove(cv);
	}
	
	public static class Container{

		private Connection cn;
		private Conversation cv;
		
		public Container(Connection c){
			cn = c;
		}
		
		public Container(Conversation c){
			cv = c;
		}

		public Connection getConnection(){
			return cn;
		}
		
		public Conversation getConversation(){
			return cv;
		}
		
		public String getName(){
			return ".";
		}
		
	}
	
	private class ContainerView extends TreeCell<Container>{

		public ContainerView(){
			
			Button closeButton = new Button();
			closeButton.setGraphic(new ImageView(new Image("/resources/delete_16.png")));
			closeButton.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					if(getItem().getConnection() != null){
						//close the connection
					}else{
						//close the conversation
					}
				}
			});
			
			setGraphic(closeButton);
			setContentDisplay(ContentDisplay.RIGHT);
		}
		
		protected void updateItem(Container item, boolean empty){
			super.updateItem(item, empty);
			setText(empty ? "" : item.getName());
		}
		
	}
	
}
