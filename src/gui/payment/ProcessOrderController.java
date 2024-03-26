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
import javafx.scene.layout.VBox;
import util.PaymentStatus;

public class ProcessOrderController extends VBox {
	private AdminController controller;
	private ObservableList<Order> orders;

    private GenericTableView<Order> tableView;

    public ProcessOrderController(AdminController controller) {
        this.controller = controller;
        this.orders = controller.getOrders();
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
        tableView.setMinWidth(380);

        Button processButton = new Button("Process Payment");
        processButton.setOnMouseClicked(event -> processPayments());

        this.getChildren().addAll(tableView, processButton);
    }

    private void processPayments() {
    	List<Order> batchOrders = new ArrayList<>();
    	
    	orders.stream()
            .filter(o -> o.getPaymentStatus().equals(PaymentStatus.INVOICE_SENT))
            .forEach(o -> {
                o.setPaymentStatus(PaymentStatus.PAID);
                batchOrders.add(o);
            });
    	
    	controller.batchUpdateOrders(batchOrders);
    	
        for (Order updatedOrder : batchOrders) {
            int index = orders.indexOf(updatedOrder);
            if (index >= 0) {
                orders.set(index, updatedOrder);
            }
        }
    }
}