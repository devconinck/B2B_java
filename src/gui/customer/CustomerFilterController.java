package gui.customer;

import java.util.stream.Collectors;

import dto.CompanyDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gui.FilterController;

public class CustomerFilterController extends FilterController<CompanyDTO>{

	public CustomerFilterController(ObservableList<CompanyDTO> originalList) {
		super(originalList);
	}

	@Override
	public ObservableList<CompanyDTO> Filter(ObservableList<CompanyDTO> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText())).collect(Collectors.toList()));
	}

}
