package domain;

public class Product {
	
	private String productId;
	private int syncId;
	private String unitOfMeasure;
	private String productAvailability;
	
	public Product(String productId, int syncId, String unitOfMeasure, String productAvailability) {
		setProductId(productId);
		setSyncId(syncId);
		setUnitOfMeasure(unitOfMeasure);
		setProductAvailability(productAvailability);
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
