package domain;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;

public abstract class Controller implements Subject{
	
	protected Set<Observer> observers;
	protected B2BPortaal portaal;
	protected Company selectedCompany;
	
	public Controller() {
		this.observers = new HashSet<>();
		this.portaal = new B2BPortaal();
	}
	
	public Company getCompany(String vat) {
		for (Company c : portaal.getCompanyList()) {
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

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
		notifyObservers();
	}
	
	protected abstract void notifyObservers();

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	

}
