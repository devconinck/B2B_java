package gui.order;

import java.util.stream.Collectors;

import dto.OrderDTO;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrdersFilterController extends FilterController<OrderDTO>{
	
	public OrdersFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
	}

	@Override
	public ObservableList<OrderDTO> Filter(ObservableList<OrderDTO> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText()))
				.collect(Collectors.toList()));
	}
}
