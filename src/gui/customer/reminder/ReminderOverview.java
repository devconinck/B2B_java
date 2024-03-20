package gui.customer.reminder;

import java.util.Map;

import domain.SupplierController;
import dto.OrderDTO;
import gui.GenericOverview;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class ReminderOverview extends GenericOverview<OrderDTO>{

	public ReminderOverview(ObservableList<OrderDTO> others, Map<String, String> attributes,
			SupplierController controller) {
		super(others, attributes, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected VBox setFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void saveEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void removeEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setCurrent() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected VBox createDetails(OrderDTO entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
