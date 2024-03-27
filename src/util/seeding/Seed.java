package util.seeding;

import java.util.ArrayList;
import java.util.List;

import domain.Company;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import repository.AccountDao;
import repository.AccountDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;
import util.SendMail;

public class Seed {
	private AccountDao accountRepo;
	private GenericDao<Company> companyRepo;
	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private List<Company> companyList = new ArrayList<>();
	private List<Product> productList = new ArrayList<>();
	private List<OrderItem> orderItems = new ArrayList<>();

	private SendMail mail;

	public Seed() {
		setAccountRepo(new AccountDaoJpa());
		setCompanyRepo(new GenericDaoJpa<Company>(Company.class));
		setOrderRepo(new GenericDaoJpa<Order>(Order.class));
		setOrderItemRepo(new GenericDaoJpa<OrderItem>(OrderItem.class));
		setProductRepo(new GenericDaoJpa<Product>(Product.class));
		mail = new SendMail();
		run();
	}

	private void setOrderRepo(GenericDaoJpa<Order> mock) {
		this.orderRepo = mock;
	}

	private void setAccountRepo(AccountDao mock) {
		this.accountRepo = mock;
	}

	private void setCompanyRepo(GenericDao<Company> mock) {
		this.companyRepo = mock;
	}

	private void setOrderItemRepo(GenericDaoJpa<OrderItem> mock) {
		this.orderItemRepo = mock;
	}
	
	private void setProductRepo(GenericDaoJpa<Product> mock) {
		this.productRepo = mock;
	}

	private void run() {
		new CompanySeeding(companyRepo);
		this.companyList = companyRepo.findAll();
		new AccountSeeding(accountRepo, companyList);
		productList = new ProductSeeding(productRepo).getProductList();
		orderItems = new OrderItemSeeding(orderItemRepo, orderItems, productList).getOrderItemsList();
		new OrderSeeding(orderRepo, companyList, orderItems);
		new CustomerSeeding(companyRepo);
	}
}
