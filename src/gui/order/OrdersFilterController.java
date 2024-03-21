package gui.order;

import java.util.List;
import java.util.stream.Collectors;

import dto.OrderDTO;
import gui.FilterController;
import javafx.collections.ObservableList;

public class OrdersFilterController extends FilterController<OrderDTO>{
	
	public OrdersFilterController(ObservableList<OrderDTO> originalList) {
		super(originalList);
	}

	@Override
	public List<OrderDTO> Filter(List<OrderDTO> list) {
		return list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText()))
				.collect(Collectors.toList());
	}
}
