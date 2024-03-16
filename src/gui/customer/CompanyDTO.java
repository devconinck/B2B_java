package gui.customer;

import java.util.List;

import domain.Company;

public record CompanyDTO(String vatNumber, String logo, List<Integer> orders, String country, String city,
		String zipcode, String street, String number, String phoneNumber, String email, List<String> paymentOptions, String customerStart,
		String name, String sector, long bankAccountNr, boolean isActive) {
	// Date does not work in Record, must be String?
	public CompanyDTO(Company c) {
		this(c.getVatNumber(), c.getLogo(), c.getOrders(), c.getAddress().getCountry(), c.getAddress().getCity(),
				c.getAddress().getZipCode(), c.getAddress().getStreet(), c.getAddress().getNumber(), c.getContact().getPhoneNumber(), c.getContact().getEmail(),
				c.getPaymentOptions(), c.getCustomerStart().toString(), c.getName(), c.getSector(), c.getBankAccountNr(),
				c.getIsActiveProperty().get());
	}

	// Needed for TableView
	public String getVatNumber() {
		return vatNumber;
	}

	public String getLogo() {
		return logo;
	}

	public List<Integer> getOrders() {
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

	public List<String> getPaymentOptions() {
		return paymentOptions;
	}

	public String getCustomerStart() {
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
}
