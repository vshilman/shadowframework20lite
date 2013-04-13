package interfaces;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputObserver {

	public void keyUpdate(KeyEvent e);
	public void mouseUpdate(MouseEvent e);



}
