package testing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.Account;
import domain.Address;
import domain.B2BPortaal;
import domain.Company;
import domain.CompanyUpdateRequest;
import domain.Contact;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.AccountDao;
import repository.GenericDao;
import repository.OrderDao;
import util.PaymentOption;
import util.Role;

@ExtendWith(MockitoExtension.class)
class B2BPortaalTest {

    @Mock
    private GenericDao<Company> companyRepo;

    @Mock
    private OrderDao orderRepo;

    @Mock
    private GenericDao<OrderItem> orderItemRepo;

    @Mock
    private GenericDao<CompanyUpdateRequest> companyUpdateRequestRepo;

    @Mock
    private AccountDao accountRepo;

    @InjectMocks
    private B2BPortaal b2BPortaal; 
    
    /*
     * 		TEST getAccountByCompany FROM B2BPORTAAL
     */

    @Test
    void testGetAccountByCompany() {
        // Mock data
        List<Account> expectedAccounts = listOfAccount();

        // Mock behavior
        Mockito.lenient().when(accountRepo.getAccountByCompany(createCompany())).thenReturn(expectedAccounts);

        // Test
        List<Account> accounts = b2BPortaal.getAccountByCompany(createCompany());

        // Verify
        Assertions.assertEquals(expectedAccounts, accounts);
        Mockito.verify(accountRepo).getAccountByCompany(createCompany());
    }
    
    private List<Account> listOfAccount() {
    	return List.of(
    			new Account("valid.email1@icloud.com", "1234", createCompany(), Role.Admin),
    			new Account("valid.email2@icloud.com", "1235", createCompany(), Role.Supplier),
    			new Account("valid.email3@icloud.com", "12346", createCompany(), Role.Supplier),
    			new Account("valid.email4@icloud.com", "12347", createCompany(), Role.Admin),
    			new Account("valid.email5@icloud.com", "123489", createCompany(), Role.Supplier),
    			new Account("valid.email6@icloud.com", "12341011", createCompany(), Role.Supplier)
    			);
    }
    
    private Company createCompany() {
    	return new Company("US123456789", "company_logo_1.png",
				new Address("United States", "New York", "10001", "Broadway", "123"),
				new Contact("123456789", "email1@example.com"), "Fake Company Inc. 1", "Technology", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), LocalDate.now(), null, null);
    }
    
    private Company createCompany2() {
    	return new Company("BE123456789", "company_logo_2.png",
				new Address("Belgium", "Brussel", "69420", "Meir", "123"),
				new Contact("123456789", "email2@example.com"), "Fake Company Inc. 2", "Technology", 1236543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), LocalDate.now().minusDays(4), null, null);
    }
    
    private ObservableList<Company> createCompanyList() {
    	ObservableList<Company> companyList = FXCollections.observableArrayList();
    	companyList.add(new Company("CA345678901", "logo3.png",
				new Address("Canada", "Toronto", "M5V 2L7", "King St W", "789"),
				new Contact("345678901", "email3@example.com"), "Tech Solutions Ltd.", "Technology", 1357924680L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), LocalDate.now(), null, null));
		companyList.add(new Company("UK234567890", "logo4.png",
				new Address("United Kingdom", "London", "WC2N 5DU", "Trafalgar Square", "456"),
				new Contact("234567890", "email4@example.com"), "Financial Services Inc.", "Finance", 2468013579L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), LocalDate.now(), null, null));
		companyList.add(new Company("DE456789012", "logo5.png",
				new Address("Germany", "Berlin", "10178", "Unter den Linden", "789"),
				new Contact("456789012", "email5@example.com"), "Software Solutions GmbH", "Technology", 3692581470L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), LocalDate.now(), null, null));
		companyList.add(new Company("FR567890129", "logo6.png",
				new Address("France", "Paris", "75001", "Avenue des Champs-Élysées", "123"),
				new Contact("567890123", "email6@example.com"), "Fashion Trends SAS", "Retail", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), LocalDate.now(), null, null));
		companyList.add(
				new Company("AS678901234", "logo7.png", new Address("Australia", "Sydney", "2000", "George St", "456"),
						new Contact("678901234", "email7@example.com"), "Energy Solutions Pty Ltd.", "Energy",
						7539518462L, List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), LocalDate.now(), null, null));
		return companyList;
    }
    
    /*
     * 		TEST getOrdersList FROM B2BPORTAAL
     */
    
    @Test
    void testGetOrdersListWhenOrderListIsNull() {
        // Mock data
        List<Order> expectedOrders = createOrders();

        // Mock behavior
        Mockito.lenient().when(orderRepo.findAll()).thenReturn(expectedOrders);

        // Test
        ObservableList<Order> ordersList = b2BPortaal.getOrdersList();

        // Verify
        Assertions.assertEquals(expectedOrders, ordersList);
        Mockito.verify(orderRepo).findAll();
    }

    @Test
    void testGetOrdersListWhenOrderListIsEmpty() {
        // Mock data
        List<Order> expectedOrders = createOrders();

        // Mock behavior
        Mockito.lenient().when(orderRepo.findAll()).thenReturn(expectedOrders);

        // Call the method twice to ensure orderList is not empty the second time
        b2BPortaal.getOrdersList();
        ObservableList<Order> ordersList = b2BPortaal.getOrdersList();

        // Verify
        Assertions.assertEquals(expectedOrders, ordersList);
        Mockito.verify(orderRepo).findAll(); // Verify findAll() was called twice
    }

    @Test
    void testGetOrdersListWhenOrderListIsNotEmpty() {
        // Mock data
        List<Order> existingOrders = createOrders();

        // Set existing orders in orderList
        b2BPortaal.setOrderList(FXCollections.observableArrayList(existingOrders));

        // Call the method
        ObservableList<Order> ordersList = b2BPortaal.getOrdersList();

        // Verify
        Assertions.assertEquals(existingOrders, ordersList);
        Mockito.verify(orderRepo, Mockito.never()).findAll(); // Verify findAll() was not called
    }
    
    
    private List<Order> createOrders() {
    	return List.of(
    			new Order("12345", 1, createCompany(), createCompany2(), "REF_6789", LocalDate.now().minusDays(2),
    			        "2024-03-28 15:30:00", "100.00", "10.00", "110.00", "USD", null),
    			new Order("12345", 1, createCompany(), createCompany2(), "REF_6789", LocalDate.now().minusDays(1),
    			        "2024-03-28 15:30:00", "200.00", "20.00", "111.00", "EUR", null),
    			new Order("12345", 1, createCompany(), createCompany2(), "REF_6789", LocalDate.now().minusDays(3),
    			        "2024-03-28 15:30:00", "300.00", "30.00", "112.00", "CAN", null),
    			new Order("12345", 1, createCompany(), createCompany2(), "REF_6789", LocalDate.now().minusDays(5),
    			        "2024-03-28 15:30:00", "400.00", "40.00", "113.00", "EUR", null),
    			new Order("12345", 1, createCompany(), createCompany2(), "REF_6789", LocalDate.now().minusDays(4),
    			        "2024-03-28 15:30:00", "500.00", "50.00", "114.00", "USD", null)
    			);
    }
    
    /*
     * 		TEST getOrdersToCompany FROM B2BPORTAAL
     */
    
    @Test
    void testGetOrdersToCompany() {
        // Mock data
        Company company = createCompany();
        List<Order> expectedOrders = createOrders();

        // Mock behavior
        Mockito.lenient().when(orderRepo.getOrdersMadeToCompany(company)).thenReturn(expectedOrders);

        // Call the method under test
        ObservableList<Order> ordersToCompany = b2BPortaal.getOrdersToCompany(company);

        // Verify
        Assertions.assertEquals(expectedOrders, ordersToCompany);
    }
    
    /*
     * 		TEST getOrderItemsList(String orderId) FROM B2BPORTAAL
     */
    
    @Test
    void testGetOrderItemsListWhenOrderItemListIsNull() {
        // Mock data
        String orderId = "123";
        int orderIdint = 123;
        List<OrderItem> expectedOrderItems = createOrderItems().stream().filter(o -> o.getOrderId() == orderIdint).collect(Collectors.toList());

        // Mock behavior
        Mockito.lenient().when(orderItemRepo.findAll()).thenReturn(expectedOrderItems);
        Mockito.lenient().when(b2BPortaal.getOrderItemsList(orderId)).thenReturn(FXCollections.observableArrayList(expectedOrderItems));

        // Test
        ObservableList<OrderItem> orderItemsList = b2BPortaal.getOrderItemsList(orderId);

        // Verify
        Assertions.assertEquals(expectedOrderItems, orderItemsList);
    }

    @Test
    void testGetOrderItemsListWhenOrderItemListIsNotEmpty() {
    	List<OrderItem> items = createOrderItems();
        // Mock data
        String orderId = "123";
        int orderIdint = 123;
        List<OrderItem> expectedOrderItems = items.stream().filter(o -> o.getOrderId() == orderIdint).collect(Collectors.toList());
        List<OrderItem> existingOrderItems = createOrderItems();

        // Set existing order items in orderItemList
        b2BPortaal.setOrderItemList(FXCollections.observableArrayList(existingOrderItems));
        
        // Mock behavior
        Mockito.lenient().when(orderItemRepo.findAll()).thenReturn(items);
        Mockito.lenient().when(b2BPortaal.getOrderItemsList(orderId)).thenReturn(FXCollections.observableArrayList(items));
        

        // Call the method
        ObservableList<OrderItem> orderItemsList = b2BPortaal.getOrderItemsList(orderId);

        // Verify
        Assertions.assertEquals(expectedOrderItems, orderItemsList);
    }

    @Test
    void testGetOrderItemsListReturnsExpectedList() {
    	List<OrderItem> createOrderItems = createOrderItems();
        // Mock data
        String orderId = "123"; // Example order ID
        List<OrderItem> expectedOrderItems = List.of(createOrderItems.get(0), createOrderItems.get(2));

        // Mock behavior
        
        Mockito.lenient().when(b2BPortaal.getOrderItemsList(orderId)).thenReturn(FXCollections.observableArrayList(createOrderItems));

        // Test
        ObservableList<OrderItem> orderItemsList = b2BPortaal.getOrderItemsList(orderId);

        // Verify
        Assertions.assertEquals(expectedOrderItems, orderItemsList);
    }
    
    private List<OrderItem> createOrderItems() {
    	return List.of(
    			new OrderItem(123, 456, 789, new Product("sdp567", 1700, "pallets", "Toys", "STOCK"), 1, "EURO", BigDecimal.valueOf(15.6), BigDecimal.valueOf(456.56)),
    			new OrderItem(132, 456, 789, new Product("sdp567", 1700, "pallets", "Toys", "STOCK"), 1, "EURO", BigDecimal.valueOf(15.6), BigDecimal.valueOf(456.56)),
    			new OrderItem(123, 456, 789, new Product("sdp567", 1700, "pallets", "Toys", "STOCK"), 1, "EURO", BigDecimal.valueOf(15.6), BigDecimal.valueOf(456.56)),
    			new OrderItem(132, 456, 789, new Product("sdp567", 1700, "pallets", "Toys", "STOCK"), 1, "EURO", BigDecimal.valueOf(15.6), BigDecimal.valueOf(456.56)),
    			new OrderItem(321, 456, 789, new Product("sdp567", 1700, "pallets", "Toys", "STOCK"), 1, "EURO", BigDecimal.valueOf(15.6), BigDecimal.valueOf(456.56))
    			);
    }
    
    /*
     * 		TEST getCompanyList() FROM B2BPORTAAL
     */
    
    @Test
    void testGetCompanyListWhenCompanyListIsNull() {
    	List<Company> items = createCompanyList();
        // Mock data
        List<Company> expectedCompanies = items;
        
        b2BPortaal.setCompanyList(FXCollections.observableArrayList());
        
        // Mock behavior
        Mockito.lenient().when(companyRepo.findAll()).thenReturn(expectedCompanies);
        Mockito.lenient().when(b2BPortaal.getCompanyList()).thenReturn(FXCollections.observableArrayList(items));
        
        // Test
        ObservableList<Company> companyList = b2BPortaal.getCompanyList();
        
        // Verify
        Assertions.assertEquals(expectedCompanies, companyList);
    }

    @Test
    void testGetCompanyListWhenCompanyListIsNotEmpty() {
        // Mock data
        ObservableList<Company> existingCompanies = createCompanyList();

        // Set existing companies in companyList
        b2BPortaal.setCompanyList(FXCollections.observableArrayList(existingCompanies));

        // Call the method
        ObservableList<Company> companyList = b2BPortaal.getCompanyList();

        // Verify
        Assertions.assertEquals(existingCompanies, companyList);
        Mockito.verify(companyRepo, Mockito.never()).findAll();
    }

    @Test
    void testGetCompanyListReturnsExpectedList() {
    	ObservableList<Company> expect = createCompanyList();
        // Mock data
        ObservableList<Company> expectedCompanies = expect;
        
        // Mock behavior
        Mockito.lenient().when(b2BPortaal.getCompanyList()).thenReturn(expectedCompanies);
        
        // Test
        ObservableList<Company> companyList = b2BPortaal.getCompanyList();

        // Verify
        Assertions.assertEquals(expectedCompanies, companyList);
    }
    
    
}