package testing;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import domain.Account;
import domain.Address;
import domain.Company;
import domain.Contact;
import util.PaymentOption;
import util.Role;

class AccountTest {

	@ParameterizedTest(name = "#{index} - run test with password = {0}")
	@MethodSource("validPasswordProvider")
	void test_password_valid(String password) {
		Company validCompany = new Company("US123456789", "company_logo_1.png",
				new Address("United States", "New York", "10001", "Broadway", "123"),
				new Contact("123456789", "email1@example.com"), "Fake Company Inc. 1", "Technology", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), LocalDate.now(), null, null);
		Assertions.assertDoesNotThrow(() -> new Account("valid.email@icloud.com", password, validCompany, Role.Supplier));
	}
	
	@ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
		Company validCompany = new Company("US123456789", "company_logo_1.png",
				new Address("United States", "New York", "10001", "Broadway", "123"),
				new Contact("123456789", "email1@example.com"), "Fake Company Inc. 1", "Technology", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), LocalDate.now(), null, null);
        Assertions.assertThrows(IllegalArgumentException.class , () -> new Account("valid.email@icloud.com", password, validCompany, Role.Supplier));
    }
	
	static Stream<String> validPasswordProvider() {
		return Stream.of(
                "AAAbbbccc@123",
                "Hello world$123",
                "A!@#&()–a1",               // test punctuation part 1
                "A[{}]:;',?/*a1",           // test punctuation part 2
                "A~$^+=<>a1",               // test symbols
                "0123456789$abcdefgAB",     // test 20 chars
                "123Aa$Aa",                 // test 8 chars
                "1234",
                "qwer"
        );
	}
	
	static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                " ",
                "",
                "123",
                "qwe"
         );                        
    }
}
