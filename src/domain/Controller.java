package domain;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import repository.GenericDaoJpa;
import repository.OrderDaoJpa;

public abstract class Controller implements Subject{
	
	protected Set<Observer> observers;
	protected B2BPortaal portaal;
	protected Company selectedCompany;
	
	public Controller() {
		this.observers = new HashSet<>();
		this.portaal = new B2BPortaal(new GenericDaoJpa<Company>(Company.class), new OrderDaoJpa(), new GenericDaoJpa<OrderItem>(OrderItem.class), new GenericDaoJpa<CompanyUpdateRequest>(CompanyUpdateRequest.class));
	}
	
	public Company getCompany(String vat) {
		for (Company c : portaal.getCompanyList()) {
			if (c.getVatNumber().equals(vat)) {
				return c;
			}
		}
		return null;
	}
	
	public abstract ObservableList<Company> getCompanyList();
	
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
