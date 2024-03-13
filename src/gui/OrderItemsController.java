package gui;

import java.io.IOException;

import domain.DomainController;
import domain.OrderItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderItemsController extends TableView<OrderItem> {
	
	DomainController dc;
	

    @FXML
    private TableColumn<OrderItem, String> nameCol;

    @FXML
    private TableColumn<OrderItem, Integer> quantityCol;

    @FXML
    private TableColumn<OrderItem, String> inStockCol;

    @FXML
    private TableColumn<OrderItem, Double> unitPriceCol;

    @FXML
    private TableColumn<OrderItem, Double> totalCol;

    public OrderItemsController(DomainController dc) {    	
        this.dc = dc;
        buildGui();
        loadOrderItems();

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

	private void loadOrderItems() {
		/*
		 * nameCol.setCellValueFactory(cellData -> cellData.getValue().);
		 * quantityCol.setCellValueFactory(cellData -> cellData.getValue().);
		 * inStockCol.setCellValueFactory(cellData -> cellData.getValue().);
		 * unitPriceCol.setCellValueFactory(cellData -> cellData.getValue().);
		 * totalCol.setCellValueFactory(cellData -> cellData.getValue().);
		 * 
		 *   this.setItems(dc.getOrderItems()?);
		 * 
		 */
        
	}


}
