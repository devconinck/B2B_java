package main;

import java.io.IOException;

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
		// new PaymentReminder(); // Stuurt 300 mails bij opstart
		
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
}
