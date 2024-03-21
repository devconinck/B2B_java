package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Company;
import domain.DomainController;
import gui.login.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import util.seeding.Seed;

public class StartUp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		if (isDevelopmentEnvironment()) {
			initializeDevelopmentEnvironment(); // Seeden
		}		
		// DomainController dc = new DomainController();
		new LoginScreen();
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

		return companyList;
	}
}
