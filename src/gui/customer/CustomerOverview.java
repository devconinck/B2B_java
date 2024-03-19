package gui.customer;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomerOverview extends GenericOverview<CompanyDTO> {

	private TextField txf_name, txf_customerSince, txf_sector, txf_country, txf_city, txf_zipcode, txf_street,
			txf_number, txf_email, txf_phonenr;
	private ImageView imgvw_logo;

	public CustomerOverview(Map<String, String> attributes, DomainController dc) {
		super(FXCollections.observableArrayList(
				dc.getCompanyList().stream().map(comp -> new CompanyDTO(comp)).collect(Collectors.toList())),
				attributes, dc);
		// TODO logica in dc voor deze super lange constructor
		hbox_main.getStylesheets().add("css/label.css");
	}

	@Override
	protected void saveEntity() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void removeEntity() {
		throw new UnsupportedOperationException();
	}

	private String createPhoneNumber(String phoneNumber)
	{
		return String.format("+32%s", phoneNumber);
	}
	@Override
	protected void setCurrent() {
		txf_name.setText(current.name());
		txf_customerSince.setText(current.customerStart());
		txf_sector.setText(current.sector());
		txf_country.setText(current.country());
		txf_city.setText(current.city());
		txf_zipcode.setText(current.zipcode());
		txf_street.setText(current.street());
		txf_number.setText(current.number());
		txf_email.setText(current.email());
		txf_phonenr.setText(createPhoneNumber(current.phoneNumber()));
		try {
			imgvw_logo.setImage(new Image(String.format("images/%s", current.logo())));
		} catch (IllegalArgumentException e) {
			System.err.println(String.format("Failed to get logo for: %s", current.name()));
			imgvw_logo.setImage(new Image(String.format("images/%s", "delaware-logo.jpg")));
		}
	}

	@Override
	protected VBox createDetails(CompanyDTO company) {
		// logo, name, sector, address, contact, customerSinds
		// TODO UC (gegevens overzicht beschikbare klanten) = naam, aantal openstaande
		// bestellingen
		// TODO UC (gegevens details klant) = naam, logo, adres, contactgegevens
		// TODO UC (gegevens bestellingen klant) = order id, datum, orderbedrag,
		// orderstatus, betalingsstatus
		vboxDetails.clear();
		VBox vbox_complete = new VBox();

		HBox hbox_logo_name_customerSince = new HBox();
		// Logo
		VBox vbox_logo = new VBox();
		vbox_logo.setPadding(new Insets(20));
		try {
			imgvw_logo = new ImageView(new Image(String.format("images/%s", current.logo())));
		} catch (IllegalArgumentException e) {
			System.err.println(String.format("Failed to get logo for: %s", current.name()));
			imgvw_logo = new ImageView(new Image(String.format("images/%s", "delaware-logo.jpg")));
		}
		imgvw_logo.setFitHeight(50);
		imgvw_logo.setFitWidth(50);
		vbox_logo.getChildren().add(imgvw_logo);
		// Name
		VBox vbox_name = new VBox(new Label("Name"));
		txf_name = new TextField(company.name());
		txf_name.setEditable(false);
		vbox_name.getChildren().add(txf_name);
		vbox_name.setPadding(new Insets(20, 10, 10, 20));
		vboxDetails.add(vbox_name);
		// Customer Since
		VBox vbox_customerSince = new VBox(new Label("Customer since"));
		txf_customerSince = new TextField(company.customerStart());
		txf_customerSince.setEditable(false);
		vbox_customerSince.getChildren().add(txf_customerSince);
		vbox_customerSince.setPadding(new Insets(20, 10, 10, 10));
		vboxDetails.add(vbox_customerSince);

		hbox_logo_name_customerSince.getChildren().addAll(vbox_logo, vbox_name, vbox_customerSince);

		// Sector
		VBox vbox_sector = new VBox(new Label("Sector"));
		txf_sector = new TextField(String.format("%s", company.sector()));
		txf_sector.setEditable(false);
		;
		vbox_sector.getChildren().add(txf_sector);
		vbox_sector.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_sector);

		// Address
		VBox vbox_address_complete = new VBox();
		Label lbl_address = new Label("Address");
		lbl_address.getStyleClass().add("labelheader");
		vbox_address_complete.getChildren().add(lbl_address);
		// AddressLine1
		HBox hbox_country_city_zipcode = new HBox();
		// Country
		VBox vbox_country = new VBox(new Label("Country"));
		txf_country = new TextField(company.country());
		txf_country.setEditable(false);
		vbox_country.getChildren().add(txf_country);
		vbox_country.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_country);
		// City
		VBox vbox_city = new VBox(new Label("City"));
		txf_city = new TextField(company.city());
		txf_city.setEditable(false);
		vbox_city.getChildren().add(txf_city);
		vbox_city.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_city);
		// Zipcode
		VBox vbox_zipcode = new VBox(new Label("Zipcode"));
		txf_zipcode = new TextField(company.zipcode());
		txf_zipcode.setEditable(false);
		vbox_zipcode.getChildren().add(txf_zipcode);
		vbox_zipcode.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_zipcode);
		hbox_country_city_zipcode.getChildren().addAll(vbox_country, vbox_city, vbox_zipcode);
		// AddressLine2
		HBox hbox_street_number = new HBox();
		// Street
		VBox vbox_street = new VBox(new Label("Street"));
		txf_street = new TextField(company.street());
		txf_street.setEditable(false);
		vbox_street.getChildren().add(txf_street);
		vbox_street.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_street);
		// Number
		VBox vbox_number = new VBox(new Label("Number"));
		txf_number = new TextField(company.number());
		txf_number.setEditable(false);
		vbox_number.getChildren().add(txf_number);
		vbox_number.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_number);
		hbox_street_number.getChildren().addAll(vbox_street, vbox_number);

		vbox_address_complete.getChildren().addAll(hbox_country_city_zipcode, hbox_street_number);

		// Contact
		VBox vbox_email_phonenr = new VBox();
		Label lbl_contact = new Label("Contact");
		lbl_contact.getStyleClass().add("labelheader");
		vbox_email_phonenr.getChildren().add(lbl_contact);
		HBox hbox_email_phonenr = new HBox();
		// Email
		VBox vbox_email = new VBox(new Label("Email"));
		txf_email = new TextField(company.email());
		txf_email.setEditable(false);
		vbox_email.getChildren().add(txf_email);
		vbox_email.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_email);
		// Phonenr
		VBox vbox_phonenr = new VBox(new Label("Phone Number"));
		txf_phonenr = new TextField(createPhoneNumber(company.phoneNumber()));
		txf_phonenr.setEditable(false);
		vbox_phonenr.getChildren().add(txf_phonenr);
		vbox_phonenr.setPadding(new Insets(10, 10, 10, 20));
		vboxDetails.add(vbox_phonenr);
		hbox_email_phonenr.getChildren().addAll(vbox_email, vbox_phonenr);
		vbox_email_phonenr.getChildren().add(hbox_email_phonenr);

		vbox_complete.getChildren().addAll(hbox_logo_name_customerSince, vbox_sector, vbox_address_complete,
				vbox_email_phonenr);

		setOrders(vbox_complete);

		return vbox_complete;
	}

	private void setOrders(VBox vbox_complete) {
		// TODO Auto-generated method stub
		// TODO UC (gegevens bestellingen klant) = order id, datum, orderbedrag,
		// orderstatus, betalingsstatus
		Map<String, String> mapOrders = new TreeMap<>(Map.ofEntries(
				Map.entry("Order ID", "orderId"),
				Map.entry("Date", "date"),
				Map.entry("Price", "orderAmount"),
				Map.entry("Order Status", "orderStatus"),
				Map.entry("Payment Status", "paymentStatus")
				));
		ObservableList<OrderDTO> orders = FXCollections.observableArrayList(
				dc.getOrdersList().stream().map(or -> new OrderDTO(or)).collect(Collectors.toList()));
		GenericTableView<OrderDTO> orderTable = new GenericTableView<>(orders.get(0), mapOrders);
		orderTable.setData(FXCollections.observableArrayList(orders.subList(0, 15)));
		vbox_complete.getChildren().add(orderTable);
	}

	@Override
	protected VBox setFilter() {
		VBox vboxFilter = new VBox();
		HBox hboxFilter = new HBox();
		hboxFilter.setPrefWidth(genericTableView.getWidth());
		
		TextField filter = new TextField();
		filter.setPromptText("Filter on name");
		
		Button btnFilter = new Button("Search");
		btnFilter.setOnMouseClicked(event -> filterTable(filter.getText()));
		btnFilter.getStyleClass().add("filterButton");
		
		hboxFilter.getChildren().addAll(filter, btnFilter);
		hboxFilter.getStyleClass().add("filterHbox");
		
		vboxFilter.getChildren().add(hboxFilter);
		
		return vboxFilter;
	}

	private void filterTable(String name) {
		// TODO logica dc
		ObservableList<CompanyDTO> listToShow = FXCollections.observableArrayList(
				dc.getCompanyList().stream().map(comp -> new CompanyDTO(comp)).filter(dto -> dto.name().toLowerCase().contains(name)).collect(Collectors.toList()));
		genericTableView.setData(listToShow);
	}

}
