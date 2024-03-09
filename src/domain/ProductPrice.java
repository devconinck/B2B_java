package domain;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductPrice {
	
	private String productPriceId;
	private String currencyId;
	private int syncPriceId;
	private String price;
	private String unitOfMeasureId;
	private LocalDateTime syncDateTime;
	private int quantity;
	
	public ProductPrice(String productPriceId, String currencyId, int syncPriceId, String price, String unitOfMeasureId, LocalDateTime syncDateTime, int quantity) {
		setProductPriceId(productPriceId);
		setCurrencyId(currencyId);
		setSyncPriceId(syncPriceId);
		setPrice(price);
		setUnitOfMeasureId(unitOfMeasureId);
		setSyncDateTime(syncDateTime);
		setQuantity(quantity);
	}
	
	protected ProductPrice() {
		
	}
	
	public String getProductPriceId() {
		return productPriceId;
	}
	
	public void setProductPriceId(String productPriceId) {
		this.productPriceId = productPriceId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public int getSyncPriceId() {
		return syncPriceId;
	}

	public void setSyncPriceId(int syncPriceId) {
		this.syncPriceId = syncPriceId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnitOfMeasureId() {
		return unitOfMeasureId;
	}

	public void setUnitOfMeasureId(String unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}

	public LocalDateTime getSyncDateTime() {
		return syncDateTime;
	}

	public void setSyncDateTime(LocalDateTime syncDateTime) {
		this.syncDateTime = syncDateTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
