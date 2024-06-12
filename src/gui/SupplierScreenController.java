package gui;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.SupplierController;
import dto.CompanyDTO;
import gui.customer.CustomerDetailsOverview;
import gui.customer.CustomerTableAndFilterOverview;
import gui.login.LoginScreen;
import gui.order.OrderDetailsOverview;
import gui.order.OrderTableAndFilterOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    
    @FXML
    private Label lbl_title;
    
    private String name;
    
    public SupplierScreenController(SupplierController controller){
    	this.controller = controller;
    	 name = controller.getCurrentCompany().getName();
    	buildGui();
    	loadWelcome();
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
		
		lbl_name_login.setText(String.format("Logged in as: %s", name));
		lbl_name_login.setFont(new Font(20));

		this.logOutButton.setOnMouseClicked(e -> logOut());
		
		ordersButton.setOnMouseClicked(e -> {
			lbl_title.setText("Orders");
			this.mainScreen.getChildren().clear();
			this.mainScreen.getChildren().addAll(orderTableAndFilter().getHBox(), orderDetails().getHBox());
		});
		
		customersButton.setOnMouseClicked(event -> {
			lbl_title.setText("Customers");

			this.mainScreen.getChildren().clear();			
			this.mainScreen.getChildren().addAll(customerTableAndFilter().getHBox(), customerDetails().getHBox());
		});
		
    }
    
    private OrderDetailsOverview orderDetails() {
    	OrderDetailsOverview orderDetails = new OrderDetailsOverview(controller);
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
    	CustomerDetailsOverview customerDetailsOverview = new CustomerDetailsOverview();
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
    private void loadWelcome() {
    	lbl_title.setText("");
		GridPane gp = new GridPane();
		Text welcome = new Text(String.format("Welcome, %s", name));
		welcome.setFont(new Font(30));
		Text subTitle = new Text("You're in charge. Let's get to work.");
		
		gp.add(welcome, 0, 0);
		gp.add(subTitle, 0, 1);

		HBox box1 = createBox("/icons/orders_black.png", "Orders", "Review and manage orders.");
	    gp.add(box1, 0, 3);
	    box1.setOnMouseClicked(e -> {
			lbl_title.setText("Orders");
			this.mainScreen.getChildren().clear();
			this.mainScreen.getChildren().addAll(orderTableAndFilter().getHBox(), orderDetails().getHBox());
        });
	    HBox box2 = createBox("/icons/customers_black.png", "Customers", "Monitor orders per customer.");
	    gp.add(box2, 0, 4);
	    box2.setOnMouseClicked(e -> { 
			lbl_title.setText("Customers");

			this.mainScreen.getChildren().clear();			
			this.mainScreen.getChildren().addAll(customerTableAndFilter().getHBox(), customerDetails().getHBox());
	        });

	    GridPane.setHgrow(box1, Priority.ALWAYS);
	    GridPane.setHgrow(box2, Priority.ALWAYS);

	    //GridPane.setVgrow(box1, Priority.ALWAYS);
	    //GridPane.setVgrow(box2, Priority.ALWAYS);
	    //GridPane.setVgrow(box3, Priority.ALWAYS);
	    
	    gp.setVgap(20);
	    
	
	    //gp.setMaxSize(600, 600);
		
		this.mainScreen.getChildren().addAll(gp);
		this.mainScreen.setPadding(new Insets(35));
		
	}
    
    private HBox createBox(String url, String t, String d) {
		Image img = new Image(getClass().getResourceAsStream(url));
		ImageView icon = new ImageView(img);
	    icon.setFitWidth(50); // Set the width of the icon
	    icon.setFitHeight(50); // Set the height of the icon


	        // Create a VBox to hold the text content
	   VBox textContent = new VBox();
	    textContent.setSpacing(10);

	        // Create a label for the title
	        Text title = new Text(t);
	        title.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Set the font and weight

	        // Create a label for the description
	        Text description = new Text(d);
	        description.setFont(Font.font("Arial", 14));
	        description.setStyle("-fx-font-color: darkgrey");

	        // Add the labels to the VBox
	        textContent.getChildren().addAll(title, description);

	        // Add the icon and text content to a HBox
	        HBox content = new HBox(icon, textContent);
	        content.setSpacing(10);
	        HBox.setHgrow(textContent, Priority.ALWAYS);
	        content.setStyle("-fx-border-width: 2px; -fx-border-color: darkgrey; -fx-border-radius: 2em;   -fx-cursor: hand;");
	        content.setPadding(new Insets(15));
	        
	        return content;
    }


}
