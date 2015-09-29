package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Main.SendEmail;




public class ComposeMail extends javax.swing.JFrame implements WindowListener{
	
	private boolean check=false;
	
	JLabel lbto = new JLabel();
    JLabel lbfrom = new JLabel();
    JLabel lbsubject = new JLabel();
    JLabel lbpassword = new JLabel();
    JLabel lbtitle = new JLabel();
    JLabel lbpath = new JLabel();
    
    JTextField _from = new JTextField();
    JTextField _to = new JTextField();
    JTextField _subject = new JTextField();
    JPasswordField _pwd = new JPasswordField();
    JScrollPane _scrollPane2 = new JScrollPane();
    JTextArea _body = new JTextArea();
    JTextField _path = new JTextField();
    
    JButton Send = new JButton();
    JButton Clear = new JButton();  
    JButton Back = new JButton(); 
    JButton Option = new JButton();
    JButton attachment = new JButton();
    JButton browse = new JButton();
    JScrollPane _scrollPane =new JScrollPane();
  
    
	 public ComposeMail() {
		   
		    setTitle("Compose Mail");
		    getContentPane().setLayout(null);
		    setSize(470, 600);
		    setVisible(false);
		    setResizable(false);
		    
		    lbfrom.setText("From:");
		    getContentPane().add(lbfrom);
		    lbfrom.setBounds(12, 90, 36, 12);
		    lbto.setText("To:");
		    getContentPane().add(lbto);
		    lbto.setBounds(12, 145, 84, 12);
		    lbsubject.setText("Subject:");
		    getContentPane().add(lbsubject);
		    lbsubject.setBounds(12, 175, 48, 12);
		    lbpassword.setText("Password");
		    getContentPane().add(lbpassword);
		    lbpassword.setBounds(12, 110, 80, 30);
		    getContentPane().add(_from);
		    lbtitle.setText("Compose Mail");
		    getContentPane().add(lbtitle);
			lbtitle.setBounds(125,10,400,30);	
			lbtitle.setForeground(Color.red);
			lbtitle.setFont(new Font("Arial",1,24));
			lbtitle.setBackground(Color.black);		
		 
		    _from.setBounds(96, 80, 300, 24);
		    getContentPane().add(_to);
		    _to.setBounds(96, 140, 300, 24);
		    getContentPane().add(_subject);
		    _subject.setBounds(96, 170, 300, 24);
		    getContentPane().add(_pwd);
		    _pwd.setBounds(96, 110, 300, 24);
		    getContentPane().add(_scrollPane2); 
		    _scrollPane2.setBounds(12, 210, 430, 280);
		    _scrollPane2.getViewport().add(_body);
		    _body.setBounds(0, 0, 381, 105);
		    
		    Send.setText("Send");
		    Send.setActionCommand("Send");
		    Send.setForeground(Color.white);
			Send.setBackground(Color.darkGray);
		    getContentPane().add(Send);    
		    Send.setBounds(20, 520, 90, 30);
		    
		    Clear.setText("Clear");
		    Clear.setActionCommand("Clear");
	        Clear.setForeground(Color.white);
	     	Clear.setBackground(Color.darkGray);
            getContentPane().add(Clear);   
	        Clear.setBounds(130, 520, 90, 30);
	        
        	Back.setText("Back");
     	    Back.setActionCommand("Back");
	 	    Back.setForeground(Color.white);
			Back.setBackground(Color.darkGray);
		    getContentPane().add(Back);
		    Back.setBounds(240, 520, 90, 30);
		    
        	Option.setText("Option");
     	    Option.setActionCommand("Option");
	 	    Option.setForeground(Color.white);
			Option.setBackground(Color.darkGray);
		    getContentPane().add(Option);
		    Option.setBounds(350, 520, 90, 30);
		    
		    attachment.setText("Attachment");
     	    attachment.setActionCommand("Attachment");
	 	    attachment.setForeground(Color.white);
			attachment.setBackground(Color.darkGray);
		    attachment.setBounds(20, 570, 90, 20);
		    
		    lbpath.setText("Path:");
		    lbpath.setBounds(12, 620, 36, 12);
		    _path.setBounds(70, 620, 250, 24);
		    
		    browse.setText("Browse");
		    browse.setActionCommand("Browse");
	 	    browse.setForeground(Color.white);
			browse.setBackground(Color.darkGray);
		    browse.setBounds(350, 620, 80, 20);
		    
		    SymAction lSymAction = new SymAction();
		    Send.addActionListener(lSymAction);
		    Clear.addActionListener(lSymAction);
		    Back.addActionListener(lSymAction);   
		    Option.addActionListener(lSymAction);
		    attachment.addActionListener(lSymAction);
		    browse.addActionListener(lSymAction);
		    
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
		      else if (object == Option)
			      	Option_actionPerformed(event);
		      else if (object == attachment)
		    	    Attachment_actionPerformed(event);
		      else if (object == browse)
		    	    browse_actionPerformed(event);
			
		}
		private void browse_actionPerformed(ActionEvent event) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);				
			if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(getParent()))
				_path.setText(fc.getSelectedFile().getPath());
			
		}
		private void Attachment_actionPerformed(ActionEvent event) {
			setSize(470, 680);
		    getContentPane().add(lbpath);
		    getContentPane().add(_path);
		    getContentPane().add(browse);
		}
		private void Option_actionPerformed(ActionEvent event) {
		    if(!check){
		    	setSize(470, 650);	
		    	getContentPane().add(attachment);
		    	check=true;
			}
		    else{
		    	getContentPane().remove(lbpath);
		    	getContentPane().remove(_path);
		    	getContentPane().remove(browse);
		    	getContentPane().remove(attachment);
		    	setSize(470, 600);
		    	check=false;
		    }
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
		     String path= _path.getText();
		     se.setUsername(usr);
		     se.setPassword(pwd);
		     se.setTo(to);
		     se.setFrom(usr);
		     se.setSubject(subject);
		     se.setBody(body);
		     se.setPath(path);
		     if(path!=""){
		    	 se.send(false);
		     }
		     else se.send(true);
		     

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
