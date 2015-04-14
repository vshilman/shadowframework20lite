package graphics;

import graphics.proxy.BriscolaPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import mediator.Mediator;

public class MouseClickListener implements MouseListener{

	
	public MouseClickListener() {
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getComponent().isEnabled()) {
			BriscolaPanel brisPanel=(BriscolaPanel)Mediator.getGMed().getActualPanel();
			if (e.getComponent().getName().equals(""+Mediator.getPMed().getCardBack())) {
				try {
					int newCard=Mediator.getPMed().nextCard();
					if (newCard==-1) {
						newCard=Integer.parseInt(((BriscolaPanel)Mediator.getGMed().getActualPanel()).getBriscola().getLabelCard().getName());
						brisPanel.getBriscola().setImg();
						brisPanel.getMazzo().setImg();
						brisPanel.getMazzo().getLabelCard().setEnabled(false);
						Mediator.getMed().getComputator().changeFlag();
					}
					if (brisPanel.getFirst().getLabelCard().getName().equals("empty")) {
						brisPanel.getFirst().setImg(newCard);
						brisPanel.getFirst().getLabelCard().setName(""+newCard);
						
					}else if (brisPanel.getSecond().getLabelCard().getName().equals("empty")) {
						brisPanel.getSecond().setImg(newCard);
						brisPanel.getSecond().getLabelCard().setName(""+newCard);
					}else if (brisPanel.getThird().getLabelCard().getName().equals("empty")) {
						brisPanel.getThird().setImg(newCard);
						brisPanel.getThird().getLabelCard().setName(""+newCard);
					}
					brisPanel.getFrontCard().setImg();
					brisPanel.validate();
					brisPanel.repaint();
					Mediator.getCMed().sendAnswerOnGaming(Mediator.getMed().getCoder().convert("OK"));
				}catch (IOException e1) {
					// TODO: handle exception
				}
			}else {
				try {
					String cardPlayed=e.getComponent().getName();
					brisPanel.getFrontCard().setImg(Integer.parseInt(cardPlayed));
					e.getComponent().setName("empty");
					if (brisPanel.getFirst().getLabelCard().getName().equals("empty")) {
						brisPanel.getFirst().setImg();					
					}else if (brisPanel.getSecond().getLabelCard().getName().equals("empty")) {
						brisPanel.getSecond().setImg();
					}else if (brisPanel.getThird().getLabelCard().getName().equals("empty")) {
						brisPanel.getThird().setImg();
					}
					brisPanel.getFirst().getLabelCard().setEnabled(false);
					brisPanel.getSecond().getLabelCard().setEnabled(false);
					brisPanel.getThird().getLabelCard().setEnabled(false);
					brisPanel.validate();
					brisPanel.repaint();
					Mediator.getCMed().sendAnswerOnGaming(Mediator.getMed().getCoder().convert(cardPlayed));
					
								
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		}		
		
	}
	
}
