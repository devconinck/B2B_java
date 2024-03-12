package main;

import java.io.IOException;

import domain.DomainController;
import gui.SupplierScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		DomainController dc = new DomainController();
		
		SupplierScreenController root = new SupplierScreenController(dc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("SDP2_G02");
		stage.show();
	}
}
