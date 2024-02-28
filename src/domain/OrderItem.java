package domain;

import java.math.BigDecimal;

public class OrderItem {

	private Product product;
	private int orderId;
	private int orderItemId;
	private int syncId;
	private String productId;
	private int quantity;
	private String unitOfMeasures;
	private BigDecimal netPrice;
	private BigDecimal netAmount;
	
	public OrderItem(Product product, int orderId, int orderItemId, int syncId, String productId, int quantity, String unitOfMeasures, BigDecimal netPrices, BigDecimal netAmount) {
		setProduct(product);
		setOrderId(orderId);
		setOrderItemId(orderItemId);
		setSyncId(syncId);
		setProductId(productId);
		setQuantity(quantity);
		setUnitOfMeasures(unitOfMeasures);
		setNetPrice(netPrice);
		setNetAmount(netAmount);
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnitOfMeasures() {
		return unitOfMeasures;
	}
	public void setUnitOfMeasures(String unitOfMeasures) {
		this.unitOfMeasures = unitOfMeasures;
	}
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
}
