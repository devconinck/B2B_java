package util;

import java.time.LocalDate;

import domain.B2BPortaal;
import domain.Company;
import domain.CompanyUpdateRequest;
import domain.OrderItem;
import repository.AccountDaoJpa;
import repository.GenericDaoJpa;
import repository.OrderDaoJpa;

public class PaymentReminder {
	private B2BPortaal portaal;
	private SendMail mail;
	
	public PaymentReminder() {
		this.portaal = new B2BPortaal(new GenericDaoJpa<Company>(Company.class), new OrderDaoJpa(), new GenericDaoJpa<OrderItem>(OrderItem.class), new GenericDaoJpa<CompanyUpdateRequest>(CompanyUpdateRequest.class), new AccountDaoJpa());
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
	            Company tempComp = order.getFromCompany();

	            mail.sendMail(tempComp.getContact().getEmail(), sender,
						String.format("Payment due to %s", paymentDueDate),
						String.format(
								"You have a order that is not been payed yet.%nThis order has id: %s%nYours Sincerely %n%s",
								order.getOrderID(), tempComp.getName()));
				
	        }
	    });
	   
	    portaal = null;
	    mail = null;
	    System.gc();
	}
}
