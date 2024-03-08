package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String productId;

	private String currencyId;
	private int syncId;
	private String price;
	private String unitOfMeasureId;
	private LocalDateTime syncDateTime;
	private int quantity;
	
	public ProductPrice(String productId, String currencyId, int syncId, String price, String unitOfMeasureId, LocalDateTime syncDateTime, int quantity) {
		setProductId(productId);
		setCurrencyId(currencyId);
		setSyncId(syncId);
		setPrice(price);
		setUnitOfMeasureId(unitOfMeasureId);
		setSyncDateTime(syncDateTime);
		setQuantity(quantity);
	}
	
	protected ProductPrice() {
		
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public int getSyncId() {
		return syncId;
	}

	public void setSyncId(int syncId) {
		this.syncId = syncId;
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
