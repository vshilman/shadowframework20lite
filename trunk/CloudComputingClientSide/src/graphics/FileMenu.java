package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import mediator.Mediator;

import connection.Connector;

public class FileMenu extends JMenu{

	private JDialog dialog;
	public FileMenu(JFrame mainFrame, final Connector connection) {
		super("File");
		
		JMenuItem changeIp= new JMenuItem("Set Ip Address...");
		
		dialog= new JDialog(mainFrame, "Change target IP");
		dialog.setMinimumSize(new Dimension(300, 200));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(false);
		dialog.setLayout(new GridLayout(2,1));
		final JTextField field= new JTextField();
		dialog.add(field);
		JButton button= new JButton("Change it!");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connection.setIp(field.getText());
				dialog.dispose();
			}
		});
		dialog.add(button);
		changeIp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				dialog.setVisible(true);
			}
		});
		
		add(changeIp);
		
		
		
		JMenuItem logout=new JMenuItem("Logout");
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Mediator.getMed().getComputator().validateLogout();
				
			}
		});
		
		add(logout);
		
		
		
		
		
		
	}
	
	
	
}
