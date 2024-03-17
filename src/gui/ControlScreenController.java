package gui;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ControlScreenController extends HBox {
	private CompanyDetailsScreenController companyDetails;

	private Button clearBtn;
	private Button saveButton;
	private Button inactiveBtn;

	public ControlScreenController(CompanyDetailsScreenController companyDetails) {
		this.companyDetails = companyDetails;
	}

	public void createButtons() {
		this.getChildren().clear();
		
        setPadding(new Insets(10, 50, 10, 50));
        setSpacing(25);
        setAlignment(Pos.CENTER);
		
		clearBtn = new Button("Clear");
		saveButton = new Button("Save");
		inactiveBtn = new Button();
		
		inactiveBtn.textProperty().bind(
		    Bindings.when(companyDetails.isActive)
		        .then("Active")
		        .otherwise("Inactive")
		);

		this.getChildren().addAll(clearBtn, saveButton, inactiveBtn);
		
		saveButton.setOnMouseClicked(e -> companyDetails.persistCompany());

		// Dikke saus code
		inactiveBtn.setOnMouseClicked(e -> companyDetails.toggleIsActive());
		
		clearBtn.setOnMouseClicked(e -> companyDetails.clearAllFields());
	}

}
