package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OrdersOverviewController extends GridPane {
	
	private OrdersScreenController ordersScreen;
	private OrderDetailsScreenController orderDetails;
	private OrderItemsController orderItems;
	private OrdersFilterController filter;
	
	public OrdersOverviewController(DomainController dc) {
		this.filter = new OrdersFilterController(dc.getOrdersList());
		this.ordersScreen = new OrdersScreenController(dc, filter);
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
		
		HBox hBox1 = new HBox();
		HBox.setHgrow(filter, Priority.ALWAYS); 
		hBox1.getChildren().addAll(filter);
		
		VBox vBox1 = new VBox();
		VBox.setVgrow(ordersScreen, Priority.ALWAYS);
		this.add(vBox1, 0, 0);
		vBox1.getChildren().addAll(hBox1, ordersScreen);
		
		VBox vBox2 = new VBox();
		vBox2.setAlignment(Pos.TOP_CENTER);
		vBox2.setPadding(new Insets(0, 10, 10, 10));
		this.add(vBox2, 1, 0);
		vBox2.getChildren().addAll(orderDetails);
		
		VBox vBox3 = new VBox();
		vBox3.setAlignment(Pos.BOTTOM_CENTER);
		vBox3.setPadding(new Insets(0, 10, 10, 10));
		this.add(vBox3, 1, 0);
		vBox3.getChildren().addAll(orderItems);
		
	    /*VBox vBox1 = new VBox();
        //VBox.setVgrow(orderDetails, Priority.ALWAYS);
        //VBox.setVgrow(orderItems, Priority.ALWAYS);
        this.add(vBox1, 1, 0);
        vBox1.getChildren().addAll(orderDetails, orderItems);
		this.add(filter, 0, 0);
		this.add(ordersScreen, 0, 1);*/
	}

}

