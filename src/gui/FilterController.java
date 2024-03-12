package gui;

import java.util.List;
import java.util.stream.Collectors;
import domain.Company;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FilterController extends HBox {
    
    private final TextField filterField;
    private final ObservableList<Company> originalList;
    private final ObservableList<Company> filteredList;

    public FilterController(ObservableList<Company> originalList) {
        this.originalList = originalList;
        this.filteredList = FXCollections.observableArrayList();
        filterField = new TextField();
        filterField.setPromptText("Search by company name");
        
        HBox.setHgrow(filterField, Priority.ALWAYS);
        
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Call the filter method whenever the text changes
                getFilteredList(originalList);
            }
        });
        
        getChildren().add(filterField);
    }
    

    public ObservableList<Company> getFilteredList(List<Company> companyList) {
        String searchText = filterField.getText().toLowerCase().trim();
        if (searchText.isEmpty()) {
            filteredList.setAll(originalList);
        } else {
            List<Company> filtered = companyList.stream()
                                                .filter(c -> c.getName().toLowerCase().contains(searchText))
                                                .collect(Collectors.toList());
            filteredList.setAll(filtered);
        }
        return filteredList;
    }
}
