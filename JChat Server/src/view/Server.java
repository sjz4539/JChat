package view;

import java.io.IOException;

import controller.Core;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Server extends Application{

	public static boolean running = false;
	
	private static Button theButton;
	private static Label status;
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setOnCloseRequest((closeRequest)->{
			//program is closing, shut down all threads
			if(running){
				Core.shutdown();
			}
		});
		
		primaryStage.setTitle("JChat Server Control");
		
		theButton = new Button("Start Server");
		status = new Label("Server stopped.");
		BorderPane root = new BorderPane();
		
		theButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Server.toggle();
			}
		});
		
		root.setCenter(theButton);
		root.setBottom(status);
		
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	}
	
	public static void toggle(){
		if(running){
			Core.shutdown();
			theButton.setText("Start Server");
			status.setText("Server stopped.");
			running = false;
		}else{
			try {
				Core.init();
				theButton.setText("Stop Server");
				status.setText("Server running.");
				running = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
