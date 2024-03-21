package domain;

import javafx.collections.ObservableList;

public class AdminController extends Controller{

	public ObservableList<Order> getOrders() {
	    return portaal.getOrdersList();
	}
	
	public void addCompany(Company company) {
		portaal.addCompany(company);
	}

	public void updateCompany(Company company) {
		portaal.updateCompany(company);	
	}
	
	public void updateOrder(Order order) {
		portaal.updateOrder(order);	
	}
	
	// TODO mag o.update(company) weg????
	@Override
	protected void notifyObservers() {
		observers.forEach(o -> o.update(selectedCompany));
	}
}
