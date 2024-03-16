package gui.customer;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public abstract class GenericOverview2<O> {
	
	protected HBox hbox_main;
	protected List<VBox> vboxDetails;
	protected O current;
	protected GenericTableView<O> genericTableView;
	private static final int SIDEBAR_WIDTH = 250;
	private static final int TOPBAR_HEIGHT = 200;
	
	public GenericOverview2(ObservableList<O> others) {
		hbox_main = new HBox();
		this.current = others.get(0);
		this.genericTableView = new GenericTableView<>(others.get(0));
		setClassFields(others);
	}
	
	public GenericOverview2(ObservableList<O> others, List<String> attributes) {
		hbox_main = new HBox();
		this.current = others.get(0);
		this.genericTableView = new GenericTableView<>(others.get(0), attributes);
		setClassFields(others);
	}
	
	private void setClassFields(ObservableList<O> others) {
		hbox_main.getChildren().clear();
		vboxDetails = new ArrayList<>();
		HBox hbox = new HBox();
		VBox vboxDetailsAndButtons = new VBox();

		// Add Table
		genericTableView.getStyleClass().add("css/customerTable.css");
		genericTableView.setData(others);
		genericTableView.setOnMouseClicked(event -> {
			this.current = genericTableView.getSelectionModel().getSelectedItem();
			clearFields();
			setCurrent();
		});

		Screen screen = Screen.getPrimary();
		Rectangle2D visualBounds = screen.getVisualBounds();
		genericTableView.setPrefWidth((visualBounds.getWidth() - SIDEBAR_WIDTH) / 2);
		genericTableView.setPrefHeight(visualBounds.getHeight() - TOPBAR_HEIGHT);

		// Details and Buttons
		VBox details = createDetails(others.get(0));
		//Buttons buttons = new Buttons(Arrays.asList("Save", "Remove", "Clear"));
		//HBox hbox_buttons = buttons.getButtonfield();
		//buttons.getButtons().get(0).setOnMouseClicked(event -> saveEntity());
		//buttons.getButtons().get(1).setOnMouseClicked(event -> removeEntity());
		//buttons.getButtons().get(2).setOnMouseClicked(event -> clearFields());

		// Add all together
		vboxDetailsAndButtons.getChildren().addAll(details/* hbox_buttons*/);
		hbox.getChildren().addAll(genericTableView, vboxDetailsAndButtons);

		hbox_main.getChildren().add(hbox);
	}
	
	protected abstract VBox createDetails(O entityClass);
	
	private void clearFields() {
		vboxDetails.stream().map(vbox -> vbox.getChildren().get(1))
				.forEach(field -> ((TextInputControl) field).clear());
	}
	
	public HBox getHBox() {
		return hbox_main;
	}
	
	protected abstract void saveEntity();
	
	protected abstract void removeEntity();
	
	protected abstract void setCurrent();

}
