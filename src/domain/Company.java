package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import util.PaymentOption;

@Entity
@Access(AccessType.FIELD)
public class Company implements Serializable, B2BCompany {
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
    
    private List<Company> customerList;
    private List<Order> orderList;
	private Date customerStart;

	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty sector = new SimpleStringProperty();
	private SimpleLongProperty bankAccountNr = new SimpleLongProperty();
	private SimpleBooleanProperty isActive = new SimpleBooleanProperty(true);


	// Default constructor JPA
	protected Company() {

	}

	// Constructor
	public Company(String vatNumber, String logo, Address address, Contact contact, String name, String sector,
			Long bankAccountNr, List<PaymentOption> paymentOptions, Date customerStart) {
		setVatNumber(vatNumber);
		setLogo(logo);
		setAddress(address);
		setContact(contact);
		setName(name);
		setSector(sector);
		setBankAccountNr(bankAccountNr);
		setPaymentOptions(paymentOptions);
		setCustomerStart(customerStart);
		// isActive = true; overbodig doordat Initiele toestand bij attributen reeds
	}
	
	// Getters
	@Override
	public String getVatNumber() {
		return VatNumber;
	}
	
	@Override
	public String getLogo() {
		return logo;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public String getAddressString() {
		return address != null
				? address.toString()
				: "No address found.";
	}

	@Override
	public Contact getContact() {
		return contact;
	}

	@Override
	@Access(AccessType.PROPERTY)
	public String getName() {
		return name.get();
	}

	@Override
	@Access(AccessType.PROPERTY)
	public String getSector() {
		return sector.get();
	}

	@Override
	@Access(AccessType.PROPERTY)
	public Long getBankAccountNr() {
		return bankAccountNr.get();
	}
	
	@Override
	@Access(AccessType.PROPERTY)
	public boolean getIsActive() {
		return isActive.get();
	}
	
	@Override
	public List<PaymentOption> getPaymentOptions() {
		return paymentOptions;
	}

	@Override
	public Date getCustomerStart() {
		return customerStart;
	}
	
	// Property gettters:
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
	
	public SimpleIntegerProperty getAmountOfCustomers() {
		return new SimpleIntegerProperty(customerList.size());
	}
	
	// Setters
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

	public void setCustomerStart(Date customerStart) {
		this.customerStart = customerStart;
	}
	
	public void toggleIsActive() {
		isActive.set(!isActive.get());
	}

	// Noodzakelijk voor JPA
	@Override
	public int hashCode() {
		return Objects.hash(VatNumber);
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
		return VatNumber == other.VatNumber;
	}

}