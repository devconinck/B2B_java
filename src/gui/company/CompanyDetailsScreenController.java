package gui.company;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.Address;
import domain.AdminController;
import domain.Company;
import domain.Contact;
import domain.Observer;
import domain.Order;
import dto.CompanyDTO;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import util.PaymentOption;
import util.Validation;

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
    private TextField logoUrlField;
    private ImageView logoImageView;
    private GridPane paymentPane;

    private ObjectProperty<Company> selectedCompanyProperty;
    private AdminController adminController;

    public CompanyDetailsScreenController(AdminController adminController) {
        this.selectedCompanyProperty = adminController.getSelectedCompanyProperty();
        this.adminController = adminController;
        this.selectedCompanyProperty.addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                loadCompany(new CompanyDTO(newValue));
            } else {
                clearAllFields();
            }
        });
        buildGui();
    }

    private void buildGui() {

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
        logoUrlField = new TextField();

        logoImageView = new ImageView();
        logoImageView.setFitHeight(60);
        logoImageView.setPreserveRatio(true);
        
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setHgap(8);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);
        
        //0
        Label headerLabel = new Label("Company Details:");
        headerLabel.setStyle("-fx-font-size: 30px;");
        gp.add(headerLabel, 0, 0); 
        GridPane.setColumnSpan(headerLabel, 3);
        //1
        gp.add(createField(nameLabel, nameField), 0, 1);   gp.add(createField(sectorLabel, sectorField), 1, 1);   gp.add(createField(customerStartLabel, customerStartField), 2, 1);
        //2
        VBox vat = createField(vatLabel, vatField);
        gp.add(vat, 0, 2); GridPane.setColumnSpan(vat, 3);
        //3
        Label addressLabel = new Label("Address:");
        addressLabel.setStyle("-fx-font-size: 20px;");
        gp.add(addressLabel, 0, 3);
        //4
        VBox street = createField(streetLabel, streetField);
        gp.add(street, 0, 4); GridPane.setColumnSpan(street, 2); gp.add(createField(addressNrLabel, addressNrField), 2, 4);
        //5
        gp.add(createField(cityLabel, cityField), 0, 5);   gp.add(createField(postalcodeLabel, postalcodeField), 1, 5);   gp.add(createField(countryLabel, countryField), 2, 5);
        //6
        Label contactLabel = new Label("Contact:");
        contactLabel.setStyle("-fx-font-size: 20px;");
        gp.add(contactLabel, 0, 6);
        GridPane.setColumnSpan(contactLabel, 3);
        //7
        VBox email = createField(emailLabel, emailField);
        gp.add(createField(phoneLabel, phoneField), 0, 7);    gp.add(email, 1, 7);
        GridPane.setColumnSpan(email, 2);
        //8
        Label paymentLabel = new Label("Payment:");
        paymentLabel.setStyle("-fx-font-size: 20px;");
        gp.add(paymentLabel, 0, 8);
        GridPane.setColumnSpan(paymentLabel, 3);
        //9: gridpane paymentPane + Label paymentOptionsLabel = VBox PaymentoptionsVBox
        gp.add(createField(bankLabel, bankField), 0, 9);
        paymentPane = createPaymentOptionsGrid();
        VBox paymentOptionsVBox = new VBox();
        paymentOptionsVBox.getChildren().addAll(paymentOptionsLabel, paymentPane);
        gp.add(paymentOptionsVBox, 1, 9);
        GridPane.setColumnSpan(paymentOptionsVBox, 2);
        //10
        Label logoLabel = new Label("Logo:");
        logoLabel.setStyle("-fx-font-size: 20px;");
        gp.add(logoLabel, 0, 10);
        //11
        Label logoUrlLabel = new Label("Logo url:");
        gp.add(logoImageView, 0, 11);
        gp.add(createField(logoUrlLabel, logoUrlField), 1, 11);

        GridPane.setHgrow(gp, Priority.ALWAYS);
        
        this.getChildren().addAll(gp);


    }

    private GridPane createPaymentOptionsGrid() {
        GridPane paymentPane = new GridPane();
        paymentPane.setHgap(10);
        paymentPane.setVgap(10);

        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentOption option : PaymentOption.values()) {
            CheckBox checkBox = new CheckBox(option.getDisplayName());
            paymentPane.add(checkBox, col, row);

            col++;
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }

        return paymentPane;
    }

    private VBox createField(Label label, TextField field) {
        VBox vbox = new VBox(5);
        vbox.getChildren().addAll(label, field);
        return vbox;
    }

    public void loadCompany(CompanyDTO companyDTO) {
        nameField.setText(companyDTO.getName());
        vatField.setText(companyDTO.getVatNumber());
        sectorField.setText(companyDTO.getSector());
        streetField.setText(companyDTO.getStreet());
        addressNrField.setText(companyDTO.getNumber());
        cityField.setText(companyDTO.getCity());
        postalcodeField.setText(companyDTO.getZipcode());
        countryField.setText(companyDTO.getCountry());
        bankField.setText(String.format("BE%s", String.valueOf(companyDTO.getBankAccountNr())));
        phoneField.setText(companyDTO.getPhoneNumber());
        emailField.setText(companyDTO.getEmail());
        customerStartField.setText(companyDTO.getCustomerStart().toString());
        logoUrlField.setText(companyDTO.getLogo());

        try {
            if (companyDTO.getLogo().isBlank()) throw new Exception();
            logoImageView.setImage(new Image(companyDTO.getLogo()));
        } catch (Exception e1) {
            try {
                logoImageView.setImage(new Image(new FileInputStream("src/images/no-logo.png")));
            } catch (Exception e) {
                // Do nothing
            }
        }

        paymentPane.getChildren().clear();
        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentOption option : PaymentOption.values()) {
            CheckBox checkBox = new CheckBox(option.getDisplayName());
            checkBox.setSelected(companyDTO.getPaymentOptions().contains(option));
            paymentPane.add(checkBox, col++, row);
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }
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
        this.logoUrlField.clear();

        
        for (Node node : paymentPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                checkBox.setSelected(false);
            }
        }
    }

    private boolean isInputValid() {
        if (this.nameField.getText().isEmpty() ||
                this.vatField.getText().isEmpty() ||
                this.sectorField.getText().isEmpty() ||
                this.streetField.getText().isEmpty() ||
                this.addressNrField.getText().isEmpty() ||
                this.cityField.getText().isEmpty() ||
                this.postalcodeField.getText().isEmpty() ||
                this.countryField.getText().isEmpty() ||
                this.bankField.getText().isEmpty() ||
                this.phoneField.getText().isEmpty() ||
                this.emailField.getText().isEmpty() ||
                this.customerStartField.getText().isEmpty()) {
            showErrorAlert("Please fill in all required fields.");
            return false;
        }
        
        if (!customerStartField.getText().matches(Validation.DATE_REGEX)) {
        	showErrorAlert("Please enter a valid Date. \nUse the following format: yyyy-MM-dd");
            return false;
        }

        if (!vatField.getText().matches(Validation.COMPANY_VAT_REGEX)) {
            showErrorAlert("Please enter a valid VAT Number.");
            return false;
        }

        if (!addressNrField.getText().matches(Validation.HOUSE_NUMBER_REGEX)) {
            showErrorAlert("Please enter a valid house number.");
            return false;
        }
        
        
        if (!bankField.getText().matches(Validation.COMPANY_VAT_REGEX)) {
            showErrorAlert("Please enter a valid bank account number.");
            return false;
        }
        
        if (!phoneField.getText().matches(Validation.PHONE_NUMBER_REGEX)) {
            showErrorAlert("Please enter a valid mobile number.");
            return false;
        }

        if (!emailField.getText().matches(Validation.EMAIL_REGEX)) {
            showErrorAlert("Please enter a valid email address.");
            return false;
        }

        boolean atLeastOnePaymentOptionSelected = false;
        for (Node node : paymentPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    atLeastOnePaymentOptionSelected = true;
                    break;
                }
            }
        }

        if (!atLeastOnePaymentOptionSelected) {
            showErrorAlert("Please select at least one payment option.");
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

    public void persistCompany() {
        if (isInputValid()) {
            String vatNumber = vatField.getText();
            Company existingCompany = adminController.getCompany(vatNumber);

            if (existingCompany != null) {
                // Update existing company
                existingCompany.setName(nameField.getText());
                existingCompany.setVatNumber(vatNumber);
                existingCompany.setSector(sectorField.getText());
                existingCompany.setBankAccountNr(Long.parseLong(bankField.getText().substring(2)));
                existingCompany.getContact().setPhoneNumber(phoneField.getText());
                existingCompany.getContact().setEmail(emailField.getText());
                existingCompany.getAddress().setCountry(countryField.getText());
                existingCompany.getAddress().setCity(cityField.getText());
                existingCompany.getAddress().setZipCode(postalcodeField.getText());
                existingCompany.getAddress().setStreet(streetField.getText());
                existingCompany.getAddress().setNumber(addressNrField.getText());

                try {
                    existingCompany.setCustomerStart(LocalDate.parse(customerStartField.getText()));
                } catch (Exception e) {
                    // Not Possible
                }

                if (!logoUrlField.getText().isBlank()) {
                    existingCompany.setLogo(logoUrlField.getText());
                }

                List<PaymentOption> selectedOptions = new ArrayList<>();
                for (Node node : paymentPane.getChildren()) {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        if (checkBox.isSelected()) {
                            PaymentOption option = PaymentOption.getByDisplayName(checkBox.getText());
                            if (option != null) {
                                selectedOptions.add(option);
                            }
                        }
                    }
                }
                existingCompany.setPaymentOptions(selectedOptions);

                adminController.updateCompany(existingCompany);
            } else {
                // Insert new company
                Address tempAddress = new Address(countryField.getText(), cityField.getText(), postalcodeField.getText(), streetField.getText(), addressNrField.getText());
                Contact tempContact = new Contact(phoneField.getText(), emailField.getText());
                List<PaymentOption> selectedOptions = new ArrayList<>();
                for (Node node : paymentPane.getChildren()) {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        if (checkBox.isSelected()) {
                            PaymentOption option = PaymentOption.getByDisplayName(checkBox.getText());
                            if (option != null) {
                                selectedOptions.add(option);
                            }
                        }
                    }
                }

                LocalDate customerStartDate;
                customerStartDate = LocalDate.parse(customerStartField.getText());

                Company tempCompany = new Company(vatField.getText(), logoUrlField.getText(), tempAddress, tempContact, nameField.getText(), sectorField.getText(), Long.parseLong(bankField.getText()), selectedOptions, customerStartDate, null, null);
                adminController.addCompany(tempCompany);
            }

            showInfoAlert("Company saved", "The company has been saved");
        }
    }

	public void toggleIsActive() {
		Company currentComp = adminController.getSelectedCompany();
		currentComp.toggleIsActive();
		adminController.updateCompany(currentComp);
	}

    @Override
    public void update(Order order) {
        // Not needed in this class
    }

    @Override
    public void update(Company company) {
        if (company.getVatNumber().equals(selectedCompanyProperty.get().getVatNumber())) {
            loadCompany(new CompanyDTO(company));
        }
    }
}