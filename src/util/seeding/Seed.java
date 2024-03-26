package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import domain.Company;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import mail.SendMail;
import repository.AccountDao;
import repository.AccountDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class Seed {
	private AccountDao accountRepo;
	private GenericDao<Company> companyRepo;
	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private List<Company> companyList = new ArrayList<>();
	private List<Product> productList = new ArrayList<>();
	private List<OrderItem> orderItems = new ArrayList<>();

	// TODO order + orderItem in andere klassen
	private String orderCSVFile = "src/CSVFiles/orderdata.csv";
	private String orderItemCSVFile = "src/CSVFiles/orderitemdata.csv";
	private String productCSVFile = "src/CSVFiles/productdata.csv";
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
		//new ProductSeeding(productRepo);
		processProductData();
		processOrderItemData();
		processOrderData();
		new CustomerSeeding(companyRepo);
	}

	// TODO
	private void processOrderData() {
		try (CSVReader reader = new CSVReader(new FileReader(orderCSVFile))) {
			String[] line;
			reader.readNext();
			List<Order> orders = new ArrayList<>();
			int index = 0;
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String orderId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String orderReference = items[3];
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String lastPaymentReminder = dateTime.format(formatter);
				String netAmount = items[5];
				String taxAmount = items[6];
				String totalAmount = items[7];
				String currency = items[8];

				int newIndex = index % (companyList.size());

				orders.add(new Order(orderId, syncId, companyList.get(newIndex), getRandomCompany(), orderReference, generateRandomDate(),
						lastPaymentReminder, netAmount, taxAmount, totalAmount, currency, getOrderItems(orderId)));
				companyList.get(newIndex % (companyList.size()))
						.setOrders(orders.stream()
								.filter(o -> o.getFromCompany().equals(companyList.get(newIndex % (companyList.size()))))
								.collect(Collectors.toSet()));
				index++;
			}
			GenericDaoJpa.startTransaction();
			orderRepo.insertBatch(orders);
			GenericDaoJpa.commitTransaction();
			System.out.printf("Number of orders for Fake Company Inc.1 : %s%n",
					orderRepo.findAll().stream().filter(o -> o.getFromCompany().getName().equals("Fake Company Inc. 1"))
							.collect(Collectors.toList()).size());
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	public static LocalDate generateRandomDate() {
		LocalDate today = LocalDate.now();
		LocalDate twoWeeksAgo = today.minusWeeks(2);
		LocalDate twoWeeksAfter = today.plusWeeks(2);

		long startEpochDay = twoWeeksAgo.toEpochDay();
		long endEpochDay = twoWeeksAfter.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

		return LocalDate.ofEpochDay(randomEpochDay);
	}
	
	private Company getRandomCompany() {
		SecureRandom random = new SecureRandom();
		int rand = random.nextInt(0, companyList.size());
		return companyList.get(rand);
	}
	
	private Set<OrderItem> getOrderItems(String orderId) {
		Set<OrderItem> list = new HashSet<>();
		for(OrderItem item : orderItems) {
			if(item.getOrderId() == Integer.parseInt(orderId))
				list.add(item);
		}
		return list;
	}
	
	private void processProductData() {
		try (CSVReader reader = new CSVReader(new FileReader(productCSVFile))) {
			String[] line;
			reader.readNext();
			//List<Product> products = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String productId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String unitOfMeasureId = items[2];
				String productCategoryId = items[3];
				String productAvailability = items[4];
				productList.add(new Product(productId, syncId, unitOfMeasureId, productCategoryId, productAvailability));
			}
			GenericDaoJpa.startTransaction();
			productRepo.insertBatch(productList);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	private void processOrderItemData() {
		try (CSVReader reader = new CSVReader(new FileReader(orderItemCSVFile))) {
			String[] line;
			reader.readNext();
			orderItems = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				int orderId = Integer.parseInt(items[0]);
				int orderItemId = Integer.parseInt(items[1]);
				int syncId = Integer.parseInt(items[2]);
				String productId = items[3];
				int quantity = Integer.valueOf(items[4].replace(".", "")) / 1000;
				String unitOfMeasureId = items[5];
				BigDecimal netPrice = BigDecimal.valueOf( Double.valueOf(items[6].replace(".", "")) / 1000);
				BigDecimal netAmount = BigDecimal.valueOf( Double.valueOf(items[7].replace(".", "")) / 100);
				
				Product product = productList.stream().filter(p -> p.getProductId().equals(productId)).findAny().orElse(null);
				
				orderItems.add(new OrderItem(orderId, orderItemId, syncId, product, quantity, unitOfMeasureId,
						netPrice, netAmount));
			}
			GenericDaoJpa.startTransaction();
			orderItemRepo.insertBatch(orderItems);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
