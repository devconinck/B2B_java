package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CompaniesOverviewController extends GridPane {
	
	//private AdminController dc;
	private DomainController dc;
	
	private CompanyScreenController companies;
	private CompanyDetailsScreenController companyDetails;
	private ControlScreenController controls;
	
	public CompaniesOverviewController(DomainController dc) {
		this.dc = dc;
		
		this.companies = new CompanyScreenController(dc);
		this.companyDetails = new CompanyDetailsScreenController(dc);
		this.controls = new ControlScreenController(dc);
		
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
		
		this.add(companies, 0, 0);
		
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER); 
		this.add(vBox, 1, 0);
		vBox.getChildren().addAll(companyDetails, controls);
	}

}
