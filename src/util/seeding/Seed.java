package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import domain.Company;
import domain.Order;
import domain.OrderItem;
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
	private List<Company> companyList = new ArrayList<>();

	// TODO order + orderItem in andere klassen
	private String orderCSVFile = "src/CSVFiles/orderdata.csv";
	private String orderItemCSVFile = "src/CSVFiles/orderitemdata.csv";
	private SendMail mail;

	public Seed() {
		setAccountRepo(new AccountDaoJpa());
		setCompanyRepo(new GenericDaoJpa<Company>(Company.class));
		setOrderRepo(new GenericDaoJpa<Order>(Order.class));
		setOrderItemRepo(new GenericDaoJpa<OrderItem>(OrderItem.class));
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

	private void run() {
		new CompanySeeding(companyRepo);
		this.companyList = companyRepo.findAll();
		new AccountSeeding(accountRepo, companyList);
		// addOrder();
		processOrderData();
		// addOrderItem();
		processOrderItemData();
		new CustomerSeeding(companyRepo);
		//checkIfOrdersExpire();
	}

	/*
	 * private void addOrder() { Order order1 = new Order("2", 12,
	 * companyList.get(0), "asdf", "asdf", "asdf", "asdf", "asdf", "asdf", "asdf");
	 * Order order2 = new Order("3", 12, companyList.get(1), "sdfg", "sdfg", "sdfg",
	 * "sdgf", "sdfg", "sdfg", "sdfg");
	 * companyList.get(0).setOrders(Set.of(order1));
	 * companyList.get(1).setOrders(Set.of(order2)); List<Order> li =
	 * List.of(order1, order2); li.stream().forEach(o -> {
	 * GenericDaoJpa.startTransaction(); orderRepo.insert(o);
	 * GenericDaoJpa.commitTransaction(); }); }
	 */

	// TODO
	private void checkIfOrdersExpire() {
		List<Order> orders = orderRepo.findAll();
		System.out.printf("Total amount of orders: %s%n", orders.size());
		List<Order> orders2 = orders.stream().filter(o -> o.getOrderDateTime().isBefore(LocalDate.now().plusDays(3))
				&& o.getOrderDateTime().isAfter(LocalDate.now())).collect(Collectors.toList());
		System.out.printf("Orders that expire in 3 days form now: %s%n", orders2.size());
		for (int i = 0; i < 5; i++) {
			Order order = orders2.get(i);
			mail.sendMail(order.getCompany().getContact().getEmail(), companyList.get(0).getContact().getEmail(),
					String.format("Payment due to %s", order.getOrderDateTime()),
					String.format(
							"You have a order that is not been payed yet.%nThis order has id: %s%nYours Sencirely %n%s",
							order.getOrderID(), companyList.get(0).getName()));
		}
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
				String orderDateTime = items[4];
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String lastPaymentReminder = dateTime.format(formatter);
				String netAmount = items[5];
				String taxAmount = items[6];
				String totalAmount = items[7];
				String currency = items[8];

				int newIndex = index % (companyList.size());

				orders.add(new Order(orderId, syncId, companyList.get(newIndex), orderReference, generateRandomDate(),
						lastPaymentReminder, netAmount, taxAmount, totalAmount, currency));
				companyList.get(newIndex % (companyList.size()))
						.setOrders(orders.stream()
								.filter(o -> o.getCompany().equals(companyList.get(newIndex % (companyList.size()))))
								.collect(Collectors.toSet()));
				index++;
			}
			GenericDaoJpa.startTransaction();
			orderRepo.insertBatch(orders);
			GenericDaoJpa.commitTransaction();
			System.out.printf("Number of orders for Fake Company Inc.1 : %s%n",
					orderRepo.findAll().stream().filter(o -> o.getCompany().getName().equals("Fake Company Inc. 1"))
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

	private void addOrderItem() {
		OrderItem orderItem1 = new OrderItem(2, 10, 1757, "S200101", "1.500.000", "HUR", "700.000", "105.000.000");
		OrderItem orderItem2 = new OrderItem(3, 30, 1757, "P100403", "30.000", "EA", "14.110.500", "42.331.500");
		OrderItem orderItem3 = new OrderItem(3, 20, 1757, "P100403", "20.000", "EA", "12.270.000", "24.540.000");
		OrderItem orderItem4 = new OrderItem(3, 10, 1757, "P100403", "10.000", "EA", "17.250.000", "17.250.000");
		OrderItem orderItem5 = new OrderItem(2, 10, 1757, "S200102", "2.500.000", "HUR", "1.000.000", "250.000.000");

		List<OrderItem> li = List.of(orderItem1, orderItem2, orderItem3, orderItem4, orderItem5);
		li.stream().forEach(o -> {
			GenericDaoJpa.startTransaction();
			orderItemRepo.insert(o);
			GenericDaoJpa.commitTransaction();
		});
	}

	private void processOrderItemData() {
		try (CSVReader reader = new CSVReader(new FileReader(orderItemCSVFile))) {
			String[] line;
			reader.readNext();
			List<OrderItem> orderItems = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				int orderId = Integer.parseInt(items[0]);
				int orderItemId = Integer.parseInt(items[1]);
				int syncId = Integer.parseInt(items[2]);
				String productId = items[3];
				String quantity = items[4];
				String unitOfMeasureId = items[5];
				String netPrice = items[6];
				String netAmount = items[7];

				orderItems.add(new OrderItem(orderId, orderItemId, syncId, productId, quantity, unitOfMeasureId,
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
