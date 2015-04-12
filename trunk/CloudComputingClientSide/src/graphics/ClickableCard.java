package graphics;

import graphics.proxy.BriscolaPanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import mediator.Mediator;

public class ClickableCard extends JLabel implements ICard{

	private Image img;
	private MouseClickListener mouseListener;
	
	public ClickableCard(int n, BriscolaPanel brisPanel) throws IOException {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setName(""+n);
		setUpImg(n);
		mouseListener=new MouseClickListener(brisPanel);
		addMouseListener(mouseListener);
	}
	
	/* (non-Javadoc)
	 * @see graphics.ICard#setImg(int)
	 */
	@Override
	public void setImg(int n)throws IOException{
		setName(""+n);
		setUpImg(n);
	}
	@Override
	public void setImg(){
		img=null;
	}
	
	private void setUpImg(int n) throws IOException {
		String path=Mediator.getPMed().getImgPathFromNumber(n);
		BufferedImage img= ImageIO.read(new File(path));
		setPreferredSize(new Dimension(70,108));
		this.img=img.getScaledInstance(70, 108, Image.SCALE_SMOOTH);
	}
	
	@Override
	public JLabel getLabelCard() {
		return this;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img!=null) {
			g.drawImage(img, 0, 0, null);
		}
		
	}
}
