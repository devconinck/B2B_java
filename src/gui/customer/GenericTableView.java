package gui.customer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenericTableView<T> extends TableView<T> {

	public GenericTableView(T entityClass) {
		Map<String, String> map = new HashMap<>();
		List<String> li = getAttributeNames(entityClass);
		li.stream().forEach(str -> map.put(str, str));
		setup(entityClass, map);
	}
	
	public GenericTableView(T entityClass, Map<String, String> attributes) {
		setup(entityClass, attributes);
	}

	private void setup(T entityClass, Map<String, String> attributes) {
		for (Map.Entry<String, String> set : attributes.entrySet()) { 
			TableColumn<T, Object> column = new TableColumn<>(set.getKey());
			column.setCellValueFactory(new PropertyValueFactory<>(set.getValue()));
			getColumns().add(column);
       } 
		/*
		for (String attribute : attributes) {
			TableColumn<T, Object> column = new TableColumn<>(attribute);
			column.setCellValueFactory(new PropertyValueFactory<>(attribute));
			getColumns().add(column);
		}
		*/
	}

	private List<String> getAttributeNames(T entityClass) {
		return Arrays.stream(entityClass.getClass().getDeclaredFields())
				.map(field -> field.getName())
				.collect(Collectors.toList());
	}
	
	public void setData(ObservableList<T> data) {
		setItems(data);
	}
}
