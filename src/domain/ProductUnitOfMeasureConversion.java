package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductUnitOfMeasureConversion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String productId;
	
	private String syncId;
	private String formUnitOfMeasure;
	private String toUnitOfMeasure;
	private String fromQuantity;
	private String toQuantity;
	
	public ProductUnitOfMeasureConversion(String productId, String syncId, String formUnitOfMeasure, String toUnitOfMeasure, String fromQuantity, String toQuantity) {
		setProductId(productId);
		setSyncId(syncId);
		setFormUnitOfMeasure(formUnitOfMeasure);
		setToUnitOfMeasure(toUnitOfMeasure);
		setFromQuantity(fromQuantity);
		setToQuantity(toQuantity);
	}
	
	protected ProductUnitOfMeasureConversion() {
		
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSyncId() {
		return syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

	public String getFormUnitOfMeasure() {
		return formUnitOfMeasure;
	}

	public void setFormUnitOfMeasure(String formUnitOfMeasure) {
		this.formUnitOfMeasure = formUnitOfMeasure;
	}

	public String getToUnitOfMeasure() {
		return toUnitOfMeasure;
	}

	public void setToUnitOfMeasure(String toUnitOfMeasure) {
		this.toUnitOfMeasure = toUnitOfMeasure;
	}

	public String getFromQuantity() {
		return fromQuantity;
	}

	public void setFromQuantity(String fromQuantity) {
		this.fromQuantity = fromQuantity;
	}

	public String getToQuantity() {
		return toQuantity;
	}

	public void setToQuantity(String toQuantity) {
		this.toQuantity = toQuantity;
	}
}
