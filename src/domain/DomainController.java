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
	private List<Order> orderList;
	private GenericDao<Order> orderRepo;
	
	private Set<Observer> observers;
	private Order currentOrder;


	public DomainController() {
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		observers = new HashSet<>();
		
		
	}


	public void setCompanyRepo(GenericDao<Company> mock) {
		orderRepo = mock;
	}

	
	public void setCurrentOrder(Order o) {
		this.currentOrder = o;
		notifyObservers();
	}
	
	// naam, sector, adres, aantal klanten, isActief
	// AANTAL KLANTEN NOG NIET CORRECT
	/*
	public List<String> giveCompanyList() {
		return getCompanyList().stream().map(null)
	
	}
	*/
	
	public ObservableList<Order> getOrderList() {
		if (orderList == null) {
			//companyList = companyRepo.findAllDisplayData();
		
			orderList = new ArrayList<Order>();
			Address fakeAddress1 = new Address("United States", "New York", "10001", "Broadway", "123");
			Address fakeAddress2 = new Address("Country2", "City2", "23456", "Street2", "2");
			
			Contact fakeContact1 = new Contact("123456789", "email1@example.com");
			Contact fakeContact2 = new Contact("987654321", "email2@example.com");

			Company fakeCompany1 = new Company(123456789L, "company_logo_1.png", fakeAddress1, fakeContact1, "Fake Company Inc. 1", "Technology", 9876543210L, List.of("Credit Card", "PayPal"), new Date());
			Company fakeCompany2 = new Company(987654321L, "company_logo_2.png", fakeAddress2, fakeContact2, "Fake Company Inc. 2", "Finance", 1234567890L, List.of("Bank Transfer", "Bitcoin"), new Date());

			companyList.add(fakeCompany1);
			companyList.add(fakeCompany2);
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
		observers.forEach(o -> o.update(currentOrder));
	}
}

