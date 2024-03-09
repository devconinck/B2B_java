package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class OrdersOverviewController extends GridPane {
		private DomainController dc;
	
	private OrdersScreenController orders;
	private OrderDetailsScreenController orderDetails;
	
	public OrdersOverviewController(DomainController dc) {
		this.dc = dc;
		
		this.orders = new OrdersScreenController(dc);
		this.orderDetails = new OrderDetailsScreenController(dc);
		
		buildGui();
	}
	
	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ordersOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Employees Screen");
			System.out.println(e.getMessage());
		}
		
		this.add(orders, 0, 0);
		this.add(orderDetails, 1, 0);
	}

}

