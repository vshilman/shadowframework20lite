package shadow.world.temp;

import java.util.ArrayList;
import java.util.Iterator;

import shadow.pipeline.SFPipelineTransform3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFModel;
import shadow.renderer.SFNode;


/**
 * TODO 
 * 
 * This World Node is a Node in charge to control visibility of more Nodes.
 * 
 * It was originally thought as a Renderer, now it will be simply an iterator on 
 * viewable nodes. 
 * 
 * @author Alessandro
 */
public class SFWorldNode implements SFNode{

	//this does not require to consider walkers, only camera.
	private SFCamera camera;
	
	private boolean shadow_world_debug=true;
	
	public SFWorldNode(SFCamera camera) {
		super();
		this.camera = camera;
	}

	private ArrayList<SFObstaclesNode> obstacles=new ArrayList<SFObstaclesNode>();
	private ArrayList<SFObstacle> visibleObstacles=new ArrayList<SFObstacle>();
	
	public void addObstacleNode(SFObstaclesNode node){
		obstacles.add(node);
	}
	
	@Override
	public void addNode(SFNode node) throws SFNodeException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public SFNode copyNode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public SFModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFPipelineTransform3f getTransform() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isDrawable() {
		return false;
	}
	
	@Override
	public Iterator<SFNode> iterator() {
		return null;
	}
	
//	public void render(GL2 gl){
//		if(camera.isChanged()){
//			visibleObstacles.clear();
//			for (int i = 0; i < obstacles.size(); i++) {
//				if(true || obstacles.get(i).isObjectInCameraBounds(camera)){
//					visibleObstacles.add(obstacles.get(i));
//					extractNodeVisibility(obstacles.get(i), camera);	
//				}
//			}
//			
//			for (int i = 0; i < visibleObstacles.size(); i++) {
//				ShadowObstacle obs=visibleObstacles.get(i);
//				for (int j = 0; j < obs.occludersCount(); j++) {
//					ShadowOccluder occ=obs.getOccluder(j);
//					for (int k = 0; k < visibleObstacles.size(); k++) {
//						ShadowObstacle obs1=visibleObstacles.get(k);
//						if(false && obs1.isObjectOccluded(occ,camera.F)){
//							//obstacle is not visible, it is occluded
//							visibleObstacles.remove(k);
//							if(i>=k)
//								i--;//because if not i skip one obstacle
//							k--;//because if not i skip one obstacle
//						}
//					}
//				}
//			}
//			
//			camera.setChanged(false);
//		}
//		
//		
//		if(shadow_world_debug)
//			for (int i = 0; i < visibleObstacles.size(); i++) {
//				visibleObstacles.get(i).draw(gl);
//			}
//		
//		
//			for (int i = 0; i < visibleObstacles.size(); i++) {
//				GLScene scene=visibleObstacles.get(i).getScene();
//				if(scene!=null)
//					scene.draw(gl);
//			}	
//		
//	}
//	
//	public void extractNodeVisibility(ShadowObstaclesNode node,ShadowCamera camera){
//		for (int i = 0; i < node.getObstaclesCount(); i++) {
//			ShadowObstacle obs=node.getObstacle(i);
//			if(true || obs.isObjectInCameraBounds(camera)){
//				if(true || obs instanceof ShadowObstaclesNode){
//					extractNodeVisibility((ShadowObstaclesNode)obs, camera);	
//				}
//				visibleObstacles.add(obs);
//			}
//		}
//	}
}
