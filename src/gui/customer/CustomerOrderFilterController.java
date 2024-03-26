package gui.customer;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import dto.OrderDTO;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.OrderStatus;
import util.PaymentStatus;

public class CustomerOrderFilterController extends FilterController<OrderDTO>{
	
	private Set<String> allowedPaymentOptions;
	private Set<String> allowedOrderOptions;
	private LocalDate fromDate = LocalDate.MIN.plusDays(1);
	private LocalDate untilDate = LocalDate.MAX.minusDays(1);

	public CustomerOrderFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
		this.allowedPaymentOptions = new HashSet<>();
		this.allowedOrderOptions = new HashSet<>();
		
		vbox.getChildren().addAll(createPaymentOptionsGrid(), createOrderOptionsGrid(), createDatePicker());
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
        
        Label lbl_paymentoptions = new Label("Order Options");
        VBox vbox_label_pane = new VBox();
        vbox_label_pane.getChildren().addAll(lbl_paymentoptions, orderPane);

        return vbox_label_pane;
    }
	
	private VBox createDatePicker() {
		DatePicker datePickerFrom = new DatePicker();
		datePickerFrom.setEditable(false);
		HBox fromDate = new HBox();
		Label lbl_fromDate = new Label("From");
		datePickerFrom.setOnAction(e -> {
			this.fromDate = datePickerFrom.getValue();
			runAllFilters();
		});
		fromDate.getChildren().addAll(lbl_fromDate, datePickerFrom);
		
		DatePicker datePickerUntil = new DatePicker();
		HBox untilDate = new HBox();
		Label lbl_untilDate = new Label("Until");
		datePickerUntil.setOnAction(e -> {
			this.untilDate = datePickerUntil.getValue();
			runAllFilters();
		});
		untilDate.getChildren().addAll(lbl_untilDate, datePickerUntil);
		
		HBox bothDates = new HBox();
		bothDates.getChildren().addAll(fromDate, untilDate);
		
		Label lbl_date = new Label("Date range");
        VBox vbox_label_datepicker = new VBox();
        vbox_label_datepicker.getChildren().addAll(lbl_date, bothDates);
        
        return vbox_label_datepicker;
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
		ObservableList<OrderDTO> newList = FXCollections.observableArrayList(copyOriginal);
		// PaymentOptions
		newList = FXCollections.observableArrayList(
				newList.stream()
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
		newList = FXCollections.observableArrayList(
				newList.stream()
				.filter(o -> o.date().isAfter(fromDate.minusDays(1)))
				.filter(o -> o.date().isBefore(untilDate.plusDays(1)))
				.sorted(Comparator.comparing(OrderDTO::date))
				.collect(Collectors.toList())
				);
		originalList.setAll(newList);
	}

}
