package gui.company;

import domain.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlScreenController extends HBox {
    private Button clearButton;
    private Button saveButton;
    private Button inactiveButton;
    
    private AdminController ac;

    public ControlScreenController(AdminController ac) {
    	this.ac = ac;
        buildGui();
    }

    private void buildGui() {
        setPadding(new Insets(10, 50, 10, 50));
        setSpacing(25);
        setAlignment(Pos.CENTER);

        clearButton = new Button("Clear");
        saveButton = new Button("Save");
        inactiveButton = new Button("Inactive");

        this.getChildren().addAll(clearButton, saveButton, inactiveButton);
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getInactiveButton() {
        return inactiveButton;
    }

    public void updateButtonText() {
    	try {
    		inactiveButton.setText(ac.getSelectedCompanyProperty().get().getIsActive() ? "Inactive" : "Active");
    	} catch (Exception e) {
    		// Do nothing
    	}
    	
    }
}