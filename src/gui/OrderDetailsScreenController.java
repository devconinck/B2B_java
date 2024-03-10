package gui;

import java.io.IOException;

import domain.Company;
import domain.DomainController;
import domain.Observer;
import domain.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class OrderDetailsScreenController extends AnchorPane implements Observer {
	
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
    @FXML
    private HBox buttonBox;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveButton;
    @FXML
    private Button inactiveBtn;

    //private AdminController dc;
    private DomainController dc;


	public OrderDetailsScreenController(DomainController dc) {
    	this.dc = dc;
    	this.dc.addObserver(this);
    	
    	buildGui();
    	//init();
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
	 * cancelBtn.setOnAction(event -> { clearAllFields(); });
	 * 
	 * }
	 */

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("orderDetails.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Order details Screen");
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void loadCompany(String companyName) {
		Company c = dc.getCompany(companyName);
		
	    this.nameField.setText(c.getName());
	    this.vatField.setText(Long.toString(c.getVatNumber()));
	    this.sectorField.setText(c.getSector());
	    this.streetField.setText(c.getAddress().getStreet());
	    this.addressNrField.setText(c.getAddress().getNumber());
	    this.cityField.setText(c.getAddress().getCity());
	    this.postalcodeField.setText(c.getAddress().getZipCode());
	    this.countryField.setText(c.getAddress().getCountry());
	    this.bankField.setText(Long.toString(c.getBankAccountNr()));
	    this.phoneField.setText(c.getContact().getPhoneNumber());
	    this.emailField.setText(c.getContact().getEmail());

    	
		/*
		 * if (this.currentUserId.equals(dc.getCurrentUserId())) {
		 * buttonBox.getChildren().clear(); buttonBox.getChildren().setAll(saveButton,
		 * cancelBtn); } else { buttonBox.getChildren().clear();
		 * buttonBox.getChildren().setAll(saveButton, deleteUserBtn, cancelBtn); }
		 */
	}
	
	/*
	 * private void clearAllFields() { this.currentCompany = "";
	 * 
	 * this.userIdField.clear(); this.userIdField.setDisable(true);
	 * this.firstnameField.clear(); this.firstnameField.setDisable(false);
	 * this.lastnameField.clear(); this.lastnameField.setDisable(false);
	 * this.cityField.clear(); this.countryField.clear(); this.emailField.clear();
	 * this.mobileNumberField.clear(); this.phoneNumberField.clear();
	 * this.postalcodeField.clear(); this.streetField.clear();
	 * this.addressNrField.clear();
	 * 
	 * 
	 * buttonBox.getChildren().clear();
	 * buttonBox.getChildren().setAll(createUserBtn, cancelBtn); }
	 */

	/*
	 * private boolean validateInput() { if (cityField.getText().isEmpty() ||
	 * countryField.getText().isEmpty() || emailField.getText().isEmpty() ||
	 * mobileNumberField.getText().isEmpty() || phoneNumberField.getText().isEmpty()
	 * || postalcodeField.getText().isEmpty() || streetField.getText().isEmpty() ||
	 * addressNrField.getText().isEmpty()) {
	 * showErrorAlert("Please enter information for all fields."); return false; }
	 * 
	 * // Validate email format String emailRegex =
	 * "^[\\w\\d._%+-]+@[\\w\\d.-]+\\.[a-zA-Z]{2,}$"; if
	 * (!emailField.getText().matches(emailRegex)) {
	 * showErrorAlert("Please enter a valid email address."); return false; }
	 * 
	 * // Validate mobile number format String mobileNumberRegex = "^\\d{10}$"; if
	 * (!phoneField.getText().matches(mobileNumberRegex)) {
	 * showErrorAlert("Please enter a valid mobile number."); return false; }
	 * 
	 * 
	 * // Validate postal code format try { int postalCode =
	 * Integer.parseInt(postalcodeField.getText()); if (postalCode < 1000 ||
	 * postalCode > 9999) {
	 * showErrorAlert("Postal code should be between 1000 and 9999."); return false;
	 * } } catch (NumberFormatException e) {
	 * showErrorAlert("Please enter a valid integer for the postal code number.");
	 * return false; }
	 * 
	 * try { int addressNumber = Integer.parseInt(addressNrField.getText()); if
	 * (addressNumber <= 0 || addressNumber >= 1000) {
	 * showErrorAlert("Please enter a valid address number between 1 and 999.");
	 * return false; } } catch (NumberFormatException e) {
	 * showErrorAlert("Please enter a valid integer for the address number.");
	 * return false; }
	 * 
	 * // If all validations pass, return true return true;
	 * 
	 * }
	 */

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
	public void update(Order o) {
		loadCompany(o.getName());
		
	}

	@Override
	public void update(Order c) {
		// TODO Auto-generated method stub
		
	}

}


