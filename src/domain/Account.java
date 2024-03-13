package domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Account.getByEmail", query = "SELECT a FROM Account a WHERE :email = a.email"), })
public class Account implements Serializable {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	private static final String COMPANY_VAT_REGEX = "[A-Z]{2}[0-9A-Za-z]{1,30}";

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;
	private String companyVAT;
	private Role role;

	protected Account() {
	};

	public Account(String email, String password, String companyVAT, Role role) {
		setEmail(email);
		setPassword(password);
		setCompanyVAT(companyVAT);
		this.role = role;
	}

	private void setEmail(String email) {
		if (!patternMatches(email, EMAIL_REGEX))
			throw new IllegalArgumentException(String.format("The email: %s , is not a valiable email", email));
		this.email = email;
	}

	private void setPassword(String password) {
		// if (!patternMatches(password, PASSWORD_REGEX))
		// throw new IllegalArgumentException(String.format("The password for %s is not
		// strong enough", email));
		this.password = LoginController.encryptPassword(password);
	}

	private void setCompanyVAT(String companyVAT) {
		// if (!patternMatches(companyVAT, COMPANY_VAT_REGEX) ||
		// !isValidMOD97(companyVAT))
		// throw new IllegalArgumentException(String.format("The company VAT number is
		// not valid for %s", email));
		this.companyVAT = companyVAT;
	}

	private static boolean isValidMOD97(String companyVAT) {
		int mod97 = 97 - (Integer.parseInt(companyVAT.substring(2, 10)) % 97);
		return mod97 == Integer.parseInt(companyVAT.substring(10));
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

	@Override
	public int hashCode() {
		return Objects.hash(companyVAT, email, password, role);
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
		return companyVAT == other.companyVAT && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && role == other.role;
	}
}
