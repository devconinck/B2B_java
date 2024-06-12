package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import util.PaymentOption;

public class OrderItemTest {
	
	private Company createCompany() {
		Set<Order> orders = new HashSet<>();
        Set<Company> customers = new HashSet<>();
        Set<Product> products = new HashSet<>();        
        
        return new Company("US1234567890", "logo.png", new Address("USA", "New York", "10001", "Broadway", "123"),
                new Contact("+1 (123) 456-7890", "john@example.com"), "Acme Inc.", "Technology", 1234567890L,
                List.of(PaymentOption.BANK_TRANSFER), LocalDate.of(2023, 1, 1), orders, customers, products);
	}

    @Test
    void testOrderItemConstructor() {
        Product product = new Product("P001", 1, "UOM001", "CAT001", "Available", createCompany(), "naam", "descriptiontest", 200);
        OrderItem orderItem = new OrderItem(1, 1, 1, product, 5, "UOM001", BigDecimal.valueOf(10.0), BigDecimal.valueOf(50.0), null);
        assertEquals("P001", orderItem.getName());
        assertEquals(5, orderItem.getQuantity());
        assertEquals("Available", orderItem.getInStock());
        assertEquals(BigDecimal.valueOf(10.0), orderItem.getUnitPrice());
        assertEquals(BigDecimal.valueOf(50.0), orderItem.getTotal());
        assertEquals(1, orderItem.getOrderId());
        assertEquals(1, orderItem.getOrderItemId());
        assertEquals(1, orderItem.getSyncId());
        assertEquals("UOM001", orderItem.getUnitOfMeasureId());
        assertEquals(product, orderItem.getProduct());
    }

    @Test
    void testSetName() {
        OrderItem orderItem = new OrderItem();
        orderItem.setName("Product 1");
        assertEquals("Product 1", orderItem.getName());
    }

    @Test
    void testSetQuantity() {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(10);
        assertEquals(10, orderItem.getQuantity());
    }

    @Test
    void testSetInStock() {
        OrderItem orderItem = new OrderItem();
        orderItem.setInStock("In Stock");
        assertEquals("In Stock", orderItem.getInStock());
    }

    @Test
    void testSetUnitPrice() {
        OrderItem orderItem = new OrderItem();
        orderItem.setUnitPrice(BigDecimal.valueOf(20.0));
        assertEquals(BigDecimal.valueOf(20.0), orderItem.getUnitPrice());
    }

    @Test
    void testSetTotal() {
        OrderItem orderItem = new OrderItem();
        orderItem.setTotal(BigDecimal.valueOf(100.0));
        assertEquals(BigDecimal.valueOf(100.0), orderItem.getTotal());
    }

    @Test
    void testSetOrderId() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(2);
        assertEquals(2, orderItem.getOrderId());
    }

    @Test
    void testSetOrderItemId() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(2);
        assertEquals(2, orderItem.getOrderItemId());
    }

    @Test
    void testSetSyncId() {
        OrderItem orderItem = new OrderItem();
        orderItem.setSyncId(2);
        assertEquals(2, orderItem.getSyncId());
    }

    @Test
    void testSetUnitOfMeasureId() {
        OrderItem orderItem = new OrderItem();
        orderItem.setUnitOfMeasureId("UOM002");
        assertEquals("UOM002", orderItem.getUnitOfMeasureId());
    }

    @Test
    void testSetProduct() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product("P001", 1, "UOM001", "CAT001", "Available", createCompany(), "naam", "descriptiontest", 200);
        orderItem.setProduct(product);
        assertEquals(product, orderItem.getProduct());
    }
}
