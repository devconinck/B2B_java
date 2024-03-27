package testing;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.Order;
import dto.CompanyDTO;
import org.junit.jupiter.api.Test;
import util.PaymentOption;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void testConstructor() {
        Address address = new Address("USA", "New York", "10001", "Broadway", "123");
        Contact contact = new Contact("+1 (123) 456-7890", "john@example.com");
        List<PaymentOption> paymentOptions = new ArrayList<>();
        paymentOptions.add(PaymentOption.BANK_TRANSFER);
        LocalDate customerStart = LocalDate.of(2023, 1, 1);
        Set<Order> orders = new HashSet<>();
        Set<Company> customers = new HashSet<>();

        Company company = new Company("US1234567890", "logo.png", address, contact, "Acme Inc.", "Technology",
                1234567890L, paymentOptions, customerStart, orders, customers);

        assertEquals("US1234567890", company.getVatNumber());
        assertEquals("logo.png", company.getLogo());
        assertEquals(address, company.getAddress());
        assertEquals(contact, company.getContact());
        assertEquals("Acme Inc.", company.getName());
        assertEquals("Technology", company.getSector());
        assertEquals(1234567890L, company.getBankAccountNr());
        assertEquals(paymentOptions, company.getPaymentOptions());
        assertEquals(customerStart, company.getCustomerStart());
        assertEquals(orders, company.getOrders());
        assertEquals(customers, company.getCustomers());
    }

    @Test
    void testConstructorWithCompanyDTO() {
        Set<Order> orders = new HashSet<>();
        Set<Company> customers = new HashSet<>();
        
        Company company = new Company("US1234567890", "logo.png", new Address("USA", "New York", "10001", "Broadway", "123"),
                new Contact("+1 (123) 456-7890", "john@example.com"), "Acme Inc.", "Technology", 1234567890L,
                List.of(PaymentOption.BANK_TRANSFER), LocalDate.of(2023, 1, 1), orders, customers);

        CompanyDTO companyDTO = new CompanyDTO(company);

        assertEquals("US1234567890", companyDTO.vatNumber());
        assertEquals("logo.png", companyDTO.logo());
        assertEquals(orders, companyDTO.orders());
        assertEquals("USA", companyDTO.country());
        assertEquals("New York", companyDTO.city());
        assertEquals("10001", companyDTO.zipcode());
        assertEquals("Broadway", companyDTO.street());
        assertEquals("123", companyDTO.number());
        assertEquals("+1 (123) 456-7890", companyDTO.phoneNumber());
        assertEquals("john@example.com", companyDTO.email());
        assertEquals(List.of(PaymentOption.BANK_TRANSFER), companyDTO.paymentOptions());
        assertEquals(LocalDate.of(2023, 1, 1), companyDTO.customerStart());
        assertEquals("Acme Inc.", companyDTO.name());
        assertEquals("Technology", companyDTO.sector());
        assertEquals(1234567890L, companyDTO.bankAccountNr());
        assertTrue(companyDTO.isActive());
        assertEquals(0, companyDTO.numberOfOpenOrders());
        assertEquals(customers, companyDTO.customers());
    }

    @Test
    void testSetVatNumberWithInvalidVatNumber() {
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> company.setVatNumber("InvalidVATNumber"));
    }

    @Test
    void testSetNameWithNullName() {
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> company.setName(null));
    }

    @Test
    void testSetNameWithEmptyName() {
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> company.setName(""));
    }

    @Test
    void testSetSectorWithNullSector() {
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> company.setSector(null));
    }

    @Test
    void testSetSectorWithEmptySector() {
        Company company = new Company();
        assertThrows(IllegalArgumentException.class, () -> company.setSector(""));
    }

    @Test
    void testSetCustomerStartWithInvalidDate() {
        Company company = new Company();
        assertThrows(DateTimeException.class, () -> company.setCustomerStart(LocalDate.of(2023, 13, 1)));
    }

    @Test
    void testGetAmountOfCustomers() {
        Company company = new Company();
        Set<Company> customers = new HashSet<>();

        Company customer1 = new Company();
        customer1.setVatNumber("VAT1");
        customer1.setName("Customer 1");

        Company customer2 = new Company();
        customer2.setVatNumber("VAT2");
        customer2.setName("Customer 2");

        customers.add(customer1);
        customers.add(customer2);

        company.setCustomers(customers);

        assertEquals(2, company.getAmountOfCustomers().get());
    }

    @Test
    void testToggleIsActive() {
        Company company = new Company();
        assertTrue(company.getIsActive());

        company.toggleIsActive();
        assertFalse(company.getIsActive());

        company.toggleIsActive();
        assertTrue(company.getIsActive());
    }
}