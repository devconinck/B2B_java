package gui.customer;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Buttons {
	
	private HBox buttonfield;
	private List<Button> buttons;
	
	public Buttons(List<String> buttonnames) {
		this.buttons = new ArrayList<>();
		this.buttonfield = new HBox();
		setup(buttonnames);
	}

	private void setup(List<String> buttonnames) {
		for (String name : buttonnames) {
			Button btn = new Button(name);
			btn.setPadding(new Insets(10));
			buttons.add(btn);
		}
		buttonfield.getChildren().addAll(buttons);
		buttonfield.setAlignment(Pos.CENTER);
	}
	
	public HBox getButtonfield() {
		return buttonfield;
	}
	
	public List<Button> getButtons() {
		return buttons;
	}

}
