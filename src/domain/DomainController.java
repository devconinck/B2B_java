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

public class DomainController implements Subject {
	private List<Order> orderList;
	private List<OrderItem> orderItemList;
	private List<String> customerIds;
	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private GenericDaoJpa<ProductPrice> productPriceRepo;
	private GenericDaoJpa<ProductDescription> productDescriptionRepo;
	private GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo;
	private GenericDaoJpa<Customer> customerRepo;
	private List<Company> companyList;
	private GenericDao<Company> companyRepo;
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
		setCustomerRepo(new GenericDaoJpa<>(Customer.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		observers = new HashSet<>();
		
		System.out.println("Adding orders...");
		od = new OrderData(orderRepo, orderItemRepo, productRepo, productPriceRepo, productDescriptionRepo, productUnitRepo);
		od.addOrderData();
		System.out.println("Adding orders complete!");
		
		listCustomers();
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
	
	public void setCustomerRepo(GenericDaoJpa<Customer> c) {
		customerRepo = c;
	}
	
	
	public void setCurrentOrder(Order o) {
		this.currentOrder = o;
		notifyObservers();
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
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
	
	public ObservableList<OrderItem> getOrderItemsList(String orderId) {
		if(orderItemList == null) { 
			orderItemList = new ArrayList<OrderItem>();
		}
		System.out.println("test1");
		List<OrderItem> orderItemsFromOrder = orderItemRepo.findAll().stream().filter(oi -> oi.getOrderId() == (Integer.parseInt(orderId))).collect(Collectors.toList());
		System.out.println("test2");
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

	public Company getCompany(String vat) {
		this.getCompanyList();
		for (Company c : companyList) {
			if (c.getVatNumber() == vat) {
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
	
	private void listCustomers() {
		List<Order> orders = orderRepo.findAll();
		customerIds = orders.stream().map(Order::getCustomer).distinct().collect(Collectors.toList());
		List<String> firstNameList = Arrays.asList(
	            "Emma", "Liam", "Olivia", "Noah", "Ava", "William", "Sophia", "James", "Isabella", "Oliver",
	            "Mia", "Benjamin", "Charlotte", "Elijah", "Amelia", "Lucas", "Harper", "Mason", "Evelyn", "Logan",
	            "Abigail", "Alexander", "Emily", "Ethan", "Elizabeth", "Jacob", "Sofia", "Michael", "Avery", "Henry",
	            "Ella", "Jackson", "Scarlett", "Sebastian", "Grace", "Aiden", "Chloe", "Matthew", "Victoria", "Samuel",
	            "Riley", "David", "Aria", "Joseph", "Lily", "Carter", "Aubrey", "Owen", "Zoey", "Wyatt", "Penelope",
	            "John", "Lillian", "Jack", "Addison", "Luke", "Layla", "Jayden", "Natalie", "Dylan", "Hannah", "Grayson",
	            "Zoey", "Levi", "Brooklyn", "Isaac", "Samantha", "Gabriel", "Audrey", "Julian", "Claire", "Anthony",
	            "Skylar", "Josiah", "Peyton", "Christopher"
	    );
		List<String> lastNameList = Arrays.asList(
		            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
		            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez",
		            "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez",
		            "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter",
		            "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins",
		            "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera",
		            "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson",
		            "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins",
		            "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster",
		            "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes"
		);

		List<String> addressesList = Arrays.asList(
				"123 Main Street, 1, Anytown, 12345, USA",
	            "456 Oak Avenue, 10, Somewhereville, 23456, USA",
	            "789 Elm Street, 25, Nowhereville, 34567, USA",
	            "321 Pine Road, 7, Anytown, 45678, USA",
	            "654 Maple Drive, 3, Somewhereville, 56789, USA",
	            "987 Cedar Lane, 15, Nowhereville, 67890, USA",
	            "135 Birch Street, 12, Anytown, 78901, USA",
	            "246 Spruce Avenue, 42, Somewhereville, 89012, USA",
	            "357 Willow Road, 30, Nowhereville, 90123, USA",
	            "468 Oakwood Drive, 5, Anytown, 23456, USA",
	            "579 Elm Lane, 8, Somewhereville, 34567, USA",
	            "680 Cedar Street, 14, Nowhereville, 45678, USA",
	            "791 Pine Avenue, 22, Anytown, 56789, USA",
	            "802 Maple Road, 11, Somewhereville, 67890, USA",
	            "913 Birch Drive, 33, Nowhereville, 78901, USA",
	            "124 Spruce Lane, 17, Anytown, 89012, USA",
	            "235 Willow Street, 9, Somewhereville, 90123, USA",
	            "346 Oak Road, 20, Nowhereville, 23456, USA",
	            "457 Elm Drive, 4, Anytown, 34567, USA",
	            "568 Cedar Avenue, 6, Somewhereville, 45678, USA",
	            "679 Pine Lane, 18, Nowhereville, 56789, USA",
	            "780 Maple Street, 31, Anytown, 67890, USA",
	            "891 Birch Avenue, 13, Somewhereville, 78901, USA",
	            "902 Spruce Road, 28, Nowhereville, 89012, USA",
	            "113 Willow Drive, 29, Anytown, 90123, USA",
	            "224 Oak Lane, 16, Somewhereville, 23456, USA",
	            "335 Elm Street, 2, Nowhereville, 34567, USA",
	            "446 Cedar Avenue, 26, Anytown, 45678, USA",
	            "557 Pine Road, 32, Somewhereville, 56789, USA",
	            "668 Maple Drive, 19, Nowhereville, 67890, USA",
	            "779 Birch Lane, 21, Anytown, 78901, USA",
	            "880 Spruce Avenue, 27, Somewhereville, 89012, USA",
	            "991 Willow Road, 23, Nowhereville, 90123, USA",
	            "102 Oak Drive, 24, Anytown, 23456, USA",
	            "213 Elm Lane, 35, Somewhereville, 34567, USA",
	            "324 Cedar Street, 37, Nowhereville, 45678, USA",
	            "435 Pine Avenue, 36, Anytown, 56789, USA",
	            "546 Maple Road, 38, Somewhereville, 67890, USA",
	            "657 Birch Drive, 39, Nowhereville, 78901, USA",
	            "768 Spruce Lane, 41, Anytown, 89012, USA",
	            "879 Willow Street, 40, Somewhereville, 90123, USA",
	            "980 Oak Road, 34, Nowhereville, 23456, USA",
	            "191 Elm Drive, 43, Anytown, 34567, USA",
	            "202 Cedar Avenue, 45, Somewhereville, 45678, USA",
	            "313 Pine Lane, 48, Nowhereville, 56789, USA",
	            "424 Maple Street, 46, Anytown, 67890, USA",
	            "535 Birch Avenue, 47, Somewhereville, 78901, USA",
	            "646 Spruce Road, 49, Nowhereville, 89012, USA",
	            "757 Willow Drive, 51, Anytown, 90123, USA",
	            "868 Oak Lane, 50, Somewhereville, 23456, USA",
	            "979 Elm Street, 44, Nowhereville, 34567, USA",
	            "180 Cedar Road, 54, Anytown, 45678, USA",
	            "291 Pine Drive, 55, Somewhereville, 56789, USA",
	            "302 Maple Lane, 56, Nowhereville, 67890, USA",
	            "413 Birch Street, 52, Anytown, 78901, USA",
	            "524 Spruce Avenue, 53, Somewhereville, 89012, USA",
	            "635 Willow Road, 57, Nowhereville, 90123, USA",
	            "746 Oak Drive, 59, Anytown, 23456, USA",
	            "857 Elm Lane, 58, Somewhereville, 34567, USA",
	            "968 Cedar Street, 61, Nowhereville, 45678, USA",
	            "179 Pine Avenue, 60, Anytown, 56789, USA",
	            "280 Maple Road, 62, Somewhereville, 67890, USA",
	            "391 Birch Drive, 63, Nowhereville, 78901, USA",
	            "402 Spruce Lane, 64, Anytown, 89012, USA",
	            "513 Willow Street, 65, Somewhereville, 90123, USA",
	            "624 Oak Road, 67, Nowhereville, 23456, USA",
	            "735 Elm Drive, 66, Anytown, 34567, USA",
	            "946 Maple Drive, 45, Nowhereville, 45678, USA",
	            "123 Chestnut Street, 9, Anytown, 54321, USA",
	            "456 Pine Avenue, 28, Somewhereville, 65432, USA",
	            "789 Maple Road, 17, Nowhereville, 76543, USA",
	            "321 Elm Lane, 45, Anytown, 87654, USA",
	            "654 Oak Street, 63, Somewhereville, 98765, USA",
	            "987 Cedar Drive, 22, Nowhereville, 10987, USA",
	            "135 Spruce Lane, 11, Anytown, 21098, USA",
	            "246 Willow Road, 36, Somewhereville, 32109, USA"
	        );

		for(int i = 0; i < customerIds.size(); i++) {
			String[] address = addressesList.get(i).split(",");
			String street = address[0];
			String addressNr = address[1];
			String city = address[2];
			String postalCode = address[3];
			String country = address[4];
			GenericDaoJpa.startTransaction();
			customerRepo.insert(new Customer(customerIds.get(i), firstNameList.get(i), lastNameList.get(i), street, addressNr, city, postalCode, country));
			GenericDaoJpa.commitTransaction();
		}
		
		customerIds = orders.stream().map(Order::getCustomer).distinct().collect(Collectors.toList());
		/*
		for(int i = 0; i < customerIds.size(); i++) {
			customerRepo.startTransaction();
			customerRepo.insert(new Customer(customerIds.get(i), firstNameList.get(i), lastNameList.get(i), street.get(i), addressNr, city, postalCode, country));
			customerRepo.commitTransaction();
		}
		*/
	}
	
	public Order getOrder(String orderId) {
		this.getOrdersList();
		for(Order o : orderList) {
			if(o.getOrderId().equals(orderId))
				return o;
		}
		return null;
	}
	
	public Customer getCustomer(String customerId) {
		for(Customer c : customerRepo.findAll()) {
			if(c.getCustomerId().equals(customerId))
				return c;
		}
		return null;
	}
	
	public String getCustomerForOrder(String customerId) {
		return customerRepo.findAll().stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst().map(Customer::getLastName).orElse("Unknown");
	}
	
	public String getCustomerForOrderItem(int orderId) {
		String customerId = orderRepo.findAll().stream().filter(o -> o.getOrderId().equals(orderId)).findFirst().map(Order::getCustomer).orElse("Unknown");
		return customerRepo.findAll().stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst().map(Customer::getLastName).orElse("Unknown");
	}
	
	
}

