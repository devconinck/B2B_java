package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class CompanyController extends BorderPane {

    
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


    }
    
   

}
