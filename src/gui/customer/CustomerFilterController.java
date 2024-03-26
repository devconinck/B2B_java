package gui.customer;

import java.util.stream.Collectors;

import dto.CompanyDTO;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerFilterController extends FilterController<CompanyDTO>{

	public CustomerFilterController(ObservableList<CompanyDTO> originalList) {
		super(originalList);
	}

	@Override
	public ObservableList<CompanyDTO> Filter(ObservableList<CompanyDTO> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText())).collect(Collectors.toList()));
	}

	@Override
	protected void runAllFilters() {
		ObservableList<CompanyDTO> newList = Filter(copyOriginal);
		originalList.setAll(newList);
	}

}
