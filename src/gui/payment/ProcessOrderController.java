package gui.payment;

import java.util.Map;
import java.util.TreeMap;

import domain.Company;
import domain.Observer;
import domain.Order;
import gui.GenericTableView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import util.PaymentStatus;

public class ProcessOrderController extends VBox implements Observer {
    private ObservableList<Order> orders;
    private GenericTableView<Order> tableView;

    public ProcessOrderController(ObservableList<Order> orders) {
        this.orders = orders;
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
        orders.stream()
            .filter(o -> o.getPaymentStatus().equals(PaymentStatus.INVOICE_SENT))
            .forEach(o -> o.setPaymentStatus(PaymentStatus.PAID));
    }

    @Override
    public void update(Order order) {
        tableView.refresh();
    }

    @Override
    public void update(Company company) {
        // Not needed in this class
    }
}