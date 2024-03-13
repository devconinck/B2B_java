package domain;

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


	private Set<Observer> observers;
	private Company currentCompany;

	public DomainController() {

		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		observers = new HashSet<>();
	}


	public void setCompanyRepo(GenericDaoJpa<Company> mock) {
		companyRepo = mock;
	}



	public void setCurrentCompany(Company c) {
		this.currentCompany = c;
		notifyObservers();
	}

	// naam, sector, adres, aantal klanten, isActief
	// AANTAL KLANTEN NOG NIET CORRECT
	/*
	 * public List<String> giveCompanyList() { return
	 * getCompanyList().stream().map(null)
	 * 
	 * }
	 */

	public ObservableList<Company> getCompanyList() {
		if (companyList == null) {
			companyList = companyRepo.findAll();

		}
		return FXCollections.observableArrayList(companyList);
	}

	public Company getCompany(String vat) {
		this.getCompanyList();
		for (Company c : companyList) {
			if (c.getVatNumber() == vat) {
				return c;
			}
		}
		return null;
	}

	public void addCompany(Company company) {
		GenericDaoJpa.startTransaction();
		companyRepo.insert(company);
		GenericDaoJpa.commitTransaction();
	}

	public void updateCompany(Company company) {
		GenericDaoJpa.startTransaction();
		companyRepo.update(company);
		GenericDaoJpa.commitTransaction();
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
