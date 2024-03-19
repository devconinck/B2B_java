package gui.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.DomainController;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public abstract class GenericOverview2<O> {
	
	protected HBox hbox_main;
	protected List<VBox> vboxDetails;
	protected O current;
	protected GenericTableView<O> genericTableView;
	protected DomainController dc;
	private static final int SIDEBAR_WIDTH = 250;
	private static final int TOPBAR_HEIGHT = 200;
	
	public GenericOverview2(ObservableList<O> others, DomainController dc) {
		this.dc = dc;
		hbox_main = new HBox();
		this.current = others.get(0);
		this.genericTableView = new GenericTableView<>(others.get(0));
		setClassFields(others);
	}
	
	public GenericOverview2(ObservableList<O> others, Map<String, String> attributes, DomainController dc) {
		this.dc = dc;
		hbox_main = new HBox();
		this.current = others.get(0);
		this.genericTableView = new GenericTableView<>(others.get(0), attributes);
		setClassFields(others);
	}
	
	private void setClassFields(ObservableList<O> others) {
		hbox_main.getChildren().clear();
		vboxDetails = new ArrayList<>();
		VBox vboxFilterAndTable = new VBox();
		VBox vboxDetailsAndButtons = new VBox();

		// Add Table
		genericTableView.getStylesheets().add("css/customerTable.css");
		genericTableView.setData(others);
		genericTableView.setOnMouseClicked(event -> {
			this.current = genericTableView.getSelectionModel().getSelectedItem();
			clearFields();
			setCurrent();
		});

		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		genericTableView.setPrefWidth((visualBounds.getWidth() - SIDEBAR_WIDTH) / 2);
		// TODO tot beneden laten gaan?
		//genericTableView.setPrefHeight(visualBounds.getHeight() - TOPBAR_HEIGHT);

		// Details and Buttons
		VBox details = createDetails(others.get(0));
		//Buttons buttons = new Buttons(Arrays.asList("Save", "Remove", "Clear"));
		//HBox hbox_buttons = buttons.getButtonfield();
		//buttons.getButtons().get(0).setOnMouseClicked(event -> saveEntity());
		//buttons.getButtons().get(1).setOnMouseClicked(event -> removeEntity());
		//buttons.getButtons().get(2).setOnMouseClicked(event -> clearFields());

		// Add all together
		vboxDetailsAndButtons.getChildren().addAll(details/* hbox_buttons*/);
		vboxFilterAndTable.getChildren().addAll(setFilter(), genericTableView);

		hbox_main.getChildren().addAll(vboxFilterAndTable, vboxDetailsAndButtons);
	}
	
	protected abstract VBox setFilter();

	private void clearFields() {
		vboxDetails.stream().map(vbox -> vbox.getChildren().get(1))
				.forEach(field -> ((TextInputControl) field).clear());
	}
	
	protected abstract VBox createDetails(O entityClass);
	
	protected abstract void saveEntity();
	
	protected abstract void removeEntity();
	
	protected abstract void setCurrent();
	
	// Nodig om van scherm te veranderen
	public HBox getHBox() {
		return hbox_main;
	}

}
