package main;

import java.io.IOException;

import domain.DomainController;
import gui.AdminScreenController;
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
		DomainController dc = new DomainController();
		
		AdminScreenController root = new AdminScreenController(dc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("SDP2_G16");
		stage.show();
	}
}
