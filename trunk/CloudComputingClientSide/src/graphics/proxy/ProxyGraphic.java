package graphics.proxy;

import javax.swing.JPanel;



public class ProxyGraphic implements IProxyGraphic {

	
	private IProxyGraphic panel;
	
	public ProxyGraphic(IProxyGraphic panel) {
		super();
		this.panel=panel;
	}
	
	
	public void setPanel(IProxyGraphic panel){
		this.panel=panel;
	}
	
	@Override
	public void refreshPanel() {
		panel.refreshPanel();
		
	}
	
	@Override
	public JPanel setUpPanel(){
		return panel.setUpPanel();
	}

	@Override
	public JPanel getActualPanel() {
		return panel.getActualPanel();
	}
}
