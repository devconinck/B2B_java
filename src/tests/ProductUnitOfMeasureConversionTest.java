package tests;

import org.junit.jupiter.api.BeforeEach;

import domain.ProductUnitOfMeasureConversion;

public class ProductUnitOfMeasureConversionTest {

	private ProductUnitOfMeasureConversion productUnitOfMeasureConversion;
	
	@BeforeEach
	public void before() {
		productUnitOfMeasureConversion = new ProductUnitOfMeasureConversion(1, "CM", "M", 2, 4);
	}
}
