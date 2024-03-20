package gui.company;

import java.util.List;
import java.util.stream.Collectors;

import domain.Company;
import gui.FilterController;
import javafx.collections.ObservableList;

public class CompanyFilterController extends FilterController<Company> {

	public CompanyFilterController(ObservableList<Company> originalList) {
		super(originalList);
	}

	@Override
	public List<Company> Filter(List<Company> list) {
		return list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText()))
							.collect(Collectors.toList());
	}

}
