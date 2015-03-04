package graphics.proxy.buttonFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import mediator.Mediator;

public class GameButton extends JButton implements IButton{

	
	
	public GameButton(String gameType) {
		super(gameType);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(getText());
				Mediator.getGMed().setRoomsPanel(getText());
				
			}
		});
	}
	
	@Override
	public JButton getButton() {
		return this;
	}
}
