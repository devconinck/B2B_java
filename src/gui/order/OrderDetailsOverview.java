package gui.order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.Order;
import domain.SupplierController;
import dto.OrderDTO;
import dto.OrderItemDTO;
import gui.GenericDetailsOverview;
import gui.GenericTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.OrderStatus;
import util.PaymentStatus;

public class OrderDetailsOverview extends GenericDetailsOverview<OrderDTO> implements PropertyChangeListener {

	@FXML
	private Button saveBtn;
	
	@FXML
	private ComboBox<String> comboBox_OrderStatus, comboBox_PaymentStatus;
	private ObservableList<String> orderStatusOptions, paymentStatusOptions;
	
	private TextField txf_name, txf_customerContact, txf_orderId, txf_street, txf_addressNr, txf_city, txf_postalcode, txf_country, txf_lastPayment;
	private Label lbl_price;
	private GenericTableView<OrderItemDTO> orderItemTable;
	private ObservableList<OrderItemDTO> orderItems;
	
	private SupplierController controller;

	public OrderDetailsOverview(SupplierController controller) {
		super();
		this.controller = controller;
		hbox_main.getStylesheets().add("css/label.css");
	}

	protected void setCurrent() {
		//OrderDetails Screen
		txf_name.setText(current.name());
		txf_customerContact.setText(current.fromCompany().getContact().getEmail());
		txf_orderId.setText(current.orderId());
		txf_street.setText(current.street());
		txf_addressNr.setText(current.addressNr()); 
		txf_city.setText(current.city());
		txf_postalcode.setText(current.postalCode());
		txf_country.setText(current.country());
		comboBox_OrderStatus.setValue(current.orderStatus().getValue());
		comboBox_PaymentStatus.setValue(current.paymentStatus().getValue());
		txf_lastPayment.setText(current.lastPaymentReminder());
		comboBox_OrderStatus.setItems(orderStatusOptions);
		comboBox_PaymentStatus.setItems(paymentStatusOptions);
	
		//OrderItems Table
		orderItems = FXCollections.observableArrayList(current.orderItems().stream().map(or -> new OrderItemDTO(or)).collect(Collectors.toList()));
		orderItemTable.setData(orderItems);
		
        //Save Button
        saveBtn.setOnMouseClicked(event -> {
        	controller.updateOrder(current.orderId(), comboBox_OrderStatus.getValue(), comboBox_PaymentStatus.getValue());
        });
        
        lbl_price.setText(getTotalOrderPrice());
	}

	@Override
	protected VBox createDetails() {
		vboxDetails.clear();
		VBox vbox_complete = new VBox();

		HBox hbox_logo_name_customerContact = new HBox();
		
		// Name
		VBox vbox_name = new VBox(new Label("Name"));
		txf_name = new TextField();
		txf_name.setEditable(false);
		vbox_name.getChildren().add(txf_name);
		vbox_name.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_name);
		
		// Customer Contact
		VBox vbox_customerContact = new VBox(new Label("Customer email"));
		txf_customerContact = new TextField();
		txf_customerContact.setEditable(false);
		vbox_customerContact.getChildren().add(txf_customerContact);
		vbox_customerContact.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_customerContact);

		// Order id
		VBox vbox_orderId = new VBox(new Label("Order ID"));
		txf_orderId = new TextField();
		txf_orderId.setEditable(false);;
		vbox_orderId.getChildren().add(txf_orderId);
		vbox_orderId.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_orderId);
		
		hbox_logo_name_customerContact.getChildren().addAll(vbox_name, vbox_customerContact, vbox_orderId);
		
		// AddressLine1
		HBox hbox_street_addressnr = new HBox();
		// Street
		VBox vbox_street = new VBox(new Label("Street"));
		txf_street = new TextField();
		txf_street.setEditable(false);
		vbox_street.getChildren().add(txf_street);
		vbox_street.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_street);
		// Number
		VBox vbox_addressnr = new VBox(new Label("Address Nr."));
		txf_addressNr = new TextField();
		txf_addressNr.setEditable(false);
		vbox_addressnr.getChildren().add(txf_addressNr);
		vbox_addressnr.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_addressnr);
		hbox_street_addressnr.getChildren().addAll(vbox_street, vbox_addressnr);
		
		// AddressLine2
		HBox hbox_city_postalcode_country = new HBox();
		// City
		VBox vbox_city = new VBox(new Label("City"));
		txf_city = new TextField();
		txf_city.setEditable(false);
		vbox_city.getChildren().add(txf_city);
		vbox_city.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_city);
		// Postalcode
		VBox vbox_postalcode = new VBox(new Label("Postalcode"));
		txf_postalcode = new TextField();
		txf_postalcode.setEditable(false);
		vbox_postalcode.getChildren().add(txf_postalcode);
		vbox_postalcode.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_postalcode);
		// Country
		VBox vbox_country = new VBox(new Label("Country"));
		txf_country = new TextField();
		txf_country.setEditable(false);
		vbox_country.getChildren().add(txf_country);
		vbox_country.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_country);
		hbox_city_postalcode_country.getChildren().addAll(vbox_city, vbox_postalcode, vbox_country);

		// Status
		HBox hbox_orderstatus_paymentstatus_lastpayment = new HBox();
		// Order Status
		VBox vbox_orderstatus = new VBox(new Label("Order Status"));
		comboBox_OrderStatus = new ComboBox<String>();
		addOrderStatusOptions();
		comboBox_OrderStatus.setItems(orderStatusOptions);
		comboBox_OrderStatus.setEditable(false);
		vbox_orderstatus.getChildren().add(comboBox_OrderStatus);
		vbox_orderstatus.setPadding(new Insets(10, 15, 10, 20));
		// Payment Status
		VBox vbox_paymentstatus = new VBox(new Label("Payment Status"));
		comboBox_PaymentStatus = new ComboBox<String>();
		addPaymentStatusOptions();
		comboBox_PaymentStatus.setItems(paymentStatusOptions);
		comboBox_PaymentStatus.setEditable(false);
		vbox_paymentstatus.getChildren().add(comboBox_PaymentStatus);
		vbox_paymentstatus.setPadding(new Insets(10, 15, 10, 20));
		// Last Payment
		VBox vbox_lastpayment = new VBox(new Label("Last Payment Update"));
		txf_lastPayment = new TextField();
		txf_lastPayment.setEditable(false);
		vbox_lastpayment.getChildren().add(txf_lastPayment);
		vbox_lastpayment.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_lastpayment);
		hbox_orderstatus_paymentstatus_lastpayment.getChildren().addAll(vbox_orderstatus, vbox_paymentstatus, vbox_lastpayment);
		
		HBox hbox_saveBtn = new HBox();
        //Save Button
        VBox saveBtnVBox = new VBox();
        saveBtn = new Button("Save changes");
        saveBtnVBox.getChildren().add(saveBtn);
        saveBtnVBox.setAlignment(Pos.CENTER);
        saveBtnVBox.setPadding(new Insets(10, 10, 10, 20));
        hbox_saveBtn.getChildren().addAll(saveBtnVBox);
		
		vbox_complete.getChildren().addAll(hbox_logo_name_customerContact, hbox_street_addressnr, hbox_city_postalcode_country, hbox_orderstatus_paymentstatus_lastpayment, hbox_saveBtn);
				
		setOrderItems(vbox_complete);
		
		VBox vbox_totalprice = new VBox();
		lbl_price = new Label();
		lbl_price.setText(String.format("Total price: %s €", 0.00));
		vbox_totalprice.getChildren().add(lbl_price);
		vbox_totalprice.setPadding(new Insets(0, 0, 0, 20));
		
		vbox_complete.getChildren().add(vbox_totalprice);

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
		orderItemTable = new GenericTableView<OrderItemDTO>(mapOrders);
		orderItemTable.getStylesheets().add("css/label.css");
		orderItemTable.setMinHeight(200);
		vbox_complete.getChildren().add(orderItemTable);
	}
	
	private void addOrderStatusOptions() {
		orderStatusOptions = FXCollections.observableArrayList();
		for(OrderStatus option : OrderStatus.values())
			orderStatusOptions.add(option.getValue());
	}
	
	private void addPaymentStatusOptions() {
		paymentStatusOptions = FXCollections.observableArrayList();
		for(PaymentStatus option : PaymentStatus.values())
			paymentStatusOptions.add(option.getValue());
	}
	
	private String getTotalOrderPrice() {
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		String formattedTotalPrice = decimalFormat.format(
		    current.orderItems().stream()
		        .map(or -> or.getUnitPrice().multiply(BigDecimal.valueOf(or.getQuantity())))
		        .map(BigDecimal::doubleValue)
		        .collect(Collectors.summarizingDouble(Double::doubleValue)).getSum()
		);
		return String.format("Total price: %s €", formattedTotalPrice);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		super.current = new OrderDTO((Order) evt.getNewValue());
		this.setCurrent();
	}

}
