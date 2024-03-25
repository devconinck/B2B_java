package domain;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String productId;
	@OneToOne
	private ProductPrice productPrice;
	@OneToOne
	private ProductDescription productDescription;
	@OneToOne
	private ProductUnitOfMeasureConversion productUnitOfMeasureConversion;
	private int syncId;
	private String productUnitOfMeasureId;
	private String productCategoryId;
	private String productAvailability;
	
	public Product(String productId, int syncId, String productUnitOfMeasureId, String productCategoryId, String productAvailability) {
		setProductId(productId);
		setSyncId(syncId);
		setProductUnitOfMeasureId(productUnitOfMeasureId);
		setProductCategoryId(productCategoryId);
		setProductAvailability(productAvailability);
	}
	
	protected Product() {
		
	}
	
	public ProductPrice getProductPrice() {
		return productPrice;
	}
	
	public ProductDescription getProductDescription() {
		return productDescription;
	}
	
	public ProductUnitOfMeasureConversion getProductUnitOfMeasureConversion() {
		return productUnitOfMeasureConversion;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getSyncId() {
		return syncId;
	}

	public void setSyncId(int syncId) {
		this.syncId = syncId;
	}

	public String getProductUnitOfMeasureId() {
		return productUnitOfMeasureId;
	}

	public void setProductUnitOfMeasureId(String productUnitOfMeasureId) {
		this.productUnitOfMeasureId = productUnitOfMeasureId;
	}
	
	public String getProductCategoryId() {
		return productCategoryId;
	}
	
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(String productAvailability) {
		this.productAvailability = productAvailability;
	}
	
	
}
