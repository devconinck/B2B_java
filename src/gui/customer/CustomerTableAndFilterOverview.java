package gui.customer;

import java.util.Map;

import domain.Company;
import domain.Controller;
import domain.SupplierController;
import dto.CompanyDTO;
import gui.FilterController;
import gui.GenericTableAndFilterOverview;
import javafx.collections.ObservableList;

public class CustomerTableAndFilterOverview extends GenericTableAndFilterOverview<CompanyDTO>{

	public CustomerTableAndFilterOverview(ObservableList<CompanyDTO> others, Map<String, String> attributes, SupplierController controller) {
		super(others, attributes, controller);
		build();
		
	}

	private void build() {
		hbox_main.getStylesheets().add("css/label.css");
		genericTableView.setOnMouseClicked(event -> {
			controller.setSelectedCompany(new Company(genericTableView.getSelectionModel().getSelectedItem()));
		});
	}

	@Override
	protected FilterController<CompanyDTO> setFilter() {
		return new CustomerFilterController(others);
	}

}
