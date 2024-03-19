package gui;

import java.io.IOException;

import domain.Company;
import domain.DomainController;
import domain.Observer;
import domain.Order;
import domain.OrderItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderItemsController extends TableView<OrderItem> implements Observer {
	
	DomainController dc;
	

    @FXML
    private TableColumn<OrderItem, String> nameCol;

    @FXML
    private TableColumn<OrderItem, String> quantityCol;

    @FXML
    private TableColumn<OrderItem, String> inStockCol;

    @FXML
    private TableColumn<OrderItem, String> unitPriceCol;

    @FXML
    private TableColumn<OrderItem, String> totalCol;

    public OrderItemsController(DomainController dc) {    	
        this.dc = dc;
        this.dc.addObserver(this);
        buildGui();
    }

	private void buildGui() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
	}

	private void loadOrderItems(String orderId) {
		  nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		  quantityCol.setCellValueFactory(cellData -> cellData.getValue().qProperty());
		  inStockCol.setCellValueFactory(cellData -> cellData.getValue().inStockProperty());
		  unitPriceCol.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty());
		  totalCol.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
		  
		  this.setItems(dc.getOrderItemsList(orderId));        
	}
	
	@Override
	public void update(Order o) {
		loadOrderItems(o.getOrderId());
	}

	@Override
	public void update(Company c) {
		// TODO Auto-generated method stub
		
	}
}
