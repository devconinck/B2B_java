package domain;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.OrderDao;

public class B2BPortaal {
	private GenericDao<Company> companyRepo;
	private OrderDao orderRepo;
	private GenericDao<OrderItem> orderItemRepo;

	private ObservableList<Order> orderList;
	private ObservableList<OrderItem> orderItemList;
	private ObservableList<Company> companyList;

	public B2BPortaal(GenericDao<Company> companyRepo, OrderDao orderRepo, GenericDao<OrderItem> orderItemRepo) {
		setCompanyRepo(companyRepo);
		setOrderRepo(orderRepo);
		setOrderItemRepo(orderItemRepo);
	}

	public void setOrderRepo(OrderDao o) {
		orderRepo = o;
	}

	public void setOrderItemRepo(GenericDao<OrderItem> oi) {
		orderItemRepo = oi;
	}

	public void setCompanyRepo(GenericDao<Company> mock) {
		companyRepo = mock;
	}

	public ObservableList<Order> getOrdersList() {
		if (orderList == null) {
			orderList = FXCollections.observableArrayList();
		}
		List<Order> ordersFromRepo = orderRepo.findAll().stream().sorted().collect(Collectors.toList());

		orderList.clear();
		orderList.addAll(ordersFromRepo);

		return FXCollections.observableArrayList(orderList);
	}
	
	public ObservableList<Order> getOrdersToCompany(Company company) {
		return FXCollections.observableArrayList(orderRepo.getOrdersMadeToCompany(company));
	}

	public ObservableList<OrderItem> getOrderItemsList(String orderId) {
		if (orderItemList == null) {
			orderItemList = FXCollections.observableArrayList();
		}
		List<OrderItem> orderItemsFromOrder = orderItemRepo.findAll().stream()
				.filter(oi -> oi.getOrderId() == (Integer.parseInt(orderId))).collect(Collectors.toList());
		orderItemList.clear();
		orderItemList.addAll(orderItemsFromOrder);

		return FXCollections.observableArrayList(orderItemList);
	}

	public ObservableList<Company> getCompanyList() {
		if (companyList == null) {
			companyList = FXCollections.observableArrayList(companyRepo.findAll());
		}
		return companyList;
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
	
	public void updateOrder(Order order) {
		GenericDaoJpa.startTransaction();
		orderRepo.update(order);
		GenericDaoJpa.commitTransaction();
	}
}
