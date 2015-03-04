package graphics.proxy;

import graphics.proxy.buttonFactory.temp.TableButton;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mediator.Mediator;
import utils.Substring;

public class RoomsPanel extends JPanel implements IProxyGraphic{

	private HashMap<String, Boolean> tables= new HashMap<String, Boolean>();
	private JPanel buttonsPanel;
	private String gameType;
	
	public RoomsPanel(String gameType) {
		build();
		this.gameType=gameType;
	}
	
	private void build(){
		removeAll();
		tables.putAll(Mediator.getMed().getComputator().getTables(gameType));
		setLayout(new GridLayout(tables.size(), 3));
		Set<String> tablesName= tables.keySet();
		for (Iterator iterator = tablesName.iterator(); iterator.hasNext();) {
			String codified=(String)iterator.next();
			Substring sub= new Substring(codified, "$$||$$");

			String tableName = sub.nextSubString();
			String tableOwner=sub.nextSubString();
			add(new JLabel(tableName));
			add(new JLabel(tableOwner));
			add(generateButtonsPanel(tableName, tables.get(codified)));
			
		}
		validate();
		repaint();
	}
	
	public void setGame(String gameType){
		this.gameType=gameType;
	}
	
	private JPanel generateButtonsPanel(String tableName, Boolean isFree){
		buttonsPanel= new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		JButton enter= new TableButton("Play!", tableName, "play");
		JButton spectate= new TableButton("Spectate!",tableName, "spectate");
		if (!isFree) {
			enter.setEnabled(false);
		}
		
		buttonsPanel.add(enter);
		buttonsPanel.add(spectate);
		return buttonsPanel;
	}
	@Override
	public void refreshPanel() {
		build();
		
	}
	
	@Override
	public JPanel setUpPanel() {
		build();
		return this;
	}
	
	
}
