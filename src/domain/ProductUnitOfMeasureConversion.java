package domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductUnitOfMeasureConversion {
	
	private String productUnitId;
	private String syncUnitId;
	private String formUnitOfMeasure;
	private String toUnitOfMeasure;
	private String fromQuantity;
	private String toQuantity;
	
	public ProductUnitOfMeasureConversion(String productUnitId, String syncUnitId, String formUnitOfMeasure, String toUnitOfMeasure, String fromQuantity, String toQuantity) {
		setProductUnitId(productUnitId);
		setSyncUnitId(syncUnitId);
		setFormUnitOfMeasure(formUnitOfMeasure);
		setToUnitOfMeasure(toUnitOfMeasure);
		setFromQuantity(fromQuantity);
		setToQuantity(toQuantity);
	}
	
	protected ProductUnitOfMeasureConversion() {
		
	}

	public String getProductUnitId() {
		return productUnitId;
	}

	public void setProductUnitId(String productUnitId) {
		this.productUnitId = productUnitId;
	}

	public String getSyncUnitId() {
		return syncUnitId;
	}

	public void setSyncUnitId(String syncUnitId) {
		this.syncUnitId = syncUnitId;
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
