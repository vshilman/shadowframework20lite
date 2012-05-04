package shadow.world.temp;


//public class ShadowWorldDrawer {
//
//	//this does not require to consider walkers, only camera.
//	private ShadowCamera camera;
//	
//	private boolean shadow_world_debug=true;
//	
//	public ShadowWorldDrawer(ShadowCamera camera) {
//		super();
//		this.camera = camera;
//	}
//
//	private ArrayList<ShadowObstaclesNode> obstacles=new ArrayList<ShadowObstaclesNode>();
//	private ArrayList<ShadowObstacle> visibleObstacles=new ArrayList<ShadowObstacle>();
//	
//	public void addObstacleNode(ShadowObstaclesNode node){
//		obstacles.add(node);
//	}
//	
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
//}
