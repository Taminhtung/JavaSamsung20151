package GUI;

import java.awt.*;

import javax.mail.search.SentDateTerm;
import javax.swing.*;



public class ReadMail extends javax.swing.JFrame {
	JComboBox<String> cbb;
	JLabel wel;
	JLabel folder;
	JLabel lbTotalEmail;
	JTextField totalEmail; 
	JLabel lbNoEmail;
	JTextField noEmail;
	JLabel lbFrom;
	JTextField from;
	JLabel lbBCC;
	JTextField bcc;
	JLabel lbSenddate;
	JTextField senddate;
	JLabel lbContent;
	JScrollPane scrollPane;
    JTextArea content;
	JButton read;
	JButton prev;
	JButton next;
	JButton back;
	public ReadMail() {
		setTitle("Read Mail By IMAP....");
		setLayout(null);
		//setResizable(false);
		setBackground(Color.lightGray);
		setSize(500,650);
		setVisible(true);
		
		wel=new JLabel("Read email");
		wel.setFont(new Font("Arial",1,24));
		wel.setBounds(160,15,405,20);
		wel.setForeground(Color.red);
		
		folder = new JLabel("Folder:");
		folder.setBounds(25,50,80,20);
		cbb = new JComboBox<String>();
		cbb.setBounds(185, 50 , 100, 20);
		cbb.addItem("Inbox");
		cbb.addItem("Outbox");
		cbb.addItem("Spam");
		cbb.addItem("Recycle bin");
		
		lbTotalEmail=new JLabel("Total Email    :");
		lbTotalEmail.setBounds(25,85,80,20);
		totalEmail=new JTextField(100);
		totalEmail.setBounds(185,85,50,20);
		totalEmail.setEditable(false);
		
		lbNoEmail=new JLabel("Email    :");
		lbNoEmail.setBounds(25,115,80,20);
		noEmail=new JTextField(100);
		noEmail.setBounds(185,115,50,20);
		
		lbFrom=new JLabel("From/To    :");
		lbFrom.setBounds(25,145,80,20);
		from=new JTextField(100);
		from.setBounds(185,145,150,20);
		from.setEditable(false);
		
		lbBCC=new JLabel("BCC    :");
		lbBCC.setBounds(25,175,80,20);
		bcc=new JTextField(100);
		bcc.setBounds(185,175,150,20);
		bcc.setEditable(false);
		
		lbSenddate=new JLabel("Send Date    :");
		lbSenddate.setBounds(25,205,80,20);
		senddate=new JTextField(100);
		senddate.setBounds(185,205,150,20);
		senddate.setEditable(false);
		
		lbContent=new JLabel("Content    :");
		lbContent.setBounds(25,235,80,20);
		
		scrollPane = new JScrollPane();
		content = new JTextArea();
		scrollPane.setBounds(25, 265, 420, 280);
	    scrollPane.getViewport().add(content);
	    content.setBounds(0, 0, 200, 100);
	    content.setEditable(false);
	    
	    read=new JButton("Read");
		read.setBackground(Color.darkGray);
		read.setForeground(Color.white);
		read.setBounds(25,570,90,30);
		
		prev=new JButton("Prev");
		prev.setBackground(Color.darkGray);
		prev.setForeground(Color.white);
		prev.setBounds(135,570,90,30);
		
		next=new JButton("Next");
		next.setBackground(Color.darkGray);
		next.setForeground(Color.white);
		next.setBounds(245,570,90,30);
		
		back=new JButton("Back");
		back.setBackground(Color.darkGray);
		back.setForeground(Color.white);
		back.setBounds(355,570,90,30);
		
		
		
		add(wel);
		add(folder);
		add(cbb);
		add(lbTotalEmail);
		add(totalEmail);
		add(lbNoEmail);
		add(noEmail);
		add(lbFrom);
		add(from);
		add(lbBCC);
		add(bcc);
		add(lbSenddate);
		add(senddate);
		add(lbContent);
		add(scrollPane);
		add(read);
		add(next);
		add(prev);
		add(back);
		
	}
}
