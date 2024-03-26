package domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

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
}