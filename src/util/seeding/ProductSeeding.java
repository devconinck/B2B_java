package util.seeding;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import domain.Company;
import domain.Product;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class ProductSeeding {
	
	private GenericDao<Product> productRepo;
	private GenericDao<Company> companyRepo;
	private List<Product> productList = new ArrayList<>();
	private String productCSVFile = "src/CSVFiles/test_seed.csv";
	
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
	    try (CSVReader reader = new CSVReaderBuilder(new FileReader(productCSVFile))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        String[] line;
	        
	        reader.readNext(); // Skip header line
	        List<Company> companies = companyRepo.findAll();
	        int numberOfCompanies = companies.size();
	        int index = 0;
	        while ((line = reader.readNext()) != null && !line[0].equals(";;;;;;;;;")) {
	            String[] items = line; // 
	            if (items.length >= 8) { // Check if the array has enough elements
	                String productId = items[0];
	                String name = items[1];
	                String description = items[2];
	                int syncId = Integer.parseInt(items[3]);
	                String unitOfMeasureId = items[4];
	                String productCategoryId = items[5];
	                String productAvailability = items[6];
	                int price = Integer.parseInt(items[7]);
	                productList.add(new Product(productId, syncId, unitOfMeasureId, productCategoryId, productAvailability, companies.get(index % numberOfCompanies), name, description, price));
	                index++;
	            } else {
	                // Handle cases where the line does not have enough elements
	                System.out.println("Invalid line format: " + Arrays.toString(items));
	            }
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
