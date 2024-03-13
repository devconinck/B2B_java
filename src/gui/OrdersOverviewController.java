package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OrdersOverviewController extends GridPane {
	private DomainController dc;
	
	private OrdersScreenController ordersScreen;
	private OrderDetailsScreenController orderDetails;
	private OrderItemsController orderItems;
	
	public OrdersOverviewController(DomainController dc) {
		this.dc = dc;
		
		this.ordersScreen = new OrdersScreenController(dc);
		this.orderDetails = new OrderDetailsScreenController(dc);
		this.orderItems = new OrderItemsController(dc);
		
		buildGui();
	}
	
	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ordersOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Orders Screen");
			System.out.println(e.getMessage());
		}
		
	    VBox vBox1 = new VBox();
        VBox.setVgrow(orderDetails, Priority.ALWAYS);
        VBox.setVgrow(orderItems, Priority.ALWAYS);
        this.add(vBox1, 1, 0);
        vBox1.getChildren().addAll(orderDetails, orderItems);
		
		this.add(ordersScreen, 0, 0);
	}

}

