package gui.customer;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.Company;
import domain.Observer;
import domain.Order;
import domain.SupplierController;
import dto.CompanyDTO;
import gui.login.LoginScreen;
import gui.order.OrderDetailsOverview;
import gui.order.OrderTableAndFilterOverview;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SupplierScreenController extends BorderPane{
	
private SupplierController controller;
	
	@FXML
	private Label lbl_name_login;
    @FXML
    private Button ordersButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button logOutButton;

    @FXML
    private HBox mainScreen;
    
    public SupplierScreenController(SupplierController controller){
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
		
		lbl_name_login.setText(String.format("Logged in as: %s", controller.getCurrentCompany().getName()));
		lbl_name_login.setFont(new Font(20));

		this.logOutButton.setOnMouseClicked(e -> logOut());
		
		ordersButton.setOnMouseClicked(e -> {
			this.mainScreen.getChildren().clear();
			this.mainScreen.getChildren().addAll(orderTableAndFilter().getHBox(), orderDetails().getHBox());
		});
		
		customersButton.setOnMouseClicked(event -> {
			this.mainScreen.getChildren().clear();			
			this.mainScreen.getChildren().addAll(customerTableAndFilter().getHBox(), customerDetails().getHBox());
		});
		
    }
    
    private OrderDetailsOverview orderDetails() {
    	OrderDetailsOverview orderDetails = new OrderDetailsOverview(controller.getCurrentOrderDTO(), controller);
    	controller.addPropertyChangeListenerOrder(orderDetails);
    	return orderDetails;
    }
    
    private OrderTableAndFilterOverview orderTableAndFilter() {
    	Map<String, String> map = new TreeMap<>();
		map.put("Order ID", "orderId");
		map.put("Name Customer", "name");
		map.put("Date", "date");
		map.put("Order Status", "orderStatus");
		map.put("Payment Status", "paymentStatus");
		OrderTableAndFilterOverview orderTableAndFilter = new OrderTableAndFilterOverview(controller.getOrdersToCompanyDTO(), map, controller);
		controller.addPropertyChangeListenerOrder(orderTableAndFilter);
		return orderTableAndFilter;
    }
    
    private CustomerDetailsOverview customerDetails() {
    	CustomerDetailsOverview customerDetailsOverview = new CustomerDetailsOverview(controller.getCurrentCompanyDTO());
    	controller.addPropertyChangeListener(customerDetailsOverview);
    	return customerDetailsOverview;
    }
    
    private CustomerTableAndFilterOverview customerTableAndFilter() {
		Map<String, String> map = new TreeMap<>();
		map.put("Open orders", "numberOfOpenOrders");
		map.put("Name", "name");
		return new CustomerTableAndFilterOverview(allCustomersDTO(), map, controller);
    }
    
    private ObservableList<CompanyDTO> allCustomersDTO() {
    	return FXCollections.observableArrayList(controller.getCurrentCompanyDTO().customers().stream()
				.map(e -> new CompanyDTO(e)).collect(Collectors.toList()));
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
