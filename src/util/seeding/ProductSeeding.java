package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import domain.Company;
import domain.Product;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class ProductSeeding {
	
	private GenericDao<Product> productRepo;
	private GenericDao<Company> companyRepo;
	private List<Product> productList = new ArrayList<>();
	private String productCSVFile = "src/CSVFiles/productdata.csv";
	
	public ProductSeeding(GenericDao<Product> productRepo, GenericDao<Company> companyRepo) {
		this.productRepo = productRepo;
		this.companyRepo = companyRepo;
		seeding();
	}
	
	private void seedToCompany() {
		List<Product> products = productRepo.findAll();
		int end = products.size();
		int startIndex = 0;
		for(Company c : companies) {
			if(startIndex + 10 >= 388) {
				startIndex = 1;
			}
			c.setProducts(products.subList(startIndex, startIndex+10).stream().collect(Collectors.toSet()));
			startIndex += 10;
		}
		GenericDaoJpa.startTransaction();
		productRepo.insertBatch(products);
		GenericDaoJpa.commitTransaction();
	}

	private void seeding() {
		try (CSVReader reader = new CSVReader(new FileReader(productCSVFile))) {
			String[] line;
			reader.readNext();
			List<Company> companies = companyRepo.findAll();
			int numberOfCompanies = companies.size();
			int index = 0;
			while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
				String[] items = line[0].split(";", -1);
				String productId = items[0];
				int syncId = Integer.parseInt(items[1]);
				String unitOfMeasureId = items[2];
				String productCategoryId = items[3];
				String productAvailability = items[4];
				productList.add(new Product(productId, syncId, unitOfMeasureId, productCategoryId, productAvailability, companies.get(index%numberOfCompanies)));
				index++;
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
