package gui.company;

import java.io.IOException;

import domain.Company;
import domain.Observer;
import domain.Order;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CompanyScreenController extends TableView<Company> implements Observer {
    @FXML private TableColumn<Company, String> nameCol;
    @FXML private TableColumn<Company, String> sectorCol;
    @FXML private TableColumn<Company, String> addressCol;
    @FXML private TableColumn<Company, Boolean> isActiveCol;
    @FXML private TableColumn<Company, Integer> amountOfCustomersCol;

    private final ObservableList<Company> companyList;
    private final ObjectProperty<Company> selectedCompanyProperty;

    public CompanyScreenController(ObservableList<Company> companyList, ObjectProperty<Company> selectedCompanyProperty) {
        this.companyList = companyList;
        this.selectedCompanyProperty = selectedCompanyProperty;
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
        amountOfCustomersCol.setCellValueFactory(cellData -> cellData.getValue().getAmountOfCustomers().asObject());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
        isActiveCol.setCellValueFactory(cellData -> cellData.getValue().getIsActiveProperty());
        isActiveCol.setCellFactory(column -> new TableCell<Company, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Active" : "Inactive");
                }
            }
        });


        this.setItems(companyList);

        this.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedCompanyProperty.set(newValue);
            }
        });

        selectedCompanyProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.getSelectionModel().select(newValue);
            }
        });
    }

    @Override
    public void update(Company company) {
        refresh();
    }

    @Override
    public void update(Order order) {
        // Not needed in this class
    }
}