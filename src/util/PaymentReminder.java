package util;

import java.time.LocalDate;

import domain.B2BPortaal;
import domain.Company;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import mail.SendMail;
import repository.GenericDaoJpa;

public class PaymentReminder {
	private B2BPortaal portaal;
	private SendMail mail;
	
	public PaymentReminder() {
		this.portaal = new B2BPortaal(new GenericDaoJpa<Company>(Company.class), new GenericDaoJpa<Order>(Order.class), new GenericDaoJpa<OrderItem>(OrderItem.class), new GenericDaoJpa<Product>(Product.class));
		this.mail = new SendMail();
		sendPaymentReminders();
	}
	
	public void sendPaymentReminders() {
	    LocalDate currentDate = LocalDate.now();
	    String sender = "sdpgroep@gmail.com";
	    
	    portaal.getOrdersList().forEach(order -> {
	        LocalDate paymentDueDate = order.getOrderDateTime().plusDays(Validation.PAYMENT_PERIOD);
	        
	        if (currentDate.isEqual(paymentDueDate.minusDays(3)) && 
	        		(!order.getPaymentStatus().equals(PaymentStatus.PAID))) {
	            Company tempComp = order.getCompany();

	            mail.sendMail(tempComp.getContact().getEmail(), sender,
						String.format("Payment due to %s", paymentDueDate),
						String.format(
								"You have a order that is not been payed yet.%nThis order has id: %s%nYours Sincerely %n%s",
								order.getOrderID(), tempComp.getName()));
				
	        }
	    });
	   
	    // Nuttig?
	    portaal = null;
	    mail = null;
	    System.gc();
	}
	
	// TODO
	// Moet nog eens volledig gerunt worden, maar denk wel dat het werkt
	/*
	int count = 0;
	
	public void sendPaymentReminders() {
	    LocalDate currentDate = LocalDate.now();
	    String sender = "sdpgroep@gmail.com";
	    
	    
	    
	    portaal.getOrdersList().forEach(order -> {
	    	
	        LocalDate paymentDueDate = order.getOrderDateTime().plusDays(Validation.PAYMENT_PERIOD);
	        
	        if (currentDate.isEqual(paymentDueDate.minusDays(3)) && 
	        		(!order.getPaymentStatus().equals(PaymentStatus.PAID))) {
	            Company tempComp = order.getCompany();
	            // Moet nog is volledig getest worden
	            
	            System.out.printf("%s %s ", order.getOrderDateTime(), order.getOrderID());
	            count += 1;
	            mail.sendMail(tempComp.getContact().getEmail(), sender,
						String.format("Payment due to %s", order.getOrderDateTime().plusDays(Validation.PAYMENT_PERIOD)),
						String.format(
								"You have a order that is not been payed yet.%nThis order has id: %s%nYours Sincerely %n%s",
								order.getOrderID(), tempComp.getName()));
				
	        }
	    });
	    
	    System.out.println(count);
	    
	    // Nuttig?
	    portaal = null;
	    mail = null;
	    System.gc();
	}
	*/
}
