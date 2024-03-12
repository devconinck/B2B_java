package gui;

import domain.Address;
import domain.Company;
import domain.Contact;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import repository.GenericDaoJpa;

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
		inactiveBtn.setOnMouseClicked(e -> companyDetails.isActive.setValue(!companyDetails.isActive.getValue()));
		
		clearBtn.setOnMouseClicked(e -> companyDetails.clearAllFields());
	}

}
