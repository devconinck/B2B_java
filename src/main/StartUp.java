package main;

import java.io.IOException;

import domain.DomainController;
import gui.AdminScreenController;
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

		AdminScreenController root = new AdminScreenController(dc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("SDP2_G16");
		stage.show();
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
