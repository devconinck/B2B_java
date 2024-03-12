package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CompaniesOverviewController extends GridPane {
	
	//private AdminController dc;
	private DomainController dc;
	
	private CompanyScreenController companies;
	private CompanyDetailsScreenController companyDetails;
	private ControlScreenController controls;
    private FilterController filter;
	
	public CompaniesOverviewController(DomainController dc) {
		this.dc = dc;
		
		this.filter = new FilterController(dc.getCompanyList());
		this.companyDetails = new CompanyDetailsScreenController(dc);
		this.controls = new ControlScreenController(companyDetails);
		this.companies = new CompanyScreenController(dc, filter, companyDetails, controls);
		buildGui();
	}
	
	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("companiesOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Employees Screen");
			System.out.println(e.getMessage());
		}
		
		
		Button createCompany = new Button();
		createCompany.setText("Create Company");
		createCompany.setOnMouseClicked(e -> companyDetails.clearAllFields());
		HBox hBox1 = new HBox();
		hBox1.getChildren().addAll(filter, createCompany);
		
		VBox vBox1 = new VBox();
		vBox1.setAlignment(Pos.CENTER); 
		this.add(vBox1, 0, 0);
		vBox1.getChildren().addAll(hBox1, companies);
		
		VBox vBox2 = new VBox();
		vBox2.setAlignment(Pos.CENTER); 
		this.add(vBox2, 1, 0);
		vBox2.getChildren().addAll(companyDetails, controls);
	}

}
