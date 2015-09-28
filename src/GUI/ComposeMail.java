package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Main.SendEmail;




public class ComposeMail extends javax.swing.JFrame implements WindowListener{
	
	
	JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JLabel JLabel3 = new JLabel();
    JLabel JLabel4 = new JLabel();
    JLabel JLabel5 = new JLabel();
    JTextField _from = new JTextField();
    JTextField _to = new JTextField();
    JTextField _subject = new JTextField();
    JPasswordField _pwd = new JPasswordField();
    JScrollPane _scrollPane2 = new JScrollPane();
    JTextArea _body = new JTextArea();
    JButton Send = new JButton();
    JButton Clear = new JButton();  
    JButton Back = new JButton(); 
    JScrollPane _scrollPane =new JScrollPane();
  
    
	 public ComposeMail() {
		   
		    setTitle("Compose Mail");
		    getContentPane().setLayout(null);
		    setSize(470, 650);
		    setVisible(false);
		    setResizable(false);
		    
		    JLabel2.setText("From:");
		    getContentPane().add(JLabel2);
		    JLabel2.setBounds(12, 90, 36, 12);
		    JLabel1.setText("To:");
		    getContentPane().add(JLabel1);
		    JLabel1.setBounds(12, 145, 84, 12);
		    JLabel3.setText("Subject:");
		    getContentPane().add(JLabel3);
		    JLabel3.setBounds(12, 175, 48, 12);
		    JLabel4.setText("Password");
		    getContentPane().add(JLabel4);
		    JLabel4.setBounds(12, 110, 80, 30);
		    getContentPane().add(_from);
		    JLabel5.setText("                   Compose Mail");
		    getContentPane().add(JLabel5);
			JLabel5.setBounds(8,10,400,30);	
			JLabel5.setForeground(Color.red);
			JLabel5.setFont(new Font("Arial",1,24));
			JLabel5.setBackground(Color.black);		
		 
		    _from.setBounds(96, 80, 300, 24);
		    getContentPane().add(_to);
		    _to.setBounds(96, 140, 300, 24);
		    getContentPane().add(_subject);
		    _subject.setBounds(96, 170, 300, 24);
		    getContentPane().add(_pwd);
		    _pwd.setBounds(96, 110, 300, 24);
		    getContentPane().add(_scrollPane2); 
		    _scrollPane2.setBounds(12, 210, 384, 310);
		    _scrollPane2.getViewport().add(_body);
		    _body.setBounds(0, 0, 381, 105);
		    
		    Send.setText("Send");
		    Send.setActionCommand("Send");
		    Send.setForeground(Color.white);
			Send.setBackground(Color.darkGray);
		    getContentPane().add(Send);
		    Send.setBounds(15, 550, 132, 20);
		    Clear.setText("Clear");
		    Clear.setActionCommand("Clear");
	        Clear.setForeground(Color.white);
	     	Clear.setBackground(Color.darkGray);
            getContentPane().add(Clear);
	        Clear.setBounds(160, 550, 120, 20);
        	Back.setText("Back");
     	    Back.setActionCommand("Back");
	 	    Back.setForeground(Color.white);
			Back.setBackground(Color.darkGray);
		    getContentPane().add(Back);
		    Back.setBounds(300, 550, 120, 20);

		    
		    SymAction lSymAction = new SymAction();
		    Send.addActionListener(lSymAction);
		    Clear.addActionListener(lSymAction);
		    Back.addActionListener(lSymAction);   
		    addWindowListener(this);
		  }
	 
	 class SymAction implements java.awt.event.ActionListener {

		public void actionPerformed(ActionEvent event) {
			 Object object = event.getSource();
		      if (object == Send)
		        Send_actionPerformed(event);
		      else if (object == Clear)
		      	Clear_actionPerformed(event);
		      else if (object == Back)
		      	Back_actionPerformed(event);        
			
		}
		private void Back_actionPerformed(ActionEvent event) {
			setVisible(false);
			(new MailClient()).show();
		}
		void Send_actionPerformed(ActionEvent event) {
		    try {

		     SendEmail se= new SendEmail();
		     String usr= _from.getText();
		     String to = _to.getText();
		     String pwd = _pwd.getText();
		     String subject = _subject.getText();
		     String body = _body.getText();
		     se.setUsername(usr);
		     se.setPassword(pwd);
		     se.setTo(to);
		     se.setFrom(usr);
		     se.setSubject(subject);
		     se.setBody(body);
		     se.send();
		     

		    } catch (Exception e) {
		      
		    }

		  }
		
		void Clear_actionPerformed(ActionEvent event) {
			  _from.setText("");
			  _to.setText("");
			  _pwd.setText("");
			  _subject.setText("");
			  _body.setText("");
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
