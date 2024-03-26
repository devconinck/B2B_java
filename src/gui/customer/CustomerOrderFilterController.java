package gui.customer;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import dto.OrderDTO;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.OrderStatus;
import util.PaymentStatus;

public class CustomerOrderFilterController extends FilterController<OrderDTO>{
	
	private Set<String> allowedPaymentOptions;
	private Set<String> allowedOrderOptions;

	public CustomerOrderFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
		this.allowedPaymentOptions = new HashSet<>();
		this.allowedOrderOptions = new HashSet<>();
		
		vbox.getChildren().addAll(createPaymentOptionsGrid(), createOrderOptionsGrid());
	}

	@Override
	public ObservableList<OrderDTO> Filter(ObservableList<OrderDTO> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.orderId().startsWith(super.getSearchText())).collect(Collectors.toList()));
	}
	
	private VBox createPaymentOptionsGrid() {
		GridPane paymentPane = new GridPane();
        paymentPane.setHgap(10);
        paymentPane.setVgap(10);

        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (PaymentStatus option : PaymentStatus.values()) {
            CheckBox checkBox = new CheckBox(option.getValue());
            paymentPane.add(checkBox, col, row);
            
            checkBox.setOnAction(event -> filterPaymentStatus(checkBox));

            col++;
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }
        
        Label lbl_paymentoptions = new Label("Payment Options");
        VBox vbox_label_pane = new VBox();
        vbox_label_pane.getChildren().addAll(lbl_paymentoptions, paymentPane);

        return vbox_label_pane;
    }
	
	private VBox createOrderOptionsGrid() {
		GridPane orderPane = new GridPane();
		orderPane.setHgap(10);
		orderPane.setVgap(10);

        int row = 0;
        int col = 0;
        int maxColumns = 3;

        for (OrderStatus option : OrderStatus.values()) {
            CheckBox checkBox = new CheckBox(option.getValue());
            orderPane.add(checkBox, col, row);
            
            checkBox.setOnAction(event -> filterOrderStatus(checkBox));

            col++;
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }
        
        Label lbl_paymentoptions = new Label("Payment Options");
        VBox vbox_label_pane = new VBox();
        vbox_label_pane.getChildren().addAll(lbl_paymentoptions, orderPane);

        return vbox_label_pane;
    }
	
	private void filterPaymentStatus(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			allowedPaymentOptions.add(checkBox.getText());
		} else {
			allowedPaymentOptions.remove(checkBox.getText());
		}
		runAllFilters();
	}
	
	private void filterOrderStatus(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			allowedOrderOptions.add(checkBox.getText());
		} else {
			allowedOrderOptions.remove(checkBox.getText());
		}
		runAllFilters();
	}
	
	@Override
	protected void runAllFilters() {
		// PaymentOptions
		ObservableList<OrderDTO> newList = FXCollections.observableArrayList(
				copyOriginal.stream()
				.filter(o -> {
					if(allowedPaymentOptions.isEmpty()) 
						return true; 
					return allowedPaymentOptions.contains(o.paymentStatus().getValue());
				})
				.collect(Collectors.toList())
				);
		// OrderId
		newList = Filter(newList);
		// OrderOptions
		newList = FXCollections.observableArrayList(
				newList.stream()
				.filter(o -> {
					if(allowedOrderOptions.isEmpty()) 
						return true; 
					return allowedOrderOptions.contains(o.orderStatus().getValue());
				})
				.collect(Collectors.toList())
				);
		originalList.setAll(newList);
	}

}
