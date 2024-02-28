package domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import domain.Company;
import domain.Product;

public class Order {
	
	private List<OrderItem> orderItems;
	private int orderId, syncId;
	private Company customer;
	private String orderReference;
	private LocalDateTime orderDateTime;
	private LocalDateTime lastPaymentReminder;
	private BigDecimal netAmount;
	private BigDecimal taxAmount;
	private BigDecimal totalAmount;
	private String currency;
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSyncId() {
		return syncId;
	}
	public void setSyncId(int syncId) {
		this.syncId = syncId;
	}
	public Company getCustomer() {
		return customer;
	}
	public void setCustomer(Company customer) {
		this.customer = customer;
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
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
