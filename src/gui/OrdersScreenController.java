
package gui;

import java.io.IOException;

import domain.Company;
import domain.DomainController;
import domain.Observer;
import domain.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrdersScreenController extends TableView<Order> implements Observer{
	// FILTER TOEVOEGEN!!!

    @FXML
    private TableColumn<Order, String> idCol;
    @FXML
    private TableColumn<Order, String> nameCol;
    @FXML
    private TableColumn<Order, String> dateCol;
    @FXML
    private TableColumn<Order, String> orderStatusCol;    
    @FXML
    private TableColumn<Order, String> paymentStatusCol;

    private DomainController dc;
    
    public OrdersScreenController(DomainController dc) {    	
        this.dc = dc;
        this.dc.addObserver(this);
        buildGui();
        loadOrders();

    }

	private void buildGui() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
	}

	private void loadOrders() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        orderStatusCol.setCellValueFactory(cellData -> cellData.getValue().orderStatusProperty());
        paymentStatusCol.setCellValueFactory(cellData -> cellData.getValue().paymentStatusProperty());
        
        this.setItems(dc.getOrdersList());

        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Order selectedOrder = this.getSelectionModel().getSelectedItem();
                if (selectedOrder != null) {
                    dc.setCurrentOrder(selectedOrder);
                }
            }
        });
	}


	@Override
	public void update(Company c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Order c) {
		// TODO Auto-generated method stub
		
	}
	
 
}
