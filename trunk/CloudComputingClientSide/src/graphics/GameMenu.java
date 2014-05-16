package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import mediator.Mediator;

public class GameMenu extends JMenu{

	private JTextField field= new JTextField();
	private JButton button= new JButton("OK");
	private JDialog dialog;

	public GameMenu() {
		super("Game");
		
		JMenuItem newTable= new JMenuItem("New Table");
		newTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog= new JDialog();
				dialog.setSize(new Dimension(300, 100));
				dialog.setLocationRelativeTo(null);
				dialog.setName("New Table");
				dialog.setLayout(new GridLayout(2, 1));
				dialog.add(new JLabel("Name your table:"));
				dialog.add(field);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (field.getText().isEmpty()) {
							Mediator.getMed().getComputator().addNewTable("Come to Play");
							
						}else {
							Mediator.getMed().getComputator().addNewTable(field.getText());
						}
						
						dialog.dispose();
					}
				});
				dialog.add(button);
				dialog.setVisible(true);
				
			}
		});
		add(newTable);
	}
	
	
	
	
}
