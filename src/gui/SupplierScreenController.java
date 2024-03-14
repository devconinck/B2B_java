package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import domain.DomainController;
import domain.UserController;
import gui.customer.CompanyDTO;
import gui.customer.Customer;
import gui.customer.CustomerOverview;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupplierScreenController extends BorderPane {
	
	private DomainController dc;

    @FXML
    private Button ordersButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button logOutButton;

    @FXML
    private AnchorPane mainScreen;
    
    public SupplierScreenController(DomainController dc) {
    	this.dc = dc;
    	buildGui();
    }
    
    private void buildGui() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("supplierScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Supplier Screen");
			System.out.println(e.getMessage());
		}
		//this.logOutButton.setOnMouseClicked(e -> logOut());
		
		ordersButton.setOnMouseClicked(e -> {
			this.mainScreen.getChildren().clear();
			OrdersOverviewController ordersScreen = new OrdersOverviewController(dc);
			this.mainScreen.getChildren().add(ordersScreen);
		});
		
		customersButton.setOnMouseClicked(e -> {
			this.mainScreen.getChildren().clear();
			List<String> attributes = Arrays.asList("name", "customerStart", "sector", "isActiveProperty", "contact");
			CustomerOverview co = new CustomerOverview(dc.getCurrentCompany(), dc.getCompanyList(), attributes);
			this.mainScreen.getChildren().add(co.getHBox());
		});
		
		logOutButton.setOnMouseClicked(e -> logOut());
		
		
		//logica om update requests scherm te tonen
		/*
		 * updateButton.setOnMouseClicked(e -> { this.dc.clearObservers();
		 * UpdateOverviewController updateScreen = new
		 * UpdateOverviewScreenController((AdminController) dc);
		 * this.mainScreen.getChildren().clear();
		 * this.mainScreen.getChildren().add(updateScreen); });
		 */
    }
    
    private ObservableList<Customer> createCustomers() {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		Customer c1 = new Customer("Henk", 29, LocalDate.of(1993, 3, 7));
		Customer c2 = new Customer("Alice", 35, LocalDate.of(1987, 8, 15));
		Customer c3 = new Customer("Bob", 42, LocalDate.of(1980, 5, 20));
		customers.addAll(c1, c2, c3);
		return customers;
	}
    
    // TODO ??? ctrl+v ctrl+c AdminScreenController
    private void logOut() {
        Stage currentStage = new Stage();
        LoginScreenController login = new LoginScreenController(dc);
        currentStage.setScene(new Scene(login));
        currentStage.show();
        currentStage.close();

        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
    
    //logica voor uitloggen, daarvoor moeten we wel met login branch mnergen
	/*
	 * private void logOut() { this.dc.logOut(); this.dc = new UserController();
	 * 
	 * LoginScreenController login = new LoginScreenController(dc); Stage stage =
	 * new Stage(); stage.setScene(new Scene(login)); stage.setFullScreen(false);
	 * stage.show(); getScene().getWindow().hide(); }
	 */

}
