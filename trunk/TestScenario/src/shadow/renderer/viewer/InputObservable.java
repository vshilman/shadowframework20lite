package shadow.renderer.viewer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputObservable {

	
	
	public void notifyObs(KeyEvent e);
	public void notifyObs(MouseEvent e);
	
	
	
}
