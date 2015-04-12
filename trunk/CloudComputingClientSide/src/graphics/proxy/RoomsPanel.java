package graphics.proxy;

import graphics.proxy.buttonFactory.temp.TableButton;

import java.awt.GridLayout;
import java.util.HashMap;
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
	private JLabel headerTable=new JLabel(" Table Name");
	private JLabel headerOwner=new JLabel(" Owner Name");;
	private JLabel headerButtons=new JLabel(" ");;
	public RoomsPanel(String gameType) {
		build(Mediator.getMed().getComputator().getTables(gameType));
		this.gameType=gameType;
	}
	
	private void build(HashMap<Integer, Table> tablesMap){
		removeAll();
		tables.clear();
		tables.putAll(tablesMap);
		if (tables.size()<10) {
			setLayout(new GridLayout(10, 3));

		}else {
			setLayout(new GridLayout(tables.size(), 3));
		}
		add(headerTable);
		add(headerOwner);
		add(headerButtons);
		
		Set<Integer> tablesName= tables.keySet();
		for (Object element : tablesName) {
			Integer tableId=(Integer)element;
			String tableName = tables.get(tableId).getName();
			String tableOwner=tables.get(tableId).getManager();
			add(new JLabel(tableName));
			add(new JLabel(tableOwner));
			add(generateButtonsPanel(tableId, tables.get(tableId).isEmpty()));
		}
		for (int i = 0; i < 9-tables.size(); i++) {
			add(new JPanel());
			add(new JPanel());
			add(new JPanel());
		}
		validate();
		repaint();
	}
	
	public void setGame(String gameType){
		this.gameType=gameType;
	}
	
	private JPanel generateButtonsPanel(int tableId, Boolean isFree){
		buttonsPanel= new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		JButton enter= new TableButton("Play!", tableId, "play");
		JButton spectate= new TableButton("Spectate!",tableId, "spectate");
		spectate.setEnabled(false);
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
