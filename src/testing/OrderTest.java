package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import domain.Address;
import domain.Company;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import dto.OrderDTO;
import util.OrderStatus;
import util.PaymentStatus;

public class OrderTest {

    @Test
    void testOrderConstructor() {
        Company fromCompany = new Company();
        Company toCompany = new Company();
        Set<OrderItem> orderItems = new HashSet<>();
        LocalDate orderDate = LocalDate.of(2023, 6, 10);

        Order order = new Order("O001", 1, fromCompany, toCompany, "REF001", orderDate, "Reminder", "100.00", "10.00", "110.00", "USD", orderItems);
        assertEquals("O001", order.getOrderID());
        assertEquals(fromCompany.getName(), order.getName());
        assertEquals(orderDate.toString(), order.getDate());
        assertNotNull(order.getOrderStatus());
        assertNotNull(order.getPaymentStatus());
        assertEquals(fromCompany, order.getFromCompany());
        assertEquals("REF001", order.getOrderReference());
        assertEquals(orderDate, order.getOrderDateTime());
        assertEquals("Reminder", order.getLastPaymentReminder());
        assertEquals("100.00", order.getNetAmount());
        assertEquals("10.00", order.getTaxAmount());
        assertEquals("110.00", order.getTotalAmount());
        assertEquals("USD", order.getCurrency());
        assertEquals(toCompany, order.getToCompany());
        assertEquals(orderItems, order.getOrderItems());
    }

    @Test
    void testSetOrderStatus() {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.COMPLETED);
        assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
    }

    @Test
    void testSetOrderStatusWithString() {
        Order order = new Order();
        order.setOrderStatus("SHIPPED");
        assertEquals(OrderStatus.SHIPPED, order.getOrderStatus());
    }

    @Test
    void testSetPaymentStatus() {
        Order order = new Order();
        order.setPaymentStatus(PaymentStatus.PAID);
        assertEquals(PaymentStatus.PAID, order.getPaymentStatus());
    }

    @Test
    void testSetPaymentStatusWithString() {
        Order order = new Order();
        order.setPaymentStatus("INVOICE_SENT");
        assertEquals(PaymentStatus.INVOICE_SENT, order.getPaymentStatus());
    }
}