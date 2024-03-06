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
	private long productPriceId;

	private String currencyId;
	private int syncId;
	private BigDecimal price;
	private String unitOfMeasureId;
	private LocalDateTime syncDateTime;
	private int quantity;
	
	public ProductPrice(String currencyId, int syncId, BigDecimal price, String unitOfMeasureId, LocalDateTime syncDateTime, int quantity) {
		setCurrencyId(currencyId);
		setSyncId(syncId);
		setPrice(price);
		setUnitOfMeasureId(unitOfMeasureId);
		setSyncDateTime(syncDateTime);
		setQuantity(quantity);
	}
	
	protected ProductPrice() {
		
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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
