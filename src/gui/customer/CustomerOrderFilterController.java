package gui.customer;

import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

import dto.OrderDTO;

public class CustomerOrderFilterController extends FilterController<OrderDTO>{

	public CustomerOrderFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
	}

	@Override
	public ObservableList<OrderDTO> Filter(ObservableList<OrderDTO> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.orderId().startsWith(super.getSearchText())).collect(Collectors.toList()));
	}

}
