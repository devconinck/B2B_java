package domain;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;

public class AdminController extends Controller implements Subject {
	
	private Set<Observer> observers;
	
	private Company selectedCompany;
	private B2BPortaal portaal;

	public AdminController() {
		observers = new HashSet<>();
		this.portaal = new B2BPortaal();
	}
	
	public Company getCompany(String vat) {
		for (Company c : getCompanyList()) {
			if (c.getVatNumber().equals(vat)) {
				return c;
			}
		}
		return null;
	}
	
	public ObservableList<Company> getCompanyList() {
		return portaal.getCompanyList();
	}
	
	public Company getSelectedCompany() {
		return selectedCompany;
	}
	
	public void setSelectedCompany(Company company) {
		this.selectedCompany = company;
		notifyObservers();
	}
	
	public void addCompany(Company company) {
		portaal.addCompany(company);
	}

	public void updateCompany(Company company) {
		portaal.updateCompany(company);
	}
	
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	// TODO mag o.update(company) weg????
	private void notifyObservers() {
		observers.forEach(o -> o.update(selectedCompany));
	}
}
