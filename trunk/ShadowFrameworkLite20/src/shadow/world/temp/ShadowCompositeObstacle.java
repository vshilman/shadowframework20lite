package shadow.world.temp;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;


public class ShadowCompositeObstacle implements ShadowObstacle{

	private ShadowObstacle[] obstacles;

	public ShadowCompositeObstacle(ShadowObstacle[] obstacles) {
		super();
		this.obstacles = obstacles;
	}
	
	@Override
	public void draw() {
		for (int i = 0; i < obstacles.length; i++) {
			obstacles[i].draw();
		}
	}
	
	@Override
	public boolean intersect(SFVertex3f P, float ray, float h) {
		for (int i = 0; i < obstacles.length; i++) {
			if(obstacles[i].intersect(P, ray, h)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isObjectInCameraBounds(SFCamera camera) {	
		for (int i = 0; i < obstacles.length; i++) {
			if(obstacles[i].isObjectInCameraBounds(camera))
				return true;
		}
		return false;
	}

	public boolean isObjectOccluded(ShadowOccluder occluder,SFVertex3f F){
		for (int i = 0; i < obstacles.length; i++) {
			if(obstacles[i].isObjectOccluded(occluder,F))
				return true;
		}
		return false;
	}

	public SFNode scene;

	public SFNode getScene() {
		return scene;
	}

	public void setScene(SFNode scene) {
		this.scene = scene;
	}

	@Override
	public int occludersCount() {
		return 0;
	}

	@Override
	public ShadowOccluder getOccluder(int index) {
		return null;
	}
}
