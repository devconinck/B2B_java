package gui.customer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Company;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomerOverview extends GenericOverview<Company, Company> {

	private TextField txf_name, txf_customerSince, txf_sector, txf_country, txf_city, txf_zipcode, txf_street,
			txf_number, txf_email, txf_phonenr;

	public CustomerOverview(Company entity, ObservableList<Company> others, List<String> attributes) {
		super(entity, others, others.get(0));
	}

	@Override
	protected void saveEntity() {
		// TODO
		current.setName(txf_name.getText());
		try {
			String sector = txf_sector.getText();
			current.setSector(sector);
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
		txf_customerSince.setText(current.getCustomerStart().toString());
		txf_sector.setText(current.getSector());
		txf_country.setText(current.getAddress().getCountry()); 
		txf_city.setText(current.getAddress().getCity());
		txf_zipcode.setText(current.getAddress().getZipCode());
		txf_street.setText(current.getAddress().getStreet());
		txf_number.setText(current.getAddress().getNumber());
		txf_email.setText(current.getContact().getEmail());
		txf_phonenr.setText(current.getContact().getPhoneNumber());
		
	}

	@Override
	protected VBox createDetails(Company customer) {
		// logo, name, sector, address, contact, customerSinds
		vboxDetails.clear();
		VBox vbox_complete = new VBox();

		HBox hbox_name_customerSince = new HBox();
		// Name
		VBox vbox_name = new VBox(new Label("Name"));
		txf_name = new TextField(customer.getName());
		vbox_name.getChildren().add(txf_name);
		vbox_name.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_name);

		// Customer Since
		VBox vbox_customerSince = new VBox(new Label("Customer since"));
		txf_customerSince = new TextField(customer.getCustomerStart().toString());
		vbox_customerSince.getChildren().add(txf_customerSince);
		vbox_customerSince.setPadding(new Insets(20, 10, 10, 10));
		vboxDetails.add(vbox_customerSince);

		hbox_name_customerSince.getChildren().addAll(vbox_name, vbox_customerSince);

		// Sector
		VBox vbox_sector = new VBox(new Label("Sector"));
		txf_sector = new TextField(String.format("%s", customer.getSector()));
		vbox_sector.getChildren().add(txf_sector);
		vbox_sector.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_sector);

		// Address
		VBox vbox_address_complete = new VBox();
		Label lbl_address = new Label("Address");
		lbl_address.setPadding(new Insets(0, 0, 0, 20));
		vbox_address_complete.getChildren().add(lbl_address);
		// AddressLine1
		HBox hbox_country_city_zipcode = new HBox();
		// Country
		VBox vbox_country = new VBox(new Label("Country"));
		txf_country = new TextField(customer.getAddress().getCountry());
		vbox_country.getChildren().add(txf_country);
		vbox_country.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_country);
		// City
		VBox vbox_city = new VBox(new Label("City"));
		txf_city = new TextField(customer.getAddress().getCity());
		vbox_city.getChildren().add(txf_city);
		vbox_city.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_city);
		// Zipcode
		VBox vbox_zipcode = new VBox(new Label("Zipcode"));
		txf_zipcode = new TextField(customer.getAddress().getZipCode());
		vbox_zipcode.getChildren().add(txf_zipcode);
		vbox_zipcode.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_zipcode);
		hbox_country_city_zipcode.getChildren().addAll(vbox_country, vbox_city, vbox_zipcode);
		// AddressLine2
		HBox hbox_street_number = new HBox();
		// Street
		VBox vbox_street = new VBox(new Label("Street"));
		txf_street = new TextField(customer.getAddress().getStreet());
		vbox_street.getChildren().add(txf_street);
		vbox_street.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_street);
		// Number
		VBox vbox_number = new VBox(new Label("Number"));
		txf_number = new TextField(customer.getAddress().getNumber());
		vbox_number.getChildren().add(txf_number);
		vbox_number.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_number);
		hbox_street_number.getChildren().addAll(vbox_street, vbox_number);

		vbox_address_complete.getChildren().addAll(hbox_country_city_zipcode, hbox_street_number);

		// Contact
		VBox vbox_email_phonenr = new VBox();
		Label lbl_contact = new Label("Contact");
		lbl_contact.setPadding(new Insets(0, 0, 0, 20));
		vbox_email_phonenr.getChildren().add(lbl_contact);
		HBox hbox_email_phonenr = new HBox();
		// Email
		VBox vbox_email = new VBox(new Label("Email"));
		txf_email = new TextField(customer.getContact().getEmail());
		vbox_email.getChildren().add(txf_email);
		vbox_email.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_email);
		// Phonenr
		VBox vbox_phonenr = new VBox(new Label("Phone Number"));
		txf_phonenr = new TextField(customer.getContact().getPhoneNumber());
		vbox_phonenr.getChildren().add(txf_phonenr);
		vbox_phonenr.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_phonenr);
		hbox_email_phonenr.getChildren().addAll(vbox_email, vbox_phonenr);
		vbox_email_phonenr.getChildren().add(hbox_email_phonenr);

		vbox_complete.getChildren().addAll(hbox_name_customerSince, vbox_sector, vbox_address_complete,
				vbox_email_phonenr);

		return vbox_complete;
	}

}
