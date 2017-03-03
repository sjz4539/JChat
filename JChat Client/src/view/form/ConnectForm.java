package view.form;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectForm extends GridPane{

	private TextField host;
	
	public ConnectForm(){
		
		Label hostLabel = new Label("Host:");
		host = new TextField();
		
		setRowIndex(hostLabel, 0);
		setColumnIndex(hostLabel, 0);
		
		setRowIndex(host, 0);
		setColumnIndex(host, 1);
		
		getChildren().addAll(hostLabel, host);
		
	}
	
	public String getHost(){
		return host.getText();
	}
	
}
