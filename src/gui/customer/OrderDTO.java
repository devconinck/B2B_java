package gui.customer;

import domain.Order;

public record OrderDTO(String orderId, String date, String orderAmount, String orderStatus, String paymentStatus) {
	public OrderDTO(Order c) {
		this(c.getOrderId(), c.getDate(), c.getTotalAmount(), c.getOrderStatus(), c.getPaymentStatus());
	}
	
	public OrderDTO() {
		this(null, null, null, null, null);
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
}
