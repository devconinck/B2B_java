package domain;

import java.io.Serializable;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Transient
	private final SimpleStringProperty name = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty q = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty inStock = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty unitPrice = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty total = new SimpleStringProperty();
	
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
	
	
	public OrderItem(int orderId, int orderItemId, int syncId, Product product, String quantity, String unitOfMeasureId, String netPrice, String netAmount) {
		setProduct(product);
		setName(product.getProductId());
		setQ(quantity);
		setInStock(product.getProductAvailability());
		setUnitPrice(netPrice);
		setTotal(netAmount);
		setOrderId(orderId);
		setOrderItemId(orderItemId);
		setSyncId(syncId);
		setQuantity(quantity);
		setUnitOfMeasureId(unitOfMeasureId);
		setNetPrice(netPrice);
		setNetAmount(netAmount);
	}
	
	protected OrderItem() {
		
	}
	
	private void setName(String name) {
		this.name.set(name);
	}
	
	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}
	
	private void setQ(String quantity) {
		this.q.set(quantity);
	}
	
	public String getQ() {
		return q.get();
	}

	public StringProperty qProperty() {
		return q;
	}
	
	private void setInStock(String inStock) {
		this.inStock.set(inStock);
	}
	
	public String getInStock() {
		return inStock.get();
	}

	public StringProperty inStockProperty() {
		return inStock;
	}
	
	private void setUnitPrice(String unitPrice) {
		this.unitPrice.set(unitPrice);
	}
	
	public String getUnitPrice() {
		return unitPrice.get();
	}

	public StringProperty unitPriceProperty() {
		return unitPrice;
	}
	
	private void setTotal(String total) {
		this.total.set(total);
	}
	
	public String getTotal() {
		return total.get();
	}

	public StringProperty totalProperty() {
		return total;
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
