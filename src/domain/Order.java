package domain;

import java.io.Serializable;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity 
@Table(name="order_table")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Transient
	private final SimpleStringProperty orderID = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty name = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty date = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty orderStatus = new SimpleStringProperty();
	@Transient
	private final SimpleStringProperty paymentStatus = new SimpleStringProperty();
	
	private String orderId;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems; 
	private String orderReference;
	private String orderDateTime;
	private String lastPaymentReminder;
	private String netAmount;
	private String taxAmount;
	private String totalAmount;
	private String currency;
	@ManyToOne
	private Company company;
	
	public Order(String orderId, int syncId, Company company, String orderReference, String orderDateTime, String lastPaymentReminder, 
			String netAmount, String taxAmount, String totalAmount, String currency) {
		setOrderID(orderId);
		setName("Temp");
		setDate(orderDateTime);
		setOrderStatus((int) (Math.random()*2)+1 == 1 ? "NOT PAID" : "PAID");
		int randomPaymentStatus = (int) (Math.random()*3)+1;
		setPaymentStatus(randomPaymentStatus == 1 ? "RECEIVED" : randomPaymentStatus == 2 ? "PROCESSED" : "DONE");
		setOrderId(orderId);
		setCompany(company);
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
	
	public String getOrderId() {
		return orderId;
	}
	
	private void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	private void setOrderID(String orderId) {
		this.orderID.set(orderId);
	}
	
	public String getOrderID() {
		return orderID.get();
	}
	
	public StringProperty orderIDProperty() {
		return orderID;
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
	
	private void setDate(String date) {
		this.date.set(date);
	}
	
	public String getDate() {
		return date.get();
	}
	
	public StringProperty dateProperty() {
		return date;
	}
	
	private void setOrderStatus(String orderStatus) {
		this.orderStatus.set(orderStatus);
	}
	
	public String getOrderStatus() {
		return orderStatus.get();
	}
	
	public StringProperty orderStatusProperty() {
		return orderStatus;
	}
	
	private void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}
	
	public String getPaymentStatus() {
		return paymentStatus.get();
	}
	
	public StringProperty paymentStatusProperty() {
		return paymentStatus;
	}
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}
	public String getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getLastPaymentReminder() {
		return lastPaymentReminder;
	}
	public void setLastPaymentReminder(String lastPaymentReminder) {
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
