package gui.customer;

import java.util.List;
import java.util.stream.Collectors;

import dto.CompanyDTO;
import gui.FilterController;
import javafx.collections.ObservableList;

public class CustomerFilterController extends FilterController<CompanyDTO>{

	public CustomerFilterController(ObservableList<CompanyDTO> originalList) {
		super(originalList);
	}

	@Override
	public List<CompanyDTO> Filter(List<CompanyDTO> list) {
		return list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText()))
				.collect(Collectors.toList());
	}

}
