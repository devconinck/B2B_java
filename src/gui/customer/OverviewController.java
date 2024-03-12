package gui.customer;

import java.io.IOException;
import java.time.LocalDate;

import domain.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class OverviewController extends BorderPane {
	
	@FXML
    private Button btn_customers, btn_orders;
	
	@FXML
    private HBox header_hbox;

    @FXML
    private AnchorPane main_anchorpane;
    
    @FXML
    private Label hbox_label;
    
    private Company currentCompany;
	
	public OverviewController(Company currentCompany) {
		this.currentCompany = currentCompany;
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
		setCustomerFields();
		//btn_customers.setOnAction(event -> setCustomerFields());
		//btn_orders.setOnAction(event -> setOrderFields());
	}

	private void setCustomerFields() {
		GenericTableView<Customer> customerTableView = new GenericTableView<>(Customer.class);
		customerTableView.setData(createCustomers());
		customerTableView.setPrefWidth(main_anchorpane.getWidth());
		main_anchorpane.getChildren().add(customerTableView);
	}
	
	private void setOrderFields() {
		
	}
	
	private ObservableList<Customer> createCustomers() {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		Customer c1 = new Customer("Henk", 29, LocalDate.of(1993, 3, 7));
        Customer c2 = new Customer("Alice", 35, LocalDate.of(1987, 8, 15));
        Customer c3 = new Customer("Bob", 42, LocalDate.of(1980, 5, 20));
        customers.addAll(c1,c2,c3);
        return customers;
	}
	
}
