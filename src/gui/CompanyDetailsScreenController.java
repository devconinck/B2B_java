package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.DomainController;
import domain.Observer;
import domain.Order;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import util.Validation;

public class CompanyDetailsScreenController extends AnchorPane implements Observer {

	@FXML
	private TextField nameField;
	@FXML
	private TextField vatField;
	@FXML
	private TextField sectorField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField addressNrField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField postalcodeField;
	@FXML
	private TextField countryField;
	@FXML
	private TextField bankField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emailField;
	
	// Niet goed
	public SimpleBooleanProperty isActive;

	// private AdminController dc;
	private DomainController dc;

	public CompanyDetailsScreenController(DomainController dc) {
		this.dc = dc;
		this.dc.addObserver(this);

		buildGui();
		// init();
	}

	/*
	 * private void init() { // Add a button to save the changes to the user's
	 * details saveButton.setOnAction(event -> { // Validate the user input before
	 * updating the user's details if (validateInput()) { // Update the user's
	 * details with the new values CompanyDTO updatedCompany = new CompanyDTO(
	 * //HIER ALLE DATA VAN COMAPNYDTO );
	 * 
	 * 
	 * // Save the changes to the user's details in the database
	 * dc.updateUser(updatedUser, currentUserId);
	 * 
	 * showInfoAlert("User information succesfully updated!", "User updated");
	 * dc.setCurrentProcess(this.currentUserId, true); } });
	 * 
	 * 
	 * }
	 */

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("companyDetails.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Company details Screen");
			System.out.println(e.getMessage());
		}

	}

	public void loadCompany(String vat) {
		Company c = dc.getCompany(vat);

		this.nameField.setText(c.getName());
		this.vatField.setText(c.getVatNumber());
		this.sectorField.setText(c.getSector());
		this.streetField.setText(c.getAddress().getStreet());
		this.addressNrField.setText(c.getAddress().getNumber());
		this.cityField.setText(c.getAddress().getCity());
		this.postalcodeField.setText(c.getAddress().getZipCode());
		this.countryField.setText(c.getAddress().getCountry());
		this.bankField.setText(Long.toString(c.getBankAccountNr()));
		this.phoneField.setText(c.getContact().getPhoneNumber());
		this.emailField.setText(c.getContact().getEmail());
		
		this.isActive = c.getIsActiveProperty();
		
		/*
		 * if (this.currentUserId.equals(dc.getCurrentUserId())) {
		 * buttonBox.getChildren().clear(); buttonBox.getChildren().setAll(saveButton,
		 * cancelBtn); } else { buttonBox.getChildren().clear();
		 * buttonBox.getChildren().setAll(saveButton, deleteUserBtn, cancelBtn); }
		 */
	}

	public void clearAllFields() {	    
	    this.nameField.clear();
	    this.vatField.clear();
	    this.sectorField.clear();
	    this.streetField.clear();
	    this.addressNrField.clear();
	    this.cityField.clear();
	    this.postalcodeField.clear();
	    this.countryField.clear();
	    this.bankField.clear();
	    this.phoneField.clear();
	    this.emailField.clear();

	}


	private boolean isInputValid() {		
	    if (	this.nameField.getText().isEmpty() ||
	    	    this.vatField.getText().isEmpty() ||
	    	    this.sectorField.getText().isEmpty() ||
	    	    this.streetField.getText().isEmpty() ||
	    	    this.addressNrField.getText().isEmpty() ||
	    	    this.cityField.getText().isEmpty() ||
	    	    this.postalcodeField.getText().isEmpty() ||
	    	    this.countryField.getText().isEmpty() ||
	    	    this.bankField.getText().isEmpty() ||
	    	    this.phoneField.getText().isEmpty() ||
	    	    this.emailField.getText().isEmpty()) {
	        showErrorAlert("Please fill in all fields.");
	        return false;
	    }

	    // Name
	    // Nuttig?
	    
	    // Vat
	    if (!vatField.getText().matches(Validation.vatRegex)) {
	        showErrorAlert("Please enter a valid VAT Number.");
	        return false;
	    }
	    
	    // House Number
	    // Nuttig?
	    
	    // Sector
	    // Nuttig?
	    
	    // Street
	    // Nuttig?
	    
	    // House Number
	    if (!addressNrField.getText().matches(Validation.houseNumberRegex)) {
	        showErrorAlert("Please enter a valid house number.");
	        return false;
	    }
	    
	    // City
	    // Nuttig?
	    
	    // Postal Code
	    // Wereldwijde postcodes hebben geen patroon, dus alles toelaten?
	    
	    // Country
	    // Nuttig?
	    
	    // Bank
	    if (!bankField.getText().matches(Validation.ibanRegex)) {
	        showErrorAlert("Please enter a valid bank account number.");
	        return false;
	    }
	    
	    // Phone
	    if (!phoneField.getText().matches(Validation.phoneNumberRegex)) {
	        showErrorAlert("Please enter a valid mobile number.");
	        return false;
	    }
	    
	    // Email
	    if (!emailField.getText().matches(Validation.emailRegex)) {
	        showErrorAlert("Please enter a valid email address.");
	        return false;
	    }
	    return true;
	}


	private void showErrorAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();

	}

	private void showInfoAlert(String message, String title) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@Override
	public void update(Company c) {
		loadCompany(c.getVatNumber());

	}
	
	public void persistCompany() {		
		if (isInputValid()) {
			String vatNumber = vatField.getText();
			
			Company existingCompany = dc.getCompany(vatNumber);
			if (existingCompany != null) {
				// update
				existingCompany.setName(nameField.getText());
                existingCompany.setVatNumber(vatNumber);
                existingCompany.setSector(sectorField.getText());
                existingCompany.getAddress().setCountry(countryField.getText());
                existingCompany.getAddress().setCity(cityField.getText());
                existingCompany.getAddress().setZipCode(postalcodeField.getText());
                existingCompany.getAddress().setStreet(streetField.getText());
                existingCompany.getAddress().setNumber(addressNrField.getText());
                existingCompany.setBankAccountNr(Long.parseLong(bankField.getText()));
                existingCompany.getContact().setPhoneNumber(phoneField.getText());
                existingCompany.getContact().setEmail(emailField.getText());
				
				dc.updateCompany(existingCompany);
			} else {
				// insert	
				Address tempAddress = new Address(countryField.getText(), cityField.getText(), postalcodeField.getText(), streetField.getText(), addressNrField.getText());
				Contact tempContact = new Contact(phoneField.getText(), emailField.getText());
				Company tempCompany = new Company(vatField.getText(), "", tempAddress, tempContact, nameField.getText(), sectorField.getText(), Long.parseLong(bankField.getText()), new ArrayList<String>(), new Date());
				dc.addCompany(tempCompany);
			}	
			showInfoAlert("Company saved", "The company has been saved");
		}
	}

	@Override
	public void update(Order c) {
		// TODO Auto-generated method stub
		
	}
}
