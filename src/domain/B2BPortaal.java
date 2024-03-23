package domain;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class B2BPortaal {
	private GenericDao<Company> companyRepo;
	private GenericDao<Order> orderRepo;
	private GenericDao<OrderItem> orderItemRepo;
//	private GenericDao<Product> productRepo;
//	private GenericDaoJpa<ProductPrice> productPriceRepo;
//	private GenericDaoJpa<ProductDescription> productDescriptionRepo;
//	private GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo;

	private ObservableList<Order> orderList;
	private ObservableList<OrderItem> orderItemList;
	private ObservableList<Company> companyList;

	public B2BPortaal(GenericDao<Company> companyRepo, GenericDao<Order> orderRepo, GenericDao<OrderItem> orderItemRepo) {
		setCompanyRepo(companyRepo);
		setOrderRepo(orderRepo);
		setOrderItemRepo(orderItemRepo);
//		setProductRepo(productRepo);
//		setProductPriceRepo(new GenericDaoJpa<>(ProductPrice.class));
//		setProductDescriptionRepo(new GenericDaoJpa<>(ProductDescription.class));
//		setProductUnitRepo(new GenericDaoJpa<>(ProductUnitOfMeasureConversion.class));
	}

	public void setOrderRepo(GenericDao<Order> o) {
		orderRepo = o;
	}

	public void setOrderItemRepo(GenericDao<OrderItem> oi) {
		orderItemRepo = oi;
	}

	/*
	public void setProductRepo(GenericDao<Product> p) {
		productRepo = p;
	}
	
	public void setProductPriceRepo(GenericDaoJpa<ProductPrice> pp) {
		productPriceRepo = pp;
	}

	public void setProductDescriptionRepo(GenericDaoJpa<ProductDescription> pd) {
		productDescriptionRepo = pd;
	}

	public void setProductUnitRepo(GenericDaoJpa<ProductUnitOfMeasureConversion> pu) {
		productUnitRepo = pu;
	}
	*/

	public void setCompanyRepo(GenericDao<Company> mock) {
		companyRepo = mock;
	}

	public ObservableList<Order> getOrdersList() {
		if (orderList == null) {
			orderList = FXCollections.observableArrayList();
		}
		List<Order> ordersFromRepo = orderRepo.findAll();

		orderList.clear();
		orderList.addAll(ordersFromRepo);

		return FXCollections.observableArrayList(orderList);
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
		System.out.println("test3");
	}
}
