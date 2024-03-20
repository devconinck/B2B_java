package domain;

import java.util.stream.Collectors;

import javafx.collections.ObservableList;

public class SupplierController extends Controller{
	
	private Company company;
	private Order currentOrder;
	

	public SupplierController(Company company) {
		super();
		setCompany(company);
		selectedCompany = portaal.getCompanyList().get(0);
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
	
	
	
	public Order getOrder(String orderId) {
		for(Order o : portaal.getOrdersList()) {
			if(o.getOrderId().equals(orderId))
				return o;
		}
		return null;
	}
	
	

	public ObservableList<Order> getOrders() {
		return portaal.getOrdersList();
	}
	
	public ObservableList<OrderItem> getOrderItems() {
		return portaal.getOrderItemsList(currentOrder.getOrderId());
	}
	
	
	
	// TODO mag o.update(company) weg????
	@Override
	protected void notifyObservers() {
		observers.forEach(o -> {o.update(company); o.update(currentOrder);});
	}
}
