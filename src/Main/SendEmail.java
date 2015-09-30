package Main;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

public class SendEmail {
	private  String to;
	private  String from;
	private  String username;
	private  String password;
	private  String subject; 
	private  String body;
	private  String path;
	
	
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



	public void setBody(String body) throws UnsupportedEncodingException {
		this.body = body;
	}



	public  void send(boolean check) throws UnsupportedEncodingException {
		
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
			
			
			//send message with attachment
			if(check){
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(body);
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				String filename = path;
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);
		       
				message.setContent(multipart);
	         }

			// Send message
			Transport.send(message);
			JOptionPane.showMessageDialog(null, "Success!");
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
			throw new RuntimeException(e);
			
		}
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
}