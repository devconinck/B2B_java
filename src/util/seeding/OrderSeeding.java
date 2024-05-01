package util.seeding;

import java.io.FileReader;
import java.io.IOException;
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
import repository.GenericDao;
import repository.GenericDaoJpa;

public class OrderSeeding {
	
	private GenericDao<Order> orderRepo;
	private GenericDao<OrderItem> orderItemRepo;
	private List<Company> companyList;
	private List<OrderItem> orderItems;
	private List<Order> orderlist;
	private String orderCSVFile = "src/CSVFiles/orderdata.csv";
	
	public OrderSeeding(GenericDao<Order> orderRepo, List<Company> companyList, GenericDao<OrderItem> orderItemRepo) {
		this.orderRepo = orderRepo;
		this.companyList = companyList;
		this.orderItemRepo = orderItemRepo;
		this.orderItems = orderItemRepo.findAll();
		this.orderlist = new ArrayList<>();
		seeding();
	}
	
	private String parseAmount(String value) {
	    return value.replaceAll("\\.", "");
	}
	private Double parseDoubleOrNull(String value) {
	    if (value == null || value.isEmpty()) {
	        return 0.0;
	    }
	    
	    try {
	    	// Geen idee waarom sommige waarden negatief
	        return Math.abs(Double.parseDouble(parseAmount(value)));
	    } catch (NumberFormatException e) {
	        return 0.0;
	    }
	}
	
	private void seeding() {
		try (CSVReader reader = new CSVReader(new FileReader(orderCSVFile))) {
			String[] line;
			reader.readNext();
			int index = 0;
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String orderId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String orderReference = items[3];
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String lastPaymentReminder = dateTime.format(formatter);
				Double netAmount = parseDoubleOrNull(items[5]);
				Double taxAmount = parseDoubleOrNull(items[6]);
				Double totalAmount = parseDoubleOrNull(items[7]);


				String currency = items[8];

				int newIndex = index % (companyList.size());

				orderlist.add(new Order(orderId, syncId, companyList.get(newIndex), getRandomCompany(), orderReference, generateRandomDate(),
						lastPaymentReminder, netAmount, taxAmount, totalAmount, currency, getOrderItems(orderId)));
				companyList.get(newIndex % (companyList.size()))
						.setOrders(orderlist.stream()
								.filter(o -> o.getFromCompany().equals(companyList.get(newIndex % (companyList.size()))))
								.collect(Collectors.toSet()));
				index++;
			}
			GenericDaoJpa.startTransaction();
			orderRepo.insertBatch(orderlist);
			GenericDaoJpa.commitTransaction();
			
			setOrderItems();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
	
	private void setOrderItems() {
		orderItems.forEach(o -> o.setFromOrder(getRandomOrder()));
		
		GenericDaoJpa.startTransaction();
		orderItems.stream().forEach(c -> orderItemRepo.update(c));
		GenericDaoJpa.commitTransaction();
	}
	
	private Order getRandomOrder() {
		SecureRandom random = new SecureRandom();
		int rand = random.nextInt(0, orderlist.size());
		return orderlist.get(rand);
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
	
	private LocalDate generateRandomDate() {
		LocalDate today = LocalDate.now();
		LocalDate twoWeeksAgo = today.minusWeeks(2);
		LocalDate twoWeeksAfter = today.plusWeeks(2);

		long startEpochDay = twoWeeksAgo.toEpochDay();
		long endEpochDay = twoWeeksAfter.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

		return LocalDate.ofEpochDay(randomEpochDay);
	}

}
