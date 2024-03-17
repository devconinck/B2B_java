package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import domain.Order;
import domain.OrderItem;


public class OrderTest {
	
	private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order("123", 1, "Customer1", "Reference1", "2024-03-17", "2024-03-18", "100", "10", "110", "USD");
    }

    // Test case for order ID
    @ParameterizedTest
    @MethodSource("orderIdProvider")
    public void testGetOrderId(String expectedOrderId) {
        assertEquals(expectedOrderId, order.getOrderId());
    }

    // MethodSource generator for order IDs
    static Stream<String> orderIdProvider() {
        return Stream.of("123", "456", "789", "ABC", "XYZ"); // Add more test cases if needed
    }

    // Test case for order ID property
    @ParameterizedTest
    @MethodSource("orderIdProvider")
    public void testGetOrderIDProperty(String expectedOrderId) {
        assertEquals(expectedOrderId, order.orderIDProperty().get());
    }

    // Test case for order name
    @ParameterizedTest
    @MethodSource("nameProvider")
    public void testGetName(String expectedName) {
        assertEquals(expectedName, order.getName());
    }

    // MethodSource generator for names
    static Stream<String> nameProvider() {
        return Stream.of("Temp", "Test", "Sample"); // Add more test cases if needed
    }

    // Test case for order date
    @ParameterizedTest
    @MethodSource("dateProvider")
    public void testGetDate(String expectedDate) {
        assertEquals(expectedDate, order.getDate());
    }

    // MethodSource generator for dates
    static Stream<String> dateProvider() {
        return Stream.of("2024-03-17", "2024-03-18", "2024-03-19"); // Add more test cases if needed
    }

    // Test case for order status
    @ParameterizedTest
    @MethodSource("statusProvider")
    public void testGetOrderStatus(String expectedStatus) {
        assertEquals(expectedStatus, order.getOrderStatus());
    }

    // MethodSource generator for order statuses
    static Stream<String> statusProvider() {
        return Stream.of("NOT PAID", "PAID", "RECEIVED", "PROCESSED", "DONE"); // Add more test cases if needed
    }

    // Test case for payment status
    @ParameterizedTest
    @MethodSource("paymentStatusProvider")
    public void testGetPaymentStatus(String expectedStatus) {
        assertEquals(expectedStatus, order.getPaymentStatus());
    }

    // MethodSource generator for payment statuses
    static Stream<String> paymentStatusProvider() {
        return Stream.of("RECEIVED", "PROCESSED", "DONE"); // Add more test cases if needed
    }

    // Test case for sync ID
    @ParameterizedTest
    @MethodSource("syncIdProvider")
    public void testGetSyncId(int expectedSyncId) {
        assertEquals(expectedSyncId, order.getSyncId());
    }

    // MethodSource generator for sync IDs
    static Stream<Integer> syncIdProvider() {
        return Stream.of(1, 2, 3); // Add more test cases if needed
    }

    // Test case for customer ID
    @ParameterizedTest
    @MethodSource("customerIdProvider")
    public void testGetCustomer(String expectedCustomerId) {
        assertEquals(expectedCustomerId, order.getCustomer());
    }

    // MethodSource generator for customer IDs
    static Stream<String> customerIdProvider() {
        return Stream.of("Customer1", "Customer2", "Customer3"); // Add more test cases if needed
    }

    // Test case for order reference
    @ParameterizedTest
    @MethodSource("orderReferenceProvider")
    public void testGetOrderReference(String expectedOrderReference) {
        assertEquals(expectedOrderReference, order.getOrderReference());
    }

    // MethodSource generator for order references
    static Stream<String> orderReferenceProvider() {
        return Stream.of("Reference1", "Reference2", "Reference3"); // Add more test cases if needed
    }

    // Test case for order date time
    @ParameterizedTest
    @MethodSource("orderDateTimeProvider")
    public void testGetOrderDateTime(String expectedOrderDateTime) {
        assertEquals(expectedOrderDateTime, order.getOrderDateTime());
    }

    // MethodSource generator for order date times
    static Stream<String> orderDateTimeProvider() {
        return Stream.of("2024-03-17", "2024-03-18", "2024-03-19"); // Add more test cases if needed
    }

    // Test case for last payment reminder
    @ParameterizedTest
    @MethodSource("lastPaymentReminderProvider")
    public void testGetLastPaymentReminder(String expectedLastPaymentReminder) {
        assertEquals(expectedLastPaymentReminder, order.getLastPaymentReminder());
    }

    // MethodSource generator for last payment reminders
    static Stream<String> lastPaymentReminderProvider() {
        return Stream.of("2024-03-18", "2024-03-19", "2024-03-20"); // Add more test cases if needed
    }

    // Test case for net amount
    @ParameterizedTest
    @MethodSource("netAmountProvider")
    public void testGetNetAmount(String expectedNetAmount) {
        assertEquals(expectedNetAmount, order.getNetAmount());
    }

    // MethodSource generator for net amounts
    static Stream<String> netAmountProvider() {
        return Stream.of("100", "200", "300"); // Add more test cases if needed
    }

    // Test case for tax amount
    @ParameterizedTest
    @MethodSource("taxAmountProvider")
    public void testGetTaxAmount(String expectedTaxAmount) {
        assertEquals(expectedTaxAmount, order.getTaxAmount());
    }

    // MethodSource generator for tax amounts
    static Stream<String> taxAmountProvider() {
        return Stream.of("10", "20", "30"); // Add more test cases if needed
    }

    // Test case for total amount
    @ParameterizedTest
    @MethodSource("totalAmountProvider")
    public void testGetTotalAmount(String expectedTotalAmount) {
        assertEquals(expectedTotalAmount, order.getTotalAmount());
    }

    // MethodSource generator for total amounts
    static Stream<String> totalAmountProvider() {
        return Stream.of("110", "220", "330"); // Add more test cases if needed
    }

    // Test case for currency
    @ParameterizedTest
    @MethodSource("currencyProvider")
    public void testGetCurrency(String expectedCurrency) {
        assertEquals(expectedCurrency, order.getCurrency());
    }

    // MethodSource generator for currencies
    static Stream<String> currencyProvider() {
        return Stream.of("USD", "EUR", "GBP"); // Add more test cases if needed
    }

    // Test case for order items
    @ParameterizedTest
    @MethodSource("orderItemsProvider")
    public void testGetOrderItems(List<OrderItem> expectedOrderItems) {
        order.setOrderItems(expectedOrderItems);
        assertEquals(expectedOrderItems, order.getOrderItems());
    }

    // MethodSource generator for order items
    static Stream<List<OrderItem>> orderItemsProvider() {
        // Create test order items
        OrderItem item1 = new OrderItem(123, 234, 1, "Product1", "10", "1", "10.0", "1500");
        OrderItem item2 = new OrderItem(321, 432, 1, "Product2", "20", "2", "20.0", "2500");
        OrderItem item3 = new OrderItem(987, 678, 1, "Product3", "30", "3", "30.0", "3500");

        return Stream.of(Arrays.asList(item1, item2, item3)); // Add more test cases if needed
    }
}
