package domain.login;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Account.getByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
})
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;
	private int companyVAT;
	private Role role;

	
	public Account() {};
	
	public Account(String email, String password, int companyVAT, Role role) {
		// TODO setter voor voorwaarden (bv. lengte wachtwoord, geldig email, ...)
		this.email = email;
		this.password = password;
		this.companyVAT = companyVAT;
		this.role = role;
	}
	
	public String getPassword() { return password; };

	public Role getRole() {	return role; }

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
