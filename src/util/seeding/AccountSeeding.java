package util.seeding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import domain.Account;
import domain.Company;
import repository.AccountDao;
import util.Role;

public class AccountSeeding {
	
	private AccountDao accountRepo;
	private List<Company> companyList;
	
	public AccountSeeding(AccountDao accountRepo, List<Company> companyList) {
		this.accountRepo = accountRepo;
		this.companyList = companyList;
		seeding();
	}

	private void seeding() {
		List<Account> adminAccounts = new ArrayList<>();
		adminAccounts.add(new Account("admin@email.com", "root", companyList.get(2), Role.Admin));
		adminAccounts.add(new Account("test@email.com", "password", companyList.get(1), Role.Admin));

		adminAccounts.stream().forEach(accountRepo::addAccount);
		
		Account acc1 = new Account("Charles.leclerc@icloud.com", "Test123!", companyList.get(0), Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", "Root123!", companyList.get(0), Role.Customer);
		Account acc3 = new Account("sebastian.vettel@yahoo.com", "Password123!", companyList.get(1), Role.Supplier);
		Account acc4 = new Account("max.verstappen@hotmail.com", "P@ssw0rd", companyList.get(1), Role.Customer);
		Account acc5 = new Account("sergio.perez@gmail.com", "SecurePass", companyList.get(2), Role.Supplier);
		Account acc6 = new Account("esteban.ocon@outlook.com", "12345678", companyList.get(2), Role.Customer);
		Account acc7 = new Account("carlos.sainz@icloud.com", "Sainz2022", companyList.get(3), Role.Supplier);
		Account acc8 = new Account("landonorris@gmail.com", "Lando123", companyList.get(3), Role.Customer);
		Account acc9 = new Account("kimiraikkonen@yahoo.com", "IceIceBaby", companyList.get(4), Role.Supplier);
		Account acc10 = new Account("mick.schumacher@hotmail.com", "KeepFighting", companyList.get(4), Role.Customer);
		Account acc11 = new Account("nico.hulkenberg@gmail.com", "HulkSmash", companyList.get(5), Role.Supplier);
		Account acc12 = new Account("fernando.alonso@outlook.com", "Al0ns0", companyList.get(5), Role.Customer);
		Stream.of(acc1, acc2, acc3, acc4, acc5, acc6, acc7, acc8, acc9, acc10, acc11, acc12)
				.forEach(accountRepo::addAccount);
	}

}
