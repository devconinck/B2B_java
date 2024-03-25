package gui.customer;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import dto.CompanyDTO;
import dto.OrderDTO;
import gui.GenericTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerOrdersOverview {
	
	private StackPane root;
	private Stage primaryStage;
	private Scene scene;
	
	private CompanyDTO current;
	private GenericTableView<OrderDTO> orderTable;
	private ObservableList<OrderDTO> orders;
	
	public CustomerOrdersOverview(CompanyDTO current) {
		this.current = current;
		this.orders = FXCollections.observableArrayList(
				current.getOrders().stream().map(or -> new OrderDTO(or)).collect(Collectors.toList()));
		primaryStage = new Stage();
		root = new StackPane();
		scene = new Scene(root, 550, 750);
		setup();
		primaryStage.show();
		scene.getStylesheets().add("css/label.css");
	}

	private void setup() {
		primaryStage.setScene(scene);
		primaryStage.setTitle(String.format("Overview orders %s", current.name()));
		root.setPadding(new Insets(30));
		addElements();
	}

	private void addElements() {
		VBox filter = new VBox();
		filter.getChildren().add(new CustomerOrderFilterController(orders));
		filter.setPadding(new Insets(0, 20, 20, 20));
		
		Map<String, String> mapOrders = new TreeMap<>(Map.ofEntries(
				Map.entry("Order ID", "orderId"),
				Map.entry("Date", "date"),
				Map.entry("Price (€)", "orderAmount"),
				Map.entry("Order Status", "orderStatus"),
				Map.entry("Payment Status", "paymentStatus")));
		orderTable = new GenericTableView<>(mapOrders);
		orderTable.setMaxHeight(400);
		orderTable.setData(orders);
		
		root.getChildren().addAll(filter, orderTable);
	}

}
