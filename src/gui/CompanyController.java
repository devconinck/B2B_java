package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import domain.Address;
import domain.Company;
import domain.DomainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

public class CompanyController extends BorderPane {
	
	// FILTER TOEVOEGEN!!!
	
    @FXML
    private TableView<Company> companyTableView;
    @FXML
    private TableColumn<Company, String> nameCol;
    @FXML
    private TableColumn<Company, String> sectorCol;
    @FXML
    private TableColumn<Company, String> addressCol;
    @FXML
    private TableColumn<Company, String> amountOfCustomersCol;
    
    // WELKE TYPES MEEGEVEN
    @FXML
    private TableColumn<?, Button> editCol;
    @FXML
    private TableColumn<Company, String> isActiveCol;

    
    private DomainController domainController;
    
    public CompanyController(DomainController domainController) {    	
        this.domainController = domainController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminCompanies.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        sectorCol.setCellValueFactory(cellData -> cellData.getValue().sectorProperty());

        addressCol.setCellValueFactory(cellData -> cellData.getValue().getAddressString());


        // NOG EENS OVER NADENKEN HOE DIT OPHALEN
        // amountOfCustomersCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        
        // Button nr nieuw scherm
        // editCol.setCellValueFactory(cellData -> cellData.getValue());
        
        isActiveCol.setCellValueFactory(cellData -> {
        	boolean isActive = cellData.getValue().isActiveProperty().get();
        	SimpleStringProperty text = new SimpleStringProperty(isActive ? "Active" : "Inactive");
        	return text;
        });
        
        companyTableView.setItems(domainController.getCompanyList());

    }
}
