package util;

import java.time.LocalDate;

import domain.B2BPortaal;
import domain.Company;
import domain.Order;
import domain.OrderItem;
import mail.SendMail;
import repository.GenericDaoJpa;

public class PaymentReminder {

	// Haal alle order op met orderDate + payment period <= 3?
	private B2BPortaal portaal;
	private SendMail mail;
	
	public PaymentReminder() {
		this.portaal = new B2BPortaal(new GenericDaoJpa<Company>(Company.class), new GenericDaoJpa<Order>(Order.class), new GenericDaoJpa<OrderItem>(OrderItem.class));
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
	            // Moet nog is volledig getest worden
	            /*
	            mail.sendMail(tempComp.getContact().getEmail(), sender,
						String.format("Payment due to %s", order.getOrderDateTime().plusDays(Validation.PAYMENT_PERIOD)),
						String.format(
								"You have a order that is not been payed yet.%nThis order has id: %s%nYours Sincerely %n%s",
								order.getOrderID(), tempComp.getName()));
				*/
	        }
	    });
	    
	    // Nuttig?
	    portaal = null;
	    mail = null;
	    System.gc();
	}
	
	
}
