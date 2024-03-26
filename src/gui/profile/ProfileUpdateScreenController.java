package gui.profile;

import java.time.LocalDate;
import java.util.List;

import domain.AdminController;
import domain.Company;
import domain.CompanyUpdateRequest;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import repository.GenericDaoJpa;

public class ProfileUpdateScreenController extends VBox {
    private TableView<CompanyUpdateRequest> tableView;
    private TextField oldNameField, oldEmailField, oldPhoneField;
    private TextField newNameField, newEmailField, newPhoneField;
    private Button acceptButton, denyButton;
    private AdminController adminController;

    private GenericDaoJpa<CompanyUpdateRequest> companyUpdateRequestDao;
    private GenericDaoJpa<Company> companyDao;

    public ProfileUpdateScreenController(AdminController adminController) {
        this.adminController = adminController;
        initializeDAOs();
        buildGui();
        populateTableView();
    }

    public void buildGui() {
        tableView = new TableView<>();
        TableColumn<CompanyUpdateRequest, String> vatNumberColumn = new TableColumn<>("VAT Number");
        vatNumberColumn.setCellValueFactory(data -> {
            CompanyUpdateRequest request = data.getValue();
            Company company = companyDao.get(request.getCompanyId());
            return new SimpleStringProperty(company.getVatNumber());
        });
        TableColumn<CompanyUpdateRequest, LocalDate> requestDateColumn = new TableColumn<>("Request Date");
        requestDateColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRequestDate()));
        tableView.getColumns().addAll(vatNumberColumn, requestDateColumn);

        oldNameField = new TextField();
        oldEmailField = new TextField();
        oldPhoneField = new TextField();
        newNameField = new TextField();
        newEmailField = new TextField();
        newPhoneField = new TextField();

        acceptButton = new Button("Accept");
        acceptButton.setOnAction(e -> acceptUpdateRequest());
        denyButton = new Button("Deny");
        denyButton.setOnAction(e -> denyUpdateRequest());

        VBox detailsBox = new VBox(10,
            new Label("Old Details:"),
            oldNameField, oldEmailField, oldPhoneField,
            new Label("New Details:"),
            newNameField, newEmailField, newPhoneField,
            acceptButton, denyButton
        );

        SplitPane splitPane = new SplitPane(tableView, detailsBox);
        splitPane.setDividerPositions(0.3);

        this.getChildren().addAll(splitPane);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayCompanyDetails(newSelection);
            }
        });
    }

    private void displayCompanyDetails(CompanyUpdateRequest request) {
        Company company = companyDao.get(request.getCompanyId());
        oldNameField.setText(company.getName());
        oldEmailField.setText(company.getContact().getEmail());
        oldPhoneField.setText(company.getContact().getPhoneNumber());
        newNameField.setText(request.getNewName());
        newEmailField.setText(request.getNewEmail());
        newPhoneField.setText(request.getNewPhone());
    }


    private void initializeDAOs() {
        companyUpdateRequestDao = new GenericDaoJpa<>(CompanyUpdateRequest.class);
        companyDao = new GenericDaoJpa<>(Company.class);
    }

    private void populateTableView() {
        List<CompanyUpdateRequest> updateRequests = companyUpdateRequestDao.findAll();
        tableView.getItems().addAll(updateRequests);
    }

    private void acceptUpdateRequest() {
        CompanyUpdateRequest selectedUpdateRequest = tableView.getSelectionModel().getSelectedItem();
        if (selectedUpdateRequest != null) {
            GenericDaoJpa.startTransaction();

            Company company = companyDao.get(selectedUpdateRequest.getCompanyId());
            if (company != null) {
                company.setName(selectedUpdateRequest.getNewName());
                company.getContact().setEmail(selectedUpdateRequest.getNewEmail());
                company.getContact().setPhoneNumber(selectedUpdateRequest.getNewPhone());;
                companyDao.update(company);
            }

            companyUpdateRequestDao.delete(selectedUpdateRequest);

            GenericDaoJpa.commitTransaction();

            tableView.getItems().remove(selectedUpdateRequest);
        }
    }

    private void denyUpdateRequest() {
        CompanyUpdateRequest selectedUpdateRequest = tableView.getSelectionModel().getSelectedItem();
        if (selectedUpdateRequest != null) {
            GenericDaoJpa.startTransaction();

            companyUpdateRequestDao.delete(selectedUpdateRequest);

            GenericDaoJpa.commitTransaction();

            tableView.getItems().remove(selectedUpdateRequest);
        }
    }

}
