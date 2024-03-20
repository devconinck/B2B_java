package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import repository.GenericDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;
import util.seeding.OrderData;

public class DomainController {	
	private OrderData od;
	
	public DomainController() {
		
		System.out.println("Adding orders...");
		od = new OrderData(orderRepo, orderItemRepo, productRepo, productPriceRepo, productDescriptionRepo, productUnitRepo, companyRepo);
		od.addOrderData();
		System.out.println("Adding orders complete!");
		
	}
		
	public void close() {
		GenericDaoJpa.closePersistency();
	}
	
}

