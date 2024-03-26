package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.stream.Collectors;

import dto.CompanyDTO;
import dto.OrderDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.PaymentOption;

public class SupplierController extends Controller{
	
	private Company company;
	private Order currentOrder;
	
	private PropertyChangeSupport supportOrder;

	public SupplierController(Company company) {
		super();
		setCompany(company);
		selectedCompany = portaal.getCompanyList().get(0);
		this.currentOrder = company.getOrders().stream().collect(Collectors.toList()).get(0);
		supportOrder = new PropertyChangeSupport(this);
	}

	private void setCompany(Company company) {
		this.company = company;
	}
	
	public void setCurrentOrder(Order o) {
		supportOrder.firePropertyChange("currentOrder", this.currentOrder, o);
		notifyObservers();
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public OrderDTO getCurrentOrderDTO() {
		return new OrderDTO(currentOrder);
	}
	
	// TODO
	public Company getCurrentCompany() {
		return company;
	}
	
	public CompanyDTO getCurrentCompanyDTO() {
		return new CompanyDTO(company);
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
	
	public ObservableList<Order> getOrdersToCompany() {
		return portaal.getOrdersToCompany(company);
	}
	
	public ObservableList<OrderDTO> getOrdersToCompanyDTO() {
		return FXCollections.observableArrayList(getOrdersToCompany().stream().map(o -> new OrderDTO(o)).collect(Collectors.toList()));
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
		setCurrentOrder(o);
	}
	
	public void addPropertyChangeListenerOrder(PropertyChangeListener pcl) {
		supportOrder.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListenerOrder(PropertyChangeListener pcl) {
    	supportOrder.removePropertyChangeListener(pcl);
    }
}
