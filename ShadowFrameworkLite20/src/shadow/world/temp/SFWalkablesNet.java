//package shadow.world.temp;
//
//import java.util.ArrayList;
//
//import shadow.math.SFBounds;
//import shadow.math.SFVertex3f;
//import shadow.renderer.SFCamera;
//import shadow.renderer.SFNode;
//import shadow.world.SFWalkable;
//
//public class SFWalkablesNet implements SFGenericWalkablesNet,SFObstaclesNode{
//	
//	private ArrayList<ShadowWalkableConnections> allwalkables=new ArrayList<ShadowWalkableConnections>();
//	
////	private float minX=0,maxX=0;
////	private float minY=0,maxY=0;
////	private float minZ=0,maxZ=0;
//	
//	private SFBounds<SFVertex3f> bounds=new SFBounds<SFVertex3f>(new SFVertex3f(),new SFVertex3f());
//	
//	public boolean entered=false;
//	
//	/* (non-Javadoc)
//	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowGenericWalkable#getWalkables(int)
//	 */
//	public SFWalkable getWalkables(int index){
//		return allwalkables.get(index).walkable;
//	}
//	
//	public boolean isEntered() {
//		return entered;
//	}
//
//	public void setEntered(boolean entered) {
//		this.entered = entered;
//	}
//
//	public SFNode scene;
//
//	public SFNode getScene() {
//		return scene;
//	}
//
//	public void setScene(SFNode scene) {
//		this.scene = scene;
//	}
//	
//	public void addWalkables(SFWalkable walkable,boolean isEntrance,boolean visibleFromOutSide){
//		
//		ShadowWalkableConnections conn=new ShadowWalkableConnections(walkable);
//		conn.isEntrance=isEntrance;
//		conn.visibleFromOutSize=visibleFromOutSide;
//		
//		for (int i = 0; i < allwalkables.size(); i++) {
//			if(walkable.isShadowWalkableConnected(allwalkables.get(i).walkable)){
//				allwalkables.get(i).connect(conn);
//			}
//		}
//		
//		SFVertex3f pos=new SFVertex3f();
//		SFVertex3f dim=new SFVertex3f();
//		
//		walkable.getBounds(pos,dim);
//		if(allwalkables.size()==0){
//			bounds.setBasePoint(pos);
//			bounds.setExtension(dim);
//		}else{
//			bounds.includeInBounds(pos.get());
//			SFVertex3f v=new SFVertex3f(pos);
//			v.add(dim);
//			bounds.includeInBounds(v.get());
//		}
//		
//		allwalkables.add(conn);
//	}
//	
//	@Override
//	public SFObstacle getObstacle(int index) {
//		return allwalkables.get(index).walkable;
//	}
//	
//	@Override
//	public int getObstaclesCount() {
//		return allwalkables.size();
//	}
//	
//	@Override
//	public boolean isObjectInCameraBounds(SFCamera camera) {
//		for (int i = 0; i < allwalkables.size(); i++) {
//			if(!(allwalkables.get(i).visibleFromOutSize)){
//				allwalkables.get(i).walkable.setVisible(entered);
//			}else {
//				allwalkables.get(i).walkable.setVisible(true);	
//			}
//		}
//		boolean v=SFRectangularObstacle.isParallelepipedInCameraBound(camera,
//				new SFVertex3f(minX,minY,minZ),
//				new SFVertex3f(maxX-minX,maxY-minY,maxZ-minZ));
//		return v;
//	}
//	
//
//	public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F){
//		boolean v=SFRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
//				new SFVertex3f(minX,minY,minZ),
//				new SFVertex3f(maxX-minX,maxY-minY,maxZ-minZ));
//		return v;
//	}
//	
//	/* (non-Javadoc)
//	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowGenericWalkable#getWalkablesCount()
//	 */
//	public int getWalkablesCount(){
//		return allwalkables.size();
//	}
//
//	@Override
//	
//	public int occludersCount() {
//		return 0;
//	}
//
//	@Override
//	public SFOccluder getOccluder(int index) {
//		return null;
//	}
//	
//	@Override
//	public boolean intersect(SFVertex3f P, float ray, float h) {
//		if(P.getX()+ray<minX || P.getX()-ray>maxX){
//			return false;
//		}
//		if(P.getZ()+ray<minZ || P.getZ()-ray>maxZ){
//			return false;
//		}
//		if(P.getY()+h<minY || P.getY()>maxY){
//			return false;
//		}
//		return false;
//	}
//	
//	public void write(){
//		for (int i = 0; i < allwalkables.size(); i++) {
//			System.err.println("allwalkables.get(i).walkable " + allwalkables.get(i));
//			System.err.println("\t is connected to:");
//			for (int j = 0; j < allwalkables.get(i).connections.size(); j++) {
//				System.err.println("\t allwalkables.get(i).connection.get(j) " + allwalkables.get(i).connections.get(j));
//			}
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowGenericWalkable#intersect(int, shadow.math.SFVertex3f, shadow.math.SFVertex3f, shadow.math.SFVertex3f)
//	 */
//	public boolean intersect(int walkable, SFVertex3f Q,SFVertex3f Dir,SFVertex3f Point){
//		
//		if(walkable==-1){
//			return false;
//		}
//		
//		SFWalkable wk=allwalkables.get(walkable).walkable;
//		
//		if(wk.intersect(Q, Dir, Point)){
//			return true;
//		}else{
//			ShadowWalkableConnections conn=allwalkables.get(walkable);
//			for (int i = 0; i < conn.connections.size(); i++) {
//				SFWalkable walk=conn.connections.get(i).walkable;
//				if(walk.intersect(Q, Dir, Point))
//					return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	private ArrayList<SFPickable> pickable=new ArrayList<SFPickable>();
//	
//	public void addPickable(SFPickable pick){
//		pickable.add(pick);
//	}
//	public SFPickable getPickable(SFVertex3f Q,SFVertex3f Dir){
//		for (int i = 0; i < pickable.size(); i++) {
//			SFPickable pick=pickable.get(i);
//			if(SFRectangularObstacle.isIntersecting(pick.p, pick.dim, Q, Dir)){
//				return pick;
//			}
//		}
//		return null;
//	}
//	
//	
//	/* (non-Javadoc)
//	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowGenericWalkable#moveOnEdge(int, shadow.math.SFVertex3f, shadow.math.SFVertex3f, float, float)
//	 */
//	public void moveOnEdge(int walkable,SFVertex3f actualPosition,SFVertex3f destination,float ray,float height){
//		
//		if(walkable==-1){
//			return;
//		}
//		
//		float coords_1[]=new float[2];
//		float coords_2[]=new float[2];
//		
//		SFWalkable wk=allwalkables.get(walkable).walkable;
//		float a=ray/wk.getD1_2Dlength();
//		float b=ray/wk.getD2_2Dlength();
//		wk.get2DPlacementCoord(actualPosition.getX(), actualPosition.getZ(), coords_1);
//		wk.get2DPlacementCoord(destination.getX(), destination.getZ(), coords_2);
//		
//		float t1=1,t2=1;
//		if(coords_2[0]+a>1){//u_dest+b>1
//			float u=1-a;
//			t1=(u-coords_1[0])/(coords_2[0]-coords_1[0]);
//		}else if(coords_2[0]-a<0){
//			float u=a;
//			t1=(u-coords_1[0])/(coords_2[0]-coords_1[0]);
//		}
//		if(coords_2[1]+b>1){//u_dest+b>1
//			float v=1-b;
//			t2=(v-coords_1[1])/(coords_2[1]-coords_1[1]);
//		}else if(coords_2[1]-b<0){
//			float v=b;
//			t2=(v-coords_1[1])/(coords_2[1]-coords_1[1]);
//		}
//		
//		float u=coords_1[0]*(1-t1)+coords_2[0]*t1;
//		float v=coords_1[1]*(1-t2)+coords_2[1]*t2;
//		
//		actualPosition.setX(wk.getX(u, v));
//		actualPosition.setY(wk.getY(u, v));
//		actualPosition.setZ(wk.getZ(u, v));
//	}
//	
//	/* (non-Javadoc)
//	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowGenericWalkable#canWalk(int, shadow.math.SFVertex3f, float, float)
//	 */
//	public int canWalk(int walkable,SFVertex3f position,float ray,float height){
//		
//		if(walkable==-1){
//			walkable=findWalkable(position);
//			if(walkable==-1)
//				return -1;
//		}
//		
//		int index=-1;
//		float coords[]=new float[2];
//		float area=0;
//		
//		SFWalkable wk=allwalkables.get(walkable).walkable;
//		float a=ray/wk.getD1_2Dlength();
//		float b=ray/wk.getD2_2Dlength();
//		
//		wk.get2DPlacementCoord(position.getX(), position.getZ(), coords);
//		
//		float val1=2*a;
//		if(coords[0]+a>1) val1-=coords[0]+a-1;
//		if(coords[0]-a<0) val1-=-(coords[0]-a);
//		if(coords[0]+a<0) val1=0;
//		if(coords[0]-a>1) val1=0;
//		val1/=2*a;
//		float val2=2*b;
//		if(coords[1]+b>1) val2-=coords[1]+b-1;
//		if(coords[1]-b<0) val2-=-(coords[1]-b);
//		if(coords[1]+b<0) val2=0;
//		if(coords[1]-b>1) val2=0;
//		val2/=2*b;
//		
//		area+=val1*val2;
//		boolean isInside=SFWalkable.areCoordsInside(coords);
//		
//		float h=wk.getH(coords[0], coords[1]);
//		if(h<height)
//			return -1;
//		
//		if(isInside && area>0.995f){
//			if(wk.intersectAnyObstacle(position,ray,height)){
//				return -2;
//			}	
//			return walkable;
//		}else{
//			if(isInside){
//				index=walkable;
//			}
//			ShadowWalkableConnections conn=allwalkables.get(walkable);
//			for (int i = 0; i < conn.connections.size(); i++) {
//				SFWalkable walk=conn.connections.get(i).walkable;
//			
//				walk.get2DPlacementCoord(position.getX(), position.getZ(), coords);
//
//				if(index==-1 && SFWalkable.areCoordsInside(coords)){
//					h=walk.getH(coords[0], coords[1]);
//					if(h<height)
//						return -1;
//					if(walk.intersectAnyObstacle(position,ray,height)){
//						return -2;
//					}
//					
//					for (int j = 0; j < allwalkables.size(); j++) {
//						if(walk==allwalkables.get(j).walkable)
//							index=j;
//					}	
//				}
//				
//				a=ray/walk.getD1_2Dlength();
//				b=ray/walk.getD2_2Dlength();
//
//				val1=2*a;
//				if(coords[0]+a>1) val1-=coords[0]+a-1;
//				if(coords[0]-a<0) val1-=-(coords[0]-a);
//				if(coords[0]+a<0) val1=0;
//				if(coords[0]-a>1) val1=0;
//				val1/=2*a;
//				val2=2*b;
//				if(coords[1]+b>1) val2-=coords[1]+b-1;
//				if(coords[1]-b<0) val2-=-(coords[1]-b);
//				if(coords[1]+b<0) val2=0;
//				if(coords[1]-b>1) val2=0;
//				val2/=2*b;
//				
//				area+=val1*val2;
//			}	
//			if(area<0.995)
//				return -1;
//		}
//		
//		return index;
//	}
//	
//	/* (non-Javadoc)
//	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowGenericWalkable#findWalkable(shadow.math.SFVertex3f)
//	 */
//	public int findWalkable(SFVertex3f position){
//		
//		float coords[]=new float[2];
//		for (int i = 0; i < allwalkables.size(); i++) {
//			SFWalkable walk=allwalkables.get(i).walkable;
//		
//			walk.get2DPlacementCoord(position.getX(), position.getZ(), coords);
//			if(SFWalkable.areCoordsInside(coords)){
//				return i;	
//			}
//		}
//		return -1;
//	}
//	
//	@Override
//	public float getY(int walk, SFVertex3f position) {
//
//		SFWalkable walkable=getWalkables(walk);
//		float[] coords=new float[2];
//		walkable.get2DPlacementCoord(position.getX(), position.getZ(), coords);
//		return walkable.getY(coords[0], coords[1]);
//		
//	}
//	
//	@Override
//	public boolean enter(SFVertex3f position,float ray) {
//		
//		for (int i = 0; i < allwalkables.size(); i++) {
//			
//			if(allwalkables.get(i).isEntrance){
//				
//				SFWalkable walk=allwalkables.get(i).walkable;
//				float coords[]=new float[2];
//				walk.get2DPlacementCoord(position.getX(), position.getZ(), coords);
//	
//				if(SFWalkable.areCoordsInside(coords)){
//					float a=ray/walk.getD1_2Dlength();
//					float b=ray/walk.getD2_2Dlength();
//	
//					float val1=2*a;
//					if(coords[0]+a>1) val1-=coords[0]+a-1;
//					if(coords[0]-a<0) val1-=-(coords[0]-a);
//					if(coords[0]+a<0) val1=0;
//					if(coords[0]-a>1) val1=0;
//					val1/=2*a;
//					float val2=2*b;
//					if(coords[1]+b>1) val2-=coords[1]+b-1;
//					if(coords[1]-b<0) val2-=-(coords[1]-b);
//					if(coords[1]+b<0) val2=0;
//					if(coords[1]-b>1) val2=0;
//					val2/=2*b;
//					
//					float area=val1*val2;
//					
//					if(area>0.9)
//						return true;
//				}
//
//			}			
//		}	
//		
//		return false;
//	}
//	
//	/**
//	 * this 'is In is for bounding box.'
//	 */
//	@Override
//	public boolean isIn(SFVertex3f position, float maxDistance) {
//		return bounds.isIn(maxDistance, position.get());
//	}
//	
//	@Override
//	public boolean canExit(int walkableIndex) {
//		if(walkableIndex==-1)
//			return true;
//		return allwalkables.get(walkableIndex).isEntrance;
//	}
//	
//	public class ShadowWalkableConnections{
//	
//		public SFWalkable walkable; 
//		public boolean isEntrance;
//		public boolean visibleFromOutSize;
//		
//		public ShadowWalkableConnections(SFWalkable walkable) {
//			super();
//			this.walkable = walkable;
//		}
//
//		public ArrayList<ShadowWalkableConnections> connections=new ArrayList<ShadowWalkableConnections>();
//		
//		public void connect(ShadowWalkableConnections other){
//			if(!other.connections.contains(this))
//				other.connections.add(this);
//			if(!connections.contains(other))
//				connections.add(other);
//		}
//	}
//}
