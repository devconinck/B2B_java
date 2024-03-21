package gui.payment;


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
	// Haal alle orders op in tableView orderid/naam/status
	// Knop "Process Payment"
		// Haal alle orders op met status INVOICE_SENT (index 1)
		// Wijzig status naar PAID (index 2)
	// Refreshen tableView
	
	private AdminController ac;
	
	public ProcessOrderController(AdminController ac) {		
		this.ac = ac;
		buildGui();
	}
	
	private void buildGui() {
		ObservableList<Order> order = ac.getOrders();
		
		Map<String, String> columns = new TreeMap<>();
		columns.put("Order ID", "orderID");
		columns.put("Name Customer", "name");
		columns.put("Date", "date");
		columns.put("Payment Status", "paymentStatus");
		GenericTableView<Order> tableView = new GenericTableView<>(columns);
		tableView.setData(order);
				
		Button proccessButton = new Button("Process Payment");
	    proccessButton.setOnMouseClicked(event -> {
	        order.stream()
	                .filter(o -> o.getPaymentStatus().equals(PaymentStatus.INVOICE_SENT))
	                .forEach(o -> {
	                	o.setPaymentStatus(PaymentStatus.PAID);
	                	ac.updateOrder(o);
	                });
	        tableView.refresh();
	    });
	    
		this.getChildren().addAll(tableView, proccessButton);
	}

}
