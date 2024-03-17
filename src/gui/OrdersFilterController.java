package gui;

import java.util.List;
import java.util.stream.Collectors;

import domain.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class OrdersFilterController extends HBox {
	
	private final TextField filterField;
    private final ObservableList<Order> originalList;
    private final ObservableList<Order> filteredList;

    public OrdersFilterController(ObservableList<Order> originalList) {
        this.originalList = originalList;
        this.filteredList = FXCollections.observableArrayList();
        filterField = new TextField();
        filterField.setPromptText("Search by order id");
        
        HBox.setHgrow(filterField, Priority.ALWAYS);
        
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Call the filter method whenever the text changes
                getFilteredList(originalList);
            }
        });
        
        getChildren().add(filterField);
    }
    

    public ObservableList<Order> getFilteredList(List<Order> orderList) {
        String searchText = filterField.getText().trim();
        if (searchText.isEmpty()) {
            filteredList.setAll(originalList);
        } else {
            List<Order> filtered = orderList.stream()
                                                .filter(o -> o.getOrderId().contains(searchText))
                                                .collect(Collectors.toList());
            filteredList.setAll(filtered);
        }
        return filteredList;
    }

}
