package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	public ObservableList<Company> getCompanyList() {
		if (companyList == null) {
			//companyList = companyRepo.findAllDisplayData();
		
			companyList = new ArrayList<Company>();
			Address fakeAddress1 = new Address("United States", "New York", "10001", "Broadway", "123");
			Address fakeAddress2 = new Address("Country2", "City2", "23456", "Street2", "2");
			
			Contact fakeContact1 = new Contact("123456789", "email1@example.com");
			Contact fakeContact2 = new Contact("987654321", "email2@example.com");

			Company fakeCompany1 = new Company(123456789L, "company_logo_1.png", fakeAddress1, fakeContact1, "Fake Company Inc. 1", "Technology", 9876543210L, List.of("Credit Card", "PayPal"), new Date());
			Company fakeCompany2 = new Company(987654321L, "company_logo_2.png", fakeAddress2, fakeContact2, "Fake Company Inc. 2", "Finance", 1234567890L, List.of("Bank Transfer", "Bitcoin"), new Date());

			companyList.add(fakeCompany1);
			companyList.add(fakeCompany2);
		}
		return FXCollections.observableArrayList(companyList);
	}

	public void close() {
		GenericDaoJpa.closePersistency();
	}
}
