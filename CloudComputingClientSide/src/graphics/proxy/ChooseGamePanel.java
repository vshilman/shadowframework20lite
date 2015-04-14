package graphics.proxy;

import graphics.proxy.buttonFactory.IButton;

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
	private IButton button;
	
	public ChooseGamePanel() {
		games.addAll(Mediator.getMed().getAvailableGames());
		
	}
	
	@Override
	public void refreshPanel() {
		
		repaint();
	}
	@Override
	public JPanel setUpPanel() {
		removeAll();
		add(message);
		for (int i = 0; i < games.size(); i++) {
			button= Mediator.getGMed().generateButton(games.get(i));
			add(button.getButton());
		}
		return this;
	}
	@Override
	public JPanel getActualPanel() {
		return this;
	}
}
