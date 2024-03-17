package gui.customer;

import domain.Order;

public record OrderDTO(String orderId, String date, String orderAmount, String orderStatus, String paymentStatus) {
	public OrderDTO(Order c) {
		this(c.getOrderId(), c.getDate(), c.getTotalAmount(), c.getOrderStatus(), c.getPaymentStatus());
	}
}
