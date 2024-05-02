package domain;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String productId;
	private int syncId;
	private String productUnitOfMeasureId;
	private String productCategoryId;
	private String productAvailability;
	private String name;
	private String description;
	private int price;
	
	@ManyToOne
    private Company fromCompany;
	
	public Product(String productId, int syncId, String productUnitOfMeasureId, String productCategoryId, String productAvailability, Company fromCompany, String name, String description, int price) {
		setProductId(productId);
		setSyncId(syncId);
		setProductUnitOfMeasureId(productUnitOfMeasureId);
		setProductCategoryId(productCategoryId);
		setProductAvailability(productAvailability);
		setCompany(fromCompany);
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product() {};
	
	public Company getFromCompany() {
		return fromCompany;
	}
	
	public void setCompany(Company fromCompany) {
		this.fromCompany = fromCompany;
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
