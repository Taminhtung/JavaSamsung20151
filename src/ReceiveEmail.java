import java.util.Iterator;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 * This class is used to receive simple email.
 * 
 * @author javawithease
 */
public class ReceiveEmail {
	public static void receiveEmail(String pop3Host, String mailStoreType,
			String userName, String password) {
		// Set properties
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", pop3Host);
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.starttls.enable", "true");

		// Get the Session object.
		Session session = Session.getInstance(props);

		try {
			// Create the POP3 store object and connect to the pop store.
			Store store = session.getStore("pop3s");
			store.connect(pop3Host, userName, password);
			// ///

			// Create the folder object and open it in your mailbox.
			Folder emailFolder = store.getFolder("inbox");
			//

			//
			emailFolder.open(Folder.READ_ONLY);
			// emailFolder.
			// Retrieve the messages from the folder object.
			Message[] messages = emailFolder.getMessages();
			System.out.println("Total Message" + messages.length);

			// Iterate the messages
			for (int i = 0; i < 5; i++) {
				Message message = messages[i];
				// messages[i].
				Address[] toAddress = message
						.getRecipients(Message.RecipientType.TO);
				System.out.println("---------------------------------");
				System.out
						.println("Details of Email Message " + (i + 1) + " :");
				System.out.println("From: " + message.getFolder());
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);

				// Iterate recipients
				System.out.println("To: ");
				for (int j = 0; j < toAddress.length; j++) {
					System.out.println(toAddress[j].toString());
				}
				System.out.println("Text: " + message.getContent().toString());
			}

			// close the folder and store objects
			emailFolder.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String pop3Host = "pop.gmail.com";// change accordingly
		String mailStoreType = "pop3s";
		final String userName = "nhbo93";// change accordingly
		final String password = "zobinnabcd";// change accordingly

		// call receiveEmail
		receiveEmail(pop3Host, mailStoreType, userName, password);
	}
}