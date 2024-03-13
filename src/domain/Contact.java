package domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import util.Validation;

@Embeddable
// QUERIES TOEVOEGEN
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String phoneNumber; // Waarschijnlijk makkelijker met validatie bibliotheek
	private String email;

	// Default constructor JPA
	protected Contact() {

	}

	// Constructor
	public Contact(String phoneNumber, String email) {
		setPhoneNumber(phoneNumber);
		setEmail(email);
	}

	// Getters en setters toevoegen + validatie
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!email.matches(Validation.emailRegex)) {
			throw new IllegalArgumentException("Not a valid email address");
		}
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (!phoneNumber.matches(Validation.phoneNumberRegex)) {
			throw new IllegalArgumentException("Not a valid phone number");
		}
		this.phoneNumber = phoneNumber;
	}

	// Noodzakelijk voor Jakarta
	@Override
	public int hashCode() {
		return Objects.hash(email, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(email, other.email) && Objects.equals(phoneNumber, other.phoneNumber);
	}

}
