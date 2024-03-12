package gui;

import java.io.IOException;
import domain.Company;
import domain.DomainController;
import domain.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CompanyScreenController extends TableView<Company> implements Observer{

    @FXML
    private TableColumn<Company, String> nameCol;
    @FXML
    private TableColumn<Company, String> sectorCol;
    @FXML
    private TableColumn<Company, String> addressCol;
    @FXML
    private TableColumn<Company, String> isActiveCol;

    private final DomainController dc;
    private final FilterController filter;
    private CompanyDetailsScreenController companyDetails;
    private ControlScreenController controls;

    public CompanyScreenController(DomainController dc, FilterController filter, CompanyDetailsScreenController companyDetails, ControlScreenController controls) {
        this.dc = dc;
        this.filter = filter;
        this.companyDetails = companyDetails;
        this.controls = controls;
        this.dc.addObserver(this);
        buildGui();
        loadCompanies();
    }

    private void buildGui() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("companyScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void loadCompanies() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        sectorCol.setCellValueFactory(cellData -> cellData.getValue().getSectorProperty());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().getAddressString());
        isActiveCol.setCellValueFactory(cellData -> {
            boolean isActive = cellData.getValue().getIsActiveProperty().get();
            SimpleStringProperty text = new SimpleStringProperty(isActive ? "Active" : "Inactive");
            return text;
        });

        this.setItems(filter.getFilteredList(dc.getCompanyList()));

        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Company selectedCompany = this.getSelectionModel().getSelectedItem();
                if (selectedCompany != null) {
                    dc.setCurrentCompany(selectedCompany);
                    controls.createButtons();
                }
            }
        });
    }

    @Override
    public void update(Company c) {
        // TODO Auto-generated method stub
    }
}
