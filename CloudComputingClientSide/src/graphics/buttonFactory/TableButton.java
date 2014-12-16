package graphics.buttonFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TableButton extends JButton{

	
	public TableButton(String name,String tableName, String type) {
		super(name);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
	}
}
