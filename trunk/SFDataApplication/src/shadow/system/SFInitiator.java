
package shadow.system;

import java.util.LinkedList;

/**
 * This is a singleton class which can store data which need to be initialized.
 * 
 * @pattern singleton
 * 
 * @author Alessandro Martinelli
 */
public class SFInitiator {

	private static SFInitiator initiator=new SFInitiator();

	private LinkedList<SFInitiable> initiables=new LinkedList<SFInitiable>();
	private LinkedList<SFInitiable> destroyables=new LinkedList<SFInitiable>();

	private SFInitiator() {
	}

	public synchronized static void addInitiable(SFInitiable initiable) {
		if (!initiator.initiables.contains(initiable)){
			//System.out.println("initializing "+initiable);
			initiator.initiables.add(initiable);
		}
	}
	
	public synchronized static void addDestroyable(SFInitiable initiable) {
		if (!initiator.destroyables.contains(initiable)){
			//System.out.println("initializing "+initiable);
			initiator.destroyables.add(initiable);
		}
	}

	public synchronized static void solveInitiables() {
		for (SFInitiable destroyable : initiator.destroyables) {
			destroyable.destroy();
		}
		initiator.destroyables.clear();
		for (SFInitiable initiable : initiator.initiables) {
			try {
				initiable.init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		initiator.initiables.clear();
	}
}
