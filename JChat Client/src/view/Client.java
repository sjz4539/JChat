package view;

import java.io.IOException;

import controller.ClientSwitchboard;
import controller.ControlRoom;
import controller.Core;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Hub;
import model.Mailroom;

public class Client extends Application{
	
	private static Label dummy = new Label("No Active Connections. Use Main->Connect to get started!");
	
	private static Button theButton;
	private static Label status;
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setOnCloseRequest((closeRequest)->{
			//program is closing, shut down all threads
			Core.shutdown();
		});
		
		primaryStage.setTitle("JChat Client");
		
		//program menu
		MenuBar topMenu = new MenuBar();
		Menu mainMenu = new Menu("Main");
		MenuItem connectMenuItem = new MenuItem("Connect...");
		MenuItem quitMenuItem = new MenuItem("Quit");
		mainMenu.getItems().addAll(connectMenuItem, quitMenuItem);
		topMenu.getMenus().add(mainMenu);
		
		connectMenuItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				//show connect dialog
			}
		});
		
		quitMenuItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Core.shutdown();
				primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			}
		});
		
		
		

		BorderPane root = new BorderPane();
		root.setCenter(dummy);
		root.setTop(topMenu);
		
		Hub.init(new ClientSwitchboard(), new ControlRoom());
		Mailroom.setNumWorkers(1);
		
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
		
	}

}
