package domain;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class AdminController extends Controller {
    private ObjectProperty<Company> selectedCompanyProperty;

    public AdminController() {
        super();
        selectedCompanyProperty = new SimpleObjectProperty<>();
    }

    public ObservableList<Company> getCompanyList() {
        return portaal.getCompanyList();
    }

    public ObservableList<Order> getOrders() {
        return portaal.getOrdersList();
    }

    public void addCompany(Company company) {
        portaal.addCompany(company);
        notifyObservers(company);
    }

    public void updateCompany(Company company) {
        portaal.updateCompany(company);
        notifyObservers(company);
    }

    public void updateOrder(Order order) {
        portaal.updateOrder(order);
        notifyObservers(order);
    }

    public ObjectProperty<Company> getSelectedCompanyProperty() {
        return selectedCompanyProperty;
    }

    public void setSelectedCompany(Company company) {
        selectedCompanyProperty.set(company);
        notifyObservers(company);
    }

    @Override
    protected void notifyObservers() {
        notifyObservers(selectedCompanyProperty.get());
    }

    private void notifyObservers(Company company) {
        for (Observer observer : observers) {
            observer.update(company);
        }
    }

    private void notifyObservers(Order order) {
        for (Observer observer : observers) {
            observer.update(order);
        }
    }
    
	public void batchUpdateOrders(List<Order> orders) {
		portaal.batchUpdateOrders(orders);
	}
	
	public void deleteUpdateRequest(CompanyUpdateRequest request) {
        portaal.deleteUpdateRequest(request);
	}
	
	public ObservableList<CompanyUpdateRequest> getCompanyUpdateRequestList() {
		return portaal.getCompanyUpdateRequestList();
	}
}