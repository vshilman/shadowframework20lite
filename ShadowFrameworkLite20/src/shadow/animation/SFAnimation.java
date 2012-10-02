package shadow.animation;

import shadow.system.SFInitiable;

/**
* @author Alessandro Martinelli
*/
public interface SFAnimation extends SFInitiable{

	/**
	 * Ask this animation to apply at the given time. 
	 * Whatever parameter controlled by this animation
	 * must assume the value this animation need at the given time. 
	 * 
	 * @param time
	 */
	public void animate(long time);

	public SFAnimation clone();
	
}
