package GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Main.ReadMailByIMAP;

public class Connect extends javax.swing.JFrame implements WindowListener {
	JLabel wel;
	JLabel lbuser;
	JLabel lbpass;
	JTextField user;
	JPasswordField pass;
	JButton clear;
	JButton con;
	JButton back;
	
	public Connect()
	{
	
		setTitle("Connect....");
		setLayout(null);
		setResizable(false);
		setBackground(Color.lightGray);
		setSize(400,250);
		
		wel=new JLabel("Connect to account");
		wel.setFont(new Font("Arial",1,24));
		wel.setBounds(80,25,405,20);
		wel.setForeground(Color.red);
		
		lbuser=new JLabel("User Name    :");
		lbuser.setBounds(25,75,80,20);
		user=new JTextField(100);
		user.setBounds(155,75,170,20);
			
		lbpass=new JLabel("Password     :");
		lbpass.setBounds(25,105,80,20);
		pass=new JPasswordField(100);
		pass.setBounds(155,105,170,20);	

		con=new JButton("Connect");
		con.setBackground(Color.darkGray);
		con.setForeground(Color.white);
		con.setBounds(25,165,100,30);
		
		clear=new JButton("Clear");
		clear.setForeground(Color.white);
		clear.setBackground(Color.darkGray);	
		clear.setBounds(145,165,100,30);
		
		back=new JButton("Back");
		back.setForeground(Color.white);
		back.setBackground(Color.darkGray);	
		back.setBounds(265,165,100,30);
		

		add(wel);
		add(lbuser);
		add(lbpass);
		add(user);
		add(pass);
		add(con);
		add(clear);
		add(back);
		setVisible(true);
		
		SymAction lSymAction = new SymAction();
		con.addActionListener(lSymAction);
		clear.addActionListener(lSymAction);
	    back.addActionListener(lSymAction);   
	    addWindowListener(this);
		}
	
	 class SymAction implements java.awt.event.ActionListener {

			public void actionPerformed(ActionEvent event) {
				 Object object = event.getSource();
			      if (object == con)
			        Connect_actionPerformed(event);
			      else if (object == clear)
			      	Clear_actionPerformed(event);
			      else if (object == back)
			    	  Back_actionPerformed(event);        
				
			}
	 private void Back_actionPerformed(ActionEvent event) {
				setVisible(false);
				(new MailClient()).show();
			}
	private void Clear_actionPerformed(ActionEvent event) {
		 	user.setText("");
		 	pass.setText("");
				
			}
	private void Connect_actionPerformed(ActionEvent event) {
		    String urs =user.getText();
		    String pwd = pass.getText();
		    ReadMailByIMAP rmbimap= new ReadMailByIMAP(urs, pwd);
		    if(rmbimap.connect()){
		    	setVisible(false);
		    	(new ReadMail(rmbimap)).show();
 			}
				
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
