package gui.company;

import java.util.stream.Collectors;

import domain.Company;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompanyFilterController extends FilterController<Company> {

	public CompanyFilterController(ObservableList<Company> originalList) {
		super(originalList);
	}

	@Override
	public ObservableList<Company> Filter(ObservableList<Company> list) {
		return FXCollections.observableArrayList(list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText()))
							.collect(Collectors.toList()));
	}

	@Override
	protected void runAllFilters() {
		ObservableList<Company> newList = Filter(copyOriginal);
		originalList.setAll(newList);
	}

}
