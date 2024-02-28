package domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
// Queries toevoegen
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToOne
    private int adresId;
	private String country;
	private String city;
	private String zipCode;
	private String street;
	private String number; // BVB Papegaaistraat 13A --> Huisnummer moet String zijn of apart veld
	
	// Default constructor JPA
	protected Address() {
		
	}

	// Constructor
	public Address(String country, String city, String zipCode, String street, String number) {
		setCountry(country);
		setCity(city);
		setZipCode(zipCode);
		setStreet(street);
		setNumber(number);
	}
	
	// Getters en setters
	// Validatie nodig?
	// Omslachtig werk tenzij Library gebruikt wordt
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	// Nodig voor JPA
	// Combinatie van alle elementen zorgt voor een uniek adres
	@Override
	public int hashCode() {
		return Objects.hash(city, country, number, street, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(number, other.number) && Objects.equals(street, other.street)
				&& Objects.equals(zipCode, other.zipCode);
	}
}
