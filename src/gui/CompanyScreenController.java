package gui;

import java.io.IOException;

import domain.Company;
import domain.DomainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class CompanyScreenController extends Pane {
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

    
    @FXML
    private TableColumn<Company, String> isActiveCol;

    private DomainController domainController;
    
    public CompanyScreenController(DomainController domainController) {    	
        this.domainController = domainController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("companyScreen.fxml"));
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
