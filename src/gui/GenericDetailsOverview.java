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

public abstract class GenericDetailsOverview<O> {
	
	protected HBox hbox_main;
	protected List<VBox> vboxDetails;
	protected O current;
	
	public GenericDetailsOverview(O current) {
		this.current = current;
		hbox_main = new HBox();
		setClassFields();
	}
	
	private void setClassFields() {
		vboxDetails = new ArrayList<>();

		// Details
		VBox details = createDetails();

		hbox_main.getChildren().addAll(details);
	}
	
	protected abstract VBox createDetails();
	
	// Nodig om van scherm te veranderen
	public HBox getHBox() {
		return hbox_main;
	}

}
