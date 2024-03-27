package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import domain.Product;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class ProductSeeding {
	
	private GenericDao<Product> productRepo;
	private List<Product> productList = new ArrayList<>();
	private String productCSVFile = "src/CSVFiles/productdata.csv";
	
	public ProductSeeding(GenericDao<Product> productRepo) {
		this.productRepo = productRepo;
		seeding();
	}
	
	private void seeding() {
		try (CSVReader reader = new CSVReader(new FileReader(productCSVFile))) {
			String[] line;
			reader.readNext();
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
	
	public List<Product> getProductList() {
		return productList;
	}
}
