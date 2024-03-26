package gui;

import domain.AdminController;
import domain.Company;
import gui.company.CompanyDetailsScreenController;
import gui.company.CompanyFilterController;
import gui.company.CompanyScreenController;
import gui.company.ControlScreenController;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CompaniesOverviewController extends GridPane {
    private CompanyScreenController companies;
    private CompanyDetailsScreenController companyDetails;
    private ControlScreenController controls;
    private CompanyFilterController filter;

    public CompaniesOverviewController(ObservableList<Company> companyList, ObjectProperty<Company> selectedCompanyProperty, AdminController adminController) {
        this.filter = new CompanyFilterController(companyList);
        this.companyDetails = new CompanyDetailsScreenController(adminController, selectedCompanyProperty);
        this.controls = new ControlScreenController();
        this.companies = new CompanyScreenController(companyList, selectedCompanyProperty);
        
        // Set up event handlers
        controls.getSaveButton().setOnAction(e -> companyDetails.persistCompany());
        controls.getClearButton().setOnAction(e -> companyDetails.clearAllFields());
        controls.getInactiveButton().setOnAction(e -> companyDetails.toggleIsActive());
        
        buildGui();
    }

    private void buildGui() {
        Button createCompany = new Button();
        createCompany.setText("Create Company");
        createCompany.setOnMouseClicked(e -> companyDetails.clearAllFields());

        HBox hBox1 = new HBox();
        HBox.setHgrow(filter, Priority.ALWAYS);
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.getChildren().addAll(filter, createCompany);

        VBox vBox1 = new VBox();
        VBox.setVgrow(companies, Priority.ALWAYS);
        vBox1.setPadding(new Insets(20));
        this.add(vBox1, 0, 0);
        vBox1.getChildren().addAll(hBox1, companies);

        VBox vBox2 = new VBox();
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setPadding(new Insets(20));
        this.add(vBox2, 1, 0);
        vBox2.getChildren().addAll(companyDetails, controls);
    }

    public CompanyScreenController getCompanies() {
        return companies;
    }

    public CompanyDetailsScreenController getCompanyDetails() {
        return companyDetails;
    }
}