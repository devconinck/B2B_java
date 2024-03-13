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

/*
public class OverviewController<T> extends BorderPane {

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

	private T currentHeader;

	public OverviewController(T currentHeader) {
		this.currentHeader = currentHeader;
		buildGui();
	}

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("supplierOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
			hbox_label.setText("Example company");
		} catch (IOException e) {
			System.out.println("Couldn't load Supplier Overview screen");
			e.printStackTrace();
		}
		btn_customers.setOnAction(event -> setCustomerFields());
	}

	private void setCustomerFields() {
		CustomerOverview co = new CustomerOverview((Company) currentHeader, createCustomers());
		main_anchorpane.getChildren().add(co.getHBox());
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
*/
