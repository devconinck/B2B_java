package dto;

import java.math.BigDecimal;

import domain.OrderItem;

public record OrderItemDTO(String name, int quantity, String inStock, BigDecimal unitPrice, BigDecimal totalProduct) {
	
	public OrderItemDTO(OrderItem oi) {
		this(oi.getProduct().getProductId(), oi.getQuantity(), oi.getProduct().getProductAvailability(), oi.getUnitPrice(), oi.getTotal());
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getInStock() {
		return inStock;
	}
	
	public String getUnitPrice() {
		return unitPrice.toPlainString();
	}
	
	public String getTotalProduct() {
		return totalProduct.toPlainString();
	}
}
