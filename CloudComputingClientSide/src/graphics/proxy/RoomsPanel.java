package graphics.proxy;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Substring;

import mediator.Mediator;

public class RoomsPanel extends JPanel implements IProxyGraphic{

	private HashMap<String, String> tables= new HashMap<String, String>();
	
	
	public RoomsPanel() {
		build();
	}
	
	private void build(){
		removeAll();
		tables.putAll(Mediator.getMed().getTables());
		setLayout(new GridLayout(tables.size(), 2));
		Set<String> tablesName= tables.keySet();
		for (Iterator iterator = tablesName.iterator(); iterator.hasNext();) {
			String tableName = (String) iterator.next();
			String tableOwner=tables.get(tableName);
			add(new JLabel(extractName(tableName)));
			add(new JLabel(tableOwner));
			
		}
		validate();
		repaint();
	}
	
	private String extractName(String nameComposed){
		Substring sub= new Substring(nameComposed, "$$||$$");
		return sub.nextSubString();
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
