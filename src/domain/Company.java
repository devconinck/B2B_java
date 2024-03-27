package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import dto.CompanyDTO;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import util.PaymentOption;

@Entity
@Access(AccessType.FIELD)
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String VatNumber;
	private String logo; // URL Nr Afbeelding

	@Embedded
	private Address address;

	@Embedded
	private Contact contact;

	// Niet volledig tevreden met het resultaat
	// Maar goed genoeg voorlopig
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<PaymentOption> paymentOptions;
	@ManyToMany
	@JoinTable(name = "company_known_companies", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "known_company_id"))
	private Set<Company> customers;
	@OneToMany(mappedBy = "fromCompany", cascade = CascadeType.ALL)
	private Set<Order> orders;
		
	private LocalDate customerStart;

	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty sector = new SimpleStringProperty();
	private SimpleLongProperty bankAccountNr = new SimpleLongProperty();
	private SimpleBooleanProperty isActive = new SimpleBooleanProperty(true);

	// Default constructor JPA
	protected Company() {
	};

	// Constructors
	public Company(String vatNumber, String logo, Address address, Contact contact, String name, String sector,
			Long bankAccountNr, List<PaymentOption> paymentOptions, LocalDate customerStart, Set<Order> orders,
			Set<Company> customers) {
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
		// isActive = true; overbodig doordat Initiele toestand bij attributen reeds
		setCustomers(customers);
	}

	public Company(CompanyDTO company) {
		this(company.vatNumber(), company.logo(),
				new Address(company.country(), company.city(), company.zipcode(), company.street(), company.number()),
				new Contact(company.phoneNumber(), company.email()), company.name(), company.sector(),
				company.bankAccountNr(), company.paymentOptions(), company.customerStart(), company.orders(),
				company.customers());
	}

	// Getters
	public Set<Company> getCustomers() {
		return customers;
	}
		
	public String getVatNumber() {
		return VatNumber;
	}

	public String getLogo() {
		return logo;
	}

	public Address getAddress() {
		return address;
	}

	public String getAddressString() {
		return address != null ? address.toString() : "No address found.";
	}

	public Contact getContact() {
		return contact;
	}

	@Access(AccessType.PROPERTY)
	public String getName() {
		return name.get();
	}

	@Access(AccessType.PROPERTY)
	public String getSector() {
		return sector.get();
	}

	@Access(AccessType.PROPERTY)
	public Long getBankAccountNr() {
		return bankAccountNr.get();
	}

	@Access(AccessType.PROPERTY)
	public boolean getIsActive() {
		return isActive.get();
	}

	public List<PaymentOption> getPaymentOptions() {
		return paymentOptions;
	}

	public LocalDate getCustomerStart() {
		return customerStart;
	}

	// Property gettters:
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
		return new SimpleStringProperty(getAddressString());
	}

	public SimpleBooleanProperty getIsActiveProperty() {
		return isActive;
	}


	// Setters
	public void setCustomers(Set<Company> customers) {
		this.customers = customers;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
		
	public void setVatNumber(String vatNumber) {
		this.VatNumber = vatNumber;
	}

	public void setIsActive(boolean isActive) {
		this.isActive.set(isActive);
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void setSector(String sector) {
		this.sector.set(sector);
	}

	public void setBankAccountNr(Long bankAccountNr) {
		this.bankAccountNr.set(bankAccountNr);
	}

	public void setPaymentOptions(List<PaymentOption> paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public void setCustomerStart(LocalDate customerStart) {
		this.customerStart = customerStart;
	}

	public void toggleIsActive() {
		isActive.set(!isActive.get());
	}

	// Noodzakelijk voor JPA

	public int hashCode() {
		return Objects.hash(VatNumber);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return VatNumber == other.VatNumber;
	}

	public Set<Order> getOrders() {
		return orders;
	}

}