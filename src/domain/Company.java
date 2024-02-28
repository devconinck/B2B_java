package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// QUERIES TOEVOEGEN
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long VatNumber;
	private String logo; // Pad naar logo bestand
	private int addressId;
	private int contactId;
	private String name;
	private String sector;
	private Long bankAccountNr;
	private List<String> paymentOptions; // Niet duidelijk welk type
	private Date customerStart;
	
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Long getBankAccountNr() {
		return bankAccountNr;
	}

	public void setBankAccountNr(Long bankAccountNr) {
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
