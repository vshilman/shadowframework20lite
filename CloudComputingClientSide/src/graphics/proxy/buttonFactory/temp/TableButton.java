package graphics.proxy.buttonFactory.temp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import mediator.Mediator;

public class TableButton extends JButton{

	
	public TableButton(String name,final int tableId,final String type) {
		super(name);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (type.equals("play")) {
					Mediator.getMed().getComputator().enterTable(tableId, "play");
				}else {
					
				}
				
				
			}
		});
	}
}
