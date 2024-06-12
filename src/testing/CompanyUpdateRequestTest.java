package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Address;
import domain.CompanyUpdateRequest;
import domain.Contact;
import util.PaymentOption;

public class CompanyUpdateRequestTest {

    private CompanyUpdateRequest companyUpdateRequest;
    private String oldVatNumber;
    private String newVatNumber;
    private LocalDate requestDate;
    private String newName;
    private String newSector;
    private Long newBankAccountNr;
    private LocalDate newCustomerStart;
    private String newSupplierEmail;
    private String newSupplierPassword;
    private String newCustomerEmail;
    private String newCustomerPassword;
    private Address newAddress;
    private Contact newContact;
    private List<PaymentOption> newPaymentOptions;
    private String newLogo;

    @BeforeEach
    public void setUp() {
        oldVatNumber = "123456789";
        newVatNumber = "987654321";
        requestDate = LocalDate.now();
        newName = "New Company Name";
        newSector = "Technology";
        newBankAccountNr = 1234567890L;
        newCustomerStart = LocalDate.of(2023, 1, 1);
        newSupplierEmail = "supplier@example.com";
        newSupplierPassword = "supplierPass";
        newCustomerEmail = "customer@example.com";
        newCustomerPassword = "customerPass";
        newAddress = new Address("USA", "New York", "10001", "Broadway", "123");
        newContact = new Contact("+1 (123) 456-7890", "john@example.com");
        newPaymentOptions = Arrays.asList(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL);
        newLogo = "logo.png";

        companyUpdateRequest = new CompanyUpdateRequest(
            oldVatNumber, newVatNumber, requestDate, newName, newSector, newBankAccountNr,
            newCustomerStart, newSupplierEmail, newSupplierPassword, newCustomerEmail,
            newCustomerPassword, newAddress, newContact, newPaymentOptions, newLogo
        );
    }

    @Test
    public void testCompanyUpdateRequestCreation() {
        assertNotNull(companyUpdateRequest);
        assertEquals(oldVatNumber, companyUpdateRequest.getOldVatNumber());
        assertEquals(newVatNumber, companyUpdateRequest.getNewVatNumber());
        assertEquals(requestDate, companyUpdateRequest.getRequestDate());
        assertEquals(newName, companyUpdateRequest.getNewName());
        assertEquals(newSector, companyUpdateRequest.getNewSector());
        assertEquals(newBankAccountNr, companyUpdateRequest.getNewBankAccountNr());
        assertEquals(newCustomerStart, companyUpdateRequest.getNewCustomerStart());
        assertEquals(newSupplierEmail, companyUpdateRequest.getSupplierEmail());
        assertEquals(newSupplierPassword, companyUpdateRequest.getSupplierPassword());
        assertEquals(newCustomerEmail, companyUpdateRequest.getCustomerEmail());
        assertEquals(newCustomerPassword, companyUpdateRequest.getCustomerPassword());
        assertEquals(newAddress, companyUpdateRequest.getNewAddress());
        assertEquals(newContact, companyUpdateRequest.getNewContact());
        assertEquals(newPaymentOptions, companyUpdateRequest.getNewPaymentOptions());
        assertEquals(newLogo, companyUpdateRequest.getNewLogo());
    }
}
