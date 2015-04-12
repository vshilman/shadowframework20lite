package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import mediator.Mediator;

public class OpponentsCard extends JLabel implements ICard {

	private Image img;
	
	
	public OpponentsCard(int n) throws IOException {
		String path=Mediator.getPMed().getImgPathFromNumber(n);
		setUpImg(n);

	}
		
	@Override
	public void setImg(int n)throws IOException{
		setName(""+n);
		setUpImg(n);
	}
	
	
	private void setUpImg(int n) throws IOException {
		String path=Mediator.getPMed().getImgPathFromNumber(n);
		BufferedImage img= ImageIO.read(new File(path));
		setPreferredSize(new Dimension(40,62));
		this.img=img.getScaledInstance(40,62, Image.SCALE_SMOOTH);
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
