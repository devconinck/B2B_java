package gui.login;

import domain.AdminController;
import domain.Controller;
import domain.SupplierController;
import gui.AdminScreenController;
import gui.SupplierScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginScreen extends Pane {
	
	private StackPane root;
	private Stage primaryStage;
	private Scene scene;
	private LoginController loginController;
	private static final Color DEFAULTBACKGROUNDCOLOR = Color.WHITE; 
	private Label errormessage = new Label();

	public LoginScreen() {
		loginController = new LoginController();
		primaryStage = new Stage();
		root = new StackPane();
		scene = new Scene(root, 500, 500);
		setup();
		primaryStage.show();
	}
	
	private void setup() {
		primaryStage.setScene(scene);
		primaryStage.setTitle("SDP2_G02");
		addElements();
		scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
	}
	
	private void addElements() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(40,40,0,40));
		vbox.setBackground(new Background(new BackgroundFill(DEFAULTBACKGROUNDCOLOR, null, null)));
		vbox.setMaxWidth(500);
		vbox.setMaxHeight(500);
		
		try {
			Image img_delawarelogo = new Image("images/delaware-logo.jpg");
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
			//TODO make life easier
			txf_email.setText("Charles.leclerc@icloud.com");
			PasswordField pwf_password = new PasswordField();
			//TODO make life easier
			pwf_password.setText("Test123!");
			
			VBox vbox_email = new VBox();
			vbox_email.getChildren().addAll(lbl_email, txf_email);
			vbox_email.setPadding(new Insets(40,0,10,0)); // Top 40 + Bottom 10
			
			VBox vbox_password = new VBox();
			vbox_password.getChildren().addAll(lbl_password, pwf_password);
			vbox_password.setPadding(new Insets(0,0,30,0)); // Bottom 30
			
			Button btn_login = new Button("Login");
			btn_login.setOnAction(event -> login(txf_email.getText(), pwf_password.getText()));
			
			HBox hbox_buttons = new HBox();
			hbox_buttons.setAlignment(Pos.CENTER);
			hbox_buttons.getChildren().add(btn_login);
			hbox_buttons.setPadding(new Insets(0,0,30,0)); // Bottom 30
			
			vbox.getChildren().addAll(imgvw_stackpane, vbox_email, vbox_password, errormessage, hbox_buttons);
			
			root.addEventHandler(KeyEvent.KEY_PRESSED,  ev -> {
		        if (ev.getCode() == KeyCode.ENTER) {
		            btn_login.fire();
		            ev.consume(); 
		         }
		     });
			
			root.getChildren().add(vbox);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	private void login(String email, String password) {
		Controller controller = loginController.login(email, password);
		if (controller instanceof AdminController) {
			System.out.println("Admin logged in");
			Scene tempScene = new Scene(new AdminScreenController((AdminController) controller));
			primaryStage.setScene(tempScene);
			
			// WERKT NIET
			primaryStage.setMaximized(true);
			//primaryStage.setResizable(false);
			primaryStage.setMinHeight(820);
			primaryStage.setMinWidth(1260);

		}
		else if (controller instanceof SupplierController) {			
			System.out.println("Supplier logged in");
			Scene tempScene = new Scene(new SupplierScreenController((SupplierController) controller));
			primaryStage.setScene(tempScene);
			
			primaryStage.setMaximized(true);
			//primaryStage.setResizable(false);
			primaryStage.setMinHeight(820);
			primaryStage.setMinWidth(1460);
			
			//WERKT NIET => MAAR: logOut werkt hiermee wel omdat het niet full screen is 
			//primaryStage.setMaximized(true);
			//primaryStage.setResizable(false);
		}
		
		else
		{
			errormessage.setStyle("-fx-font-color: red;");
			errormessage.setText("Login Failed");
		}
		
			
	}

}
