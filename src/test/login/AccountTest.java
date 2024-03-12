package test.login;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import domain.login.Account;
import domain.login.Role;

class AccountTest {

	@ParameterizedTest(name = "#{index} - run test with password = {0}")
	@MethodSource("validPasswordProvider")
	void test_password_valid(String password) {
		Assertions.assertDoesNotThrow(() -> new Account("valid.email@icloud.com", password, "BE0404754472", Role.Supplier));
	}
	
	@ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
        Assertions.assertThrows(IllegalArgumentException.class , () -> new Account("valid.email@icloud.com", password, "BE0404754472", Role.Supplier));
    }
	
	static Stream<String> validPasswordProvider() {
		return Stream.of(
                "AAAbbbccc@123",
                "Hello world$123",
                "A!@#&()–a1",               // test punctuation part 1
                "A[{}]:;',?/*a1",           // test punctuation part 2
                "A~$^+=<>a1",               // test symbols
                "0123456789$abcdefgAB",     // test 20 chars
                "123Aa$Aa"                  // test 8 chars
        );
	}
	
	static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                "12345678",                 // invalid, only digit
                "abcdefgh",                 // invalid, only lowercase
                "ABCDEFGH",                 // invalid, only uppercase
                "abc123$$$",                // invalid, at least one uppercase
                "ABC123$$$",                // invalid, at least one lowercase
                "ABC$$$$$$",                // invalid, at least one digit
                "java REGEX 123",           // invalid, at least one special character
                "java REGEX 123 %",         // invalid, % is not in the special character group []
                "________",                 // invalid
                "--------",                 // invalid
                " ",                        // empty
                "");                        // empty
    }
}
