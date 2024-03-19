package domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;

public class SupplierController extends Controller implements Subject {
	
	private Set<Observer> observers;
	
	private Company company;
	private Company selectedCompany;
	private Order currentOrder;
	private B2BPortaal portaal;

	public SupplierController(Company company) {
		observers = new HashSet<>();
		setCompany(company);
		this.portaal = new B2BPortaal();
		this.selectedCompany = portaal.getCompanyList().get(0);
		this.currentOrder = company.getOrders().stream().collect(Collectors.toList()).get(0);
	}

	private void setCompany(Company company) {
		this.company = company;
	}
	
	public void setCurrentOrder(Order o) {
		this.currentOrder = o;
		notifyObservers();
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public Company getCurrentCompany() {
		return company;
	}
	
	public Company getCompany(String vat) {
		for (Company c : portaal.getCompanyList()) {
			if (c.getVatNumber().equals(vat)) {
				return c;
			}
		}
		return null;
	}
	
	public Order getOrder(String orderId) {
		for(Order o : portaal.getOrdersList()) {
			if(o.getOrderId().equals(orderId))
				return o;
		}
		return null;
	}
	
	public Company getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public ObservableList<Order> getOrders() {
		return portaal.getOrdersList();
	}
	
	public ObservableList<OrderItem> getOrderItems() {
		return portaal.getOrderItemsList(currentOrder.getOrderId());
	}
	
	public ObservableList<Company> getCompany() {
		return portaal.getCompanyList();
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
		observers.forEach(o -> {o.update(company); o.update(currentOrder);});
	}
}
