package shadow.system;

import java.util.ArrayList;


/**
 * This is a class which can store data which need to be updated.
 * 
 * @pattern singleton
 * 
 * @author Alessandro Martinelli
 */
public class SFUpdater{
	
	private static SFUpdater updater=new SFUpdater();

	private ArrayList<SFUpdatable> updatables=new ArrayList<SFUpdatable>();
	
	private SFUpdater(){
		
	}
	
	public synchronized static void addUpdatable(SFUpdatable updatable){
		if(!updater.updatables.contains(updatable))
			updater.updatables.add(updatable);
	}
	
	public synchronized static void removeUpdatable(SFUpdatable updatable){
		if(updater.updatables.contains(updatable))
			updater.updatables.remove(updatable);
	}
	
	public synchronized static void refresh(){
		
		for (int i = 0; i < updater.updatables.size(); i++) {
			SFUpdatable updatable=(SFUpdatable) updater.updatables.get(i);
			updatable.update();
		}
		
		updater.updatables.clear();
	}
}
