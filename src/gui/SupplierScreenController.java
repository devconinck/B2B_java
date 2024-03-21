package gui;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.SupplierController;
import dto.CompanyDTO;
import gui.customer.CustomerOverview;
import gui.login.LoginScreen;
import gui.order.OrdersOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SupplierScreenController extends BorderPane {
	
	private SupplierController controller;
	
	@FXML
    private VBox vbox_name_login;

    @FXML
    private Button ordersButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button logOutButton;

    @FXML
    private AnchorPane mainScreen;
    
    public SupplierScreenController(SupplierController controller) {
    	this.controller = controller;
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
		
		Label lbl_name_login = new Label();
		lbl_name_login.setText(String.format("Logged in as: %s", controller.getCurrentCompany().getName()));
		lbl_name_login.setFont(new Font(20));
		vbox_name_login.getChildren().add(lbl_name_login);
		
		this.logOutButton.setOnMouseClicked(e -> logOut());
		
		ordersButton.setOnMouseClicked(e -> {
			this.mainScreen.getChildren().clear();
			Map<String, String> map = new TreeMap<>();
			map.put("Order ID", "orderId");
			map.put("Name Customer", "name");
			map.put("Date", "date");
			map.put("Order Status", "orderStatus");
			map.put("Payment Status", "paymentStatus");
			OrdersOverview oo = new OrdersOverview(map, controller);
			this.mainScreen.getChildren().add(oo.getHBox());
			
			/*this.mainScreen.getChildren().clear();
			OrdersOverviewController ordersScreen = new OrdersOverviewController(dc);
			this.mainScreen.getChildren().add(ordersScreen);*/
		});
		
		customersButton.setOnMouseClicked(e -> {
			this.mainScreen.getChildren().clear();
			Map<String, String> map = new TreeMap<>();
			map.put("Open orders", "numberOfOpenOrders");
			map.put("Name", "name");
			map.put("Logo", "logo");
			ObservableList<CompanyDTO> customerList = FXCollections.observableArrayList(
					controller.getCurrentCompany().getCustomers().stream().map(comp -> new CompanyDTO(comp)).collect(Collectors.toList()));
			CustomerOverview co = new CustomerOverview(customerList, map, controller);
			this.mainScreen.getChildren().add(co.getHBox());
		});
		
    }
    
    private void logOut() {
        Stage currentStage = new Stage();
        LoginScreen login = new LoginScreen();
        currentStage.setScene(new Scene(login));
        currentStage.show();
        currentStage.close();

        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

}
