package gui.profile;

import java.time.LocalDate;
import java.util.List;

import domain.Account;
import domain.Address;
import domain.AdminController;
import domain.Company;
import domain.CompanyUpdateRequest;
import domain.Contact;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.PaymentOption;

public class ProfileUpdateScreenController extends VBox {
    private TableView<CompanyUpdateRequest> tableView;
    private TextField vatNumberField, requestDateField;
    private TextField newNameField, newSectorField, newBankAccountNrField, newCustomerStartField;
    private TextField newStreetField, newAddressNrField, newCityField, newPostalcodeField, newCountryField;
    private TextField newPhoneField, newEmailField;
    private TextField newLogoField;
    private GridPane newPaymentOptionsPane;
    private Button acceptButton, denyButton;
    
    private AdminController adminController;

    public ProfileUpdateScreenController(AdminController adminController) {
        this.adminController = adminController;
        buildGui();
        tableView.getItems().addAll(adminController.getCompanyUpdateRequestList());
    }

    public void buildGui() {
        tableView = new TableView<>();
        TableColumn<CompanyUpdateRequest, String> vatNumberColumn = new TableColumn<>("VAT Number");
        vatNumberColumn.setCellValueFactory(data -> {
            CompanyUpdateRequest request = data.getValue();
            return new SimpleStringProperty(request.getVatNumber());
        });
        TableColumn<CompanyUpdateRequest, LocalDate> requestDateColumn = new TableColumn<>("Request Date");
        requestDateColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRequestDate()));
        tableView.getColumns().addAll(vatNumberColumn, requestDateColumn);

        vatNumberField = new TextField();
        requestDateField = new TextField();
        newNameField = new TextField();
        newSectorField = new TextField();
        newBankAccountNrField = new TextField();
        newCustomerStartField = new TextField();
        newStreetField = new TextField();
        newAddressNrField = new TextField();
        newCityField = new TextField();
        newPostalcodeField = new TextField();
        newCountryField = new TextField();
        newPhoneField = new TextField();
        newEmailField = new TextField();
        newLogoField = new TextField();

        // Set fields to be uneditable
        vatNumberField.setEditable(false);
        requestDateField.setEditable(false);
        newNameField.setEditable(false);
        newSectorField.setEditable(false);
        newBankAccountNrField.setEditable(false);
        newCustomerStartField.setEditable(false);
        newStreetField.setEditable(false);
        newAddressNrField.setEditable(false);
        newCityField.setEditable(false);
        newPostalcodeField.setEditable(false);
        newCountryField.setEditable(false);
        newPhoneField.setEditable(false);
        newEmailField.setEditable(false);
        newLogoField.setEditable(false);

        newPaymentOptionsPane = createPaymentOptionsGrid();

        acceptButton = new Button("Accept");
        acceptButton.setOnAction(e -> acceptUpdateRequest());
        denyButton = new Button("Deny");
        denyButton.setOnAction(e -> denyUpdateRequest());

        GridPane detailsGrid = new GridPane();
        detailsGrid.setHgap(10);
        detailsGrid.setVgap(5);
        detailsGrid.setPadding(new Insets(10));

        int row = 0;
        detailsGrid.add(new Label("VAT Number:"), 0, row);
        detailsGrid.add(vatNumberField, 1, row);
        row++;
        detailsGrid.add(new Label("Request Date:"), 0, row);
        detailsGrid.add(requestDateField, 1, row);
        row++;
        detailsGrid.add(new Label("New Name:"), 0, row);
        detailsGrid.add(newNameField, 1, row);
        row++;
        detailsGrid.add(new Label("New Sector:"), 0, row);
        detailsGrid.add(newSectorField, 1, row);
        row++;
        detailsGrid.add(new Label("New Bank Account Nr:"), 0, row);
        detailsGrid.add(newBankAccountNrField, 1, row);
        row++;
        detailsGrid.add(new Label("New Customer Start:"), 0, row);
        detailsGrid.add(newCustomerStartField, 1, row);
        row++;

        GridPane addressGrid = new GridPane();
        addressGrid.setHgap(10);
        addressGrid.setVgap(5);
        addressGrid.add(new Label("Street:"), 0, 0);
        addressGrid.add(newStreetField, 1, 0);
        addressGrid.add(new Label("House Number:"), 0, 1);
        addressGrid.add(newAddressNrField, 1, 1);
        addressGrid.add(new Label("City:"), 0, 2);
        addressGrid.add(newCityField, 1, 2);
        addressGrid.add(new Label("Postal Code:"), 2, 2);
        addressGrid.add(newPostalcodeField, 3, 2);
        addressGrid.add(new Label("Country:"), 0, 3);
        addressGrid.add(newCountryField, 1, 3);

        GridPane contactGrid = new GridPane();
        contactGrid.setHgap(10);
        contactGrid.setVgap(5);
        contactGrid.add(new Label("Phone:"), 0, 0);
        contactGrid.add(newPhoneField, 1, 0);
        contactGrid.add(new Label("Email:"), 0, 1);
        contactGrid.add(newEmailField, 1, 1);

        TitledPane addressPane = new TitledPane("New Address", addressGrid);
        TitledPane contactPane = new TitledPane("New Contact", contactGrid);
        TitledPane paymentOptionsPane = new TitledPane("New Payment Options", newPaymentOptionsPane);

        detailsGrid.add(addressPane, 0, row, 2, 1);
        row++;
        detailsGrid.add(contactPane, 0, row, 2, 1);
        row++;
        detailsGrid.add(paymentOptionsPane, 0, row, 2, 1);
        row++;
        detailsGrid.add(new Label("New Logo:"), 0, row);
        detailsGrid.add(newLogoField, 1, row);
        row++;
        detailsGrid.add(acceptButton, 0, row);
        detailsGrid.add(denyButton, 1, row);

        ScrollPane scrollPane = new ScrollPane(detailsGrid);
        scrollPane.setFitToWidth(true);

        this.getChildren().addAll(tableView, scrollPane);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayCompanyUpdateRequestDetails(newSelection);
            }
        });
    }

    private GridPane createPaymentOptionsGrid() {
        GridPane paymentOptionsPane = new GridPane();
        paymentOptionsPane.setHgap(10);
        paymentOptionsPane.setVgap(10);

        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentOption option : PaymentOption.values()) {
            CheckBox checkBox = new CheckBox(option.getDisplayName());
            checkBox.setDisable(true);
            paymentOptionsPane.add(checkBox, col, row);

            col++;
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }

        return paymentOptionsPane;
    }

    private void displayCompanyUpdateRequestDetails(CompanyUpdateRequest request) {
        vatNumberField.setText(request.getVatNumber());
        requestDateField.setText(request.getRequestDate().toString());
        newNameField.setText(request.getNewName());
        newSectorField.setText(request.getNewSector());
        newBankAccountNrField.setText(String.valueOf(request.getNewBankAccountNr()));
        newCustomerStartField.setText(request.getNewCustomerStart().toString());
        newStreetField.setText(request.getNewAddress().getStreet());
        newAddressNrField.setText(request.getNewAddress().getNumber());
        newCityField.setText(request.getNewAddress().getCity());
        newPostalcodeField.setText(request.getNewAddress().getZipCode());
        newCountryField.setText(request.getNewAddress().getCountry());
        newPhoneField.setText(request.getNewContact().getPhoneNumber());
        newEmailField.setText(request.getNewContact().getEmail());
        newLogoField.setText(request.getNewLogo());

        newPaymentOptionsPane.getChildren().clear();
        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentOption option : PaymentOption.values()) {
            CheckBox checkBox = new CheckBox(option.getDisplayName());
            checkBox.setDisable(true);
            checkBox.setSelected(request.getNewPaymentOptions().contains(option));
            newPaymentOptionsPane.add(checkBox, col++, row);
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }
    }

    private void acceptUpdateRequest() {
        CompanyUpdateRequest selectedUpdateRequest = tableView.getSelectionModel().getSelectedItem();
        if (selectedUpdateRequest != null) {
            Company company = adminController.getCompany(selectedUpdateRequest.getVatNumber());
            if (company != null) {
                company.setName(selectedUpdateRequest.getNewName());
                company.setSector(selectedUpdateRequest.getNewSector());
                company.setBankAccountNr(selectedUpdateRequest.getNewBankAccountNr());
                company.setCustomerStart(selectedUpdateRequest.getNewCustomerStart());
                company.setAddress(selectedUpdateRequest.getNewAddress());
                company.setContact(selectedUpdateRequest.getNewContact());
                company.setPaymentOptions(selectedUpdateRequest.getNewPaymentOptions());
                company.setLogo(selectedUpdateRequest.getNewLogo());
                adminController.updateCompany(company);
            }
            adminController.deleteUpdateRequest(selectedUpdateRequest);
            tableView.getItems().remove(selectedUpdateRequest);
        }
    }

    private void denyUpdateRequest() {
        CompanyUpdateRequest selectedUpdateRequest = tableView.getSelectionModel().getSelectedItem();
        
        if (selectedUpdateRequest != null) {
            adminController.deleteUpdateRequest(selectedUpdateRequest);
            tableView.getItems().remove(selectedUpdateRequest);
        }
    }
}