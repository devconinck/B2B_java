package main;

import domain.OrderController;

public class Start {
	private OrderController oc;
	
	public static void main(String[] args) {
		new Start().run();
	}
	
	public void run() {
		oc = new OrderController();
		oc.close();
	}
}


