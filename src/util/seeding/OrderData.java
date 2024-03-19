package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.ProductDescription;
import domain.ProductPrice;
import domain.ProductUnitOfMeasureConversion;
import repository.GenericDao;
import repository.GenericDaoJpa;
import domain.Company;

public class OrderData {

	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private GenericDaoJpa<ProductPrice> productPriceRepo;
	private GenericDaoJpa<ProductDescription> productDescriptionRepo;
	private GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo;
	private GenericDaoJpa<Company> companyRepo;
	private String orderCSVFile = "src/CSVFiles/orderdata.csv";
	private String productPriceCSVFile = "src/CSVFiles/productpricedata.csv";
	private String productDescriptionCSVFile = "src/CSVFiles/productdescriptiondata.csv";
	private String productUnitOfMeasureConversionCSVFile = "src/CSVFiles/productunitofmeasureconversiondata.csv";
	private String productCSVFile = "src/CSVFiles/productdata.csv";
	private String orderItemCSVFile = "src/CSVFiles/orderitemdata.csv";

	public OrderData(GenericDaoJpa<Order> orderRepo, GenericDaoJpa<OrderItem> orderItemRepo,
			GenericDaoJpa<Product> productRepo, GenericDaoJpa<ProductPrice> productPriceRepo,
			GenericDaoJpa<ProductDescription> productDescriptionRepo,
			GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo,
			GenericDaoJpa<Company> companyRepo2) {
		this.orderRepo = orderRepo;
		this.orderItemRepo = orderItemRepo;
		this.productRepo = productRepo;
		this.productPriceRepo = productPriceRepo;
		this.productDescriptionRepo = productDescriptionRepo;
		this.productUnitRepo = productUnitRepo;
		this.companyRepo = companyRepo2;
	}

	public void addOrderData() {
		//processOrderData();
		processOrderItemData();
		processProductData();
		processProductPriceData();
		processProductDescriptionData();
		processProductUnitOfMeasureConversionData();
	}

	/*
	private void processOrderData() {
		try (CSVReader reader = new CSVReader(new FileReader(orderCSVFile))) {
			String[] line;
			reader.readNext();
			List<Order> orders = new ArrayList<>();
			List<Company> companies = companyRepo.findAll();
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
				

				orders.add(new Order(orderId, syncId, companies.get(index % (companies.size() -1)), orderReference, orderDateTime, lastPaymentReminder,
						netAmount, taxAmount, totalAmount, currency));
				index++;
			}
			GenericDaoJpa.startTransaction();
			orderRepo.insertBatch(orders);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
	*/

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

	private void processProductData() {
		try (CSVReader reader = new CSVReader(new FileReader(productCSVFile))) {
			String[] line;
			reader.readNext();
			List<Product> products = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String productId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String unitOfMeasureId = items[2];
				String productCategoryId = items[3];
				String productAvailability = items[4];

				products.add(new Product(productId, syncId, unitOfMeasureId, productCategoryId, productAvailability));
			}
			GenericDaoJpa.startTransaction();
			productRepo.insertBatch(products);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	private void processProductPriceData() {
		try (CSVReader reader = new CSVReader(new FileReader(productPriceCSVFile))) {
			String[] line;
			reader.readNext();
			List<ProductPrice> productPrices = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String productId = items[0];
				String currencyId = items[1];
				int syncId = Integer.parseInt(items[2]);
				String price = items[3];
				String unitOfMeasureId = items[4];
				String str = items[5];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
				LocalDateTime syncDateTime = LocalDateTime.parse(str, formatter);
				int quantity = Integer.parseInt(items[6]);

				productPrices.add(new ProductPrice(productId, currencyId, syncId, price, unitOfMeasureId, syncDateTime,
						quantity));
			}
			GenericDaoJpa.startTransaction();
			productPriceRepo.insertBatch(productPrices);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	private void processProductDescriptionData() {
		try (CSVReader reader = new CSVReader(new FileReader(productDescriptionCSVFile))) {
			String[] line;
			reader.readNext();
			List<ProductDescription> productDescriptions = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String productId = items[0];
				String languageId = items[1];
				int syncId = Integer.parseInt(items[2]);
				String productName = items[3];
				String productListerDescription = items[4];
				String productShortDescription = items[5];
				String productLongDescription = items[6];

				productDescriptions.add(new ProductDescription(productId, languageId, syncId, productName,
						productListerDescription, productShortDescription, productLongDescription));
			}
			GenericDaoJpa.startTransaction();
			productDescriptionRepo.insertBatch(productDescriptions);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	private void processProductUnitOfMeasureConversionData() {
		try (CSVReader reader = new CSVReader(new FileReader(productUnitOfMeasureConversionCSVFile))) {
			String[] line;
			reader.readNext();
			List<ProductUnitOfMeasureConversion> productUnitConversions = new ArrayList<>();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String productId = items[0];
				String syncId = items[1];
				String formUnitOfMeasure = items[2];
				String toUnitOfMeasure = items[3];
				String formQuantity = items[4];
				String toQuantity = items[5];

				productUnitConversions.add(new ProductUnitOfMeasureConversion(productId, syncId, formUnitOfMeasure,
						toUnitOfMeasure, formQuantity, toQuantity));
			}
			GenericDaoJpa.startTransaction();
			productUnitRepo.insertBatch(productUnitConversions);
			GenericDaoJpa.commitTransaction();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}