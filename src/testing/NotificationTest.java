package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Company;
import domain.Notification;
import util.NotificationStatus;
import util.NotificationType;

public class NotificationTest {

    private Notification notification;
    private NotificationType notificationType;
    private LocalDate date;
    private String text;
    private String orderID;
    private NotificationStatus notificationStatus;
    private Company company;

    @BeforeEach
    public void setUp() {
        notificationType = NotificationType.ORDER_READY;
        date = LocalDate.now();
        text = "Order has been placed";
        orderID = "12345";
        notificationStatus = NotificationStatus.UNREAD;
        company = new Company();  // Assuming Company has a default constructor

        notification = new Notification(notificationType, date, text, orderID, notificationStatus, company);
    }

    @Test
    public void testNotificationCreation() {
        assertNotNull(notification);
        assertEquals(notificationType, notification.getNotificationType());
        assertEquals(date, notification.getDate());
        assertEquals(text, notification.getText());
        assertEquals(orderID, notification.getOrderID());
        assertEquals(notificationStatus, notification.getNotificationStatus());
        assertEquals(company, notification.getCompany());
    }

    @Test
    public void testEqualsAndHashCode() {
        Notification anotherNotification = new Notification(notificationType, date, text, orderID, notificationStatus, company);

        assertTrue(notification.equals(anotherNotification));
        assertEquals(notification.hashCode(), anotherNotification.hashCode());
    }
}

