package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import GUI.ComposeMail.SymAction;
import Main.SendEmail;


public class MailClient extends JFrame implements WindowListener{
	JLabel wel;
	JButton SendEmail;
	JButton ReadEmail;
	
	public MailClient() {
		setTitle("Java Mail Client.");
		setLayout(null);
		setResizable(false);
		setBackground(Color.lightGray);
		setSize(400,150);
		
		wel=new JLabel(" Java Mail Client");
		wel.setFont(new Font("Arial",1,24));
		wel.setBounds(100,25,405,20);
		wel.setForeground(Color.red);
		
		SendEmail=new JButton("Send Email");
		SendEmail.setBackground(Color.darkGray);
		SendEmail.setForeground(Color.white);
		SendEmail.setBounds(25,75,150,30);
		
		ReadEmail=new JButton("Read Email");
		ReadEmail.setBackground(Color.darkGray);
		ReadEmail.setForeground(Color.white);
		ReadEmail.setBounds(200,75,150,30);
		
		add(wel);
		add(SendEmail);
		add(ReadEmail);
		
		setVisible(true);
		
		SymAction lSymAction = new SymAction();
	    SendEmail.addActionListener(lSymAction);
	    ReadEmail.addActionListener(lSymAction); 
	    
	    addWindowListener(this);
		
	}
	
	class SymAction implements java.awt.event.ActionListener {

		public void actionPerformed(ActionEvent event) {
			 Object object = event.getSource();
		      if (object == SendEmail)
		        SendEmail_actionPerformed(event);
		      else if (object == ReadEmail)
		      	ReadEmail_actionPerformed(event);        
			
		}
	
		private void ReadEmail_actionPerformed(ActionEvent event) {
			setVisible(false);
			(new Connect()).show();
			
		}

		private void SendEmail_actionPerformed(ActionEvent event) {
			setVisible(false);
			(new ComposeMail()).show();
			
		}
	}
	
	public static void main(String args[]) {
		  (new MailClient()).show(); 
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
