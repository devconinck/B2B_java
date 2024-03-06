package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import domain.AdminController;
import domain.Controller;
import domain.SupplierController;
import domain.login.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		primaryStage.setScene(scene);
		primaryStage.setTitle("SDP2_G16");
		addElements();
	}
	
	private void addElements() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(40,40,0,40));
		vbox.setBackground(new Background(new BackgroundFill(DEFAULTBACKGROUNDCOLOR, null, null)));
		vbox.setMaxWidth(500);
		vbox.setMaxHeight(500);
		
		try {
			Image img_delawarelogo = new Image(new FileInputStream("../images/delaware-logo.jpg"), 0.2, 0.2, false ,false);
			ImageView imgvw_delawarelogo = new ImageView(img_delawarelogo);
			imgvw_delawarelogo.setFitHeight(100);
			imgvw_delawarelogo.setPreserveRatio(true);
			imgvw_delawarelogo.setSmooth(true);
			imgvw_delawarelogo.setCache(true);
			StackPane imgvw_stackpane = new StackPane();
			imgvw_stackpane.getChildren().add(imgvw_delawarelogo);
			
			Label lbl_email = new Label("Email");
			Label lbl_password = new Label("Password");
			
			TextField txf_email = new TextField();
			txf_email.setPromptText("example.first@icloud.com");
			TextField txf_password = new TextField();
			
			VBox vbox_email = new VBox();
			vbox_email.getChildren().addAll(lbl_email, txf_email);
			vbox_email.setPadding(new Insets(40,0,10,0)); // Top 40 + Bottom 10
			
			VBox vbox_password = new VBox();
			vbox_password.getChildren().addAll(lbl_password, txf_password);
			vbox_password.setPadding(new Insets(0,0,30,0)); // Bottom 30
			
			Button btn_signup = new Button("Sign Up");
			Button btn_login = new Button("Login");
			btn_login.setOnAction(event -> login(txf_email.getText(), txf_password.getText()));
			
			HBox hbox_buttons = new HBox();
			hbox_buttons.setSpacing(vbox.getMaxWidth()/2);
			hbox_buttons.setAlignment(Pos.CENTER);
			hbox_buttons.getChildren().addAll(btn_signup, btn_login);
			
			vbox.getChildren().addAll(imgvw_stackpane, vbox_email, vbox_password, hbox_buttons);
			root.getChildren().add(vbox);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void login(String email, String password) {
		Controller controller = loginController.login(email, password);
		if (controller instanceof AdminController)
			System.out.println("Admin logged in");
		else if (controller instanceof SupplierController)
			System.out.println("Supplier logged in");
		else
			System.out.println("Login failed");
			
	}

}
