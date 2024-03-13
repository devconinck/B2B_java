package gui.customer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class OverviewController extends BorderPane {

	@FXML
	private Button btn_customers, btn_orders;

	@FXML
	private HBox hbox_header;

	@FXML
	private AnchorPane main_anchorpane;

	@FXML
	private Label hbox_label;

	@FXML
	private VBox vbox_sidebar;

	private Company currentCompany;
	private Customer currentCustomer;
	private List<VBox> vboxDetails;

	public OverviewController(Company currentCompany) {
		this.currentCompany = currentCompany;
		this.vboxDetails = new ArrayList<>();
		buildGui();
	}

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("supplierOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
			hbox_label.setText(currentCompany.getName());
		} catch (IOException e) {
			System.out.println("Couldn't load Supplier Overview screen");
			e.printStackTrace();
		}
		btn_customers.setOnAction(event -> setCustomerFields());
		btn_orders.setOnAction(event -> setOrderFields());
	}

	private void setCustomerFields() {
		main_anchorpane.getChildren().clear();
		HBox hbox = new HBox();
		VBox vboxDetailsAndButtons = new VBox();

		// Add Table
		GenericTableView<Customer> customerTableView = new GenericTableView<>(Customer.class);
		customerTableView.setData(createCustomers());
		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		double screenWidth = visualBounds.getWidth();
		customerTableView.setPrefWidth((visualBounds.getWidth() - vbox_sidebar.getWidth()) / 2);
		// customerTableView.setPrefHeight((visualBounds.getHeight() -
		// hbox_header.getHeight()) / 2);

		// Details and Buttons
		VBox details = createDetails(createCustomers().get(0));
		Buttons buttons = new Buttons(Arrays.asList("Save", "Remove", "Clear"));
		HBox hbox_buttons = buttons.getButtonfield();
		buttons.getButtons().get(0).setOnMouseClicked(event -> saveFields());
		buttons.getButtons().get(2).setOnMouseClicked(event -> clearFields());

		// Add all together
		vboxDetailsAndButtons.getChildren().addAll(details, hbox_buttons);
		hbox.getChildren().addAll(customerTableView, vboxDetailsAndButtons);

		main_anchorpane.getChildren().add(hbox);
	}
	
	private void clearFields() {
		vboxDetails.stream().map(vbox -> vbox.getChildrenUnmodifiable().get(1)).forEach(field -> ((TextInputControl) field).clear());
	}

	private void saveFields() {
		vboxDetails.stream().map(vbox -> vbox.getChildren().get(1)).forEach(field -> System.out.println(((TextInputControl) field).getText()));
	}

	private void setOrderFields() {
		main_anchorpane.getChildren().clear();
		HBox hbox = new HBox();
		
		// Table
		GenericTableView<Order> orderTableView = new GenericTableView<>(Order.class, getAttributeNames(Order.class));
		orderTableView.setData(createOrders(6));
		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		orderTableView.setPrefWidth(visualBounds.getWidth() / 2);

		// Add details screen
		hbox.getChildren().addAll(orderTableView, createDetails(createOrders(1).get(0)));

		main_anchorpane.getChildren().add(hbox);
	}

	private <T> VBox createDetails(T entityClass) {
		List<String> attributes = getAttributeNames(entityClass.getClass());
		VBox vbox_mainDetails = new VBox();

		for (int i = 0; i < attributes.size(); i += 2) {
			HBox hbox_twoAttributes = new HBox();
			hbox_twoAttributes.setPadding(new Insets(20));
			if (attributes.size() - i > 1)
				hbox_twoAttributes.getChildren().addAll(
						createLabel(String.format("%s", attributes.get(i) + i), attributes.get(i)),
						createLabel(String.format("%s", attributes.get(i + 1) + (i + 1)), attributes.get(i + 1)));
			else
				hbox_twoAttributes.getChildren()
						.add(createLabel(String.format("%s", attributes.get(i) + i), attributes.get(i)));
			vbox_mainDetails.getChildren().add(hbox_twoAttributes);
		}
		return vbox_mainDetails;
	}

	private VBox createLabel(String field_name, String attribute) {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vboxDetails.add(vbox);
		Label lbl_generic = new Label(attribute);
		TextField txf_generic = new TextField();
		vbox.getChildren().addAll(lbl_generic, txf_generic);
		return vbox;
	}

	private <T> List<String> getAttributeNames(Class<T> entityClass) {
		return Arrays.stream(entityClass.getDeclaredFields()).map(field -> field.getName())
				.filter(field -> !field.contains("id")) // dont show the "id" used in the db
				.collect(Collectors.toList());
	}

	private ObservableList<Order> createOrders(int numberOfOrders) {
		ObservableList<Order> orders = FXCollections.observableArrayList();
		for (int i = 1; i <= numberOfOrders; i++) {
			String orderId = "Order" + i;
			int syncId = i;
			String customerId = "Customer" + i;
			String orderReference = "Reference" + i;
			String orderDateTime = "2024-03-07 12:00:00"; // You may use the actual date and time logic here
			String lastPaymentReminder = "2024-03-14 12:00:00"; // You may use the actual date and time logic here
			String netAmount = "100.00"; // You may use the actual amount logic here
			String taxAmount = "20.00"; // You may use the actual amount logic here
			String totalAmount = "120.00"; // You may use the actual amount logic here
			String currency = "USD";

			Order order = new Order(orderId, syncId, customerId, orderReference, orderDateTime, lastPaymentReminder,
					netAmount, taxAmount, totalAmount, currency);

			orders.add(order);
		}
		return orders;
	}

	private ObservableList<Customer> createCustomers() {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		Customer c1 = new Customer("Henk", 29, LocalDate.of(1993, 3, 7));
		Customer c2 = new Customer("Alice", 35, LocalDate.of(1987, 8, 15));
		Customer c3 = new Customer("Bob", 42, LocalDate.of(1980, 5, 20));
		customers.addAll(c1, c2, c3);
		return customers;
	}

}
