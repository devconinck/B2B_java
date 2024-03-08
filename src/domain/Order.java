package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import domain.Company;
import domain.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="order_table")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int orderId;

	//@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	private int syncId;
	private String customerId; //moet misschien nog aangepast worden, later bekijken wanneer company gekoppeld wordt
	private String orderReference;
	private LocalDateTime orderDateTime;
	private LocalDateTime lastPaymentReminder;
	private String netAmount;
	private String taxAmount;
	private String totalAmount;
	private String currency;
	
	public Order(int orderId, int syncId, String customerId, String orderReference, LocalDateTime orderDateTime, LocalDateTime lastPaymentReminder, 
			String netAmount, String taxAmount, String totalAmount, String currency) {
		setOrderId(orderId);
		setSyncId(syncId);
		setCustomer(customerId);
		setOrderReference(orderReference);
		setOrderDateTime(orderDateTime);
		setLastPaymentReminder(lastPaymentReminder);
		setNetAmount(netAmount);
		setTaxAmount(taxAmount);
		setTotalAmount(totalAmount);
		setCurrency(currency);
	}
	
	protected Order() {
		
	}
	
	public void showOrders() {
		
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public int getSyncId() {
		return syncId;
	}
	public void setSyncId(int syncId) {
		this.syncId = syncId;
	}
	public String getCustomer() {
		return customerId;
	}
	public void setCustomer(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public LocalDateTime getLastPaymentReminder() {
		return lastPaymentReminder;
	}
	public void setLastPaymentReminder(LocalDateTime lastPaymentReminder) {
		this.lastPaymentReminder = lastPaymentReminder;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
