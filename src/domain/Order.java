package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import domain.Company;
import domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	private List<OrderItem> orderItems;
	private int syncId;
	private Company customer;
	private String orderReference;
	private LocalDateTime orderDateTime;
	private LocalDateTime lastPaymentReminder;
	private BigDecimal netAmount;
	private BigDecimal taxAmount;
	private BigDecimal totalAmount;
	private String currency;
	
	public Order(List<OrderItem> orderItems, int syncId, Company customer, String orderReference, LocalDateTime orderDateTime, LocalDateTime lastPaymentReminder, 
			BigDecimal netAmount, BigDecimal taxAmount, BigDecimal totalAmount, String currency) {
		setOrderItems(orderItems);
		setSyncId(syncId);
		setCustomer(customer);
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
