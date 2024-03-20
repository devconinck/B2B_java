package gui;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public abstract class FilterController<T> extends HBox {
    
    private final TextField filterField;
    private final ObservableList<T> originalList;
    private final ObservableList<T> filteredList;

    public FilterController(ObservableList<T> originalList) {
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
    
    public String getSearchText() {
    	return filterField.getText().toLowerCase().trim();
    }
    
    public abstract List<T> Filter(List<T> list);

    public ObservableList<T> getFilteredList(List<T> companyList) {
        if (getSearchText().isEmpty()) {
            filteredList.setAll(originalList);
        } else {
            filteredList.setAll(Filter(companyList));
        }
        return filteredList;
    }
}
