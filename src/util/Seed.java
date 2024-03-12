package util;

import java.util.stream.Stream;

import domain.login.Account;
import domain.login.LoginController;
import domain.login.Role;
import repository.AccountDao;
import repository.AccountDaoJpa;

public class Seed {
	
	private AccountDao accountRepo;
	
	public Seed() {
		setAccountRepo(new AccountDaoJpa());
		run();
	}
	
	private void setAccountRepo(AccountDao mock) {
		this.accountRepo = mock;
	}
	
	private void run() {
		addUsers();
	}
	
	private void addUsers() {
		Account acc1 = new Account("Charles.leclerc@icloud.com", "Test123!", "BE0404754472", Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", "Root123!", "BE0404754472", Role.Customer);
		Stream.of(acc1, acc2).forEach(accountRepo::addAccount);
	}
}
