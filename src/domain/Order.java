package domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import domain.Company;
import domain.Product;

public class Order {
	private Product product;
	private int orderId, syncId;
	private Company customer;
	private String orderReference;
	private LocalDateTime orderDateTime;
	private BigDecimal netAmount;
	private BigDecimal taxAmount;
	private BigDecimal totalAmount;
	private String currency;
	
	
	private String nameCustomer, deliverAddress, orderStatus, paymentStatus;
	private List<String> contactDetails;
	private Date dateOrder, lastPaymentReminder;
}
