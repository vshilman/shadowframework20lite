package graphics;

import graphics.proxy.BriscolaPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

public class MouseClickListener implements MouseListener{

	private BriscolaPanel brisPanel;
	
	public MouseClickListener(BriscolaPanel brisPanel) {
		this.brisPanel=brisPanel;
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
		if (e.getComponent().getName().equals(""+41)) {
			System.out.println("ESTRAGGO CARTA");
			try {
				if (brisPanel.getFirst().getLabelCard().getName().equals("empty")) {
					brisPanel.getFirst().setImg(41);
					brisPanel.getFirst().getLabelCard().setName(""+41);
					
				}else if (brisPanel.getSecond().getLabelCard().getName().equals("empty")) {
					brisPanel.getSecond().setImg(41);
					brisPanel.getSecond().getLabelCard().setName(""+41);
				}else if (brisPanel.getThird().getLabelCard().getName().equals("empty")) {
					brisPanel.getThird().setImg(41);
					brisPanel.getThird().getLabelCard().setName(""+41);
				}
				brisPanel.getMazzo().getLabelCard().setEnabled(false);
				brisPanel.getFirst().getLabelCard().setEnabled(true);
				brisPanel.getSecond().getLabelCard().setEnabled(true);
				brisPanel.getSecond().getLabelCard().setEnabled(true);

				brisPanel.getFrontCard().setImg();
				brisPanel.validate();
				brisPanel.repaint();
			}catch (IOException e1) {
				// TODO: handle exception
			}
		}else {
			try {
				brisPanel.getMazzo().getLabelCard().setEnabled(true);
				brisPanel.getFrontCard().setImg(Integer.parseInt(e.getComponent().getName()));
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
