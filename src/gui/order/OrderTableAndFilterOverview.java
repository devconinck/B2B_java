package gui.order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import domain.Order;
import domain.SupplierController;
import dto.OrderDTO;
import gui.FilterController;
import gui.GenericTableAndFilterOverview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderTableAndFilterOverview extends GenericTableAndFilterOverview<OrderDTO> implements PropertyChangeListener{

	public OrderTableAndFilterOverview(ObservableList<OrderDTO> others, Map<String, String> attributes, SupplierController controller) {
		super(others, attributes, controller);
		build();
		
	}

	private void build() {
		hbox_main.getStylesheets().add("css/label.css");
		genericTableView.setOnMouseClicked(event -> {
			controller.setCurrentOrder(new Order(genericTableView.getSelectionModel().getSelectedItem()));
		});
	}

	@Override
	protected FilterController<OrderDTO> setFilter() {
		return new OrdersFilterController(others);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.others.setAll(FXCollections.observableArrayList(controller.getOrdersToCompanyDTO()));
	}

}
