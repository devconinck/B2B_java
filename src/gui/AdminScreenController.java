package gui;

import java.io.IOException;

import domain.AdminController;
import gui.login.LoginScreen;
import gui.payment.ProcessOrderController;
import gui.profile.ProfileUpdateScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminScreenController extends BorderPane {
    private AdminController controller;

    @FXML 
    private Button companiesButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button processButton;
    
    @FXML
    private Button logOutButton;
    
    @FXML 
    private Label titleLabel;
    
    @FXML
    private BorderPane mainScreen;
    
    public AdminScreenController(AdminController controller) {
        this.controller = controller;
        buildGui();
        loadWelcome();
    }

    private void loadWelcome() {
    	titleLabel.setText("");
		GridPane gp = new GridPane();
		Text welcome = new Text("Welcome, Admin");
		welcome.setFont(new Font(30));
		Text subTitle = new Text("You're in charge. Let's get to work.");
		
		gp.add(welcome, 0, 0);
		gp.add(subTitle, 0, 1);

		HBox box1 = createBox("/icons/companies_black.png", "Companies", "Manage the companies using your services.");
	    gp.add(box1, 0, 3);
	    box1.setOnMouseClicked(e -> {
	    CompaniesOverviewController companiesScreen = new CompaniesOverviewController(controller);
        	titleLabel.setText("Companies");
        	this.mainScreen.getChildren().clear();
        	this.mainScreen.setCenter(companiesScreen);
        });
	    HBox box2 = createBox("/icons/request_black.png", "Update Requests", "Review changes requested by clients.");
	    gp.add(box2, 0, 4);
	    box2.setOnMouseClicked(e -> { 
	        	ProfileUpdateScreenController updateScreen = new ProfileUpdateScreenController(controller);
	        	titleLabel.setText("Update Requests");
	        	this.mainScreen.getChildren().clear();
	        	this.mainScreen.setCenter(updateScreen); 
	        });
	    HBox box3 = createBox("/icons/process_black.png", "Process Payments", "Simulate payments being processed.");
	    gp.add(box3, 0, 5);
	    box3.setOnMouseClicked(e -> {
	            ProcessOrderController processScreen = new ProcessOrderController(controller);
	            titleLabel.setText("Process Orders");
	            this.mainScreen.getChildren().clear();
	        	this.mainScreen.setCenter(processScreen);
	        });
	        
	    GridPane.setHgrow(box1, Priority.ALWAYS);
	    GridPane.setHgrow(box2, Priority.ALWAYS);
	    GridPane.setHgrow(box3, Priority.ALWAYS);
	    //GridPane.setVgrow(box1, Priority.ALWAYS);
	    //GridPane.setVgrow(box2, Priority.ALWAYS);
	    //GridPane.setVgrow(box3, Priority.ALWAYS);
	    
	    gp.setVgap(20);
	    
	
	    //gp.setMaxSize(600, 600);
		
		this.mainScreen.setCenter(gp);
		this.mainScreen.setPadding(new Insets(35));
		
	}
    
    private HBox createBox(String url, String t, String d) {
		Image img = new Image(getClass().getResourceAsStream(url));
		ImageView icon = new ImageView(img);
	    icon.setFitWidth(50); // Set the width of the icon
	    icon.setFitHeight(50); // Set the height of the icon


	        // Create a VBox to hold the text content
	   VBox textContent = new VBox();
	    textContent.setSpacing(10);

	        // Create a label for the title
	        Text title = new Text(t);
	        title.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Set the font and weight

	        // Create a label for the description
	        Text description = new Text(d);
	        description.setFont(Font.font("Arial", 14));
	        description.setStyle("-fx-font-color: darkgrey");

	        // Add the labels to the VBox
	        textContent.getChildren().addAll(title, description);

	        // Add the icon and text content to a HBox
	        HBox content = new HBox(icon, textContent);
	        content.setSpacing(10);
	        HBox.setHgrow(textContent, Priority.ALWAYS);
	        content.setStyle("-fx-border-width: 2px; -fx-border-color: darkgrey; -fx-border-radius: 2em;   -fx-cursor: hand;");
	        content.setPadding(new Insets(15));
	        
	        return content;
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
        	titleLabel.setText("Companies");
        	this.mainScreen.getChildren().clear();
        	this.mainScreen.setCenter(companiesScreen);
        });
        
        updateButton.setOnMouseClicked(e -> { 
        	ProfileUpdateScreenController updateScreen = new ProfileUpdateScreenController(controller);
        	titleLabel.setText("Update Requests");
        	this.mainScreen.getChildren().clear();
        	this.mainScreen.setCenter(updateScreen); 
        });
        
        processButton.setOnMouseClicked(e -> {
            ProcessOrderController processScreen = new ProcessOrderController(controller);
            titleLabel.setText("Process Orders");
            this.mainScreen.getChildren().clear();
        	this.mainScreen.setCenter(processScreen);
        });
    }
   
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
