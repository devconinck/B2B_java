package domain;

import com.opencsv.CSVReader;


import com.opencsv.exceptions.CsvValidationException;

import repository.GenericDaoJpa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderData {
	
	private GenericDaoJpa<Order> orderRepo;
	private GenericDaoJpa<OrderItem> orderItemRepo;
	private GenericDaoJpa<Product> productRepo;
	private GenericDaoJpa<ProductPrice> productPriceRepo;
	private GenericDaoJpa<ProductDescription> productDescriptionRepo;
	private GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo;
	private String orderCSVFile = "src/CSVFiles/orderdata.csv";
	private String productPriceCSVFile = "src/CSVFiles/productpricedata.csv";
	private String productDescriptionCSVFile = "src/CSVFiles/productdescriptiondata.csv";
	private String productUnitOfMeasureConversionCSVFile = "src/CSVFiles/productunitofmeasureconversiondata.csv";
	private String productCSVFile = "src/CSVFiles/productdata.csv";
	private String orderItemCSVFile = "src/CSVFiles/orderitemdata.csv";
	
	public OrderData(GenericDaoJpa<Order> orderRepo, GenericDaoJpa<OrderItem> orderItemRepo, GenericDaoJpa<Product> productRepo, GenericDaoJpa<ProductPrice> productPriceRepo, GenericDaoJpa<ProductDescription> productDescriptionRepo, GenericDaoJpa<ProductUnitOfMeasureConversion> productUnitRepo) {
		this.orderRepo = orderRepo;
		this.orderItemRepo = orderItemRepo;
		this.productRepo = productRepo;
		this.productPriceRepo = productPriceRepo;
		this.productDescriptionRepo = productDescriptionRepo;
		this.productUnitRepo = productUnitRepo;
	}
		
	public void addOrderData() {
		try (CSVReader reader = new CSVReader(new FileReader(orderCSVFile))){
			String[] line, items;
			reader.readNext();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) { //2 deel alternatief zoeken
				items = line[0].split(";", -1);
				String orderId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String customerId = items[2];
				String orderReference = items[3];
				String orderDateTime = items[4];
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String lastPaymentReminder = dateTime.format(formatter);
				String netAmount = items[5];
				String taxAmount = items[6];
				String totalAmount = items[7];
				String currency = items[8];
				
				orderRepo.startTransaction();
				orderRepo.insert(new Order(orderId, syncId, customerId, orderReference, orderDateTime, lastPaymentReminder, netAmount, taxAmount, totalAmount, currency));
				orderRepo.commitTransaction();
			}
		}catch(IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		try (CSVReader reader = new CSVReader(new FileReader(orderItemCSVFile))){
			String[] line, items;
			reader.readNext();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				items = line[0].split(";", -1);
				int orderId = Integer.parseInt(items[0]);
				int orderItemId = Integer.parseInt(items[1]);
				int syncId = Integer.parseInt(items[2]);
				String productId = items[3];
				String quantity = items[4];
				String unitOfMeasureId = items[5];
				String netPrice = items[6];
				String netAmount = items[7];
			
				orderItemRepo.startTransaction();
				orderItemRepo.insert(new OrderItem(orderId, orderItemId, syncId, productId, quantity, unitOfMeasureId, netPrice, netAmount));
				orderItemRepo.commitTransaction();
			}	
		}catch(IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		try (CSVReader reader = new CSVReader(new FileReader(productCSVFile))){
			String[] line, items;
			reader.readNext();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				items = line[0].split(";", -1);
				String productId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String unitOfMeasureId = items[2];
				String productCategoryId = items[3];
				String productAvailability = items[4];
				
				productRepo.startTransaction();
				productRepo.insert(new Product(productId, syncId, unitOfMeasureId, productCategoryId, productAvailability));
				productRepo.commitTransaction();
			}
		}catch(IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		try (CSVReader reader = new CSVReader(new FileReader(productPriceCSVFile))){
			String[] line, items;
			reader.readNext();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				items = line[0].split(";", -1);
				String productId = items[0];
				String currencyId = items[1];
				int syncId = Integer.parseInt(items[2]);
				String price = items[3];
				String unitOfMeasureId = items[4];
				String str = items[5];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
				LocalDateTime syncDateTime = LocalDateTime.parse(str, formatter);
				int quantity = Integer.parseInt(items[6]);
				
				productPriceRepo.startTransaction();
				productPriceRepo.insert(new ProductPrice(productId, currencyId, syncId, price, unitOfMeasureId, syncDateTime, quantity));
				productPriceRepo.commitTransaction();
			}
		}catch(IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		try (CSVReader reader = new CSVReader(new FileReader(productDescriptionCSVFile))){
			String[] line, items;
			reader.readNext();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				items = line[0].split(";", -1);
				String productId = items[0];
				String languageId = items[1];
				int syncId = Integer.parseInt(items[2]);
				String productName = items[3];
				String productListerDescription = items[4];
				String productShortDescription = items[5];
				String productLongDescription = items[6];
				
				productDescriptionRepo.startTransaction();
				productDescriptionRepo.insert(new ProductDescription(productId, languageId, syncId, productName, productListerDescription, productShortDescription, productLongDescription));
				productDescriptionRepo.commitTransaction();
			}
		}catch(IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		try (CSVReader reader = new CSVReader(new FileReader(productUnitOfMeasureConversionCSVFile))){
			String[] line, items;
			reader.readNext();
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				items = line[0].split(";", -1);
				String productId = items[0];
				String syncId = items[1];
				String formUnitOfMeasure = items[2];
				String toUnitOfMeasure = items[3];
				String formQuantity = items[4];
				String toQuantity = items[5];
				
				productUnitRepo.startTransaction();
				productUnitRepo.insert(new ProductUnitOfMeasureConversion(productId, syncId, formUnitOfMeasure, toUnitOfMeasure, formQuantity, toQuantity));
				productUnitRepo.commitTransaction();
			}
		}catch(IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
