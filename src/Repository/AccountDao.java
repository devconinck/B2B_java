package Repository;

import domain.login.Account;
import jakarta.persistence.EntityNotFoundException;

public interface AccountDao extends GenericDao<Account> {

	public Account getAccountByEmail(String email) throws EntityNotFoundException;
	
	public void addAccount(Account account);
}
