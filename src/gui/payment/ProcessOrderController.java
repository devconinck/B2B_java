package gui.payment;

import java.util.Map;
import java.util.TreeMap;

import domain.AdminController;
import domain.Order;
import gui.GenericTableView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import util.PaymentStatus;

public class ProcessOrderController extends VBox {
    private AdminController adminController;
    private GenericTableView<Order> tableView;

    public ProcessOrderController(AdminController adminController) {
        this.adminController = adminController;
        buildGui();
    }

    private void buildGui() {
        Map<String, String> columns = new TreeMap<>();
        columns.put("Order ID", "orderID");
        columns.put("Name Customer", "name");
        columns.put("Date", "date");
        columns.put("Payment Status", "paymentStatus");

        tableView = new GenericTableView<>(columns);
        tableView.setData(adminController.getOrders());
        tableView.setMinWidth(380);

        Button processButton = new Button("Process Payment");
        processButton.setOnMouseClicked(event -> processPayments());

        this.getChildren().addAll(tableView, processButton);
    }

    private void processPayments() {
        adminController.getOrders().stream()
            .filter(o -> o.getPaymentStatus().equals(PaymentStatus.INVOICE_SENT))
            .forEach(o -> {
                o.setPaymentStatus(PaymentStatus.PAID);
                adminController.updateOrder(o);
            });
        tableView.refresh();
    }
}