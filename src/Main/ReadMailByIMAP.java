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
					s+=ch;
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		}
		return s;
	}

	public String procesMultiPart(Multipart content) {
		String s="";
		try {
//			int multiPartCount = content.getCount();
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
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
			
			return false;
		}
	}

	
	
	public int gettotalEmail(int choice){
		try {
			if(choice>=0)
				folder = fld[choice];
			return folder.getMessageCount();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		    return 0; 	
		} 
		
	}
	
	public void openfolder(int choice){
		
		try {
			if(choice>=0) 
				folder = fld[choice];
			folder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		}
	}
	
	
	public String getfrom(int choice, int n) throws Exception {
		String s="";
		int t;
		if(choice>=0) 
			folder = fld[choice];
		t = folder.getMessageCount();
		if(n>t) n=t;
		else if(n<1) n=1;
		Message msg= folder.getMessage(n);
		Address[] in;
		in = msg.getFrom();
		for (Address address : in) 
			s+=address.toString();
		return s;
			
	}
	
	public String getto(int choice, int n) throws Exception {
		String s="";
		int t;
		if(choice>=0) 
			folder = fld[choice];
		t = folder.getMessageCount();
		if(n>t) n=t;
		else if(n<1) n=1;
		Message msg= folder.getMessage(n);
		s+=InternetAddress.toString(msg.getRecipients(Message.RecipientType.TO));
		return s;
			
	}
	
	public String getbcc(int choice, int n) throws Exception {
		String s="";
		int t;
		if(choice>=0) 
			folder = fld[choice];
		t = folder.getMessageCount();
		if(n>t) n=t;
		else if(n<1) n=1;
		Message msg= folder.getMessage(n);
		s+=InternetAddress.toString(msg.getRecipients(Message.RecipientType.BCC));
		return s;
	}
	
	public String getsubject(int choice, int n) throws Exception {
		String s="";
		int t;
		if(choice>=0) 
			folder = fld[choice];
		t = folder.getMessageCount();
		if(n>t) n=t;
		else if(n<1) n=1;
		Message msg= folder.getMessage(n);
		s+=msg.getSubject();
		return s;
	}
	public String getSenddate(int choice, int n) throws Exception {
		String s="";
		int t;
		if(choice>=0) 
			folder = fld[choice];
		t = folder.getMessageCount();
		if(n>t) n=t;
		else if(n<1) n=1;
		Message msg= folder.getMessage(n);
		s+=msg.getSentDate();
		return s;
	}
	
	public String getContent(int choice, int n) throws Exception{
		String s="";
		int t;
		if(choice>=0) 
			folder = fld[choice];
		t = folder.getMessageCount();
		if(n>t) n=t;
		else if(n<1) n=1;
		Message msg= folder.getMessage(n);
		s+=processMessageBody(msg);
		return s;
	}
	
	public void closefoder(int choice){
		
		try {
			if(choice>=0) 
				folder = fld[choice];
			if(folder.isOpen())
				folder.close(true);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		}
	}
	
	public void close(){
		try {
			if(store.isConnected())
				store.close();
		} catch (MessagingException e) {
			
			JOptionPane.showMessageDialog(null, "Error!"+e.getMessage());
		}
	}
}
