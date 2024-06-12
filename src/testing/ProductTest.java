package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.Order;
import domain.Product;
import util.PaymentOption;

public class ProductTest {
	
	private Company createCompany() {
		Set<Order> orders = new HashSet<>();
        Set<Company> customers = new HashSet<>();
        Set<Product> products = new HashSet<>();        
        
        return new Company("US1234567890", "logo.png", new Address("USA", "New York", "10001", "Broadway", "123"),
                new Contact("+1 (123) 456-7890", "john@example.com"), "Acme Inc.", "Technology", 1234567890L,
                List.of(PaymentOption.BANK_TRANSFER), LocalDate.of(2023, 1, 1), orders, customers, products);
	}

    @Test
    void testProductConstructor() {
        Product product = new Product("P001", 1, "UOM001", "CAT001", "Available", createCompany(), "naam", "descriptiontest", 200);
        assertEquals("P001", product.getProductId());
        assertEquals(1, product.getSyncId());
        assertEquals("UOM001", product.getProductUnitOfMeasureId());
        assertEquals("CAT001", product.getProductCategoryId());
        assertEquals("Available", product.getProductAvailability());
    }

    @Test
    void testSetProductId() {
        Product product = new Product();
        product.setProductId("P002");
        assertEquals("P002", product.getProductId());
    }

    @Test
    void testSetSyncId() {
        Product product = new Product();
        product.setSyncId(2);
        assertEquals(2, product.getSyncId());
    }

    @Test
    void testSetProductUnitOfMeasureId() {
        Product product = new Product();
        product.setProductUnitOfMeasureId("UOM002");
        assertEquals("UOM002", product.getProductUnitOfMeasureId());
    }

    @Test
    void testSetProductCategoryId() {
        Product product = new Product();
        product.setProductCategoryId("CAT002");
        assertEquals("CAT002", product.getProductCategoryId());
    }

    @Test
    void testSetProductAvailability() {
        Product product = new Product();
        product.setProductAvailability("Out of Stock");
        assertEquals("Out of Stock", product.getProductAvailability());
    }
}
