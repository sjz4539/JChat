package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TreeItemContainerView extends TreeCell<TreeItemContainer>{

	private TreeItemContainer con;
	
	public TreeItemContainerView(TreeItemContainer c, String n){
		con = c;
		setText(n);
		
		Button closeButton = new Button();
		closeButton.setGraphic(new ImageView(new Image("/resources/delete_16.png")));
		closeButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				//close the connection or conversation held in this container
			}
		});
		
		setGraphic(closeButton);
		setContentDisplay(ContentDisplay.RIGHT);
		
	}
	
}
