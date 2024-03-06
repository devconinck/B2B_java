package domain;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String productId;
	
	@OneToOne
	private ProductPrice productPrice;
	@OneToOne
	private ProductDescription productDescription;
	@OneToOne
	private ProductUnitOfMeasureConversion productUnitOfMeasureConversion;
	private int syncId;
	private String unitOfMeasure;
	private String productAvailability;
	
	public Product(String productId, int syncId, String unitOfMeasure, String productAvailability) {
		setProductId(productId);
		setSyncId(syncId);
		setUnitOfMeasure(unitOfMeasure);
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

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(String productAvailability) {
		this.productAvailability = productAvailability;
	}
	
	
}
