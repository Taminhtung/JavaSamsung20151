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
import javax.swing.JOptionPane;

public class ReadMailByIMAP {
	private String ursername;
	private String password;
	private Store store;
	private Folder[] fld;
	private Folder folder;
	

	public ReadMailByIMAP(String ursername, String password) {
		super();
		this.ursername = ursername;
		this.password = password;
	}

	public String getUrsername() {
		return ursername;
	}



	public void setUrsername(String ursername) {
		this.ursername = ursername;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String processMessageBody(Message message) {
		String s="";
		try {
			Object content = message.getContent();
			// check for string
			// then check for multipart
			if (content instanceof String) {
				 s+=(String) content;
			} else if (content instanceof Multipart) {
				Multipart multiPart = (Multipart) content;
				s+=procesMultiPart(multiPart);
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
		return s;
	}

	public String procesMultiPart(Multipart content) {
		String s="";
		try {
			int multiPartCount = content.getCount();
//			for (int i = 0; i < multiPartCount; i++) {
				BodyPart bodyPart = content.getBodyPart(0);
				Object o;
				o = bodyPart.getContent();
				if (o instanceof String) {
					s+= (String) o;
				} else if (o instanceof Multipart) {
					s+=procesMultiPart((Multipart) o);
				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public boolean connect(){
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try{
			Session session = Session.getInstance(props, null);
			store = session.getStore();
			store.connect("imap.gmail.com", ursername, password);
			JOptionPane.showMessageDialog(null, "Connected!");
			fld = store.getFolder("[Gmail]").list();
			folder = store.getFolder("Inbox");
			return true;
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error!");
			return false;
		}
	}

	
	
	public int gettotalEmail(int choice){
		try {
			if(choice>=0)
				folder = fld[choice];
			return folder.getMessageCount();
		} catch (MessagingException e) {
		    return 0; 	
		} 
		
	}
	
	public void openfolder(int choice){
		
		try {
			if(choice>=0) 
				folder = fld[choice];
			folder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getaddress(int choice, int n) {
		String s="";
		int t;
		try {
			if(choice>=0) 
				folder = fld[choice];
			t = folder.getMessageCount();
			if(n>t) n=t;
			else if(n<1) n=1;
			Message msg= folder.getMessage(n);
			Address[] in;
			if(choice!=2)
				in = msg.getFrom();
			else in=msg.getReplyTo();
			for (Address address : in) {
					s+=address.toString();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return s;
			
	}
	
	public String getbcc(int choice, int n) {
		String s="";
		int t;
		try {
			if(choice>=0) 
				folder = fld[choice];
			t = folder.getMessageCount();
			if(n>t) n=t;
			else if(n<1) n=1;
			Message msg= folder.getMessage(n);
			s+=InternetAddress.toString(msg.getRecipients(Message.RecipientType.BCC));
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return s;
	}
	
	public String getSenddate(int choice, int n) {
		String s="";
		int t;
		try {
			if(choice>=0) 
				folder = fld[choice];
			t = folder.getMessageCount();
			if(n>t) n=t;
			else if(n<1) n=1;
			Message msg= folder.getMessage(n);
			s+=msg.getSentDate();
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return s;
	}
	
	public String getContent(int choice, int n) {
		String s="";
		int t;
		try {
			if(choice>=0) 
				folder = fld[choice];
			t = folder.getMessageCount();
			if(n>t) n=t;
			else if(n<1) n=1;
			Message msg= folder.getMessage(n);
			s+=processMessageBody(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return s;
	}
	
	public void closefoder(int choice){
		
		try {
			if(choice>=0) 
				folder = fld[choice];
			folder.close(true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
