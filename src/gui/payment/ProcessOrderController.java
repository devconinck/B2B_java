package gui.payment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.AdminController;
import domain.Order;
import gui.GenericTableView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import util.PaymentStatus;

public class ProcessOrderController extends GridPane {
	private AdminController ac;
	private ObservableList<Order> orders;

    private GenericTableView<Order> tableView;
    public ProcessOrderController(AdminController ac) {
        this.ac = ac;
        this.orders = ac.getOrders();
        buildGui();
    }


    private void buildGui() {
        Map<String, String> columns = new TreeMap<>();
        columns.put("Order ID", "orderID");
        columns.put("Name Customer", "name");
        columns.put("Date", "date");
        columns.put("Payment Status", "paymentStatus");

        tableView = new GenericTableView<>(columns);
        tableView.setData(orders);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMinWidth(380);
        
        tableView.setStyle("-fx-padding: 20 35 10 35");
        
        // Apply CSS styling to the table
        

        Button processButton = new Button("Process Payment");
        processButton.getStyleClass().add("custom-button");
        processButton.setOnMouseClicked(event -> processPayments());

        HBox buttonContainer = new HBox(processButton);
        buttonContainer.setStyle("-fx-padding: 15 10 10 35");
        
        
        VBox v = new VBox();
        v.setMinSize(600, 400);
        v.getChildren().addAll(tableView, buttonContainer);
        this.add(v, 0, 0);
        GridPane.setHgrow(v, Priority.ALWAYS);
        GridPane.setVgrow(v, Priority.ALWAYS);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        
        
    }

    private void processPayments() {
    	List<Order> batchOrders = new ArrayList<>();
    	
    	orders.stream()
            .filter(o -> o.getPaymentStatus().equals(PaymentStatus.INVOICE_SENT))
            .forEach(o -> {
                o.setPaymentStatus(PaymentStatus.PAID);
                batchOrders.add(o);
            });
    	
    	ac.batchUpdateOrders(batchOrders);
    	
        for (Order updatedOrder : batchOrders) {
            int index = orders.indexOf(updatedOrder);
            if (index >= 0) {
                orders.set(index, updatedOrder);
            }
        }
    }
}