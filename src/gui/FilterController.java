package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public abstract class FilterController<T> extends HBox {
    

	private final TextField filterField;
    protected ObservableList<T> originalList;
    protected ObservableList<T> copyOriginal;
    protected VBox vbox;

    public FilterController(ObservableList<T> originalList) {
        this.originalList = originalList;
        this.copyOriginal = FXCollections.observableArrayList(originalList);
        this.vbox = new VBox();
        HBox.setHgrow(vbox, Priority.ALWAYS);
        
        filterField = new TextField();
        filterField.setPromptText("Search by company name");
        vbox.getChildren().add(filterField);
        
        HBox.setHgrow(filterField, Priority.ALWAYS);
        
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Call the filter method whenever the text changes
                getFilteredList(originalList);
            }
        });
        
        getChildren().add(vbox);
    }
    
    public String getSearchText() {
    	return filterField.getText().toLowerCase().trim();
    }
    
    public abstract ObservableList<T> Filter(ObservableList<T> list);

    public ObservableList<T> getFilteredList(ObservableList<T> companyList) {
        if (getSearchText().isEmpty()) {
        	originalList.setAll(copyOriginal);
        } else {
        	originalList.setAll(copyOriginal);
        	originalList.setAll(Filter(companyList));
        }
        return originalList;
    }
}
