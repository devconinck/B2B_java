package testing;

import org.junit.jupiter.api.BeforeEach;

import domain.ProductDescription;

public class ProductDescriptionTest {
	
	private ProductDescription productDescription;
	
	@BeforeEach
	public void before() {
		productDescription = new ProductDescription("ENG", 1, "Computer Mouse", "", "", "");
	}

}
