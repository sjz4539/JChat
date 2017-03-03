package model;

import javafx.beans.property.SimpleStringProperty;

public class User {

	private SimpleStringProperty name;
	
	public User(String un){
		name = new SimpleStringProperty(un);
	}
	
	public String getName(){
		return name.getValue();
	}
}
