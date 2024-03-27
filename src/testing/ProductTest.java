package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import domain.Product;

public class ProductTest {

    @Test
    void testProductConstructor() {
        Product product = new Product("P001", 1, "UOM001", "CAT001", "Available");
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
