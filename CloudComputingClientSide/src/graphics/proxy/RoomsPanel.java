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
import utils.Table;

public class RoomsPanel extends JPanel implements IProxyGraphic{

	private HashMap<Integer, Table> tables= new HashMap<Integer, Table>();
	private JPanel buttonsPanel;
	private String gameType;
	
	public RoomsPanel(String gameType) {
		build(Mediator.getMed().getComputator().getTables(gameType));
		this.gameType=gameType;
	}
	
	private void build(HashMap<Integer, Table> tablesMap){
		removeAll();
		tables.clear();
		tables.putAll(tablesMap);
		setLayout(new GridLayout(tables.size(), 3));
		Set<Integer> tablesName= tables.keySet();
		for (Iterator iterator = tablesName.iterator(); iterator.hasNext();) {
			Integer tableID=(Integer)iterator.next();
			String tableName = tables.get(tableID).getName();
			String tableOwner=tables.get(tableID).getManager();
			add(new JLabel(tableName));
			add(new JLabel(tableOwner));
			add(generateButtonsPanel(tableName, tables.get(tableID).isEmpty()));
			
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
		build(Mediator.getMed().getComputator().getTables(gameType));
		
	}
	
	@Override
	public JPanel setUpPanel() {
		build(Mediator.getMed().getComputator().getTables(gameType));
		return this;
	}
	
	
}
