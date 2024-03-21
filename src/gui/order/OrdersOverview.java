package gui.order;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.Company;
import domain.SupplierController;
import dto.OrderDTO;
import dto.OrderItemDTO;
import gui.FilterController;
import gui.GenericOverview;
import gui.GenericTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.PaymentOption;

public class OrdersOverview extends GenericOverview<OrderDTO> {
	
	private TextField txf_name, txf_customerContact, txf_orderId, txf_street, txf_addressNr, txf_city, txf_postalcode, txf_country,
	txf_orderStatus, txf_paymentStatus, txf_lastPayment;
	private GenericTableView<OrderItemDTO> orderItemTable;
	private ObservableList<OrderItemDTO> orderItems;
	private GridPane paymentPane;
	
	
	public OrdersOverview(Map<String, String> attributes, SupplierController sc) {
		super(FXCollections.observableArrayList(sc.getOrders().stream().map(o -> new OrderDTO(o)).collect(Collectors.toList())), attributes, sc);
		// TODO om te laten zien dat de scrollbar ook css heeft, werkt niet?
		hbox_main.getStylesheets().add("css/label.css");
	}
	
	@Override
	protected void saveEntity() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void removeEntity() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void setCurrent() {
		txf_name.setText(current.name());
		txf_customerContact.setText(current.name());
		txf_orderId.setText(current.orderId());
		txf_street.setText(current.street());
		txf_addressNr.setText(current.addressNr()); 
		txf_city.setText(current.city());
		txf_postalcode.setText(current.postalCode());
		txf_country.setText(current.country());
		txf_orderStatus.setText(current.orderStatus());
		txf_paymentStatus.setText(current.paymentStatus().getValue());
		txf_lastPayment.setText(current.lastPaymentReminder());
	
		orderItems = FXCollections.observableArrayList(((SupplierController) controller).getOrderItems(current.orderId()).stream().map(or -> new OrderItemDTO(or)).collect(Collectors.toList()));
		orderItemTable.setData(orderItems);
		
		Company c = controller.getCurrentCompany();
		paymentPane.getChildren().clear();
        int row = 0;
        int col = 0;
        int maxColumns = 3;
        for (PaymentOption option : PaymentOption.values()) {
            CheckBox checkBox = new CheckBox(option.getDisplayName());
            checkBox.setSelected(c.getPaymentOptions().contains(option));
            paymentPane.add(checkBox, col++, row);
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }
	}
	
	@Override
	protected VBox createDetails(OrderDTO order) {
		vboxDetails.clear();
		VBox vbox_complete = new VBox();

		HBox hbox_logo_name_customerContact = new HBox();
		
		// Name
		VBox vbox_name = new VBox(new Label("Name"));
		txf_name = new TextField(order.name());
		txf_name.setEditable(false);
		vbox_name.getChildren().add(txf_name);
		vbox_name.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_name);
		
		// Customer Contact
		VBox vbox_customerContact = new VBox(new Label("Customer Contact"));
		txf_customerContact = new TextField(order.name());
		txf_customerContact.setEditable(false);
		vbox_customerContact.getChildren().add(txf_customerContact);
		vbox_customerContact.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_customerContact);

		// Order id
		VBox vbox_orderId = new VBox(new Label("Order ID"));
		txf_orderId = new TextField(String.format("%s", order.orderId()));
		txf_orderId.setEditable(false);;
		vbox_orderId.getChildren().add(txf_orderId);
		vbox_orderId.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_orderId);
		
		hbox_logo_name_customerContact.getChildren().addAll(vbox_name, vbox_customerContact, vbox_orderId);
		
		// AddressLine1
		HBox hbox_street_addressnr = new HBox();
		// Street
		VBox vbox_street = new VBox(new Label("Street"));
		txf_street = new TextField(order.street());
		txf_street.setEditable(false);
		vbox_street.getChildren().add(txf_street);
		vbox_street.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_street);
		// Number
		VBox vbox_addressnr = new VBox(new Label("Address Nr."));
		txf_addressNr = new TextField(order.addressNr());
		txf_addressNr.setEditable(false);
		vbox_addressnr.getChildren().add(txf_addressNr);
		vbox_addressnr.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_addressnr);
		hbox_street_addressnr.getChildren().addAll(vbox_street, vbox_addressnr);
		
		// AddressLine2
		HBox hbox_city_postalcode_country = new HBox();
		// City
		VBox vbox_city = new VBox(new Label("City"));
		txf_city = new TextField(order.city());
		txf_city.setEditable(false);
		vbox_city.getChildren().add(txf_city);
		vbox_city.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_city);
		// Postalcode
		VBox vbox_postalcode = new VBox(new Label("Postalcode"));
		txf_postalcode = new TextField(order.postalCode());
		txf_postalcode.setEditable(false);
		vbox_postalcode.getChildren().add(txf_postalcode);
		vbox_postalcode.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_postalcode);
		// Country
		VBox vbox_country = new VBox(new Label("Country"));
		txf_country = new TextField(order.country());
		txf_country.setEditable(false);
		vbox_country.getChildren().add(txf_country);
		vbox_country.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_country);
		hbox_city_postalcode_country.getChildren().addAll(vbox_city, vbox_postalcode, vbox_country);

		// Status
		HBox hbox_orderstatus_paymentstatus_lastpayment = new HBox();
		// Order Status
		VBox vbox_orderstatus = new VBox(new Label("Order Status"));
		txf_orderStatus = new TextField(order.orderStatus());
		txf_orderStatus.setEditable(false);
		vbox_orderstatus.getChildren().add(txf_orderStatus);
		vbox_orderstatus.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_orderstatus);
		// Payment Status
		VBox vbox_paymentstatus = new VBox(new Label("Payment Status"));
		txf_paymentStatus = new TextField(order.paymentStatus().toString());
		txf_paymentStatus.setEditable(false);
		vbox_paymentstatus.getChildren().add(txf_paymentStatus);
		vbox_paymentstatus.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_paymentstatus);
		// Last Payment
		VBox vbox_lastpayment = new VBox(new Label("Last Payment Update"));
		txf_lastPayment = new TextField(order.paymentStatus().toString());
		txf_lastPayment.setEditable(false);
		vbox_lastpayment.getChildren().add(txf_lastPayment);
		vbox_lastpayment.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_lastpayment);
		hbox_orderstatus_paymentstatus_lastpayment.getChildren().addAll(vbox_orderstatus, vbox_paymentstatus, vbox_lastpayment);
		
		//Payment Options
		Label paymentOptionsLabel = new Label("Payment Options:");
        paymentPane = createPaymentOptionsGrid();
        VBox paymentOptionsVBox = new VBox(5);
        paymentOptionsVBox.getChildren().addAll(paymentOptionsLabel, paymentPane);
        paymentOptionsVBox.setPadding(new Insets(10, 10, 10, 20));
		
		vbox_complete.getChildren().addAll(hbox_logo_name_customerContact, hbox_street_addressnr, hbox_city_postalcode_country, hbox_orderstatus_paymentstatus_lastpayment, paymentOptionsVBox);
				
		setOrderItems(vbox_complete);

		return vbox_complete;
	}
	
	private void setOrderItems(VBox vbox_complete) {
		Map<String, String> mapOrders = new TreeMap<>(Map.ofEntries(
				Map.entry("Name", "name"),
				Map.entry("Quantity", "quantity"),
				Map.entry("In Stock", "inStock"),
				Map.entry("Unit Price", "unitPrice"),
				Map.entry("Total Product", "totalProduct")
				));
		orderItems = FXCollections.observableArrayList(((SupplierController) controller).getOrderItems(current.getOrderId()).stream().map(or -> new OrderItemDTO(or)).collect(Collectors.toList()));
		orderItemTable = new GenericTableView<OrderItemDTO>(mapOrders);
		orderItemTable.setData(FXCollections.observableArrayList(orderItems));
		orderItemTable.getStylesheets().add("css/label.css");
		vbox_complete.getChildren().add(orderItemTable);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected FilterController setFilter() {
		return new OrdersFilterController(others);
	}
	
	private GridPane createPaymentOptionsGrid() {
        GridPane paymentPane = new GridPane();
        paymentPane.setHgap(10);
        paymentPane.setVgap(10);

        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentOption option : PaymentOption.values()) {
            CheckBox checkBox = new CheckBox(option.getDisplayName());
            paymentPane.add(checkBox, col, row);

            col++;
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }

        return paymentPane;
    }
	
}
