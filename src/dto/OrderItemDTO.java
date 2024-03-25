package dto;

import domain.OrderItem;

public record OrderItemDTO(String name, String quantity, String inStock, String unitPrice, String totalProduct) {
	
	public OrderItemDTO(OrderItem oi) {
		this(oi.getProduct().getProductId(), oi.getQuantity(), oi.getProduct().getProductAvailability(), oi.getUnitPrice(), oi.getTotal());
	}
	
	public String getName() {
		return name;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getInStock() {
		return inStock;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}
	
	public String getTotalProduct() {
		return totalProduct;
	}
}
