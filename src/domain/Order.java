package domain;

import java.io.Serializable;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleStringProperty;
import util.PaymentStatus;

@Entity
@Table(name = "order_table")
@Access(AccessType.FIELD)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private SimpleStringProperty orderID = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty orderStatus = new SimpleStringProperty();
    private PaymentStatus paymentStatus;

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

    // Default constructor JPA
    protected Order() {}

    // Constructor
    public Order(String orderId, int syncId, Company company, String orderReference, String orderDateTime,
            String lastPaymentReminder, String netAmount, String taxAmount, String totalAmount, String currency) {
        setOrderID(orderId);
        setName("Temp");
        setDate(orderDateTime);
        setOrderStatus((int) (Math.random() * 2) + 1 == 1 ? "NOT PAID" : "PAID");
        int randomPaymentStatus = (int) (Math.random() * 3) + 1;
        setPaymentStatus(randomPaymentStatus == 1 ? PaymentStatus.INVOICE_SENT
                : randomPaymentStatus == 2 ? PaymentStatus.PAID : PaymentStatus.UNPROCESSED);
        setCompany(company);
        setOrderReference(orderReference);
        setOrderDateTime(orderDateTime);
        setLastPaymentReminder(lastPaymentReminder);
        setNetAmount(netAmount);
        setTaxAmount(taxAmount);
        setTotalAmount(totalAmount);
        setCurrency(currency);
    }
    
    public Order(OrderDTO orderdto) {
    	setOrderID(orderdto.orderId());
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
    public String getOrderStatus() {
        return orderStatus.get();
    }

    @Access(AccessType.PROPERTY)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Company getCompany() {
        return company;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public String getLastPaymentReminder() {
        return lastPaymentReminder;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public String getTotalAmount() {
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

    public SimpleStringProperty orderStatusProperty() {
        return orderStatus;
    }

    public SimpleStringProperty paymentStatusProperty() {
        return new SimpleStringProperty(paymentStatus.toString());
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

    public void setOrderStatus(String orderStatus) {
        this.orderStatus.set(orderStatus);
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

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void setLastPaymentReminder(String lastPaymentReminder) {
        this.lastPaymentReminder = lastPaymentReminder;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}