package gui;

import java.util.Map;

import domain.Controller;
import domain.SupplierController;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public abstract class GenericTableAndFilterOverview<O> {
	
	protected HBox hbox_main;
	protected GenericTableView<O> genericTableView;
	protected ObservableList<O> others;
	private static final int SIDEBAR_WIDTH = 250;
	protected SupplierController controller;
	
	public GenericTableAndFilterOverview(ObservableList<O> others, Map<String, String> attributes, SupplierController controller) {
		this.controller = controller;
		this.others = others;
		hbox_main = new HBox();
		this.genericTableView = new GenericTableView<>(attributes);
		setClassFields();
	}
	
	private void setClassFields() {
		VBox vboxFilterAndTable = new VBox();

		// Add Table
		genericTableView.setData(others);
		
		// Set Width table
		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		genericTableView.setPrefWidth((visualBounds.getWidth() - SIDEBAR_WIDTH) / 2);

		vboxFilterAndTable.getChildren().addAll(setFilter(), genericTableView);

		hbox_main.getChildren().addAll(vboxFilterAndTable);
	}

	protected abstract FilterController<O> setFilter();
	
	// Nodig om van scherm te veranderen
	public HBox getHBox() {
		return hbox_main;
	}

}
