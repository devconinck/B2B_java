package domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity
@Access(AccessType.FIELD)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private SimpleStringProperty inStock = new SimpleStringProperty();
    private BigDecimal unitPrice;
    private BigDecimal total;

    private int orderId;
    private int orderItemId;
    private int syncId;
    private String unitOfMeasureId;
    
    @ManyToOne
    private Order fromOrder;

    @OneToOne
    private Product product;

    public OrderItem(int orderId, int orderItemId, int syncId, Product product, int quantity, String unitOfMeasureId, BigDecimal unitPrice, BigDecimal total, Order fromOrder) {
        setProduct(product);
        setName(product.getProductId());
        setQuantity(quantity);
        setInStock(product.getProductAvailability());
        setUnitPrice(unitPrice);
        setTotal(total);
        setOrderId(orderId);
        setOrderItemId(orderItemId);
        setSyncId(syncId);
        setUnitOfMeasureId(unitOfMeasureId);
        setFromOrder(fromOrder);
    }

    public OrderItem() {};

    // Getters
    @Access(AccessType.PROPERTY)
    public String getName() {
        return name.get();
    }

    public Order getFromOrder() {
		return fromOrder;
	}

	public void setFromOrder(Order fromOrder) {
		this.fromOrder = fromOrder;
	}

	@Access(AccessType.PROPERTY)
    public int getQuantity() {
        return quantity.get();
    }

    @Access(AccessType.PROPERTY)
    public String getInStock() {
        return inStock.get();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getSyncId() {
        return syncId;
    }

    public String getUnitOfMeasureId() {
        return unitOfMeasureId;
    }

    public Product getProduct() {
        return product;
    }

    // Setters
    public void setName(String name) {
        this.name.set(name);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public void setInStock(String inStock) {
        this.inStock.set(inStock);
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setSyncId(int syncId) {
        this.syncId = syncId;
    }

    public void setUnitOfMeasureId(String unitOfMeasureId) {
        this.unitOfMeasureId = unitOfMeasureId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Property getters
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public SimpleStringProperty inStockProperty() {
        return inStock;
    }
}