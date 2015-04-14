package graphics.proxy;

import graphics.ClickableCard;
import graphics.ICard;
import graphics.OpponentsCard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mediator.Mediator;


public class BriscolaPanel extends JPanel implements IProxyGraphic{

	
	private JPanel centerTable;
	private JLabel nick1=new JLabel("Player 1 ");
	private JLabel nick2=new JLabel(" Player 2");
	private JLabel nick3=new JLabel(" Player 3");
	private JLabel points1=new JLabel("Points: 0 ");
	private JLabel points2=new JLabel(" Points: 0");
	private JLabel points3=new JLabel(" Points: 0");
	private JLabel myPoints=new JLabel("Points: 0");
	private JLabel myNick=new JLabel(Mediator.getMed().getComputator().getNick());
	
	private JLabel turnTalk=new JLabel();
	
	private JPanel opponent1;
	private JPanel opponent2;
	private JPanel opponent3;
	
	
	private JPanel myCards;
	private ICard mazzo;
	private ICard briscola;
	private ICard frontCard;
	private ICard first;
	private ICard second;
	private ICard third;
	
	private ICard opponent1FrontCard;
	private ICard opponent2FrontCard;
	private ICard opponent3FrontCard;

	
	
	
	private ICard card;
	private JPanel panel= new JPanel();
	
	public BriscolaPanel() {
		
	}
	public ICard getFirst() {
		return first;
	}

	public ICard getSecond() {
		return second;
	}

	public ICard getThird() {
		return third;
	}
	public ICard getFrontCard() {
		return frontCard;
	}
	public ICard getBriscola() {
		return briscola;
	}
	public ICard getMazzo() {
		return mazzo;
	}
	
	
	
	public JLabel getNick1() {
		return nick1;
	}
	public void setNick1(JLabel nick1) {
		this.nick1 = nick1;
	}
	public JLabel getNick2() {
		return nick2;
	}
	public void setNick2(JLabel nick2) {
		this.nick2 = nick2;
	}
	public JLabel getNick3() {
		return nick3;
	}
	public void setNick3(JLabel nick3) {
		this.nick3 = nick3;
	}
	public JLabel getPoints1() {
		return points1;
	}
	public void setPoints1(JLabel points1) {
		this.points1 = points1;
	}
	public JLabel getPoints2() {
		return points2;
	}
	public void setPoints2(JLabel points2) {
		this.points2 = points2;
	}
	public JLabel getPoints3() {
		return points3;
	}
	public void setPoints3(JLabel points3) {
		this.points3 = points3;
	}
	public JLabel getMyPoints() {
		return myPoints;
	}
	public void setMyPoints(JLabel myPoints) {
		this.myPoints = myPoints;
	}
	public void buildGame() {
		centerTable.removeAll();
		try {
			centerTable.setLayout(new BorderLayout());
			panel=new JPanel();
			mazzo=new ClickableCard(Mediator.getPMed().getCardBack(), this);
			mazzo.getLabelCard().setEnabled(false);
			int nameBriscola=Mediator.getPMed().getFirstCard();
			briscola=new ClickableCard(nameBriscola, this);
			briscola.getLabelCard().setName(""+nameBriscola);
			briscola.getLabelCard().setEnabled(false);
//			briscola.getLabelCard().setVisible(true);
			panel.add(mazzo.getLabelCard());
			panel.add(briscola.getLabelCard());
			centerTable.add(panel,BorderLayout.CENTER);
			first.setImg(Mediator.getPMed().nextCard());
			first.getLabelCard().setEnabled(false);
			second.setImg(Mediator.getPMed().nextCard());
			second.getLabelCard().setEnabled(false);
			third.setImg(Mediator.getPMed().nextCard());
			third.getLabelCard().setEnabled(false);
			
			JPanel panler= new JPanel();

			panler.setLayout(new BorderLayout());
			frontCard= new OpponentsCard();
			panler.add(frontCard.getLabelCard());
			JPanel pan=new JPanel();
			pan.add(frontCard.getLabelCard());
			pan.setPreferredSize(new Dimension(40, 62));
			panler.add(pan, BorderLayout.CENTER);
			panler.add(new JLabel(), BorderLayout.EAST);
			panler.add(new JLabel(), BorderLayout.WEST);
			centerTable.add(panler, BorderLayout.SOUTH);
			
			
			panler= new JPanel();
			panler.setLayout(new BorderLayout());
			opponent1FrontCard= new OpponentsCard();
			panler.add(opponent1FrontCard.getLabelCard());
			pan=new JPanel();
			pan.add(opponent1FrontCard.getLabelCard());
			pan.setPreferredSize(new Dimension(40, 62));
			panler.add(pan, BorderLayout.CENTER);
			panler.add(new JLabel(), BorderLayout.NORTH);
			panler.add(new JLabel(), BorderLayout.SOUTH);
			centerTable.add(panler, BorderLayout.EAST);
			
			panler= new JPanel();
			panler.setLayout(new BorderLayout());
			opponent2FrontCard= new OpponentsCard();
			panler.add(opponent2FrontCard.getLabelCard());
			pan=new JPanel();
			pan.add(opponent2FrontCard.getLabelCard());
			pan.setPreferredSize(new Dimension(40, 62));
			panler.add(pan, BorderLayout.CENTER);
			panler.add(new JLabel(), BorderLayout.EAST);
			panler.add(new JLabel(), BorderLayout.WEST);
			centerTable.add(panler, BorderLayout.NORTH);
			
			panler= new JPanel();
			panler.setLayout(new BorderLayout());
			opponent3FrontCard= new OpponentsCard();
			panler.add(opponent3FrontCard.getLabelCard());
			pan=new JPanel();
			pan.add(opponent3FrontCard.getLabelCard());
			pan.setPreferredSize(new Dimension(40, 62));
			panler.add(pan, BorderLayout.CENTER);
			panler.add(new JLabel(), BorderLayout.NORTH);
			panler.add(new JLabel(), BorderLayout.SOUTH);
			centerTable.add(panler, BorderLayout.WEST);
			
			LinkedList<String> players=Mediator.getPMed().getOrderedPlayers();
			int myTurn=players.indexOf(Mediator.getMed().getComputator().getNick());
			String tmp="";
			for (int i = 0; i < myTurn+1; i++) {
				tmp=players.poll();
				players.add(tmp);
			}
			nick1.setName(players.poll());
			nick2.setName(players.poll());
			nick3.setName(players.poll());
			
			centerTable.validate();
			validate();
			repaint();

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	public void setEnableMazzo(boolean choice){
		mazzo.getLabelCard().setEnabled(choice);
		if (choice) {
			turnTalk.setText(" Pesca!");
			validate();
			repaint();
		}
	}
	public void setEnableCard(boolean choice){
		first.getLabelCard().setEnabled(choice);
		second.getLabelCard().setEnabled(choice);
		second.getLabelCard().setEnabled(choice);
		if (choice) {
			turnTalk.setText(" Tocca a Te!");
			validate();
			repaint();
		}
		
	}
	@Override
	public JPanel getActualPanel() {
		return this;
	}
	
	@Override
	public void refreshPanel() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JPanel setUpPanel() {
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
		
			JPanel info= new JPanel();
			info.setLayout(new GridLayout(2,1));
			panel.setPreferredSize(new Dimension(70, 424));//+100
			info.add(nick1);
			info.add(points1);
			panel.add(info);
			for (int i = 0; i < 3; i++) {
				
				card=new OpponentsCard(Mediator.getPMed().getCardBack());
				
				panel.add(card.getLabelCard());
			}
			
			opponent1.add(panel, BorderLayout.CENTER);
			opponent1.add(new JLabel(), BorderLayout.NORTH);
			opponent1.add(new JLabel(), BorderLayout.SOUTH);
			
			panel= new JPanel();
			panel.setPreferredSize(new Dimension(310, 114));
			info= new JPanel();
			info.setLayout(new GridLayout(2,1));
			info.add(nick2);
			info.add(points2);
			panel.add(info);
			for (int i = 0; i < 3; i++) {
				card=new OpponentsCard(Mediator.getPMed().getCardBack());
				panel.add(card.getLabelCard());
			}
			panel.add(new JLabel());
			
			opponent2.add(panel, BorderLayout.CENTER);
			opponent2.add(new JLabel(), BorderLayout.WEST);
			opponent2.add(new JLabel(), BorderLayout.EAST);
			
			panel= new JPanel();
			panel.setPreferredSize(new Dimension(70, 424));
			info= new JPanel();
			info.setLayout(new GridLayout(2,1));
			info.add(nick3);
			info.add(points3);
			panel.add(info);
			for (int i = 0; i < 3; i++) {
				card=new OpponentsCard(Mediator.getPMed().getCardBack());
				panel.add(card.getLabelCard());
			}
			opponent3.add(panel, BorderLayout.CENTER);
			opponent3.add(new JLabel(), BorderLayout.NORTH);
			opponent3.add(new JLabel(), BorderLayout.SOUTH);
	
			add(opponent1, BorderLayout.EAST);
			add(opponent2, BorderLayout.NORTH);
			add(opponent3, BorderLayout.WEST);
			
			panel= new JPanel();
			panel.setPreferredSize(new Dimension(330, 114));
			info= new JPanel();
			info.setLayout(new GridLayout(2,1));
			info.add(myNick);
			info.add(myPoints);
			first=new ClickableCard(Mediator.getPMed().getCardBack(), this);
			first.getLabelCard().setEnabled(false);
			second=new ClickableCard(Mediator.getPMed().getCardBack(), this);
			second.getLabelCard().setEnabled(false);
			third=new ClickableCard(Mediator.getPMed().getCardBack(), this);
			third.getLabelCard().setEnabled(false);
			panel.add(info);
			panel.add(first.getLabelCard());
			panel.add(second.getLabelCard());
			panel.add(third.getLabelCard());
			myCards.add(panel, BorderLayout.CENTER);
			myCards.add(new JLabel(), BorderLayout.WEST);
			myCards.add(new JLabel(), BorderLayout.EAST);
			add(myCards, BorderLayout.SOUTH);
			
			
			JButton startGame= new JButton("Start!");
			startGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String ans=Mediator.getMed().getComputator().startGame(40);
					if (ans.equals("OK")) {
						buildGame();
					}else {
						Mediator.getGMed().generateDialog(ans);
					}
				}
			});
			centerTable.add(startGame);
			add(centerTable, BorderLayout.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
}
