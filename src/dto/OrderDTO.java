package dto;

import java.util.Set;

import domain.Order;
import domain.OrderItem;
import util.OrderStatus;
import util.PaymentOption;
import util.PaymentStatus;

public record OrderDTO(String name, String orderId, String date, String orderAmount, OrderStatus orderStatus, PaymentStatus paymentStatus, String lastPaymentReminder, 
		String street, String addressNr, String city, String postalCode, String country, Set<OrderItem> orderItems) {

	public OrderDTO(Order o) {
		this(o.getToCompany().getName(), o.getOrderID(), o.getDate(), o.getTotalAmount(), o.getOrderStatus(), o.getPaymentStatus(), o.getLastPaymentReminder(), 
				o.getToCompany().getAddress().getStreet(), o.getToCompany().getAddress().getNumber(), o.getToCompany().getAddress().getCity(), 
				o.getToCompany().getAddress().getZipCode(), o.getToCompany().getAddress().getCountry(), o.getOrderItems());
	}
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
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
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public PaymentStatus getPaymentStatus() {
		return paymentStatus; 
	}
	
	public String getLastPaymentReminder() {
		return lastPaymentReminder;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getAddressNr() {
		return addressNr;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public String getCountry() {
		return country;
	}
}
