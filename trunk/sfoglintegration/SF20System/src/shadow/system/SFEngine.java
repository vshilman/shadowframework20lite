package shadow.system;

import java.util.LinkedList;

/**
 * Main drawing component in the Framework
 * 
 * @author Alessandro Martinelli
 */
public class SFEngine implements SFDrawable{
	
	private SFDrawable drawable;

	private LinkedList<SFInitiable> initiables=new LinkedList<SFInitiable>();
	private LinkedList<SFInitiable> destroyables=new LinkedList<SFInitiable>();
	private LinkedList<SFUpdatable> updatables=new LinkedList<SFUpdatable>();
	
	/**
	 * Initialized this Engine
	 */
	public SFEngine() {
		super();
	}
	
	/**
	 * Initialized this Engine, given an element which must be drawn
	 * @param drawable A drawing algorithm; the Engine will manage initialization an destruction of the element used in the algorithm
	 */
	public SFEngine(SFDrawable drawable) {
		super();
		this.drawable = drawable;
	}
	
	/**
	 * Initialized this Engine, given an element which must be drawn
	 * @param drawable A drawing algorithm; the Engine will manage initialization an destruction of the element used in the algorithm
	 */
	public void setDrawable(SFDrawable drawable) {
		this.drawable = drawable;
	}

	/**
	 * Main Drawing Algorith of the Framework:
	 * <ul>
	 *  <li> Call the destroy method on anything which should be destroyed</li>
	 * 	<li> Call the init method on anything which should be initialized. The element to be destroyed are destroyed
	 * 	before this step, so that memory get free for new initializations</li>
	 *  <li> Updates everything which must be updated </li>
	 *  <li> Draw the base drawable element in this engine </li>
	 * </ul>
	 */
	public synchronized void draw(){
		solveInitiables();
		refresh();
		drawable.draw();
	}
	
	/**
	 * Add an updatable component to this algorithm. Updatable components 
	 * are update at each call of the draw method
	 * @param updatable
	 */
	public synchronized void addUpdatable(SFUpdatable updatable){
		updatables.add(updatable);
	}

	/**
	 * Remove an updatable from the list of updatable components. Updatable components 
	 * are update at each call of the draw method
	 * @param updatable
	 */
	public synchronized void removeUpdatable(SFUpdatable updatable){
		updatables.remove(updatable);
	}
	
	/**
	 * Add an initiable component. The initiable init method will be called once at the next call of draw,
	 * than the initiable will be discarded 
	 * @param initiable 
	 */
	public synchronized void addInitiable(SFInitiable initiable) {
		initiables.add(initiable);
	}
	
	/**
	 * Add an initiable component. The initiable destroy method will be called once at the next call of draw,
	 * than the initiable will be discarded
	 * @param initiable 
	 */
	public synchronized void addDestroyable(SFInitiable initiable) {
		destroyables.add(initiable);
	}

	public void solveInitiables() {
		for (SFInitiable destroyable : destroyables) {
			destroyable.destroy();
		}
		destroyables.clear();
		for (SFInitiable initiable : initiables) {
			initiable.init();
		}
		initiables.clear();
	}
	

	public void refresh(){
		for (SFUpdatable updatable : updatables) {
			updatable.update();
		}
	}
}
