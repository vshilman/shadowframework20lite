package graphics.proxy;

import graphics.Card;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mediator.Mediator;


public class BriscolaPanel extends JPanel implements IProxyGraphic{

	
	private JPanel centerTable;
	private JPanel opponent1;
	private JPanel opponent2;
	private JPanel opponent3;
	
	
	private JPanel myCards;
	
	private Card card;
	private JPanel panel= new JPanel();
	
	public BriscolaPanel() {
		setLayout(new BorderLayout());
		centerTable= new JPanel();
		opponent1= new JPanel();
		opponent2= new JPanel();
		opponent3= new JPanel();
		myCards= new JPanel();
		
		opponent1.setLayout(new BorderLayout());
		opponent3.setLayout(new BorderLayout());
		opponent2.setLayout(new BorderLayout());
		
		try {
		
			panel.setPreferredSize(new Dimension(70, 324));
			for (int i = 0; i < 3; i++) {
				
				card=new Card(Mediator.getPMed().getCardBack());
				
				panel.add(card);
			}
			opponent1.add(panel, BorderLayout.CENTER);
			opponent1.add(new JLabel(), BorderLayout.NORTH);
			opponent1.add(new JLabel(), BorderLayout.SOUTH);
			
			panel= new JPanel();
			panel.setPreferredSize(new Dimension(210, 114));
			for (int i = 0; i < 3; i++) {
				card=new Card(Mediator.getPMed().getCardBack());
				panel.add(card);
			}
			opponent2.add(panel, BorderLayout.CENTER);
			opponent2.add(new JLabel(), BorderLayout.WEST);
			opponent2.add(new JLabel(), BorderLayout.EAST);
			
			panel= new JPanel();
			panel.setPreferredSize(new Dimension(70, 324));
			for (int i = 0; i < 3; i++) {
				card=new Card(Mediator.getPMed().getCardBack());
				panel.add(card);
			}
			opponent3.add(panel, BorderLayout.CENTER);
			opponent3.add(new JLabel(), BorderLayout.NORTH);
			opponent3.add(new JLabel(), BorderLayout.SOUTH);
	
			add(opponent1, BorderLayout.WEST);
			add(opponent2, BorderLayout.NORTH);
			add(opponent3, BorderLayout.EAST);
			
			panel= new JPanel();
			panel.setPreferredSize(new Dimension(230, 114));
			for (int i = 0; i < 3; i++) {
				card=new Card(Mediator.getPMed().getCardBack());
				panel.add(card);
			}
			myCards.add(panel, BorderLayout.CENTER);
			myCards.add(new JLabel(), BorderLayout.NORTH);
			myCards.add(new JLabel(), BorderLayout.SOUTH);
	
			add(myCards, BorderLayout.SOUTH);
			JButton startGame= new JButton("Start!");
			startGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Mediator.getMed().getComputator().startGame();
				}
			});
			centerTable.add(startGame);
			add(centerTable, BorderLayout.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void refreshPanel() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JPanel setUpPanel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
