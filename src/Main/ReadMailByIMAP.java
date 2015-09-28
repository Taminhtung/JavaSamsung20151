package Main;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class ReadMailByIMAP {
	public void processMessageBody(Message message) {
		try {
			Object content = message.getContent();
			// check for string
			// then check for multipart
			if (content instanceof String) {
				System.out.println(content);
			} else if (content instanceof Multipart) {
				Multipart multiPart = (Multipart) content;
				procesMultiPart(multiPart);
			} else if (content instanceof InputStream) {
				InputStream inStream = (InputStream) content;
				int ch;
				while ((ch = inStream.read()) != -1) {
					System.out.write(ch);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void procesMultiPart(Multipart content) {
		try {
			int multiPartCount = content.getCount();
//			for (int i = 0; i < multiPartCount; i++) {
				BodyPart bodyPart = content.getBodyPart(0);
				Object o;
				o = bodyPart.getContent();
				if (o instanceof String) {
					System.out.println(o);
				} else if (o instanceof Multipart) {
					procesMultiPart((Multipart) o);
				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void read(String username, String password) {
		ReadMailByIMAP readMailByIMAP = new ReadMailByIMAP();
		Properties props = new Properties();

		props.setProperty("mail.store.protocol", "imaps");

		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", "tungtm9358@gmail.com", "100kglpcelovemu");
			Folder[] folderList = store.getFolder("[Gmail]").list();
			for (int i = 0; i < folderList.length; i++) {
				System.out.println(folderList[i].getFullName());
			}
			Folder inbox = store.getFolder("inbox");
			inbox.open(Folder.READ_ONLY);
			
			int count = inbox.getMessageCount();
			for(int i=0; i<=count; i++){
				Message msg = inbox.getMessage(count-i);
				Address[] in = msg.getFrom();
				for (Address address : in) {
					System.out.println("FROM:" + address.toString());
				}
				// Multipart mp = (Multipart) msg.getContent();
				// BodyPart bp = mp.getBodyPart(0);
				System.out.println("Bcc User NAme :"
					+ InternetAddress.toString(msg
							.getRecipients(Message.RecipientType.BCC)));
				System.out.println("SENT DATE:" + msg.getSentDate());
				System.out.println("SUBJECT:" + msg.getSubject());
				System.out.println("CONTENT:");
				readMailByIMAP.processMessageBody(msg);
			}
			store.close();
		} catch (Exception mex) {
			mex.printStackTrace();

		}
	}
}
