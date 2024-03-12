package gui;

import java.io.IOException;

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
    private TextField contactField;
    @FXML
    private TextField orderIdField;
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
    private TextField orderStatusField;
    @FXML
    private TextField paymentStatusField;
    @FXML
    private TextField lastPaymentField;
    @FXML
    private HBox buttonBox;
    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button inactiveBtn;

    //private AdminController dc;
    private DomainController dc;


	public OrderDetailsScreenController(DomainController dc) {
    	this.dc = dc;
    	this.dc.addObserver(this);
    	
    	buildGui();
    }

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
	
	
	public void loadOrder(String orderId) {
		Order o = dc.getOrder(orderId);
		
	    this.nameField.setText(o.getName());
	    this.contactField.setText("Temp");
	    this.orderIdField.setText(o.getOrderId());
	    this.streetField.setText("Temp");
	    this.addressNrField.setText("Temp");
	    this.cityField.setText("Temp");
	    this.postalcodeField.setText("Temp");
	    this.countryField.setText("Temp");
	    this.orderStatusField.setText(o.getOrderStatus());
	    this.paymentStatusField.setText(o.getPaymentStatus());
	    this.lastPaymentField.setText(o.getLastPaymentReminder());
	}

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
		loadOrder(o.getOrderId());
	}
}


