package domain;

import java.io.Serializable;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class OrderItem implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int orderId;
	@OneToOne
	private Product product;
	private int orderItemId;
	private int syncId;
	private String productId;
	private String quantity;
	private String unitOfMeasureId;
	private String netPrice;
	private String netAmount;
	
	public OrderItem(int orderId, int orderItemId, int syncId, String productId, String quantity, String unitOfMeasureId, String netPrice, String netAmount) {
		setOrderId(orderId);
		setOrderItemId(orderItemId);
		setSyncId(syncId);
		setProductId(productId);
		setQuantity(quantity);
		setUnitOfMeasureId(unitOfMeasureId);
		setNetPrice(netPrice);
		setNetAmount(netAmount);
	}
	
	protected OrderItem() {
		
	}
	
	public Product getProduct() {
		return product;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getSyncId() {
		return syncId;
	}
	public void setSyncId(int syncId) {
		this.syncId = syncId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitOfMeasureId() {
		return unitOfMeasureId;
	}
	public void setUnitOfMeasureId(String unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}
	public String getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(String netPrice) {
		this.netPrice = netPrice;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
}
