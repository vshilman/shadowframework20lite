package graphics.proxy;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JLabel chooseAGame=new JLabel("Choose a Game: ");
	private JComboBox<String> games;
	private String passwordToSend;
	private JLabel altro= new JLabel();
	
	
	public LoggingPanel() {
		List<String> gamesList= Mediator.getMed().getAvailableGames();
		String[] gamesNames= new String[gamesList.size()];
		for (int i = 0; i < gamesList.size(); i++) {
			gamesNames[i]=gamesList.get(i);
		}
		games= new JComboBox<String>(gamesNames);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				passwordToString(password.getPassword());
				Mediator.getCMed().getConnection().login(username.getText(),passwordToSend, (String)games.getSelectedItem());
			}
		});
		password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar()=='\n') {
					passwordToString(password.getPassword());
					Mediator.getCMed().getConnection().login(username.getText(),passwordToSend, (String)games.getSelectedItem());
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		nick.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				passwordToString(password.getPassword());
				Mediator.getCMed().getConnection().login(username.getText(),passwordToSend, (String)games.getSelectedItem());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
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
		add(chooseAGame);
		add(games);
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
