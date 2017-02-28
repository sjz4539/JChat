package view;

import javafx.scene.control.Tab;
import model.Connection;

public class ConnectionView extends Tab{

	//model instance
	private Connection conn;
	
	//GUI components
	
	
	public ConnectionView(Connection c){
		conn = c;
	}
	
	public void onModelUpdated(){
		
	}
	
}
