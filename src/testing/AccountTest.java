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
	
	@ParameterizedTest(name = "#{index} - Run test with invalid email = {0}")
    @MethodSource("invalidEmailProvider")
    void test_email_regex_invalid(String email) {
		Company validCompany = new Company("US123456789", "company_logo_1.png",
				new Address("United States", "New York", "10001", "Broadway", "123"),
				new Contact("123456789", "email1@example.com"), "Fake Company Inc. 1", "Technology", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), LocalDate.now(), null, null);
        Assertions.assertThrows(IllegalArgumentException.class , () -> new Account(email, "1234", validCompany, Role.Supplier));
    }
	
	@ParameterizedTest(name = "#{index} - run test with valid email = {0}")
	@MethodSource("validEmailProvider")
	void test_email_valid(String email) {
		Company validCompany = new Company("US123456789", "company_logo_1.png",
				new Address("United States", "New York", "10001", "Broadway", "123"),
				new Contact("123456789", "email1@example.com"), "Fake Company Inc. 1", "Technology", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), LocalDate.now(), null, null);
		Assertions.assertDoesNotThrow(() -> new Account(email, "1234", validCompany, Role.Supplier));
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
	
	static Stream<String> validEmailProvider() {
	    return Stream.of(
	            "user@example.com",
	            "user123@example.co.uk",
	            "user_name@example-domain.com",
	            "user!name@example.com",
	            "user.name@example.com",
	            "user%name@example.com",
	            "user*name@example.com",
	            "user+name@example.com",
	            "user=name@example.com",
	            "user?name@example.com",
	            "user`name@example.com",
	            "user|name@example.com",
	            "user~name@example.com",
	            "user^name@example.com",
	            "user.name@example-domain.com",
	            "user_name@example.com",
	            "user_name123@example.com"
	    );
	}

	static Stream<String> invalidEmailProvider() {
	    return Stream.of(                 // invalid, missing top-level domain
	            "user@",                        // invalid, missing domain
	            "@example.com",                 // invalid, missing local part                   // invalid, empty domain
	            "user@example.com@",            // invalid, special character before '@'
	            "user name@example.com",        // invalid, space in local part
	            "user@exa_mple.com",           // invalid, underscore in domain
	            "user@examp!le.com",            // invalid, special character in domain
	            "user@exa>mple.com",            // invalid, special character in domain
	            "user@exa(mple.com",            // invalid, special character in domain
	            "user@exa_mple.com",            // invalid, underscore in domain
	            "user@examp!le.com",            // invalid, special character in domain
	            "user@exa%mple.com",            // invalid, special character in domain
	            "user@exa&ample.com"            // invalid, special character in domain
	    );
	}
}
