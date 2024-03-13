package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String customerId;
	private String firstName;
	private String lastName;
	private String street;
	private String addressNr;
	private String city;
	private String postalCode;
	private String country;

	public Customer(String customerId, String firstName, String lastName, String street, String addressNr, String city, String postalCode, String country) {
		setCustomerId(customerId);
		setFirstName(firstName);
		setLastName(lastName);
		setStreet(street);
		setAddressNr(addressNr);
		setCity(city);
		setPostalCode(postalCode);
		setCountry(country);
	}
	
	protected Customer() {
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressNr() {
		return addressNr;
	}

	public void setAddressNr(String addressNr) {
		this.addressNr = addressNr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}
