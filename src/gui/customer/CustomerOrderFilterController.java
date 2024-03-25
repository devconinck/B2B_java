package gui.customer;

import java.util.stream.Collectors;

import dto.OrderDTO;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import util.PaymentStatus;

public class CustomerOrderFilterController extends FilterController<OrderDTO>{
	
	private GridPane paymentPane;
	private ToggleGroup toggleGroup;

	public CustomerOrderFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
		this.paymentPane = new GridPane();
		this.toggleGroup = new ToggleGroup();
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
        	ToggleButton tb = new ToggleButton(option.getValue());
            //CheckBox checkBox = new CheckBox(option.getValue());
            paymentPane.add(tb, col, row);
            
            tb.setToggleGroup(toggleGroup);
            
            tb.setOnAction(event -> filterPaymentStatus(tb));

            col++;
            if (col >= maxColumns) {
                col = 0;
                row++;
            }
        }

        return paymentPane;
    }
	
	private void filterPaymentStatus(ToggleButton cb) {
		System.out.println(toggleGroup.getSelectedToggle().toString());
		System.out.println(toggleGroup.getSelectedToggle().getProperties());
		originalList.setAll(copyOriginal);
		ObservableList<OrderDTO> newList = FXCollections.observableArrayList(originalList.stream().filter(o -> o.paymentStatus().toString().contains(getAccessibleHelp()) toggleGroup.getSelectedToggle().toString().contains()).collect(Collectors.toList()));
		originalList.setAll(newList);
	}

}
