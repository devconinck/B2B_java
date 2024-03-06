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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class CompanyController extends BorderPane {
	    
	
    
    @FXML
    private GridPane companyOverviewGrid;
	
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
        
        
        companyOverviewGrid.add(new CompanyScreenController(domainController), 0, 0);
        //companyOverviewGrid.add(new CompanyDetailsController(), 1, 0);
        
       
        
       
    }
}
