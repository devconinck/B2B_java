package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.DomainController;
import gui.AdminScreenController;
import gui.customer.OverviewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartUp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		//DomainController dc = new DomainController();
		
		//AdminScreenController root = new AdminScreenController(dc);
		
		OverviewController root = new OverviewController(createCompanies().get(0));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("SDP2_G16");
		stage.show();
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
