package dto;

import domain.OrderItem;

public record OrderItemDTO(String Name, String Quantity, String InStock, String UnitPrice, String TotalProduct) {
	
	public OrderItemDTO(OrderItem oi) {
		this(oi.getName(), oi.getQuantity(), oi.getInStock(), oi.getUnitPrice(), oi.getTotal());
	}
	
	public String getName() {
		return Name;
	}
	
	public String getQuantity() {
		return Quantity;
	}
	
	public String InStock() {
		return InStock;
	}
	
	public String UnitPrice() {
		return UnitPrice;
	}
	
	public String TotalProduct() {
		return TotalProduct;
	}
}
