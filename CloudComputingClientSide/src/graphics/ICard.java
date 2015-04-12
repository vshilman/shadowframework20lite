package graphics;

import java.io.IOException;

import javax.swing.JLabel;

public interface ICard {

	public void setImg(int n) throws IOException;
	public void setImg();

	public JLabel getLabelCard();

}