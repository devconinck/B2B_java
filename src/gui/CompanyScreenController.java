package gui;

import java.io.IOException;
import domain.Company;
import domain.AdminController;
import domain.Observer;
import domain.Order;
import javafx.beans.binding.Bindings;
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
    @FXML
    private TableColumn<Company, Integer> amountOfCustCol;

    private final AdminController ac;
    private final FilterController filter;
    private ControlScreenController controls;

    public CompanyScreenController(AdminController ac, FilterController filter, ControlScreenController controls) {
        this.ac = ac;
        this.filter = filter;
        this.controls = controls;
        this.ac.addObserver(this);
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
        //amountOfCustCol.setCellValueFactory(cellData -> cellData.getValue().getAmountOfCustomers());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
        
        isActiveCol.setCellValueFactory(cellData -> {
            SimpleStringProperty text = new SimpleStringProperty();
            text.bind(Bindings.when(cellData.getValue().getIsActiveProperty())
                    .then("Active")
                    .otherwise("Inactive"));
            return text;
        });

        this.setItems(filter.getFilteredList(ac.getCompanyList()));
        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Company selectedCompany = this.getSelectionModel().getSelectedItem();
                if (selectedCompany != null) {
                    ac.setSelectedCompany(selectedCompany);
                    controls.createButtons();
                }
            }
        });
    }
    
    // Deze oplossing werkt maar is het een goeie oplossing?
    // Wisselt ook pas als je naar andere company gaat
    @Override
    public void update(Company c) {
    	// this.refresh();
    }

	@Override
	public void update(Order c) {
		// TODO Auto-generated method stub
		
	}
}
