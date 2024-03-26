package gui;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import domain.AdminController;
import domain.Order;
import gui.company.CompanyDetailsScreenController;
import gui.company.CompanyFilterController;
import gui.company.CompanyScreenController;
import gui.company.ControlScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class CompaniesOverviewController extends GridPane {

	private CompanyScreenController companies;
	private CompanyDetailsScreenController companyDetails;
	private ControlScreenController controls;
	private CompanyFilterController filter;

	public CompaniesOverviewController(AdminController ac) {
		this.filter = new CompanyFilterController(ac.getCompanyList());
		this.companyDetails = new CompanyDetailsScreenController(ac);
		this.controls = new ControlScreenController(companyDetails);
		this.companies = new CompanyScreenController(ac.getCompanyList(), ac.getSelectedCompanyProperty());
         // Set up event handlers
        controls.getSaveButton().setOnAction(e -> companyDetails.persistCompany());
        controls.getClearButton().setOnAction(e -> companyDetails.clearAllFields());
        controls.getInactiveButton().setOnAction(e -> companyDetails.toggleIsActive());
		buildGui();
	}

	private void buildGui() {
		

		//linkerdeel scherm
		//filter + create company
		HBox filter_create = new HBox();
		filter_create.getStyleClass().add("button-container");
		HBox.setHgrow(filter, Priority.ALWAYS);
		Button createCompany = new Button();
		createCompany.setText("Create Company");
		createCompany.setOnMouseClicked(e -> companyDetails.clearAllFields());
		filter_create.getChildren().addAll(filter, createCompany);
		//list van companies
		VBox vLinks = new VBox();
		VBox.setVgrow(companies, Priority.ALWAYS);
		companies.setMinWidth(380);
		vLinks.setPadding(new Insets(20));
		vLinks.setMinSize(600, 400);
		vLinks.getChildren().addAll(filter_create, companies);
		companies.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		companies.setMinWidth(400);
		this.add(vLinks, 0, 0);
		this.getStylesheets().add("styles.css");
		companies.getStylesheets().add("/css/label/css");

		companies.getStyleClass().add("custom-table-view");
		vLinks.setAlignment(Pos.TOP_CENTER);
		GridPane.setHgrow(vLinks, Priority.ALWAYS);
		GridPane.setVgrow(vLinks, Priority.ALWAYS);
		


		VBox vRechts = new VBox();
		vRechts.setAlignment(Pos.TOP_CENTER);		
		vRechts.setPadding(new Insets(10));
		vRechts.setMinSize(400, 600);
		vRechts.getChildren().addAll(companyDetails, controls);
		this.add(vRechts, 3, 0);
		VBox.setVgrow(vRechts, Priority.ALWAYS);
		GridPane.setHgrow(vRechts, Priority.ALWAYS);
		GridPane.setVgrow(vRechts, Priority.ALWAYS);
	

		

		
		  
		 
	}


    public CompanyScreenController getCompanies() {
        return companies;
    }

    public CompanyDetailsScreenController getCompanyDetails() {
        return companyDetails;
    }
}