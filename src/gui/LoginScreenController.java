package gui;

import domain.login.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginScreenController {
	
	private StackPane root;
	private Stage primaryStage;
	private Scene scene;
	private LoginController loginController;
	private static final Color DEFAULTBACKGROUNDCOLOR = Color.WHITE; 

	public LoginScreenController() {
		loginController = new LoginController();
		primaryStage = new Stage();
		root = new StackPane();
		scene = new Scene(root, 500, 500);
		setup();
		primaryStage.show();
	}
	
	private void setup() {
		scene.setFill(DEFAULTBACKGROUNDCOLOR);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SDP2_G16");
		addTextFields();
	}
	
	private void addTextFields() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(40));
		vbox.setAlignment(Pos.CENTER);
		
		Label lbl_email = new Label("Email");
		lbl_email.setAlignment(Pos.BASELINE_LEFT);
		Label lbl_password = new Label("Password");
		
		TextField txf_email = new TextField();
		txf_email.setPromptText("example.first@icloud.com");
		TextField txf_password = new TextField();
		
		vbox.getChildren().addAll(lbl_email, txf_email, lbl_password, txf_password);
		root.getChildren().add(vbox);		
	}

}
