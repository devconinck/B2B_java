package gui;

import java.io.IOException;

import domain.Controller;
import gui.login.LoginScreen;
import domain.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class AdminScreenController extends BorderPane {
	
	private AdminController controller;

    @FXML
    private Button companiesButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button logOutButton;

    @FXML
    private AnchorPane mainScreen;
    
    public AdminScreenController(AdminController controller) {
    	this.controller = controller;
    	buildGui();
    }
    
    private void buildGui() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("adminScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Application Screen");
			System.out.println(e.getMessage());
		}
		
		this.logOutButton.setOnMouseClicked(e -> logOut());
		
		companiesButton.setOnMouseClicked(e -> {
			CompaniesOverviewController companiesScreen = new CompaniesOverviewController(controller);
			this.mainScreen.getChildren().clear();
			this.mainScreen.getChildren().add(companiesScreen);
		});
		
		
		//logica om update requests scherm te tonen
		/*
		 * updateButton.setOnMouseClicked(e -> { this.controller.clearObservers();
		 * UpdateOverviewController updateScreen = new
		 * UpdateOverviewScreenController((AdminController) controller);
		 * this.mainScreen.getChildren().clear();
		 * this.mainScreen.getChildren().add(updateScreen); });
		 */
    }
   
    // ???
    private void logOut() {
        Stage currentStage = new Stage();
        LoginScreen login = new LoginScreen();
        currentStage.setScene(new Scene(login));
        currentStage.show();
        currentStage.close();

        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

}
