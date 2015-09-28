package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Main.ReadMailByIMAP;



public class ReadMail extends javax.swing.JFrame implements WindowListener {
	private ReadMailByIMAP rm;
	private int total=0;
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
	public ReadMail(ReadMailByIMAP rm) {
		setTitle("Read Mail By IMAP....");
		setLayout(null);
		setResizable(false);
		setBackground(Color.lightGray);
		setSize(690,650);
		setVisible(true);
		
		wel=new JLabel("Read email");
		wel.setFont(new Font("Arial",1,24));
		wel.setBounds(260,15,465,20);
		wel.setForeground(Color.red);
		
		folder = new JLabel("Folder:");
		folder.setBounds(25,50,80,20);
		cbb = new JComboBox<String>();
		cbb.setBounds(200, 50 , 130, 20);
		cbb.addItem("Inbox");
		cbb.addItem("Important");
		cbb.addItem("Outbox");
		cbb.addItem("Spam");
		cbb.addItem("Recycle bin");
		
		lbTotalEmail=new JLabel("Total Email    :");
		lbTotalEmail.setBounds(25,85,80,20);
		totalEmail=new JTextField(100);
		totalEmail.setBounds(200,85,70,20);
		totalEmail.setEditable(false);
		
		lbNoEmail=new JLabel("Email    :");
		lbNoEmail.setBounds(25,115,80,20);
		noEmail=new JTextField(100);
		noEmail.setText("1");
		noEmail.setBounds(200,115,70,20);
		noEmail.setEditable(false);
		
		lbFrom=new JLabel("From/To    :");
		lbFrom.setBounds(25,145,80,20);
		from=new JTextField(100);
		from.setBounds(200,145,300,20);
		from.setEditable(false);
		
		lbBCC=new JLabel("BCC    :");
		lbBCC.setBounds(25,175,80,20);
		bcc=new JTextField(100);
		bcc.setBounds(200,175,300,20);
		bcc.setEditable(false);
		
		lbSenddate=new JLabel("Send Date    :");
		lbSenddate.setBounds(25,205,80,20);
		senddate=new JTextField(100);
		senddate.setBounds(200,205,300,20);
		senddate.setEditable(false);
		
		lbContent=new JLabel("Content    :");
		lbContent.setBounds(25,235,80,20);
		
		scrollPane = new JScrollPane();
		content = new JTextArea();
		scrollPane.setBounds(25, 265, 630, 280);
	    scrollPane.getViewport().add(content);
	    content.setBounds(0, 0, 300, 100);
	    content.setEditable(false);
	    
	    read=new JButton("Read");
		read.setBackground(Color.darkGray);
		read.setForeground(Color.white);
		read.setBounds(45,570,120,30);
		
		prev=new JButton("Prev");
		prev.setBackground(Color.darkGray);
		prev.setForeground(Color.white);
		prev.setBounds(200,570,120,30);
		
		next=new JButton("Next");
		next.setBackground(Color.darkGray);
		next.setForeground(Color.white);
		next.setBounds(355,570,120,30);
		
		back=new JButton("Back");
		back.setBackground(Color.darkGray);
		back.setForeground(Color.white);
		back.setBounds(510,570,120,30);
		
		prev.setEnabled(false);
	    next.setEnabled(false);
	    
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
		
		SymAction lSymAction = new SymAction();
		read.addActionListener(lSymAction);
		prev.addActionListener(lSymAction);
		next.addActionListener(lSymAction);
		back.addActionListener(lSymAction);
		
		this.rm=rm;
		addWindowListener(this);
	}
	
	class SymAction implements java.awt.event.ActionListener {

		public void actionPerformed(ActionEvent event) {
			 Object object = event.getSource();
		      if (object == read)
		        Read_actionPerformed(event);
		      else if (object == prev)
		      	Prev_actionPerformed(event);
		      else if (object == next)
			    Next_actionPerformed(event);
		      else if (object == back)
		      	Back_actionPerformed(event);        
			
		}

		private void Back_actionPerformed(ActionEvent event) {
			setVisible(false);
			(new Connect()).show();
			
		}

		private void Next_actionPerformed(ActionEvent event) {
				int n= Integer.parseInt(noEmail.getText());
				n=n+1;
				if(n>total || n<1) n=1;
				noEmail.setText(""+n);
			
		}

		private void Prev_actionPerformed(ActionEvent event) {
				int n= Integer.parseInt(noEmail.getText());
				n=n-1;
				if(n<1 || n>total) n=total; 
	
				noEmail.setText(""+n);
			
			
		}

		private void Read_actionPerformed(ActionEvent event) {
			    prev.setEnabled(false);
			    next.setEnabled(false);
				int choice;
				int n;
				switch(cbb.getSelectedIndex()){
				case 0: choice =-1;break;
				case 1: choice=0;break;
				case 2: choice=5;break;
				case 3: choice=1;break;
				default: choice=2;break;
				}
			
				total = rm.gettotalEmail(choice);
				totalEmail.setText(""+total);
				rm.openfolder(choice);
				n= Integer.parseInt(noEmail.getText());
				from.setText(rm.getaddress(choice, n));
				bcc.setText(rm.getbcc(choice, n));
				senddate.setText(rm.getSenddate(choice, n));
				content.setText(rm.getContent(choice, n));
				rm.closefoder(choice);
				prev.setEnabled(true);
			    next.setEnabled(true);
			
		}
		
		}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	    
}
