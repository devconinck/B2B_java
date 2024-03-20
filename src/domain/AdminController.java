package domain;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;

public class AdminController extends Controller{
	
	public void addCompany(Company company) {
		portaal.addCompany(company);
	}

	public void updateCompany(Company company) {
		portaal.updateCompany(company);
	}
	
	// TODO mag o.update(company) weg????
	@Override
	protected void notifyObservers() {
		observers.forEach(o -> o.update(selectedCompany));
	}
}
