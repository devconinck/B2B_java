package main;

import java.io.IOException;

import domain.DomainController;
import domain.login.LoginController;
import gui.LoginScreenController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartUp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException {		
		new LoginScreenController();
		
	}
}
