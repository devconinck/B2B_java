package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import dto.CompanyDTO;
import jakarta.persistence.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import util.PaymentOption;
import util.Validation;

@Entity
@Access(AccessType.FIELD)
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vatNumber;
    private String logo;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<PaymentOption> paymentOptions;

    @ManyToMany
    @JoinTable(name = "company_known_companies", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "known_company_id"))
    private Set<Company> customers;

    @OneToMany(mappedBy = "fromCompany", cascade = CascadeType.ALL)
    private Set<Order> orders;
    
    @OneToMany(mappedBy = "fromCompany", cascade = CascadeType.ALL)
    private Set<Product> products;

    private LocalDate customerStart;

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty sector = new SimpleStringProperty();
    private SimpleLongProperty bankAccountNr = new SimpleLongProperty();
    private SimpleBooleanProperty isActive = new SimpleBooleanProperty(true);

    @Transient
    private SimpleStringProperty addressProperty = new SimpleStringProperty();

    //Voor testen
    public Company() {
    }

    // Constructors
    public Company(String vatNumber, String logo, Address address, Contact contact, String name, String sector,
                   Long bankAccountNr, List<PaymentOption> paymentOptions, LocalDate customerStart, Set<Order> orders,
                   Set<Company> customers, Set<Product> products) {
        setVatNumber(vatNumber);
        setLogo(logo);
        setAddress(address);
        setContact(contact);
        setName(name);
        setSector(sector);
        setBankAccountNr(bankAccountNr);
        setPaymentOptions(paymentOptions);
        setCustomerStart(customerStart);
        setOrders(orders);
        setCustomers(customers);
        updateAddressProperty();
        setProducts(products);
    }

    public Company(CompanyDTO company) {
        this(company.vatNumber(), company.logo(),
                new Address(company.country(), company.city(), company.zipcode(), company.street(), company.number()),
                new Contact(company.phoneNumber(), company.email()), company.name(), company.sector(),
                company.bankAccountNr(), company.paymentOptions(), company.customerStart(), company.orders(),
                company.customers(), company.products());
    }

    // Getters and setters
    
    public Set<Product> getProducts() {
        return products;
    }

    public Set<Company> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Company> customers) {
        this.customers = customers;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        if (vatNumber == null || !vatNumber.matches(Validation.VAT_REGEX)) {
            throw new IllegalArgumentException("Invalid VAT number");
        }
        this.vatNumber = vatNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Access(AccessType.PROPERTY)
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name.set(name.trim());
    }

    @Access(AccessType.PROPERTY)
    public String getSector() {
        return sector.get();
    }

    public void setSector(String sector) {
        if (sector == null || sector.trim().isEmpty()) {
            throw new IllegalArgumentException("Sector cannot be null or empty");
        }
        this.sector.set(sector.trim());
    }

    @Access(AccessType.PROPERTY)
    public Long getBankAccountNr() {
        return bankAccountNr.get();
    }

    public void setBankAccountNr(Long bankAccountNr) {
        if (bankAccountNr == null || !String.valueOf(bankAccountNr).matches(Validation.COMPANY_BANK_ACCOUNT)) {
            throw new IllegalArgumentException("Invalid bank account number");
        }
        this.bankAccountNr.set(bankAccountNr);
    }

    @Access(AccessType.PROPERTY)
    public boolean getIsActive() {
        return isActive.get();
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    public List<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public LocalDate getCustomerStart() {
        return customerStart;
    }

    public void setCustomerStart(LocalDate customerStart) {
        if (customerStart == null || !customerStart.toString().matches(Validation.DATE_REGEX)) {
            throw new IllegalArgumentException("Invalid customer start date");
        }
        this.customerStart = customerStart;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // Property getters

    public SimpleIntegerProperty getAmountOfCustomers() {
        return new SimpleIntegerProperty(customers.size());
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public SimpleStringProperty getSectorProperty() {
        return sector;
    }

    public SimpleStringProperty getAddressProperty() {
        addressProperty.set(getAddressString());
        return addressProperty;
    }

    public void updateAddressProperty() {
        this.addressProperty.set(getAddressString());
    }

    public SimpleBooleanProperty getIsActiveProperty() {
        return isActive;
    }

    // Utility methods

    private String getAddressString() {
        return address != null ? address.toString() : "No address found.";
    }

    public void toggleIsActive() {
        isActive.set(!isActive.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        return Objects.equals(vatNumber, other.vatNumber);
    }
}