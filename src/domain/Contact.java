package domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;


@Entity
// QUERIES TOEVOEGEN
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
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
		// Source: https://emailregex.com
		String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		if (!email.matches(emailRegex)) {
			throw new IllegalArgumentException("Not a valid email address");
		}
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return email;
	}

	public void setPhoneNumber(String phoneNumber) {
		// Is een placeholder
		// Source: https://stackoverflow.com/questions/42104546/java-regular-expressions-to-validate-phone-numbers#42105140
		String allCountryRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		if (!phoneNumber.matches(allCountryRegex)) {
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
