package domain;

public class OrderController {
	private OrderManager om = new OrderManager();
	
	public void close() {
		om.closePersistence();
	}

}
