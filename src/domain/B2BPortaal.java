package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class B2BPortaal {
	private GenericDaoJpa<Company> companyRepo;
	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private GenericDaoJpa<ProductPrice> productPriceRepo;
	private GenericDaoJpa<ProductDescription> productDescriptionRepo;
	private GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo;

	private List<Order> orderList;
	private List<OrderItem> orderItemList;
	private List<Company> companyList;

	public B2BPortaal() {
		setOrderRepo(new GenericDaoJpa<>(Order.class));
		setOrderItemRepo(new GenericDaoJpa<>(OrderItem.class));
		setProductRepo(new GenericDaoJpa<>(Product.class));
		setProductPriceRepo(new GenericDaoJpa<>(ProductPrice.class));
		setProductDescriptionRepo(new GenericDaoJpa<>(ProductDescription.class));
		setProductUnitRepo(new GenericDaoJpa<>(ProductUnitOfMeasureConversion.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
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

	public void setCompanyRepo(GenericDaoJpa<Company> mock) {
		companyRepo = mock;
	}

	public ObservableList<Order> getOrdersList() {
		if (orderList == null) {
			orderList = new ArrayList<Order>();
		}
		List<Order> ordersFromRepo = orderRepo.findAll();

		orderList.clear();
		orderList.addAll(ordersFromRepo);

		return FXCollections.observableArrayList(orderList);
	}

	public ObservableList<OrderItem> getOrderItemsList(String orderId) {
		if (orderItemList == null) {
			orderItemList = new ArrayList<OrderItem>();
		}
		List<OrderItem> orderItemsFromOrder = orderItemRepo.findAll().stream()
				.filter(oi -> oi.getOrderId() == (Integer.parseInt(orderId))).collect(Collectors.toList());
		orderItemList.clear();
		orderItemList.addAll(orderItemsFromOrder);

		return FXCollections.observableArrayList(orderItemList);
	}

	public ObservableList<Company> getCompanyList() {
		if (companyList == null) {
			companyList = companyRepo.findAll();
		}
		return FXCollections.observableArrayList(companyList);
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
}
