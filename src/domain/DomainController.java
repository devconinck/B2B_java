package domain;

import java.util.List;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomainController {
	private List<Company> companyList;
	private GenericDao<Address> addressRepo;
	private GenericDao<Contact> contactRepo;


	public DomainController() {
		setAddressRepo(new GenericDaoJpa<>(Address.class));
		setContactRepo(new GenericDaoJpa<>(Contact.class));
	}

	public void setAddressRepo(GenericDao<Address> mock) {
		addressRepo = mock;
	}

	public void setContactRepo(GenericDao<Contact> mock) {
		contactRepo = mock;
	}


	public void close() {
		GenericDaoJpa.closePersistency();
	}
}
