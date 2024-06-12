package gui;

import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenericTableView<T> extends TableView<T> {
	
	public GenericTableView(Map<String, String> attributes) {
		setup(attributes);
	}

	private void setup(Map<String, String> attributes) {
		for (Map.Entry<String, String> set : attributes.entrySet()) { 
			TableColumn<T, Object> column = new TableColumn<>(set.getKey());
			column.setCellValueFactory(new PropertyValueFactory<>(set.getValue()));
			getColumns().add(column);
       }
	}
	
	public void setData(ObservableList<T> data) {
		setFixedCellSize(24);
		setPrefHeight((data.size() + 1) * getFixedCellSize() + 45);
		setMaxHeight(500);
		setItems(data);
	}
}
