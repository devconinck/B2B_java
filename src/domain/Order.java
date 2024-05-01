package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import dto.OrderDTO;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleStringProperty;
import util.OrderStatus;
import util.PaymentStatus;

@Entity
@Table(name = "order_table")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "Order.orderMadeToCompany", query = "SELECT o FROM Order o WHERE o.toCompany = :company"),
})
public class Order implements Serializable, Comparable<Order>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private SimpleStringProperty orderID = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "fromOrder", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
    private String orderReference;
    private LocalDate orderDateTime;
    private String lastPaymentReminder;
    private double netAmount;
    private double taxAmount;
    private double totalAmount;
    private String currency;
    @ManyToOne
    private Company fromCompany;
    @OneToOne
    private Company toCompany;
    

    // Default constructor JPA
    public Order() {}

    // Constructor
    public Order(String orderId, int syncId, Company fromCompany, Company toCompany, String orderReference, LocalDate orderDateTime,
            String lastPaymentReminder, double netAmount, double taxAmount, double totalAmount, String currency, Set<OrderItem> orderItems) {
        setOrderID(orderId);
        setName(fromCompany.getName());
        setDate(orderDateTime.toString());
        setOrderStatus(OrderStatus.values()[((int) (Math.random() * OrderStatus.values().length) + 1) - 1]);
        setPaymentStatus(PaymentStatus.values()[((int) (Math.random() * PaymentStatus.values().length) + 1) - 1]);
        setFromCompany(fromCompany);
        setOrderReference(orderReference);
        setOrderDateTime(orderDateTime);
        setLastPaymentReminder(lastPaymentReminder);
        setNetAmount(netAmount);
        setTaxAmount(taxAmount);
        setTotalAmount(totalAmount);
        setCurrency(currency);
        setToCompany(toCompany);
        setOrderItems(orderItems);
    }

	public Order(OrderDTO order) {
    	setOrderID(order.orderId());
    	setName(order.fromCompany().getName());
        setDate(order.date().toString());
        setOrderStatus(order.orderStatus());
        setPaymentStatus(order.paymentStatus());
        setFromCompany(order.fromCompany());
        setOrderReference(order.orderReference());
        setOrderDateTime(order.date());
        setLastPaymentReminder(order.lastPaymentReminder());
        setNetAmount(order.netAmount());
        setTaxAmount(order.taxAmount());
        setTotalAmount(order.totalAmount());
        setCurrency(order.currency());
        setToCompany(order.toCompany());
        setOrderItems(order.orderItems());
    }


    // Getters
    @Access(AccessType.PROPERTY)
    public String getOrderID() {
        return orderID.get();
    }

    @Access(AccessType.PROPERTY)
    public String getName() {
        return name.get();
    }

    @Access(AccessType.PROPERTY)
    public String getDate() {
        return date.get();
    }

    @Access(AccessType.PROPERTY)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Access(AccessType.PROPERTY)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Company getFromCompany() {
        return fromCompany;
    }
    
    public Company getToCompany() {
    	return toCompany;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public LocalDate getOrderDateTime() {
        return orderDateTime;
    }

    public String getLastPaymentReminder() {
        return lastPaymentReminder;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    // Property getters
    public SimpleStringProperty orderIDProperty() {
        return orderID;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    // Setters
    public void setOrderID(String orderId) {
        this.orderID.set(orderId);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
    	this.orderStatus = orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = OrderStatus.valueOf(orderStatus);
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = PaymentStatus.valueOf(paymentStatus);
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setFromCompany(Company company) {
        this.fromCompany = company;
    }
    
    public void setToCompany(Company toCompany) {
		this.toCompany = toCompany;		
	}

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public void setOrderDateTime(LocalDate orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void setLastPaymentReminder(String lastPaymentReminder) {
        this.lastPaymentReminder = lastPaymentReminder;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

	@Override
	public int hashCode() {
		return Objects.hash(date, orderID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(date, other.date) && Objects.equals(orderID, other.orderID);
	}
	
	@Override
	public int compareTo(Order otherOrder) {
		int dateComparison = this.getDate().compareTo(otherOrder.getDate());
		if (dateComparison == 0) {
		     // Als de datums gelijk zijn, sorteer op orderId
		     return Integer.parseInt(this.getOrderID()) - Integer.parseInt(otherOrder.getOrderID());
		}
		// Sorteer op datum
		return dateComparison;
    }
}