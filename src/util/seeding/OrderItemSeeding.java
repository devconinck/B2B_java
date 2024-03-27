package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import domain.OrderItem;
import domain.Product;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class OrderItemSeeding {
	
	private GenericDao<OrderItem> orderItemRepo;
	private List<OrderItem> orderItems = new ArrayList<>();
	private List<Product> productList = new ArrayList<>();
	private String orderItemCSVFile = "src/CSVFiles/orderitemdata.csv";
	
	public OrderItemSeeding(GenericDao<OrderItem> orderItemRepo, List<OrderItem> orderItems, List<Product> productList) {
		this.orderItemRepo = orderItemRepo;
		this.productList = productList;
		seeding();
	}
	
	private void seeding() {
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
	
	public List<OrderItem> getOrderItemsList() {
		return orderItems;
	}
}
