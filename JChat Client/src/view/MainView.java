package view;

import javafx.scene.Group;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class MainView extends Group{

	private TabPane connectionPane;
	
	public MainView(){
		connectionPane = new TabPane();
		connectionPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
	}
	
	public void addConnectionView(ConnectionView cv){
		connectionPane.getTabs().add(cv);
	}

	public void removeConnectionView(ConnectionView cv){
		connectionPane.getTabs().remove(cv);
	}
	
}
