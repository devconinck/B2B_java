package dto;

import java.util.Set;

import domain.Order;
import domain.OrderItem;

public record OrderDTO(String name, String orderId, String date, String orderAmount, String orderStatus, String paymentStatus, String lastPaymentReminder, 
		String street, String addressNr, String city, String postalCode, String country, Set<OrderItem> orderItems) {

	public OrderDTO(Order o) {
		this(o.getCompany().getName(), o.getOrderId(), o.getDate(), o.getTotalAmount(), o.getOrderStatus(), o.getPaymentStatus(), o.getLastPaymentReminder(), 
				o.getCompany().getAddress().getStreet(), o.getCompany().getAddress().getNumber(), o.getCompany().getAddress().getCity(), 
				o.getCompany().getAddress().getZipCode(), o.getCompany().getAddress().getCountry(), o.getOrderItems());
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
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public String getPaymentStatus() {
		return paymentStatus; 
	}
	
	public String getLastPaymentReminder() {
		return lastPaymentReminder;
	}
	
	public String getStreet() {
		return street;
	}
	
	/*public String getAddressNr() {
		return addressNr;
	}*/
	
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
