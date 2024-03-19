package gui;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import domain.DomainController;
import gui.customer.CustomerOverview;
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
			Map<String, String> map = new TreeMap<>();
			map.put("Open orders", "numberOfOpenOrders");
			map.put("Name", "name");
			map.put("Logo", "logo");
			CustomerOverview co = new CustomerOverview(map, dc);
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

}
