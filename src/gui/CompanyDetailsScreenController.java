package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.DomainController;
import domain.Observer;
import domain.Order;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class CompanyDetailsScreenController extends VBox implements Observer {

	private TextField nameField;
	private TextField vatField;
	private TextField sectorField;
	private TextField streetField;
	private TextField addressNrField;
	private TextField cityField;
	private TextField postalcodeField;
	private TextField countryField;
	private TextField bankField;
	private TextField phoneField;
	private TextField emailField;
	private TextField customerStartField;
	private TextField paymentOptionsField;
	private TextField logoUrlField;
	private ImageView logoImageView;

	
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
        Label headerLabel = new Label("Company Details:");
        headerLabel.setStyle("-fx-font-size: 30px;");

        // Labels
        Label paymentOptionsLabel = new Label("Payment Options:");
        Label customerStartLabel = new Label("Customer since: ");
        Label nameLabel = new Label("Name:");
        Label vatLabel = new Label("VAT Number:");
        Label sectorLabel = new Label("Sector:");
        Label streetLabel = new Label("Street:");
        Label addressNrLabel = new Label("House Number:");
        Label cityLabel = new Label("City:");
        Label postalcodeLabel = new Label("Postal Code:");
        Label countryLabel = new Label("Country:");
        Label bankLabel = new Label("Bank Account:");
        Label phoneLabel = new Label("Phone:");
        Label emailLabel = new Label("Email:");

        // Create text fields for each input
        nameField = new TextField();
        vatField = new TextField();
        sectorField = new TextField();
        streetField = new TextField();
        addressNrField = new TextField();
        cityField = new TextField();
        postalcodeField = new TextField();
        countryField = new TextField();
        bankField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();
        customerStartField = new TextField();
        paymentOptionsField = new TextField();
        logoUrlField = new TextField();
        
        logoImageView = new ImageView();
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);

        HBox generalBox = new HBox(10); // Add 10 pixels of spacing between children
        generalBox.getChildren().addAll(createField(nameLabel, nameField), createField(sectorLabel, sectorField),
                createField(customerStartLabel, customerStartField));
        
        vatField.setMaxWidth(470);

        Label addressLabel = new Label("Address:");
        addressLabel.setStyle("-fx-font-size: 20px;");
        
        streetField.setPrefWidth(310);
        
        HBox addressBox1 = new HBox(10);
        addressBox1.getChildren().addAll(createField(streetLabel, streetField),
                createField(addressNrLabel, addressNrField));

        HBox addressBox2 = new HBox(10);
        addressBox2.getChildren().addAll(createField(cityLabel, cityField), createField(postalcodeLabel, postalcodeField),
                createField(countryLabel, countryField));

        Label contactLabel = new Label("Contact:");
        contactLabel.setStyle("-fx-font-size: 20px;");

        HBox contactBox = new HBox(10);
        contactBox.getChildren().addAll(createField(phoneLabel, phoneField), createField(emailLabel, emailField));

        Label paymentLabel = new Label("Payment:");
        paymentLabel.setStyle("-fx-font-size: 20px;");

        HBox paymentBox = new HBox(10);
        paymentBox.getChildren().addAll(createField(bankLabel, bankField),
                createField(paymentOptionsLabel, paymentOptionsField));

        Label logoLabel = new Label("Logo:");
        logoLabel.setStyle("-fx-font-size: 20px;");

        Label logoUrlLabel = new Label("Logo url:");
        
        logoUrlField.setMaxWidth(470);

        VBox logoBox = new VBox(10);
        logoBox.getChildren().addAll(logoImageView, createField(logoUrlLabel, logoUrlField));

        VBox mainVBox = new VBox(3);
        mainVBox.getChildren().addAll(headerLabel, generalBox, createField(vatLabel, vatField), addressLabel,
                addressBox1, addressBox2, contactLabel, contactBox, paymentLabel, paymentBox, logoLabel, logoBox);
        
        VBox.setMargin(this, new Insets(0, 0, 0, 50));
        this.getChildren().addAll(mainVBox);
    }

    private VBox createField(Label label, TextField field) {
        VBox vbox = new VBox(5); 
        vbox.getChildren().addAll(label, field);
        return vbox;
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
		
		this.customerStartField.setText(c.getCustomerStart().toString());
		this.paymentOptionsField.setText(c.getPaymentOptions().toString());
		this.logoUrlField.setText(c.getLogo());
		try {
			this.logoImageView.setImage(new Image(new FileInputStream(c.getLogo())));
		} catch (FileNotFoundException fe) {
			try {
				this.logoImageView.setImage(new Image(new FileInputStream("src/images/no-logo.png")));
			} catch (Exception e) {
				// Do nothing
			}
		}
		
		
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
	    this.customerStartField.clear();
	    this.paymentOptionsField.clear();
	    this.logoUrlField.clear();
	}



	private boolean isInputValid() {		
		/*
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
	    if (!vatField.getText().matches(Validation.VAT_REGEX)) {
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
	    if (!addressNrField.getText().matches(Validation.HOUSE_NUMBER_REGEX)) {
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
	    if (!bankField.getText().matches(Validation.IBAN_REGEX)) {
	        showErrorAlert("Please enter a valid bank account number.");
	        return false;
	    }
	    
	    // Phone
	    if (!phoneField.getText().matches(Validation.PHONE_NUMBER_REGEX)) {
	        showErrorAlert("Please enter a valid mobile number.");
	        return false;
	    }
	    
	    // Email
	    if (!emailField.getText().matches(Validation.EMAIL_REGEX)) {
	        showErrorAlert("Please enter a valid email address.");
	        return false;
	    }
	    */
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
	
	public void toggleIsActive() {
		Company currentComp = dc.getCurrentCompany();
		currentComp.toggleIsActive();
		dc.updateCompany(currentComp);
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
                existingCompany.setBankAccountNr(Long.parseLong(bankField.getText()));
                
                existingCompany.getAddress().setCountry(countryField.getText());
                existingCompany.getAddress().setCity(cityField.getText());
                existingCompany.getAddress().setZipCode(postalcodeField.getText());
                existingCompany.getAddress().setStreet(streetField.getText());
                existingCompany.getAddress().setNumber(addressNrField.getText());
                existingCompany.getContact().setPhoneNumber(phoneField.getText());
                existingCompany.getContact().setEmail(emailField.getText());
                existingCompany.setIsActive(isInputValid());
                
				
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
