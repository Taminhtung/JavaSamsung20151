import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendEmail {
	private  String to;
	private  String from;
	private  String username;
	private  String password;
	private  String subject; 
	private  String body;
	
	
	
	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public  void send() throws UnsupportedEncodingException {
		// Recipient's email ID needs to be mentioned.
		//to = "tungtm9358@gmail.com";

		// Sender's email ID needs to be mentioned
		//from = "tungtm9358@gmail.com";
		//username = "tungtm9358@gmail.com";// change accordingly
		//password = "100kglpcelovemu";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
		
			message.setSubject(subject);

			// Now set the actual message
		 
			message.setText(body);

			// Send message
			Transport.send(message);
			JOptionPane.showMessageDialog(null, "Success!");
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!");
			throw new RuntimeException(e);
			
		}
	}
}