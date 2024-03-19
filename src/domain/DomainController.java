package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import repository.GenericDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;
import util.seeding.OrderData;

public class DomainController implements Subject {
	
	
	
	private Company currentCompany;
	
	private OrderData od;
	
	public DomainController() {
		
		this.currentCompany = getCompanyList().get(0);
		
		
		System.out.println("Adding orders...");
		od = new OrderData(orderRepo, orderItemRepo, productRepo, productPriceRepo, productDescriptionRepo, productUnitRepo, companyRepo);
		od.addOrderData();
		System.out.println("Adding orders complete!");
		
	}
	
	
	
	

	
	

	public void setCurrentCompany(Company c) {
		this.currentCompany = c;
		notifyObservers();
	}
	
	public Company getCurrentCompany() {
		return currentCompany;
	}
	
	// TODO niet alles teruggeven enkel items van specifiek order !!
	

	public Company getCompany(String vat) {
		for (Company c : companyList) {
			if (c.getVatNumber().equals(vat)) {
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
		observers.forEach(o -> {o.update(currentCompany); o.update(currentOrder);});
	}
	
	public Order getOrder(String orderId) {
		this.getOrdersList();
		for(Order o : orderList) {
			if(o.getOrderId().equals(orderId))
				return o;
		}
		return null;
	}	
	
}

