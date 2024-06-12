package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class GenericDetailsOverview<O> {
	
	protected HBox hbox_main;
	protected List<VBox> vboxDetails;
	protected O current;
	
	public GenericDetailsOverview() {
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
