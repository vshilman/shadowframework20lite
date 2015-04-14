package graphics.proxy;

import javax.swing.JPanel;

public interface IProxyGraphic {

	public JPanel setUpPanel();
	public void refreshPanel();
	public JPanel getActualPanel();

}