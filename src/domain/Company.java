package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String VatNumber;
	private String logo; // URL Nr Afbeelding
							// https://stackoverflow.com/questions/76284097/how-do-i-set-an-imageview-in-javafx-to-have-a-url-of-an-image-on-the-internet

	@OneToOne
	// @JoinColumn(name = "address_id")
	@Embedded
	private Address address;

	@OneToOne
	// @JoinColumn(name = "contact_id")
	@Embedded
	private Contact contact;
	private List<String> paymentOptions; // Niet duidelijk welk type
	private LocalDate customerStart;

	// TableView Attributes + Gewone properties om serializable te zijn
	@Transient
	private SimpleStringProperty nameProperty = new SimpleStringProperty();
	private String name;

	@Transient
	private SimpleStringProperty sectorProperty = new SimpleStringProperty();
	private String sector;

	@Transient
	private SimpleLongProperty bankAccountNrProperty = new SimpleLongProperty();
	private Long bankAccountNr;

	@Transient
	private SimpleBooleanProperty isActiveProperty = new SimpleBooleanProperty(true);
	private Boolean isActive = true;

	// Default constructor JPA
	protected Company() {

	}

	// Constructor
	public Company(String vatNumber, String logo, Address address, Contact contact, String name, String sector,
			Long bankAccountNr, List<String> paymentOptions, LocalDate customerStart, List<Integer> orders) {
		setVatNumber(vatNumber);
		setLogo(logo);
		setAddressId(address);
		setContactId(contact);
		setName(name);
		setSector(sector);
		setBankAccountNr(bankAccountNr);
		setPaymentOptions(paymentOptions);
		setCustomerStart(customerStart);
		// isActive = true; overbodig doordat Initiele toestand bij attributen reeds
		// goed gezet wordt
	}

	public void setActive() {
		isActiveProperty.set(!isActiveProperty.get());
		isActive = !isActive;
	}

	// Property Getters
	// Geen idee of waarschuwing relevant is
	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public StringProperty getSectorProperty() {
		return sectorProperty;
	}

	public SimpleLongProperty getBankAccountNrProperty() {
		return bankAccountNrProperty;
	}

	public SimpleBooleanProperty getIsActiveProperty() {
		return isActiveProperty;
	}

	// Getters en setters toevoegen
	// SETTER VALIDATIE TOEVOEGEN
	public String getVatNumber() {
		return VatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.VatNumber = vatNumber;
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

	public SimpleStringProperty getAddressString() {
		return address != null
				? new SimpleStringProperty(address.getStreet() + ", " + address.getZipCode() + " " + address.getCity()
						+ ", " + address.getCountry())
				: new SimpleStringProperty("No address found.");
	}

	public void setAddressId(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContactId(Contact contact) {
		this.contact = contact;
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		this.nameProperty.set(name);
		this.name = name;
	}

	public String getSector() {
		return sectorProperty.get();
	}

	public void setSector(String sector) {
		this.sectorProperty.set(sector);
		this.sector = sector;
	}

	public Long getBankAccountNr() {
		return bankAccountNrProperty.get();
	}

	public void setBankAccountNr(Long bankAccountNr) {
		this.bankAccountNrProperty.set(bankAccountNr);
		this.bankAccountNr = bankAccountNr;
	}

	public List<String> getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(List<String> paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public Date getCustomerStart() {
		return customerStart;
	}

	public void setCustomerStart(LocalDate customerStart) {
		this.customerStart = customerStart;
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
