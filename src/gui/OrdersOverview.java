package gui;

import java.util.Map;
import java.util.stream.Collectors;

import domain.DomainController;
import gui.GenericOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrdersOverview extends GenericOverview<OrderDTO> {
	
	private TextField txf_name, txf_customerContact, txf_orderId, txf_street, txf_addressNr, txf_city, txf_postalcode, txf_country,
	txf_orderStatus, txf_paymentStatus, txf_lastPayment;
	
	private ImageView imgvw_logo;
	
	public OrdersOverview(Map<String, String> attributes, DomainController dc) {
		super(FXCollections.observableArrayList(dc.getOrdersList().stream().map(o -> new OrderDTO(o)).collect(Collectors.toList())), attributes, dc);
		// TODO om te laten zien dat de scrollbar ook css heeft, werkt niet?
		genericTableView.setPrefHeight(100);
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
		txf_street.setText(current.name());
		txf_addressNr.setText(current.name()); 
		txf_city.setText(current.name());
		txf_postalcode.setText(current.name());
		txf_orderStatus.setText(current.orderStatus());
		txf_paymentStatus.setText(current.paymentStatus());
		txf_lastPayment.setText(current.lastPaymentReminder());
		/*try {
			imgvw_logo.setImage(new Image(String.format("images/%s", current.logo())));
		} catch (IllegalArgumentException e) {
			System.err.println(String.format("Failed to get logo for: %s", current.name()));
			imgvw_logo.setImage(new Image(String.format("images/%s", "delaware-logo.jpg")));
		}*/
	}
	
	@Override
	protected VBox createDetails(OrderDTO order) {
		// logo, name, sector, address, contact, customerSinds
		// TODO UC (gegevens overzicht beschikbare klanten) = naam, aantal openstaande bestellingen
		// TODO UC (gegevens details klant) = naam, logo, adres, contactgegevens
		// TODO UC (gegevens bestellingen klant) = order id, datum, orderbedrag, orderstatus, betalingsstatus
		vboxDetails.clear();
		VBox vbox_complete = new VBox();

		HBox hbox_logo_name_customerContact = new HBox();
		
		HBox hbox_address_complete = new HBox();
		
		// Logo
		VBox vbox_logo = new VBox();
		vbox_logo.setPadding(new Insets(20));
		/*try {
			imgvw_logo = new ImageView(new Image(String.format("images/%s", current.logo())));
		} catch (IllegalArgumentException e) {
			System.err.println(String.format("Failed to get logo for: %s", current.name()));
			imgvw_logo = new ImageView(new Image(String.format("images/%s", "delaware-logo.jpg")));
		}
		imgvw_logo.setFitHeight(50);
		imgvw_logo.setFitWidth(50);
		vbox_logo.getChildren().add(imgvw_logo);*/
		
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
		vbox_customerContact.setPadding(new Insets(20, 10, 10, 10));
		vboxDetails.add(vbox_customerContact);

		// Order id
		VBox vbox_orderId = new VBox(new Label("Order ID"));
		txf_orderId = new TextField(String.format("%s", order.orderId()));
		txf_orderId.setEditable(false);;
		vbox_orderId.getChildren().add(txf_orderId);
		vbox_orderId.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_orderId);
		
		hbox_logo_name_customerContact.getChildren().addAll(vbox_logo, vbox_name, vbox_customerContact, vbox_orderId);
		
		// AddressLine1
		HBox hbox_street_addressnr = new HBox();
		// Street
		VBox vbox_street = new VBox(new Label("Street"));
		txf_street = new TextField(order.name());
		txf_street.setEditable(false);
		vbox_street.getChildren().add(txf_street);
		vbox_street.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_street);
		// Number
		VBox vbox_addressnr = new VBox(new Label("Address Nr."));
		txf_addressNr = new TextField(order.name());
		txf_addressNr.setEditable(false);
		vbox_addressnr.getChildren().add(txf_addressNr);
		vbox_addressnr.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_addressnr);
		hbox_street_addressnr.getChildren().addAll(vbox_street, vbox_addressnr);
		
		// AddressLine2
		HBox hbox_city_postalcode_country = new HBox();
		// City
		VBox vbox_city = new VBox(new Label("City"));
		txf_city = new TextField(order.name());
		txf_city.setEditable(false);
		vbox_city.getChildren().add(txf_city);
		vbox_city.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_city);
		// Postalcode
		VBox vbox_postalcode = new VBox(new Label("Postalcode"));
		txf_postalcode = new TextField(order.name());
		txf_postalcode.setEditable(false);
		vbox_postalcode.getChildren().add(txf_postalcode);
		vbox_postalcode.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_postalcode);
		// Country
		VBox vbox_country = new VBox(new Label("Country"));
		txf_country = new TextField(order.name());
		txf_country.setEditable(false);
		vbox_country.getChildren().add(txf_country);
		vbox_country.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_country);
		hbox_city_postalcode_country.getChildren().addAll(vbox_city, vbox_postalcode, vbox_country);

		hbox_address_complete.getChildren().addAll(hbox_street_addressnr, hbox_city_postalcode_country);

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
		txf_paymentStatus = new TextField(order.paymentStatus());
		txf_paymentStatus.setEditable(false);
		vbox_paymentstatus.getChildren().add(txf_paymentStatus);
		vbox_paymentstatus.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_paymentstatus);
		// Last Payment
		VBox vbox_lastpayment = new VBox(new Label("Last Payment Update"));
		txf_lastPayment = new TextField(order.paymentStatus());
		txf_lastPayment.setEditable(false);
		vbox_lastpayment.getChildren().add(txf_lastPayment);
		vbox_lastpayment.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_lastpayment);
		hbox_orderstatus_paymentstatus_lastpayment.getChildren().addAll(vbox_orderstatus, vbox_paymentstatus, vbox_lastpayment);
		
		vbox_complete.getChildren().addAll(hbox_logo_name_customerContact, hbox_address_complete, hbox_orderstatus_paymentstatus_lastpayment);
				
		setOrders(vbox_complete);

		return vbox_complete;
	}
	
	private void setOrders(VBox vbox_complete) {
		// TODO Auto-generated method stub
		// TODO UC (gegevens bestellingen klant) = order id, datum, orderbedrag, orderstatus, betalingsstatus
		ObservableList<OrderDTO> orders = FXCollections.observableArrayList(dc.getOrdersList().stream().map(or -> new OrderDTO(or)).collect(Collectors.toList()));
		GenericTableView<OrderDTO> orderTable = new GenericTableView<>(orders.get(0));
		orderTable.setData(FXCollections.observableArrayList(orders.subList(0, 15)));
		orderTable.getStylesheets().add("css/orderPage.css");
		vbox_complete.getChildren().add(orderTable);
	}
	
	
}
