package gui.company;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Company;
import gui.FilterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CompanyFilterController extends FilterController<Company> {
	private Set<String> allowedSectors;
	private Set<String> allowedCountries;
	private TextField txtCustomerCountStart;
	private TextField txtCustomerCountEnd;
	private CheckBox chkActive;
	private CheckBox chkInactive;

	public CompanyFilterController(ObservableList<Company> originalList) {
		super(originalList);
		this.allowedSectors = new HashSet<>();
		this.allowedCountries = new HashSet<>();

		vbox.getChildren().addAll(createSectorGrid(), createCountryGrid(), createCustomerCountRange(),
				createActiveInactiveCheckboxes());
	}

	@Override
	public ObservableList<Company> Filter(ObservableList<Company> list) {
		return FXCollections.observableArrayList(
				list.stream().filter(c -> c.getName().toLowerCase().contains(super.getSearchText().toLowerCase()))
						.collect(Collectors.toList()));
	}

	private VBox createSectorGrid() {
		GridPane sectorPane = createCheckboxGrid();

		// Get unique sectors from the original list
		Set<String> sectors = copyOriginal.stream().map(Company::getSector).collect(Collectors.toSet());

		addCheckboxesToGrid(sectorPane, sectors, this::filterSector);

		Label lblSectors = new Label("Sectors");
		VBox vboxSectors = new VBox();
		vboxSectors.getChildren().addAll(lblSectors, sectorPane);

		return vboxSectors;
	}

	private VBox createCountryGrid() {
		GridPane countryPane = createCheckboxGrid();

		// Get unique countries from the original list
		Set<String> countries = copyOriginal.stream().map(c -> c.getAddress().getCountry()).collect(Collectors.toSet());

		addCheckboxesToGrid(countryPane, countries, this::filterCountry);

		Label lblCountries = new Label("Countries");
		VBox vboxCountries = new VBox();
		vboxCountries.getChildren().addAll(lblCountries, countryPane);

		return vboxCountries;
	}

	private GridPane createCheckboxGrid() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));

		// Set the maximum width of each column
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setPercentWidth(20); // Set each column to occupy 20% of the grid width
		gridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints,
				columnConstraints, columnConstraints);

		return gridPane;
	}

	private void addCheckboxesToGrid(GridPane gridPane, Set<String> items, CheckboxHandler handler) {
		int row = 0;
		int col = 0;
		int maxColumns = 5;

		for (String item : items) {
			CheckBox checkBox = new CheckBox(item);
			gridPane.add(checkBox, col, row);

			checkBox.setOnAction(event -> handler.handle(checkBox));

			col++;
			if (col >= maxColumns) {
				col = 0;
				row++;
			}
		}
	}

	private VBox createCustomerCountRange() {
		txtCustomerCountStart = new TextField();
		txtCustomerCountStart.setPromptText("Min");

		txtCustomerCountEnd = new TextField();
		txtCustomerCountEnd.setPromptText("Max");

		HBox hboxCustomerCount = new HBox();
		hboxCustomerCount.setSpacing(10);
		hboxCustomerCount.getChildren().addAll(txtCustomerCountStart, txtCustomerCountEnd);

		txtCustomerCountStart.textProperty().addListener((observable, oldValue, newValue) -> runAllFilters());
		txtCustomerCountEnd.textProperty().addListener((observable, oldValue, newValue) -> runAllFilters());

		Label lblCustomerCount = new Label("Customer Count Range");
		VBox vboxCustomerCount = new VBox();
		vboxCustomerCount.getChildren().addAll(lblCustomerCount, hboxCustomerCount);

		return vboxCustomerCount;
	}

	private VBox createActiveInactiveCheckboxes() {
		chkActive = new CheckBox("Active");
		chkInactive = new CheckBox("Inactive");

		chkActive.setOnAction(e -> runAllFilters());
		chkInactive.setOnAction(e -> runAllFilters());

		HBox hboxActiveInactive = new HBox();
		hboxActiveInactive.setSpacing(10);
		hboxActiveInactive.getChildren().addAll(chkActive, chkInactive);

		Label lblActiveInactive = new Label("Company Status");
		VBox vboxActiveInactive = new VBox();
		vboxActiveInactive.getChildren().addAll(lblActiveInactive, hboxActiveInactive);

		return vboxActiveInactive;
	}

	private void filterSector(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			allowedSectors.add(checkBox.getText());
		} else {
			allowedSectors.remove(checkBox.getText());
		}
		runAllFilters();
	}

	private void filterCountry(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			allowedCountries.add(checkBox.getText());
		} else {
			allowedCountries.remove(checkBox.getText());
		}
		runAllFilters();
	}

	@Override
	protected void runAllFilters() {
		ObservableList<Company> newList = FXCollections.observableArrayList(copyOriginal);

		// Company Name
		newList = Filter(newList);

		// Sector
		newList = FXCollections.observableArrayList(newList.stream().filter(c -> {
			if (allowedSectors.isEmpty())
				return true;
			return allowedSectors.contains(c.getSector());
		}).collect(Collectors.toList()));

		// Country
		newList = FXCollections.observableArrayList(newList.stream().filter(c -> {
			if (allowedCountries.isEmpty())
				return true;
			return allowedCountries.contains(c.getAddress().getCountry());
		}).collect(Collectors.toList()));

		// Customer Count Range
		int minCustomerCount = txtCustomerCountStart.getText().isEmpty() ? 0
				: Integer.parseInt(txtCustomerCountStart.getText());
		int maxCustomerCount = txtCustomerCountEnd.getText().isEmpty() ? Integer.MAX_VALUE
				: Integer.parseInt(txtCustomerCountEnd.getText());

		newList = FXCollections
				.observableArrayList(
						newList.stream()
								.filter(c -> c.getAmountOfCustomers().get() >= minCustomerCount
										&& c.getAmountOfCustomers().get() <= maxCustomerCount)
								.collect(Collectors.toList()));

		// Active/Inactive
		boolean includeActive = chkActive.isSelected();
		boolean includeInactive = chkInactive.isSelected();

		if (includeActive && !includeInactive) {
			newList = FXCollections
					.observableArrayList(newList.stream().filter(Company::getIsActive).collect(Collectors.toList()));
		} else if (!includeActive && includeInactive) {
			newList = FXCollections
					.observableArrayList(newList.stream().filter(c -> !c.getIsActive()).collect(Collectors.toList()));
		}

		originalList.setAll(newList);
	}

	@FunctionalInterface
	private interface CheckboxHandler {
		void handle(CheckBox checkBox);
	}
}