package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import mediator.Mediator;

public class Card extends JLabel {

	private Image img;
	
	
	public Card(int n) throws IOException {
		String path=Mediator.getPMed().getImgPathFromNumber(n);
		BufferedImage img= ImageIO.read(new File(path));
		setPreferredSize(new Dimension(70,108));
		this.img=img.getScaledInstance(70, 108, Image.SCALE_SMOOTH);

	}
		
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img!=null) {
			g.drawImage(img, 0, 0, null);
		}
		
	}
}
