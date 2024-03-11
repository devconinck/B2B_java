package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomainController implements Subject {
	private List<Company> companyList;
	private GenericDao<Company> companyRepo;
	private GenericDao<Address> addressRepo;
	private GenericDao<Contact> contactRepo;
	
	private Set<Observer> observers;
	private Company currentCompany;

	public DomainController() {
		setAddressRepo(new GenericDaoJpa<>(Address.class));
		setContactRepo(new GenericDaoJpa<>(Contact.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		observers = new HashSet<>();
	}

	public void setAddressRepo(GenericDaoJpa<Address> mock) {
		addressRepo = mock;
	}

	public void setCompanyRepo(GenericDaoJpa<Company> mock) {
		companyRepo = mock;
	}
	
	public void setContactRepo(GenericDaoJpa<Contact> mock) {
		contactRepo = mock;
	}
	
	public void setCurrentCompany(Company c) {
		this.currentCompany = c;
		notifyObservers();
	}
	
	// naam, sector, adres, aantal klanten, isActief
	// AANTAL KLANTEN NOG NIET CORRECT
	/*
	public List<String> giveCompanyList() {
		return getCompanyList().stream().map(null)
	
	}
	*/
	
	public ObservableList<Company> getCompanyList() {
		if (companyList == null) {
			companyList = companyRepo.findAll();
	
		}
		return FXCollections.observableArrayList(companyList);
	}
	
	public Company getCompany(String name) {
		this.getCompanyList();
		for (Company c : companyList) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	public void close() {
		GenericDaoJpa.closePersistency();
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}
	
	private void notifyObservers() {
		observers.forEach(o -> o.update(currentCompany));
	}
}
