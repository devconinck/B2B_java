package gui.order;

import java.util.List;
import java.util.stream.Collectors;

import domain.Order;
import gui.FilterController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class OrdersFilterController extends FilterController<Order> {

	public OrdersFilterController(ObservableList<Order> originalList) {
		super(originalList);
	}

	@Override
	public List<Order> Filter(List<Order> list) {
		return list.stream()
                .filter(o -> o.getOrderId().contains(super.getSearchText()))
                .collect(Collectors.toList());
	}
	
}
