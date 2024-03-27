package repository;

import java.util.List;

import domain.Account;
import domain.Company;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class AccountDaoJpa extends GenericDaoJpa<Account> implements AccountDao {

	public AccountDaoJpa() {
		super(Account.class);
	}

	@Override
	public Account getAccountByEmail(String email) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Account.getByEmail", Account.class).setParameter("email", email).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void addAccount(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
	}

	@Override
	public List<Account> getAccountByCompany(Company company) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Account.getByCompany", Account.class).setParameter("company", company).getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}
}
