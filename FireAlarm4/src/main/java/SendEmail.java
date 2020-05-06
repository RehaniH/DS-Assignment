import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//Email sender class
public class SendEmail {

	public SendEmail(String to, final String from, final String password) {
		// TODO Auto-generated method stub

	      // Get system properties
	      Properties properties = new Properties();
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.port", 587);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
	    	  protected PasswordAuthentication getPasswordAuthentication() {
	    		  return new PasswordAuthentication(from, password);
	    	  }
	      });

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("Alert");

	         // Now set the actual message
	         message.setText("Alert: One or more of the Overnight Coders® sensors have been triggered");

	         // Send message
	         Transport.send(message);
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	
//	public static void main(String args[]) {
//		SendEmail v = new SendEmail("ranulds@gmail.com","overnightcoders92@gmail.com","overnightcoders92admin");
//		System.out.println("sent");
//	}

}
