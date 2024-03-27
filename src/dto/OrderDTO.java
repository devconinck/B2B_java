package dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import domain.Company;
import domain.Order;
import domain.OrderItem;
import util.OrderStatus;
import util.PaymentStatus;

public record OrderDTO(String name, String orderId, LocalDate date, String orderAmount, OrderStatus orderStatus,
		PaymentStatus paymentStatus, String lastPaymentReminder, String street, String addressNr, String city,
		String postalCode, String country, Set<OrderItem> orderItems, Company fromCompany, String orderReference,
		String netAmount, String taxAmount, String totalAmount, String currency, Company toCompany) {

	public OrderDTO(Order o) {
		this(o.getFromCompany().getName(), o.getOrderID(), o.getOrderDateTime(), o.getTotalAmount(), o.getOrderStatus(),
				o.getPaymentStatus(), o.getLastPaymentReminder(), o.getFromCompany().getAddress().getStreet(),
				o.getFromCompany().getAddress().getNumber(), o.getFromCompany().getAddress().getCity(),
				o.getFromCompany().getAddress().getZipCode(), o.getFromCompany().getAddress().getCountry(),
				o.getOrderItems(), o.getFromCompany(), o.getOrderReference(), o.getNetAmount(), o.getTaxAmount(),
				o.getNetAmount(), o.getCurrency(), o.getToCompany());
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return date.format(formatter);
	}
	
	public LocalDate getOrderDate() {
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
