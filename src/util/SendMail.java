package util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SendMail {
	
	private final static String HOST = "smtp.gmail.com";
	private final static String USERNAME = "sdpgroep@gmail.com";
	private final static String PASSWORD = "mgjdxqxqwypruomi";
	private final Properties props;
	private final Authenticator authenticator;
	private final Session session;

	public SendMail() {
		props = new Properties();
		props.put("mail.smtp.host", HOST);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    authenticator = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(USERNAME, PASSWORD);
	        }
	    };
	    session = Session.getInstance(props, authenticator);
	    
	    //sendMail("sdpgroep@gmail.com", "test@gmail.com", "Test function", "dit moet de body zijn");
	}

	public void sendMail(String toEmail, String fromEmail, String subject, String content) {
	    // configure Gmail's SMTP server details
	    try {
	        // create a MimeMessage object
	        Message message = new MimeMessage(session);
	        // set From email field
	        message.setFrom(new InternetAddress(fromEmail));
	        // set To email field
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	        // set email subject field
	        message.setSubject(subject);
	        // set the content of the email message
	        message.setContent(content, "text/html");
	        // send the email message
	        Transport.send(message);
	        System.out.println("Email Message Sent Successfully");
	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
}
