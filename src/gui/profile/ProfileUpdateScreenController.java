package gui.profile;

import java.time.LocalDate;

import domain.AdminController;
import domain.Company;
import domain.CompanyUpdateRequest;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.PaymentOption;

public class ProfileUpdateScreenController extends VBox {
    private TableView<CompanyUpdateRequest> tableView;
    private TextField requestDateField;
    private TextField oldNameField, oldSectorField, oldBankAccountNrField, oldCustomerStartField;
    private TextField newNameField, newSectorField, newBankAccountNrField, newCustomerStartField;
    private TextField oldStreetField, oldAddressNrField, oldCityField, oldPostalcodeField, oldCountryField;
    private TextField newStreetField, newAddressNrField, newCityField, newPostalcodeField, newCountryField;
    private TextField oldPhoneField, oldEmailField;
    private TextField newPhoneField, newEmailField;
    private TextField oldLogoField, newLogoField;
    private TextField oldVatNumberField, newVatNumberField;
    private GridPane oldPaymentOptionsPane, newPaymentOptionsPane;
    private Button acceptButton, denyButton;
    
    private AdminController adminController;

    public ProfileUpdateScreenController(AdminController adminController) {
        this.adminController = adminController;
        buildGui();
        tableView.setFixedCellSize(24);
        tableView.setMaxHeight(300);
        tableView.getItems().addAll(adminController.getCompanyUpdateRequestList());
        tableView.setPrefHeight((tableView.getItems().size() + 1) * tableView.getFixedCellSize() + 45);
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

        oldVatNumberField = new TextField();
        newVatNumberField = new TextField();
        requestDateField = new TextField();
        oldNameField = new TextField();
        oldSectorField = new TextField();
        oldBankAccountNrField = new TextField();
        oldCustomerStartField = new TextField();
        newNameField = new TextField();
        newSectorField = new TextField();
        newBankAccountNrField = new TextField();
        newCustomerStartField = new TextField();
        oldStreetField = new TextField();
        oldAddressNrField = new TextField();
        oldCityField = new TextField();
        oldPostalcodeField = new TextField();
        oldCountryField = new TextField();
        newStreetField = new TextField();
        newAddressNrField = new TextField();
        newCityField = new TextField();
        newPostalcodeField = new TextField();
        newCountryField = new TextField();
        oldPhoneField = new TextField();
        oldEmailField = new TextField();
        newPhoneField = new TextField();
        newEmailField = new TextField();
        oldLogoField = new TextField();
        newLogoField = new TextField();

        // Set fields to be uneditable
        oldVatNumberField.setEditable(false);
        newVatNumberField.setEditable(false);
        requestDateField.setEditable(false);
        oldNameField.setEditable(false);
        oldSectorField.setEditable(false);
        oldBankAccountNrField.setEditable(false);
        oldCustomerStartField.setEditable(false);
        newNameField.setEditable(false);
        newSectorField.setEditable(false);
        newBankAccountNrField.setEditable(false);
        newCustomerStartField.setEditable(false);
        oldStreetField.setEditable(false);
        oldAddressNrField.setEditable(false);
        oldCityField.setEditable(false);
        oldPostalcodeField.setEditable(false);
        oldCountryField.setEditable(false);
        newStreetField.setEditable(false);
        newAddressNrField.setEditable(false);
        newCityField.setEditable(false);
        newPostalcodeField.setEditable(false);
        newCountryField.setEditable(false);
        oldPhoneField.setEditable(false);
        oldEmailField.setEditable(false);
        newPhoneField.setEditable(false);
        newEmailField.setEditable(false);
        oldLogoField.setEditable(false);
        newLogoField.setEditable(false);

        oldPaymentOptionsPane = createPaymentOptionsGrid();
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
        detailsGrid.add(new Label("Request Date:"), 0, row);
        detailsGrid.add(requestDateField, 1, row);
        row++;

        detailsGrid.add(new Label("Old VAT Number:"), 0, row);
        detailsGrid.add(oldVatNumberField, 1, row);
        detailsGrid.add(new Label("New VAT Number:"), 2, row);
        detailsGrid.add(newVatNumberField, 3, row);
        row++;
        detailsGrid.add(new Label("Old Name:"), 0, row);
        detailsGrid.add(oldNameField, 1, row);
        detailsGrid.add(new Label("New Name:"), 2, row);
        detailsGrid.add(newNameField, 3, row);
        row++;
        detailsGrid.add(new Label("Old Sector:"), 0, row);
        detailsGrid.add(oldSectorField, 1, row);
        detailsGrid.add(new Label("New Sector:"), 2, row);
        detailsGrid.add(newSectorField, 3, row);
        row++;
        detailsGrid.add(new Label("Old Bank Account Nr:"), 0, row);
        detailsGrid.add(oldBankAccountNrField, 1, row);
        detailsGrid.add(new Label("New Bank Account Nr:"), 2, row);
        detailsGrid.add(newBankAccountNrField, 3, row);
        row++;
        detailsGrid.add(new Label("Old Customer Start:"), 0, row);
        detailsGrid.add(oldCustomerStartField, 1, row);
        detailsGrid.add(new Label("New Customer Start:"), 2, row);
        detailsGrid.add(newCustomerStartField, 3, row);
        row++;

        GridPane oldAddressGrid = new GridPane();
        oldAddressGrid.setHgap(10);
        oldAddressGrid.setVgap(5);
        oldAddressGrid.add(new Label("Street:"), 0, 0);
        oldAddressGrid.add(oldStreetField, 1, 0);
        oldAddressGrid.add(new Label("House Number:"), 0, 1);
        oldAddressGrid.add(oldAddressNrField, 1, 1);
        oldAddressGrid.add(new Label("City:"), 0, 2);
        oldAddressGrid.add(oldCityField, 1, 2);
        oldAddressGrid.add(new Label("Postal Code:"), 2, 2);
        oldAddressGrid.add(oldPostalcodeField, 3, 2);
        oldAddressGrid.add(new Label("Country:"), 0, 3);
        oldAddressGrid.add(oldCountryField, 1, 3);

        GridPane newAddressGrid = new GridPane();
        newAddressGrid.setHgap(10);
        newAddressGrid.setVgap(5);
        newAddressGrid.add(new Label("Street:"), 0, 0);
        newAddressGrid.add(newStreetField, 1, 0);
        newAddressGrid.add(new Label("House Number:"), 0, 1);
        newAddressGrid.add(newAddressNrField, 1, 1);
        newAddressGrid.add(new Label("City:"), 0, 2);
        newAddressGrid.add(newCityField, 1, 2);
        newAddressGrid.add(new Label("Postal Code:"), 2, 2);
        newAddressGrid.add(newPostalcodeField, 3, 2);
        newAddressGrid.add(new Label("Country:"), 0, 3);
        newAddressGrid.add(newCountryField, 1, 3);

        GridPane oldContactGrid = new GridPane();
        oldContactGrid.setHgap(10);
        oldContactGrid.setVgap(5);
        oldContactGrid.add(new Label("Phone:"), 0, 0);
        oldContactGrid.add(oldPhoneField, 1, 0);
        oldContactGrid.add(new Label("Email:"), 0, 1);
        oldContactGrid.add(oldEmailField, 1, 1);

        GridPane newContactGrid = new GridPane();
        newContactGrid.setHgap(10);
        newContactGrid.setVgap(5);
        newContactGrid.add(new Label("Phone:"), 0, 0);
        newContactGrid.add(newPhoneField, 1, 0);
        newContactGrid.add(new Label("Email:"), 0, 1);
        newContactGrid.add(newEmailField, 1, 1);

        TitledPane oldAddressPane = new TitledPane("Old Address", oldAddressGrid);
        TitledPane newAddressPane = new TitledPane("New Address", newAddressGrid);
        TitledPane oldContactPane = new TitledPane("Old Contact", oldContactGrid);
        TitledPane newContactPane = new TitledPane("New Contact", newContactGrid);
        TitledPane oldPaymentOptionsPane = new TitledPane("Old Payment Options", this.oldPaymentOptionsPane);
        TitledPane newPaymentOptionsPane = new TitledPane("New Payment Options", this.newPaymentOptionsPane);

        detailsGrid.add(oldAddressPane, 0, row, 2, 1);
        detailsGrid.add(newAddressPane, 2, row, 2, 1);
        row++;
        detailsGrid.add(oldContactPane, 0, row, 2, 1);
        detailsGrid.add(newContactPane, 2, row, 2, 1);
        row++;
        detailsGrid.add(oldPaymentOptionsPane, 0, row, 2, 1);
        detailsGrid.add(newPaymentOptionsPane, 2, row, 2, 1);
        row++;
        detailsGrid.add(new Label("Old Logo:"), 0, row);
        detailsGrid.add(oldLogoField, 1, row);
        detailsGrid.add(new Label("New Logo:"), 2, row);
        detailsGrid.add(newLogoField, 3, row);
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
        Company company = adminController.getCompany(request.getVatNumber());

        requestDateField.setText(request.getRequestDate().toString());

        oldVatNumberField.setText(request.getVatNumber());
        oldNameField.setText(company.getName());
        oldSectorField.setText(company.getSector());
        oldBankAccountNrField.setText(String.valueOf(company.getBankAccountNr()));
        oldCustomerStartField.setText(company.getCustomerStart().toString());
        oldStreetField.setText(company.getAddress().getStreet());
        oldAddressNrField.setText(company.getAddress().getNumber());
        oldCityField.setText(company.getAddress().getCity());
        oldPostalcodeField.setText(company.getAddress().getZipCode());
        oldCountryField.setText(company.getAddress().getCity());
        oldPhoneField.setText(company.getContact().getPhoneNumber());
        oldEmailField.setText(company.getContact().getEmail());
        oldLogoField.setText(company.getLogo());
        
        
        newVatNumberField.setText(request.getVatNumber());
        newNameField.setText(request.getNewName());
        newSectorField.setText(request.getNewSector());
        newBankAccountNrField.setText(String.valueOf(request.getNewBankAccountNr()));
        newCustomerStartField.setText(request.getNewCustomerStart().toString());
        newStreetField.setText(request.getNewAddress().getStreet());
        newAddressNrField.setText(request.getNewAddress().getNumber());
        newCityField.setText(request.getNewAddress().getCity());
        newPostalcodeField.setText(request.getNewAddress().getZipCode());
        newCountryField.setText(request.getNewAddress().getCity());
        newPhoneField.setText(request.getNewContact().getPhoneNumber());
        newEmailField.setText(request.getNewContact().getEmail());
        newLogoField.setText(request.getNewLogo());

        oldPaymentOptionsPane.getChildren().clear();
        newPaymentOptionsPane.getChildren().clear();
        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentOption option : PaymentOption.values()) {
            CheckBox oldCheckBox = new CheckBox(option.getDisplayName());
            oldCheckBox.setDisable(true);
            oldCheckBox.setSelected(company.getPaymentOptions().contains(option));
            oldPaymentOptionsPane.add(oldCheckBox, col, row);

            CheckBox newCheckBox = new CheckBox(option.getDisplayName());
            newCheckBox.setDisable(true);
            newCheckBox.setSelected(request.getNewPaymentOptions().contains(option));
            newPaymentOptionsPane.add(newCheckBox, col, row);

            col++;
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