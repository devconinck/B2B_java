package domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

import gui.login.LoginController;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import util.Role;
import util.Validation;

@Entity
@NamedQueries({ @NamedQuery(name = "Account.getByEmail", query = "SELECT a FROM Account a WHERE :email = a.email"), })
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;
	private Company company;
	private Role role;
	
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	protected Account() {};

	public Account(String email, String password, Company company, Role role) {
		setEmail(email);
		setPassword(password);
		setCompany(company);
		this.role = role;
	}

	private void setCompany(Company company) {
		this.company = company;
		
	}

	private void setEmail(String email) {
		if (!patternMatches(email, EMAIL_REGEX))
			throw new IllegalArgumentException(String.format("The email: %s , is not a valid email", email));
		this.email = email;
	}

	private void setPassword(String password) {
		// if (!patternMatches(password, Validation.PASSWORD_REGEX))
		// throw new IllegalArgumentException(String.format("The password for %s is not
		// strong enough", email));
		this.password = LoginController.encryptPassword(password);
	}

	private static boolean patternMatches(String string, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(string).matches();
	}

	public String getPassword() {
		return password;
	};

	public Role getRole() {
		return role;
	};
	
	public Company getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, email, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(company, other.company) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && role == other.role;
	}
}
