package main;

import java.io.IOException;
<<<<<<< HEAD
import gui.LoginScreenController;
=======
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Address;
import domain.Company;
import domain.Contact;
>>>>>>> raadplegen-klanten
import domain.DomainController;
import gui.AdminScreenController;
import gui.customer.OverviewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Seed;

public class StartUp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		if (isDevelopmentEnvironment()) {
			initializeDevelopmentEnvironment(); // Seeden
		}		
		DomainController dc = new DomainController();
		
		new LoginScreenController(dc);
	}

	private boolean isDevelopmentEnvironment() {
		// Kijk of --dev is meegegeven bij de startup opties
		// https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.Parameters.html
		return getParameters().getRaw().contains("--dev");

	}

	private void initializeDevelopmentEnvironment() throws IOException {
		try {
			System.out.println("Seeding");
			new Seed();
			System.out.println("Seeding complete");
		} catch (Exception e) {
			System.out.println("Something went wrong");
			System.out.println(e.getMessage());
		}
	}
	
	private List<Company> createCompanies() {
		List<Company> companyList = new ArrayList<Company>();
		Address fakeAddress1 = new Address("United States", "New York", "10001", "Broadway", "123");
		Address fakeAddress2 = new Address("Country2", "City2", "23456", "Street2", "2");
		
		Contact fakeContact1 = new Contact("123456789", "email1@example.com");
		Contact fakeContact2 = new Contact("987654321", "email2@example.com");

		Company fakeCompany1 = new Company(123456789L, "company_logo_1.png", fakeAddress1, fakeContact1, "Fake Company Inc. 1", "Technology", 9876543210L, List.of("Credit Card", "PayPal"), new Date());
		Company fakeCompany2 = new Company(987654321L, "company_logo_2.png", fakeAddress2, fakeContact2, "Fake Company Inc. 2", "Finance", 1234567890L, List.of("Bank Transfer", "Bitcoin"), new Date());

		companyList.add(fakeCompany1);
		companyList.add(fakeCompany2);
		return companyList;
	}
}
