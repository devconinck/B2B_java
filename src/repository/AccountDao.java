package repository;

import java.util.List;

import domain.Account;
import domain.Company;
import jakarta.persistence.EntityNotFoundException;

public interface AccountDao extends GenericDao<Account> {

	public Account getAccountByEmail(String email) throws EntityNotFoundException;
	
	public void addAccount(Account account);
	
	public List<Account> getAccountByCompany(Company company) throws EntityNotFoundException;
}
