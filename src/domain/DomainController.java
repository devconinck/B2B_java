package domain;

import java.util.List;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomainController {
	private List<Company> companyList;
	private GenericDao<Company> companyRepo;
	private GenericDao<Address> addressRepo;
	private GenericDao<Contact> contactRepo;


	public DomainController() {
		setAddressRepo(new GenericDaoJpa<>(Address.class));
		setContactRepo(new GenericDaoJpa<>(Contact.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
	}

	public void setAddressRepo(GenericDao<Address> mock) {
		addressRepo = mock;
	}

	public void setCompanyRepo(GenericDao<Company> mock) {
		companyRepo = mock;
	}
	
	public void setContactRepo(GenericDao<Contact> mock) {
		contactRepo = mock;
	}
	
	// naam, sector, adres, aantal klanten, isActief
	// AANTAL KLANTEN NOG NIET CORRECT
	/*
	public List<String> giveCompanyList() {
		return getCompanyList().stream().map(null)
	
	}
	*/
	
	public List<Company> getCompanyList() {
		if (companyList == null) {
			companyList = companyRepo.findAll();
		}
		return companyList;
	}

	public void close() {
		GenericDaoJpa.closePersistency();
	}
}
