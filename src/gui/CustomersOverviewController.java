package gui;

import java.io.IOException;

import domain.DomainController;
import javafx.fxml.FXMLLoader;

public class CustomersOverviewController {
private DomainController dc;
	
	private CustomersScreenController customers;
	
	public CustomersOverviewController(DomainController dc) {
		this.dc = dc;
		
		this.customers = new CustomersScreenController(dc);
		
		buildGui();
	}
	
	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ordersOverview.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Couldn't load Orders Screen");
			System.out.println(e.getMessage());
		}
		
		//this.add(orders, 0, 0);
	}

}
