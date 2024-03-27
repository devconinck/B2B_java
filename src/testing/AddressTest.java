package testing;

import domain.Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testConstructor() {
        Address address = new Address("USA", "New York", "10001", "Broadway", "123");
        assertEquals("USA", address.getCountry());
        assertEquals("New York", address.getCity());
        assertEquals("10001", address.getZipCode());
        assertEquals("Broadway", address.getStreet());
        assertEquals("123", address.getNumber());
    }

    @Test
    void testSetCountry() {
        Address address = new Address();
        address.setCountry("Canada");
        assertEquals("Canada", address.getCountry());
    }

    @Test
    void testSetCountryNull() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setCountry(null));
    }

    @Test
    void testSetCountryEmpty() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setCountry(""));
    }

    @Test
    void testSetCity() {
        Address address = new Address();
        address.setCity("London");
        assertEquals("London", address.getCity());
    }

    @Test
    void testSetCityNull() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setCity(null));
    }

    @Test
    void testSetCityEmpty() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setCity(""));
    }

    @Test
    void testSetZipCode() {
        Address address = new Address();
        address.setZipCode("12345");
        assertEquals("12345", address.getZipCode());
    }

    @Test
    void testSetZipCodeNull() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setZipCode(null));
    }

    @Test
    void testSetZipCodeEmpty() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setZipCode(""));
    }

    @Test
    void testSetStreet() {
        Address address = new Address();
        address.setStreet("Main Street");
        assertEquals("Main Street", address.getStreet());
    }

    @Test
    void testSetStreetNull() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setStreet(null));
    }

    @Test
    void testSetStreetEmpty() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setStreet(""));
    }
    
    @Test
    void testSetNumberNull() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setNumber(null));
    }

    @Test
    void testSetNumberEmpty() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setNumber(""));
    }

    @Test
    void testSetNumberInvalid() {
        Address address = new Address();
        assertThrows(IllegalArgumentException.class, () -> address.setNumber("0123"));
    }

    @Test
    void testToString() {
        Address address = new Address("USA", "New York", "10001", "Broadway", "123");
        assertEquals("Broadway, 123, 10001, New York, USA", address.toString());
    }

    @Test
    void testEquals() {
        Address address1 = new Address("USA", "New York", "10001", "Broadway", "123");
        Address address2 = new Address("USA", "New York", "10001", "Broadway", "123");
        assertEquals(address1, address2);
    }

    @Test
    void testNotEquals() {
        Address address1 = new Address("USA", "New York", "10001", "Broadway", "123");
        Address address2 = new Address("Canada", "Toronto", "M5V 2T6", "Queen Street", "456");
        assertNotEquals(address1, address2);
    }
}