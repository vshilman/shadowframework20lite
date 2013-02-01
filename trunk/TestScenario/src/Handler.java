
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



public  class Handler implements MouseMotionListener, KeyListener, InputObservable {


   
     private static ArrayList<InputObserver> obsList;

  
     
     public Handler() throws AWTException{
    	 
    	  obsList = new ArrayList<InputObserver>();
    	 
    	 
     }
 
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
    	   notifyObs(e);
   
	}

	public void keyPressed(KeyEvent e) {
		
	notifyObs(e);
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	

	@Override
	public void notifyObs(KeyEvent e) {
		
		  for(InputObserver obs : obsList)
	       {
	           obs.keyUpdate(e);
	       }

		
	}
	public static void addObserver(InputObserver obs)
	   {
	       if (obs != null)
	          obsList.add(obs);
	   }

	@Override
	public void notifyObs(MouseEvent e) {
		
		for(InputObserver obs : obsList)
	       {
	           obs.mouseUpdate(e);
	       }

		
	}

	
}
