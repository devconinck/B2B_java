package gui.customer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import domain.Company;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomerOverview extends GenericOverview<Company, Customer> {
	
	private TextField txf_name, txf_birthdate, txf_age;

	public CustomerOverview(Company entity, ObservableList<Customer> others) {
		super(entity, others, others.get(0));
	}

	@Override
	protected void saveEntity() {
		current.setName(txf_name.getText());
		try {
		    int age = Integer.parseInt(txf_age.getText());
		    current.setAge(age);
		    LocalDate date = LocalDate.parse(txf_birthdate.getText());
		    current.setBirthdate(date);
		} catch (NumberFormatException e) {
		    // Handle the case where the text field does not contain a valid integer
		    System.err.println("Invalid age format");
		} catch (DateTimeParseException e) {
			System.err.println("Invalid date format");
		}
		
	}

	@Override
	protected void removeEntity() {
		System.out.printf("Removing %s%n", current.getName());
	}

	@Override
	protected void setCurrent() {
		txf_name.setText(current.getName());
		txf_birthdate.setText(current.getBirthdate().toString());
		txf_age.setText(String.format("%s", current.getAge()));
	}

	@Override
	protected VBox createDetails(Customer customer) {
		vboxDetails.clear();
		VBox vbox_complete = new VBox();
		
		HBox hbox_name_birthdate = new HBox();
		// Name
		VBox vbox_name = new VBox(new Label("Name"));
		txf_name = new TextField(customer.getName());
		vbox_name.getChildren().add(txf_name);
		vboxDetails.add(vbox_name);
		
		// Birthdate
		VBox vbox_birthdate = new VBox(new Label("Birthdate"));
		txf_birthdate = new TextField(customer.getBirthdate().toString());
		vbox_birthdate.getChildren().add(txf_birthdate);
		vboxDetails.add(vbox_birthdate);
		
		hbox_name_birthdate.getChildren().addAll(vbox_name, vbox_birthdate);
		
		// Age
		VBox vbox_age = new VBox(new Label("Age"));
		txf_age = new TextField(String.format("%s", customer.getAge()));
		vbox_age.getChildren().add(txf_age);
		vboxDetails.add(vbox_age);
		
		vbox_complete.getChildren().addAll(hbox_name_birthdate, vbox_age);
		
		return vbox_complete;
	}

}
