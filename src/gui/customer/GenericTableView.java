package gui.customer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenericTableView<T> extends TableView<T> {

	public GenericTableView(T entityClass) {
		setup(entityClass, getAttributeNames(entityClass));
	}
	
	public GenericTableView(T entityClass, List<String> attributes) {
		setup(entityClass, attributes);
	}

	private void setup(T entityClass, List<String> attributes) {		
		for (String attribute : attributes) {
			TableColumn<T, Object> column = new TableColumn<>(attribute);
			column.setCellValueFactory(new PropertyValueFactory<>(attribute));
			getColumns().add(column);
		}
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
