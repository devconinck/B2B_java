package util;

import java.util.stream.Stream;

import Repository.AccountDao;
import Repository.AccountDaoJpa;
import domain.login.Account;
import domain.login.LoginController;
import domain.login.Role;

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
		Account acc1 = new Account("Charles.leclerc@gmail.com", LoginController.encryptPassword("test123"), 123456, Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", LoginController.encryptPassword("root123"), 123456, Role.Admin);
		Stream.of(acc1, acc2).forEach(accountRepo::addAccount);
	}
}
