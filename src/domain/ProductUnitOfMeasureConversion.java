package domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProductUnitOfMeasureConversion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productUnitId;
	
	private int syncId;
	private String formUnitOfMeasure;
	private String toUnitOfMeasure;
	private int fromQuantity;
	private int toQuantity;
	
	public ProductUnitOfMeasureConversion(int syncId, String formUnitOfMeasure, String toUnitOfMeasure, int fromQuantity, int toQuantity) {
		setSyncId(syncId);
		setFormUnitOfMeasure(formUnitOfMeasure);
		setToUnitOfMeasure(toUnitOfMeasure);
		setFromQuantity(fromQuantity);
		setToQuantity(toQuantity);
	}
	
	protected ProductUnitOfMeasureConversion() {
		
	}

	public int getSyncId() {
		return syncId;
	}

	public void setSyncId(int syncId) {
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

	public int getFromQuantity() {
		return fromQuantity;
	}

	public void setFromQuantity(int fromQuantity) {
		this.fromQuantity = fromQuantity;
	}

	public int getToQuantity() {
		return toQuantity;
	}

	public void setToQuantity(int toQuantity) {
		this.toQuantity = toQuantity;
	}
}
