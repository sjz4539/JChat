package view.dialog;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import view.form.ConnectForm;

public class ConnectDialog extends Dialog<ButtonType>{

	private ConnectForm formPane;
	
	public ConnectDialog(){
		formPane = new ConnectForm();
		getDialogPane().setContent(formPane);
		getDialogPane().getButtonTypes().clear();
		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
	}
	
	public String getHost(){
		return formPane.getHost();
	}
	
}
