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
import util.PaymentStatus;

public class CustomerOrderFilterController extends FilterController<OrderDTO>{
	
	private GridPane paymentPane;
	private Set<String> allowedPaymentOptions;

	public CustomerOrderFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
		this.paymentPane = new GridPane();
		this.allowedPaymentOptions = new HashSet<>();
		Label lbl_paymentoptions = new Label("Payment Options");
		vbox.getChildren().addAll(lbl_paymentoptions, createPaymentOptionsGrid());
	}

	@Override
	public ObservableList<OrderDTO> Filter(ObservableList<OrderDTO> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.orderId().startsWith(super.getSearchText())).collect(Collectors.toList()));
	}
	
	private GridPane createPaymentOptionsGrid() {
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

        return paymentPane;
    }
	
	private void filterPaymentStatus(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			allowedPaymentOptions.add(checkBox.getText());
		} else {
			allowedPaymentOptions.remove(checkBox.getText());
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
		originalList.setAll(newList);
	}

}
