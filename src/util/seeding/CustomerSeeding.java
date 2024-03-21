package util.seeding;

import java.util.List;
import java.util.Set;

import domain.Company;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class CustomerSeeding {
	
	private GenericDao<Company> companyRepo;
	private List<Company> companyList;
	
	public CustomerSeeding(GenericDao<Company> companyRepo) {
		this.companyRepo = companyRepo;
		this.companyList = companyRepo.findAll();
		seeding();
	}

	private void seeding() {
		companyList.get(0).setCustomers(Set.of(companyList.get(1), companyList.get(6), companyList.get(11),
				companyList.get(16), companyList.get(21), companyList.get(26), companyList.get(31)));
		companyList.get(1).setCustomers(Set.of(companyList.get(2), companyList.get(7), companyList.get(12),
				companyList.get(17), companyList.get(22), companyList.get(27), companyList.get(32)));
		companyList.stream().forEach(c -> {
			GenericDaoJpa.startTransaction();
			companyRepo.update(c);
			GenericDaoJpa.commitTransaction();
		});
	}

}
