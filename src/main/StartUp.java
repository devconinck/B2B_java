package main;

import java.io.IOException;

import gui.LoginScreenController;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Seed;

public class StartUp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		new Seed();
		new LoginScreenController();
	}
}
