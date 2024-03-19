package util.seeding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import domain.Account;
import domain.Address;
import domain.Company;
import domain.Contact;
import domain.Order;
import repository.AccountDao;
import repository.AccountDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;
import util.PaymentOption;
import util.Role;

public class Seed {
	private AccountDao accountRepo;
	private GenericDao<Company> companyRepo;
	private GenericDaoJpa<Order> orderRepo;
	private List<Company> companyList = new ArrayList<>();

	public Seed() {
		setAccountRepo(new AccountDaoJpa());
		setCompanyRepo(new GenericDaoJpa<Company>(Company.class));
		setOrderRepo(new GenericDaoJpa<Order>(Order.class));
		run();
	}

	private void setOrderRepo(GenericDaoJpa<Order> mock) {
		this.orderRepo = mock;
	}

	private void setAccountRepo(AccountDao mock) {
		this.accountRepo = mock;
	}

	private void setCompanyRepo(GenericDao<Company> mock) {
		this.companyRepo = mock;
	}

	private void run() {
		addCompanies();
		addAdmins();
		addUsers();
		addSuppliersAndCustomers();
		addOrder();
		addCustomers();
	}

	private void addSuppliersAndCustomers() {
		List<Account> suppliersAndCustomers = new ArrayList<>();

	}

	private void addAdmins() {
		List<Account> adminAccounts = new ArrayList<>();
		adminAccounts.add(new Account("admin@email.com", "root", companyList.get(2), Role.Admin));
		adminAccounts.add(new Account("test@email.com", "password", companyList.get(1), Role.Admin));

		adminAccounts.stream().forEach(accountRepo::addAccount);
	}

	private void addUsers() {
		Account acc1 = new Account("Charles.leclerc@icloud.com", "Test123!", companyList.get(0), Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", "Root123!", companyList.get(0), Role.Customer);
		Stream.of(acc1, acc2).forEach(accountRepo::addAccount);
	}
	
	private void addOrder() {
		Order order1 = new Order("2", 12, companyList.get(0), "asdf", "asdf", "asdf", 
				 "asdf",  "asdf",  "asdf",  "asdf");
		Order order2 = new Order("2", 12, companyList.get(1), "sdfg", "sdfg", "sdfg", 
				 "sdgf",  "sdfg",  "sdfg",  "sdfg");
		companyList.get(0).setOrders(Set.of(order1));
		companyList.get(1).setOrders(Set.of(order2));
		List<Order> li = List.of(order1, order2);
		li.stream().forEach(o -> {
			GenericDaoJpa.startTransaction();
			orderRepo.insert(o);
			GenericDaoJpa.commitTransaction();
		});
	}
	
	private void addCustomers() {
		companyList.get(0).setCustomers(Set.of(companyList.get(1)));
		companyList.get(1).setCustomers(Set.of(companyList.get(2)));
		companyList.stream().forEach(c -> {
			GenericDaoJpa.startTransaction();
			companyRepo.update(c);
			GenericDaoJpa.commitTransaction();
		});
	}

	private void addCompanies() {

		companyList.add(new Company("US123456789", "company_logo_1.png",
				new Address("United States", "New York", "10001", "Broadway", "123"),
				new Contact("123456789", "email1@example.com"), "Fake Company Inc. 1", "Technology", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.PAYPAL), new Date(), null, null));
		companyList.add(new Company("CT987654321", "company_logo_2.png",
				new Address("Country2", "City2", "23456", "Street2", "2"),
				new Contact("987654321", "email2@example.com"), "Fake Company Inc. 2", "Finance", 1234567890L,
				List.of(PaymentOption.BANK_TRANSFER, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(
				new Company("CA345678901", "logo3.png", new Address("Canada", "Toronto", "M5V 2L7", "King St W", "789"),
						new Contact("345678901", "email3@example.com"), "Tech Solutions Ltd.", "Technology",
						1357924680L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("UK234567890", "logo4.png",
				new Address("United Kingdom", "London", "WC2N 5DU", "Trafalgar Square", "456"),
				new Contact("234567890", "email4@example.com"), "Financial Services Inc.", "Finance", 2468013579L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("DE456789012", "logo5.png",
				new Address("Germany", "Berlin", "10178", "Unter den Linden", "789"),
				new Contact("456789012", "email5@example.com"), "Software Solutions GmbH", "Technology", 3692581470L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("FR567890129", "logo6.png",
				new Address("France", "Paris", "75001", "Avenue des Champs-Élysées", "123"),
				new Contact("567890123", "email6@example.com"), "Fashion Trends SAS", "Retail", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(
				new Company("AS678901234", "logo7.png", new Address("Australia", "Sydney", "2000", "George St", "456"),
						new Contact("678901234", "email7@example.com"), "Energy Solutions Pty Ltd.", "Energy",
						7539518462L, List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(
				new Company("JP789012345", "logo8.png", new Address("Japan", "Tokyo", "100-0004", "Shibakoen", "789"),
						new Contact("789012345", "email8@example.com"), "Tech Innovations KK", "Technology",
						1592637480L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("BR890123456", "logo9.png",
				new Address("Brazil", "São Paulo", "01204-000", "Avenida Paulista", "456"),
				new Contact("890123456", "email9@example.com"), "Marketing Solutions Ltda.", "Marketing", 3692581470L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("SA901234567", "logo10.png",
				new Address("South Africa", "Johannesburg", "2001", "Simmonds St", "123"),
				new Contact("901234567", "email10@example.com"), "Logistics Services Pty Ltd.", "Logistics",
				9876543210L, List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		// 10
		companyList.add(
				new Company("ES109876543", "logo11.png", new Address("Spain", "Madrid", "28001", "Gran Vía", "789"),
						new Contact("109876543", "email11@example.com"), "Healthcare Solutions S.L.", "Healthcare",
						1234567890L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(
				new Company("IT210987654", "logo12.png", new Address("Italy", "Rome", "00187", "Via del Corso", "456"),
						new Contact("210987654", "email12@example.com"), "Fashion Trends Italia", "Retail", 2345678901L,
						List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("IN321098765", "logo13.png",
				new Address("India", "Mumbai", "400001", "Nariman Point", "123"),
				new Contact("321098765", "email13@example.com"), "Software Solutions Pvt. Ltd.", "Technology",
				3456789012L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(
				new Company("CH43210987", "logo14.png", new Address("China", "Shanghai", "200002", "Nanjing Rd", "789"),
						new Contact("432109876", "email14@example.com"), "International Trading Co.", "Trade",
						4567890123L, List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("RU543210987", "logo15.png",
				new Address("Russia", "Moscow", "101000", "Tverskaya St", "456"),
				new Contact("543210987", "email15@example.com"), "Tech Innovations LLC", "Technology", 5678901234L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("MX654321098", "logo16.png",
				new Address("Mexico", "Mexico City", "06010", "Paseo de la Reforma", "123"),
				new Contact("654321098", "email16@example.com"), "Transport Solutions S.A. de C.V.", "Transport",
				6789012345L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("CA765432109", "logo17.png",
				new Address("Canada", "Vancouver", "V6C 1H2", "Granville St", "789"),
				new Contact("765432109", "email17@example.com"), "Engineering Services Ltd.", "Engineering",
				7890123456L, List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("DE876543210", "logo18.png",
				new Address("Germany", "Munich", "80331", "Marienplatz", "456"),
				new Contact("876543210", "email18@example.com"), "Food Solutions GmbH", "Food", 8901234567L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("US987654321", "logo19.png",
				new Address("United States", "Los Angeles", "90001", "Hollywood Blvd", "123"),
				new Contact("987654321", "email19@example.com"), "Media Solutions Inc.", "Media", 9012345678L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("UK198765432", "logo20.png",
				new Address("United Kingdom", "Manchester", "M1 1FN", "Market St", "789"),
				new Contact("198765432", "email20@example.com"), "Retail Solutions Ltd.", "Retail", 1098765432L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		// 20
		companyList.add(new Company("SE201987654", "logo21.png",
				new Address("Sweden", "Stockholm", "111 29", "Drottninggatan", "123"),
				new Contact("201987654", "email21@example.com"), "Tech Solutions AB", "Technology", 2109876543L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("SW212345678", "logo22.png",
				new Address("Switzerland", "Zurich", "8001", "Bahnhofstrasse", "456"),
				new Contact("212345678", "email22@example.com"), "Finance Innovations AG", "Finance", 3210987654L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("NL223456789", "logo23.png",
				new Address("Netherlands", "Amsterdam", "1012 JS", "Dam Square", "789"),
				new Contact("223456789", "email23@example.com"), "Fashion Solutions BV", "Retail", 4321098765L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("SP234567890", "logo24.png",
				new Address("Singapore", "Singapore", "178882", "Marina Bay Sands", "123"),
				new Contact("234567890", "email24@example.com"), "International Trading Pte Ltd.", "Trade", 5432109876L,
				List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("SK245678901", "logo25.png",
				new Address("South Korea", "Seoul", "04555", "Myeong-dong", "456"),
				new Contact("245678901", "email25@example.com"), "Logistics Solutions Co. Ltd.", "Logistics",
				6543210987L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("AR256789012", "logo26.png",
				new Address("Argentina", "Buenos Aires", "C1002AAA", "Avenida 9 de Julio", "789"),
				new Contact("256789012", "email26@example.com"), "Transport Innovations S.A.", "Transport", 7654321098L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(
				new Company("DK267890123", "logo27.png", new Address("Denmark", "Copenhagen", "1123", "Nyhavn", "123"),
						new Contact("267890123", "email27@example.com"), "Engineering Innovations ApS", "Engineering",
						8765432109L, List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("NO278901234", "logo28.png",
				new Address("Norway", "Oslo", "0251", "Karl Johans gate", "456"),
				new Contact("278901234", "email28@example.com"), "Food Innovations AS", "Food", 9876543210L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("IN289012345", "logo29.png",
				new Address("India", "New Delhi", "110001", "Connaught Place", "789"),
				new Contact("289012345", "email29@example.com"), "Media Innovations Pvt. Ltd.", "Media", 1987654321L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("DE290123456", "logo30.png",
				new Address("Germany", "Hamburg", "20095", "Mönckebergstraße", "123"),
				new Contact("290123456", "email30@example.com"), "Retail Innovations GmbH", "Retail", 2019876543L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		// 30
		companyList.add(new Company("US301234567", "logo31.png",
				new Address("United States", "Chicago", "60603", "Magnificent Mile", "456"),
				new Contact("301234567", "email31@example.com"), "Tech Services Inc.", "Technology", 2123456789L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("UK312345678", "logo32.png",
				new Address("United Kingdom", "Edinburgh", "EH1 2NG", "Princes Street", "789"),
				new Contact("312345678", "email32@example.com"), "Finance Solutions Ltd.", "Finance", 2234567890L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("AS323456789", "logo33.png",
				new Address("Australia", "Melbourne", "3000", "Flinders St", "123"),
				new Contact("323456789", "email33@example.com"), "Fashion Innovations Pty Ltd.", "Retail", 2345678901L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("CA334567890", "logo34.png",
				new Address("Canada", "Calgary", "T2P 2V7", "Stephen Ave", "456"),
				new Contact("334567890", "email34@example.com"), "Software Innovations Ltd.", "Technology", 2456789012L,
				List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("BR345678901", "logo35.png",
				new Address("Brazil", "Rio de Janeiro", "20010-000", "Avenida Atlântica", "789"),
				new Contact("345678901", "email35@example.com"), "Transport Solutions Ltda.", "Transport", 2567890123L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("CH356789012", "logo36.png",
				new Address("China", "Beijing", "100006", "Wangfujing St", "123"),
				new Contact("356789012", "email36@example.com"), "Engineering Solutions Co. Ltd.", "Engineering",
				2678901234L, List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("FR367890123", "logo37.png",
				new Address("France", "Nice", "06000", "Promenade des Anglais", "456"),
				new Contact("367890123", "email37@example.com"), "Food Innovations SAS", "Food", 2789012345L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("RU378901234", "logo38.png",
				new Address("Russia", "St. Petersburg", "190000", "Nevsky Prospect", "789"),
				new Contact("378901234", "email38@example.com"), "Media Innovations LLC", "Media", 2890123456L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("IT390123456", "logo40.png",
				new Address("Italy", "Milan", "20121", "Via Monte Napoleone", "456"),
				new Contact("390123456", "email40@example.com"), "Retail Innovations Italia", "Retail", 3901234567L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(
				new Company("SP401234567", "logo41.png", new Address("Spain", "Barcelona", "08002", "La Rambla", "789"),
						new Contact("401234567", "email41@example.com"), "Tech Innovations S.L.", "Technology",
						4012345678L, List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		// 40
		companyList.add(new Company("CA412345678", "logo42.png",
				new Address("Canada", "Montreal", "H2X 1Y9", "Saint-Laurent Blvd", "123"),
				new Contact("412345678", "email42@example.com"), "Finance Innovations Inc.", "Finance", 4123456789L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(
				new Company("DE423456789", "logo43.png", new Address("Germany", "Frankfurt", "60313", "Zeil", "456"),
						new Contact("423456789", "email43@example.com"), "Fashion Innovations GmbH", "Retail",
						4234567890L, List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("US434567890", "logo44.png",
				new Address("United States", "Miami", "33132", "Biscayne Blvd", "789"),
				new Contact("434567890", "email44@example.com"), "Transport Innovations Inc.", "Transport", 4345678901L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("UK445678901", "logo45.png",
				new Address("United Kingdom", "Birmingham", "B2 4QA", "Bullring", "123"),
				new Contact("445678901", "email45@example.com"), "Engineering Innovations Ltd.", "Engineering",
				4456789012L, List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("FR456789012", "logo46.png",
				new Address("France", "Lyon", "69002", "Rue de la République", "456"),
				new Contact("456789012", "email46@example.com"), "Food Innovations SA", "Food", 4567890123L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("JP467890123", "logo47.png",
				new Address("Japan", "Osaka", "550-0014", "Shinsaibashi", "789"),
				new Contact("467890123", "email47@example.com"), "Media Innovations KK", "Media", 4678901234L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("AS478901234", "logo48.png",
				new Address("Australia", "Perth", "6000", "St Georges Terrace", "123"),
				new Contact("478901234", "email48@example.com"), "Retail Innovations Pty Ltd.", "Retail", 4789012345L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("BR489012345", "logo49.png",
				new Address("Brazil", "Brasília", "70000-000", "Esplanada dos Ministérios", "456"),
				new Contact("489012345", "email49@example.com"), "Tech Innovations Ltda.", "Technology", 4890123456L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("SA490123456", "logo50.png",
				new Address("South Africa", "Pretoria", "0001", "Church Square", "789"),
				new Contact("490123456", "email50@example.com"), "Finance Innovations Pty Ltd.", "Finance", 4901234567L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("CA501234567", "logo51.png",
				new Address("Canada", "Ottawa", "K1P 1J1", "Wellington St", "123"),
				new Contact("501234567", "email51@example.com"), "Fashion Innovations Inc.", "Retail", 5012345678L,
				List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		// 50
		companyList.add(new Company("DE512345678", "logo52.png",
				new Address("Germany", "Düsseldorf", "40213", "Königsallee", "456"),
				new Contact("512345678", "email52@example.com"), "Transport Innovations GmbH", "Transport", 5123456789L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("US523456789", "logo53.png",
				new Address("United States", "Seattle", "98101", "Pike Place", "789"),
				new Contact("523456789", "email53@example.com"), "Engineering Innovations Inc.", "Engineering",
				5234567890L, List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("UK534567890", "logo54.png",
				new Address("United Kingdom", "Glasgow", "G1 3SL", "Buchanan St", "123"),
				new Contact("534567890", "email54@example.com"), "Food Innovations Ltd.", "Food", 5345678901L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("FR545678901", "logo55.png",
				new Address("France", "Marseille", "13001", "La Canebière", "456"),
				new Contact("545678901", "email55@example.com"), "Media Innovations SAS", "Media", 5456789012L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(
				new Company("IT556789012", "logo56.png", new Address("Italy", "Naples", "80132", "Via Toledo", "789"),
						new Contact("556789012", "email56@example.com"), "Retail Innovations Italia", "Retail",
						5567890123L, List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("DE567890123", "logo57.png",
				new Address("Germany", "Cologne", "50667", "Hohe Straße", "123"),
				new Contact("567890123", "email57@example.com"), "Tech Innovations GmbH", "Technology", 5678901234L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("US578901234", "logo58.png",
				new Address("United States", "San Francisco", "94103", "Market St", "456"),
				new Contact("578901234", "email58@example.com"), "Finance Innovations Inc.", "Finance", 5789012345L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("UK589012345", "logo59.png",
				new Address("United Kingdom", "Liverpool", "L1 1JE", "Bold St", "789"),
				new Contact("589012345", "email59@example.com"), "Fashion Innovations Ltd.", "Retail", 5890123456L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(
				new Company("CA90123456L", "logo60.png", new Address("Canada", "Toronto", "M5B 1R7", "Yonge St", "123"),
						new Contact("590123456", "email60@example.com"), "Transport Innovations Inc.", "Transport",
						5901234567L, List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("DE601234567", "logo61.png",
				new Address("Germany", "Stuttgart", "70173", "Königstraße", "456"),
				new Contact("601234567", "email61@example.com"), "Engineering Innovations GmbH", "Engineering",
				6012345678L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		// 60
		companyList.add(new Company("US612345678", "logo62.png",
				new Address("United States", "Boston", "02116", "Newbury St", "789"),
				new Contact("612345678", "email62@example.com"), "Food Innovations Inc.", "Food", 6123456789L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("UK623456789", "logo63.png",
				new Address("United Kingdom", "Bristol", "BS1 4SB", "Park St", "123"),
				new Contact("623456789", "email63@example.com"), "Media Innovations Ltd.", "Media", 6234567890L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("AS634567890", "logo64.png",
				new Address("Australia", "Adelaide", "SA 5000", "Rundle Mall", "456"),
				new Contact("634567890", "email64@example.com"), "Retail Innovations Pty Ltd.", "Retail", 6345678901L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("BR645678901", "logo65.png",
				new Address("Brazil", "Salvador", "40010-045", "Av. Tancredo Neves", "789"),
				new Contact("645678901", "email65@example.com"), "Tech Innovations Ltda.", "Technology", 6456789012L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("SA656789012", "logo66.png",
				new Address("South Africa", "Durban", "4001", "Umgeni Rd", "123"),
				new Contact("656789012", "email66@example.com"), "Finance Innovations Pty Ltd.", "Finance", 6567890123L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("CA667890123", "logo67.png",
				new Address("Canada", "Vancouver", "V6B 6N9", "Granville Island", "456"),
				new Contact("667890123", "email67@example.com"), "Fashion Innovations Inc.", "Retail", 6678901234L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("DE678901234", "logo68.png",
				new Address("Germany", "Hannover", "30159", "Georgstraße", "789"),
				new Contact("678901234", "email68@example.com"), "Transport Innovations GmbH", "Transport", 6789012345L,
				List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("BE801234567", "logo81.png",
				new Address("Belgium", "Brussels", "1000", "Grand Place", "123"),
				new Contact("801234567", "email81@example.com"), "Engineering Innovations BVBA", "Engineering",
				8012345678L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("BE812345678", "logo82.png",
				new Address("Belgium", "Antwerp", "2000", "Grote Markt", "456"),
				new Contact("812345678", "email82@example.com"), "Food Innovations NV", "Food", 8123456789L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList
				.add(new Company("BE823456789", "logo83.png", new Address("Belgium", "Ghent", "9000", "Graslei", "789"),
						new Contact("823456789", "email83@example.com"), "Media Innovations BV", "Media", 8234567890L,
						List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		// 70
		companyList.add(
				new Company("BE834567890", "logo84.png", new Address("Belgium", "Bruges", "8000", "Burg Square", "123"),
						new Contact("834567890", "email84@example.com"), "Retail Innovations BVBA", "Retail",
						8345678901L, List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(
				new Company("BE845678901", "logo85.png", new Address("Belgium", "Leuven", "3000", "Oude Markt", "456"),
						new Contact("845678901", "email85@example.com"), "Tech Innovations NV", "Technology",
						8456789012L, List.of(PaymentOption.CREDIT_CARD, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("BE856789012", "logo86.png",
				new Address("Belgium", "Liege", "4000", "Place Saint-Lambert", "789"),
				new Contact("856789012", "email86@example.com"), "Finance Innovations SA", "Finance", 8567890123L,
				List.of(PaymentOption.PAYPAL, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList.add(new Company("BE867890123", "logo87.png",
				new Address("Belgium", "Namur", "5000", "Place d'Armes", "123"),
				new Contact("867890123", "email87@example.com"), "Transport Innovations NV", "Transport", 8678901234L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.STRIPE), new Date(), null, null));
		companyList.add(new Company("BE878901234", "logo88.png",
				new Address("Belgium", "Mechelen", "2800", "Grote Markt", "456"),
				new Contact("878901234", "email88@example.com"), "Engineering Innovations BV", "Engineering",
				8789012345L, List.of(PaymentOption.PAYPAL, PaymentOption.BITCOIN), new Date(), null, null));
		companyList.add(new Company("BE889012345", "logo89.png",
				new Address("Belgium", "Brussels", "1000", "Avenue Louise", "789"),
				new Contact("889012345", "email89@example.com"), "Food Innovations NV", "Food", 8890123456L,
				List.of(PaymentOption.CREDIT_CARD, PaymentOption.BANK_TRANSFER), new Date(), null, null));
		companyList
				.add(new Company("BE890123456", "logo90.png", new Address("Belgium", "Antwerp", "2000", "Meir", "123"),
						new Contact("890123456", "email90@example.com"), "Media Innovations BVBA", "Media", 8901234567L,
						List.of(PaymentOption.PAYPAL, PaymentOption.STRIPE), new Date(), null, null));

		companyList.stream().forEach(c -> {
			GenericDaoJpa.startTransaction();
			companyRepo.insert(c);
			GenericDaoJpa.commitTransaction();
		});
	}
}
