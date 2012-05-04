package shadow.world.temp;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;


public interface ShadowObstacle {

	public abstract boolean intersect(SFVertex3f P, float ray, float h);

	public abstract void draw();

	public abstract boolean isObjectInCameraBounds(SFCamera camera);

	public abstract boolean isObjectOccluded(ShadowOccluder occluder,SFVertex3f F);
	
	public int occludersCount();
	
	public ShadowOccluder getOccluder(int index);
	
	public SFNode getScene();
}