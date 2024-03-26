package domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import util.PaymentOption;

@Entity
@Table(name = "company_update_requests")
public class CompanyUpdateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oldVatNumber;
    private String newVatNumber;

    private LocalDate requestDate;
    private String newName;
    private String newSector;
    private Long newBankAccountNr;
	private LocalDate newCustomerStart;
	
	private Account supplierAccount;
	private Account customerAccount;
    
	@Embedded
	private Address newAddress;

	@Embedded
	private Contact newContact;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<PaymentOption> newPaymentOptions;
	
    private String newLogo;
    
    protected CompanyUpdateRequest() {
    	
    }

	public CompanyUpdateRequest(String oldVatNumber, String newVatNumber, LocalDate requestDate, String newName, String newSector,
			Long newBankAccountNr, LocalDate newCustomerStart, Account supplierAccount, Account customerAccount,
			Address newAddress, Contact newContact, List<PaymentOption> newPaymentOptions, String newLogo) {
		this.oldVatNumber = oldVatNumber;
		this.newVatNumber = newVatNumber;
		this.requestDate = requestDate;
		this.newName = newName;
		this.newSector = newSector;
		this.newBankAccountNr = newBankAccountNr;
		this.newCustomerStart = newCustomerStart;
		this.supplierAccount = supplierAccount;
		this.customerAccount = customerAccount;
		this.newAddress = newAddress;
		this.newContact = newContact;
		this.newPaymentOptions = newPaymentOptions;
		this.newLogo = newLogo;
	}

	public String getOldVatNumber() {
		return oldVatNumber;
	}

	public String getNewVatNumber() {
		return newVatNumber;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public String getNewName() {
		return newName;
	}

	public String getNewSector() {
		return newSector;
	}

	public Long getNewBankAccountNr() {
		return newBankAccountNr;
	}

	public LocalDate getNewCustomerStart() {
		return newCustomerStart;
	}

	public Account getSupplierAccount() {
		return supplierAccount;
	}

	public Account getCustomerAccount() {
		return customerAccount;
	}

	public Address getNewAddress() {
		return newAddress;
	}

	public Contact getNewContact() {
		return newContact;
	}

	public List<PaymentOption> getNewPaymentOptions() {
		return newPaymentOptions;
	}

	public String getNewLogo() {
		return newLogo;
	}
	
}