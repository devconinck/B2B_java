package gui;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LoginScreenController extends Pane {
	
	 private DomainController dc;
	 private HBox hb = new HBox();

	public LoginScreenController(DomainController dc) {
		this.dc = dc;
		buildGUI();
	}

	private void buildGUI() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SpelOverzicht.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
		
	}
	
	

}
