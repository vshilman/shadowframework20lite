package graphics.proxy;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mediator.Mediator;

public class LoggingPanel extends JPanel implements IProxyGraphic{

	private JTextField username= new JTextField();
	private JLabel nick= new JLabel("Username: ");
	private JPasswordField password= new JPasswordField();
	private JLabel pass= new JLabel("Password: ");
	private JButton login= new JButton("Login!");
	private JButton logout= new JButton("Logout!");
	private String passwordToSend;
	private JLabel altro= new JLabel();
	
	
	public LoggingPanel() {
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				passwordToString(password.getPassword());
				Mediator.getCMed().getConnection().login(username.getText(),passwordToSend);
			}
		});

//		logout.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				Mediator.getCMed().logout();
//				
//			}
//		});

		setLayout(new GridLayout(4,2));
		add(nick);
		add(username);
		add(pass);
		add(password);
		add(altro);
		add(login);
	}
	
	private void passwordToString(char[] pass) {
		 passwordToSend="";
		for (int i = 0; i < pass.length; i++) {
			passwordToSend+=pass[i];
		}

	}
	
	@Override
	public void refreshPanel() {
		repaint();
		
	}
	
	@Override
	public JPanel setUpPanel() {

		return this;
		
	}
}
