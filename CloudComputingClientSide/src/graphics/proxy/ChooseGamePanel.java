package graphics.proxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mediator.Mediator;

public class ChooseGamePanel extends JPanel implements IProxyGraphic {

	private JLabel message= new JLabel("Scegli a quale gioco giocare: ");
	private List<String> games= new ArrayList<String>();
	private JButton button;
	public ChooseGamePanel() {
		add(message);
		games.addAll(Mediator.getMed().getAvailableGames());
		for (int i = 0; i < games.size(); i++) {
			button= new JButton(games.get(i));
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Mediator.getGMed().setRoomsPanel(button.getText());//TODO: aggiungere parametro per scegliere le rooms giuste;
					
				}
			});
			add(button);
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
