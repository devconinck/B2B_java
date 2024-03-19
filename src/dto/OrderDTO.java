package dto;

import domain.Order;

public record OrderDTO(String name, String orderId, String date, String orderAmount, String orderStatus, String paymentStatus, String lastPaymentReminder) {

	public OrderDTO(Order o) {
		this(o.getCompany().getName(), o.getOrderId(), o.getDate(), o.getTotalAmount(), o.getOrderStatus(), o.getPaymentStatus(), o.getLastPaymentReminder());
	}
		
	public String getName() {
		return name;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getOrderAmount() {
		return orderAmount;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public String getPaymentStatus() {
		return paymentStatus; 
	}
	
	public String getLastPaymentReminder() {
		return lastPaymentReminder;
	}
}
