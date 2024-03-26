package gui;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import domain.SupplierController;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public abstract class GenericOverview<O> {
	
	protected HBox hbox_main;
	protected List<VBox> vboxDetails;
	protected O current;
	protected GenericTableView<O> genericTableView;
	protected SupplierController controller;
	protected ObservableList<O> others;
	private static final int SIDEBAR_WIDTH = 250;
	
	public GenericOverview(ObservableList<O> others, Map<String, String> attributes, SupplierController controller) {
		this.controller = controller;
		this.others = others;
		hbox_main = new HBox();
		this.genericTableView = new GenericTableView<>(attributes);
		setClassFields();
	}
	
	private void setClassFields() {
		hbox_main.getChildren().clear();
		vboxDetails = new ArrayList<>();
		VBox vboxFilterAndTable = new VBox();

		// Add Table
		genericTableView.getStylesheets().add("css/label.css");
		genericTableView.setData(others);
		genericTableView.setOnMouseClicked(event -> {
			this.current = genericTableView.getSelectionModel().getSelectedItem();
			clearFields();
			setCurrent();
		});

		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		genericTableView.setPrefWidth((visualBounds.getWidth() - SIDEBAR_WIDTH) / 2);

		// Details
		VBox details = createDetails();

		vboxFilterAndTable.getChildren().addAll(setFilter(), genericTableView);

		hbox_main.getChildren().addAll(vboxFilterAndTable, details);
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract FilterController setFilter();

	private void clearFields() {
		vboxDetails.stream().map(vbox -> vbox.getChildren().get(1))
				.forEach(field -> ((TextInputControl) field).clear());
	}
	
	protected abstract VBox createDetails();
	
	protected abstract void saveEntity();
	
	protected abstract void removeEntity();
	
	protected abstract void setCurrent();
	
	// Nodig om van scherm te veranderen
	public HBox getHBox() {
		return hbox_main;
	}

}
