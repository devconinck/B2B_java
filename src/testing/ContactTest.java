package testing;

import domain.Contact;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void testConstructor() {
        Contact contact = new Contact("+1 (123) 456-7890", "john@example.com");
        assertEquals("+1 (123) 456-7890", contact.getPhoneNumber());
        assertEquals("john@example.com", contact.getEmail());
    }

    @Test
    void testSetEmail() {
        Contact contact = new Contact();
        contact.setEmail("jane@example.com");
        assertEquals("jane@example.com", contact.getEmail());
    }

    @Test
    void testSetEmailNull() {
        Contact contact = new Contact();
        assertThrows(NullPointerException.class, () -> contact.setEmail(null));
    }

    @Test
    void testSetEmailEmpty() {
        Contact contact = new Contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setEmail(""));
    }

    @Test
    void testSetEmailInvalid() {
        Contact contact = new Contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setEmail("invalid-email"));
    }

    @Test
    void testSetPhoneNumber() {
        Contact contact = new Contact();
        contact.setPhoneNumber("+1 (987) 654-3210");
        assertEquals("+1 (987) 654-3210", contact.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumberNull() {
        Contact contact = new Contact();
        assertThrows(NullPointerException.class, () -> contact.setPhoneNumber(null));
    }

    @Test
    void testSetPhoneNumberEmpty() {
        Contact contact = new Contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber(""));
    }

    @Test
    void testSetPhoneNumberInvalid() {
        Contact contact = new Contact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber("invalid-number"));
    }

    @Test
    void testEquals() {
        Contact contact1 = new Contact("+1 (123) 456-7890", "john@example.com");
        Contact contact2 = new Contact("+1 (123) 456-7890", "john@example.com");
        assertEquals(contact1, contact2);
    }

    @Test
    void testNotEquals() {
        Contact contact1 = new Contact("+1 (123) 456-7890", "john@example.com");
        Contact contact2 = new Contact("+1 (987) 654-3210", "jane@example.com");
        assertNotEquals(contact1, contact2);
    }
}