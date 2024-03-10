package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class ControlScreenController extends HBox {
	private DomainController dc;
	
    public ControlScreenController(DomainController dc) {
    	this.dc = dc;
    	buildGui();
    }
    
    private void buildGui() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("controlScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Application Screen");
			System.out.println(e.getMessage());
		}
    }
}
