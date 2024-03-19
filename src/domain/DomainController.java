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
	private List<Order> orderList;
	private List<OrderItem> orderItemList;
	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private GenericDaoJpa<ProductPrice> productPriceRepo;
	private GenericDaoJpa<ProductDescription> productDescriptionRepo;
	private GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo;
	private List<Company> companyList;
	private GenericDaoJpa<Company> companyRepo;
	private Set<Observer> observers;
	private Company currentCompany;
	private Order currentOrder;
	private OrderData od;
	public DomainController() {
		setOrderRepo(new GenericDaoJpa<>(Order.class));
		setOrderItemRepo(new GenericDaoJpa<>(OrderItem.class));
		setProductRepo(new GenericDaoJpa<>(Product.class));
		setProductPriceRepo(new GenericDaoJpa<>(ProductPrice.class));
		setProductDescriptionRepo(new GenericDaoJpa<>(ProductDescription.class));
		setProductUnitRepo(new GenericDaoJpa<>(ProductUnitOfMeasureConversion.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		observers = new HashSet<>();
		this.currentCompany = getCompanyList().get(0);
		
		System.out.println("Adding orders...");
		od = new OrderData(orderRepo, orderItemRepo, productRepo, productPriceRepo, productDescriptionRepo, productUnitRepo, companyRepo);
		od.addOrderData();
		System.out.println("Adding orders complete!");
		
		//listCustomers();
	}
	public void setOrderRepo(GenericDaoJpa<Order> o) {
		orderRepo = o;
	}
	
	public void setOrderItemRepo(GenericDaoJpa<OrderItem> oi) {
		orderItemRepo = oi;
	}
	
	public void setProductRepo(GenericDaoJpa<Product> p) {
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
	
	public void setCurrentOrder(Order o) {
		this.currentOrder = o;
		notifyObservers();
	}

	
	
	public void setCompanyRepo(GenericDaoJpa<Company> mock) {
		companyRepo = mock;
	}

	public void setCurrentCompany(Company c) {
		this.currentCompany = c;
		notifyObservers();
	}
	public Company getCurrentCompany() {
		return currentCompany;
	}
	public ObservableList<Order> getOrdersList() {
		if(orderList == null) { 
			orderList = new ArrayList<Order>();
		}
		List<Order> ordersFromRepo = orderRepo.findAll();
		
		orderList.clear();
		orderList.addAll(ordersFromRepo);
	
		return FXCollections.observableArrayList(orderList);
	}
	
	// TODO niet alles teruggeven enkel items van specifiek order !!
	public ObservableList<OrderItem> getOrderItemsList() {
		if(orderItemList == null) { 
			orderItemList = new ArrayList<OrderItem>();
		}
		List<OrderItem> orderItemsFromRepo = orderItemRepo.findAll();
		
		orderItemList.clear();
		orderItemList.addAll(orderItemsFromRepo);
	
		return FXCollections.observableArrayList(orderItemList);
	}
	

	public ObservableList<Company> getCompanyList() {
		if (companyList == null) {
			companyList = companyRepo.findAll();

		}
		return FXCollections.observableArrayList(companyList);
	}

	public Company getCompany(String vat) {
		for (Company c : companyList) {
			if (c.getVatNumber().equals(vat)) {
				return c;
			}
		}
		return null;
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

