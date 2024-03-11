package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Address;
import domain.Company;
import domain.Contact;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class Seed {
    
    private GenericDao<Company> companyRepo;
    private GenericDao<Address> addressRepo;
    private GenericDao<Contact> contactRepo;
    
    public Seed() {
        setAddressRepo(new GenericDaoJpa<>(Address.class));
        setContactRepo(new GenericDaoJpa<>(Contact.class));
        setCompanyRepo(new GenericDaoJpa<>(Company.class));
        run();
    }
    
    private void setCompanyRepo(GenericDaoJpa<Company> mock) {
        this.companyRepo = mock;
    }
    
    private void setAddressRepo(GenericDaoJpa<Address> mock) {
        this.addressRepo = mock;
    }
    
    private void setContactRepo(GenericDaoJpa<Contact> mock) {
        this.contactRepo = mock;
    }
    
    private void run() {
    	addCompanies();
    }

    private void addCompanies() {
        List<Company> companyList = new ArrayList<>();

        Address fakeAddress1 = new Address("United States", "New York", "10001", "Broadway", "123");
        Address fakeAddress2 = new Address("Country2", "City2", "23456", "Street2", "2");

        Contact fakeContact1 = new Contact("123456789", "email1@example.com");
        Contact fakeContact2 = new Contact("987654321", "email2@example.com");

        Company fakeCompany1 = new Company(123456789L, "company_logo_1.png", fakeAddress1, fakeContact1, "Fake Company Inc. 1", "Technology", 9876543210L, List.of("Credit Card", "PayPal"), new Date());
        Company fakeCompany2 = new Company(987654321L, "company_logo_2.png", fakeAddress2, fakeContact2, "Fake Company Inc. 2", "Finance", 1234567890L, List.of("Bank Transfer", "Bitcoin"), new Date());

        companyList.add(fakeCompany1);
        companyList.add(fakeCompany2);

        GenericDaoJpa.startTransaction();
        try {
            for (Company c : companyList) {
                if (!companyRepo.exists(c.getVatNumber())) {
                    companyRepo.insert(c);
                }
            }
            GenericDaoJpa.commitTransaction();
        } catch (Exception e) {
            GenericDaoJpa.rollbackTransaction();
            throw e;
        }
        
        //GenericDaoJpa.closePersistency();
    }
}
