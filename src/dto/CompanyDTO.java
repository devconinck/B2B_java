package dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import domain.Company;
import domain.Order;
import util.PaymentOption;

public record CompanyDTO(String vatNumber, String logo, Set<Order> orders, String country, String city,
		String zipcode, String street, String number, String phoneNumber, String email, List<PaymentOption> paymentOptions, LocalDate customerStart,
		String name, String sector, long bankAccountNr, boolean isActive, int numberOfOpenOrders, Set<Company> customers) {
	// Date does not work in Record, must be String?
	public CompanyDTO(Company c) {
		this(c.getVatNumber(), c.getLogo(), c.getOrders(), c.getAddress().getCountry(), c.getAddress().getCity(),
				c.getAddress().getZipCode(), c.getAddress().getStreet(), c.getAddress().getNumber(), c.getContact().getPhoneNumber(), c.getContact().getEmail(),
				c.getPaymentOptions(), c.getCustomerStart(), c.getName(), c.getSector(), c.getBankAccountNr(),
				c.getIsActiveProperty().get(), c.getOrders().size(), c.getCustomers());
	}

	// Needed for TableView
	public String getVatNumber() {
		return vatNumber;
	}

	public String getLogo() {
		return logo;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public List<PaymentOption> getPaymentOptions() {
		return paymentOptions;
	}

	public LocalDate getCustomerStart() {
		return customerStart;
	}

	public String getName() {
		return name;
	}

	public String getSector() {
		return sector;
	}

	public long getBankAccountNr() {
		return bankAccountNr;
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public int getNumberOfOpenOrders() {
		return numberOfOpenOrders;
	}
}
