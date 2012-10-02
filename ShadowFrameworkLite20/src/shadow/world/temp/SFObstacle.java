package shadow.world.temp;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;


/**
 * An abstraction for all obstacles
 * @author Alessandro
 */
public interface SFObstacle {
	
	public abstract boolean intersect(SFVertex3f P, float ray, float h);

	//Do not break ISP please!
	//public abstract void draw();

	public abstract boolean isObjectInCameraBounds(SFCamera camera);

	public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F);
	
	public int occludersCount();
	
	public SFOccluder getOccluder(int index);
	
	public SFNode getScene();
}