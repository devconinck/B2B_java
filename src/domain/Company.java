package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@NamedQueries({
    @NamedQuery(name = "Company.findAll",
                         query = "select c from Company c")            
})
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long VatNumber;
	private String logo; // Pad naar logo bestand
	@OneToOne(mappedBy = "addressId")
	private int addressId;
	@OneToOne(mappedBy = "contactId")
	private int contactId;
	private List<String> paymentOptions; // Niet duidelijk welk type
	private Date customerStart;
	
	// TableView Attributes
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty sector = new SimpleStringProperty();
	private SimpleLongProperty bankAccountNr = new SimpleLongProperty();
	private SimpleBooleanProperty isActive = new SimpleBooleanProperty(true);
	
	// Default constructor JPA
	protected Company() {
		
	}
	
	// Constructor
	public Company(long vatNumber, String logo, int addressId, int contactId, String name, String sector, Long bankAccountNr, List<String> paymentOptions, Date customerStart) {
		setVatNumber(vatNumber);
		setLogo(logo);
		setAddressId(addressId);
		setContactId(contactId);
		setName(name);
		setSector(sector);
		setBankAccountNr(bankAccountNr);
		setPaymentOptions(paymentOptions);
		setCustomerStart(customerStart);
		// isActive = true; overbodig doordat Initiele toestand bij attributen reeds goed gezet wordt
	}
	
	
	public void setActive() {
		isActive.set(!isActive.get());
	}
	
	// Property Getters
	// Geen idee of waarschuwing relevant is
    public StringProperty nameProperty() {
        return name;
    }
    
    public StringProperty sectorProperty() {
        return sector;
    }
    
    public SimpleLongProperty bankAccountNrProperty() {
        return bankAccountNr;
    }
    
    public SimpleBooleanProperty isActiveProperty() {
        return isActive;
    }
	
	// Getters en setters toevoegen
	// SETTER VALIDATIE TOEVOEGEN
	public long getVatNumber() {
		return VatNumber;
	}
	
	public void setVatNumber(long vatNumber) {
		this.VatNumber = vatNumber;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getSector() {
		return sector.get();
	}

	public void setSector(String sector) {
		this.sector.set(sector);
	}

	public Long getBankAccountNr() {
		return bankAccountNr.get();
	}

	public void setBankAccountNr(Long bankAccountNr) {
		this.bankAccountNr.set(bankAccountNr);
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

	public void setCustomerStart(Date customerStart) {
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
