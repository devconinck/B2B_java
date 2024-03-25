package domain;

import java.util.List;
import java.util.stream.Collectors;

import dto.OrderDTO;
import javafx.collections.ObservableList;
import util.OrderStatus;
import util.PaymentOption;

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
			if(o.getOrderID().equals(orderId))
				return o;
		}
		return null;
	}

	public ObservableList<OrderItem> getOrderItems(String orderId) {
		return portaal.getOrderItemsList(orderId);
	}
	
	// TODO mag o.update(company) weg????
	@Override
	protected void notifyObservers() {
		observers.forEach(o -> {o.update(company); o.update(currentOrder);});
	}

	@Override
	public ObservableList<Company> getCompanyList() {
		throw new UnsupportedOperationException();
	}
	
	public void updateCompany(List<PaymentOption> options) {
		Company c = getCurrentCompany();
		c.setPaymentOptions(options);
		portaal.updateCompany(c);
	}
	
	public void updateOrder(String orderId, String orderStatus, String paymentStatus) {
		Order o = getOrder(orderId);
		o.setOrderStatus(orderStatus.toUpperCase().replace(' ', '_'));
		o.setPaymentStatus(paymentStatus.toUpperCase().replace(' ', '_'));
		portaal.updateOrder(o);
	}
}
