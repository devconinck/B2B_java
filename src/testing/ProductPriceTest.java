package testing;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.ProductPrice;

public class ProductPriceTest {

	private ProductPrice productPrice;
	
	@BeforeEach
	public void before() {
		productPrice = new ProductPrice("EUR", 1, new BigDecimal(15.95), "M", LocalDateTime.of(2024, 3, 3, 10, 30, 0), 5);
	}
	
	@Test
	public void testMethode() {
		
	}
}
