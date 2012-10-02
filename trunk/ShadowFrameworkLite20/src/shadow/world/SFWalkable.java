package shadow.world;

import java.util.ArrayList;

import shadow.math.SFBounds;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;
import shadow.world.temp.SFObstacle;
import shadow.world.temp.SFObstaclesNode;
import shadow.world.temp.SFOccluder;
import shadow.world.temp.SFRectangularObstacle;

public class SFWalkable implements SFObstaclesNode{

	private ArrayList<SFObstacle> obstacles=new ArrayList<SFObstacle>();
	
	private SFOccluderSet occluders = new SFOccluderSet();
	
	private SFQuadrilateralWalkable quadrilateral ;
	
	private boolean isVisible=true;
	private SFVertex3f edges[][];
	
	private float d1_2Dlength=0;
	private float d2_2Dlength=0;
	
	public SFBounds<SFVertex3f> bounds=new SFBounds<SFVertex3f>(new SFVertex3f(), new SFVertex3f());
	

	public SFNode scene;

	public SFNode getScene() {
		return scene;
	}

	public void setScene(SFNode scene) {
		this.scene = scene;
	}

	private void setup() {
//		data.getPosition().set3f(posx,posy,posz);
//		data.getD1().set3f(d1x,d1y,d1z);
//		data.getD2().set3f(d2x,d2y,d2z);
		
		//used by all calls to get2DPlacementCoord
		
		SFVertex3f pos2=new SFVertex3f(quadrilateral.getPosition());
		pos2.add3f(quadrilateral.getD1());
		SFVertex3f pos3=new SFVertex3f(quadrilateral.getPosition());
		pos3.add3f(quadrilateral.getD2());
		SFVertex3f eds[][]={
			{quadrilateral.getPosition(),quadrilateral.getD1()},
			{quadrilateral.getPosition(),quadrilateral.getD2()},
			{pos2,quadrilateral.getD2()},
			{pos3,quadrilateral.getD1()}
		};
		
		this.edges=eds;
		this.d1_2Dlength=(float)(Math.sqrt(quadrilateral.getD1().getX()*quadrilateral.getD1().getX()+quadrilateral.getD1().getZ()*quadrilateral.getD1().getZ()));
		this.d2_2Dlength=(float)(Math.sqrt(quadrilateral.getD2().getX()*quadrilateral.getD2().getX()+quadrilateral.getD2().getZ()*quadrilateral.getD2().getZ()));
		
		bounds.setBasePoint(quadrilateral.getPosition());

		bounds.includeInBounds(quadrilateral.getPosition().getX(), quadrilateral.getPosition().getY()+quadrilateral.getH1(), quadrilateral.getPosition().getZ());
		bounds.includeInBounds(quadrilateral.getPosition().getX()+quadrilateral.getD1().getX(), quadrilateral.getPosition().getY()+quadrilateral.getD1().getY(), quadrilateral.getPosition().getZ()+quadrilateral.getD1().getZ());
		bounds.includeInBounds(quadrilateral.getPosition().getX()+quadrilateral.getD1().getX(), quadrilateral.getPosition().getY()+quadrilateral.getD1().getY()+quadrilateral.getH2(), quadrilateral.getPosition().getZ()+quadrilateral.getD1().getZ());
		bounds.includeInBounds(quadrilateral.getPosition().getX()+quadrilateral.getD2().getX(), quadrilateral.getPosition().getY()+quadrilateral.getD2().getY(), quadrilateral.getPosition().getZ()+quadrilateral.getD2().getZ());
		bounds.includeInBounds(quadrilateral.getPosition().getX()+quadrilateral.getD2().getX(), quadrilateral.getPosition().getY()+quadrilateral.getD2().getY()+quadrilateral.getH3(), quadrilateral.getPosition().getZ()+quadrilateral.getD2().getZ());
		bounds.includeInBounds(quadrilateral.getPosition().getX()+quadrilateral.getD2().getX()+quadrilateral.getD1().getX(), quadrilateral.getPosition().getY()+quadrilateral.getD2().getY()+quadrilateral.getD1().getY(), quadrilateral.getPosition().getZ()+quadrilateral.getD2().getZ()+quadrilateral.getD1().getZ());
		bounds.includeInBounds(quadrilateral.getPosition().getX()+quadrilateral.getD2().getX(), quadrilateral.getPosition().getY()+quadrilateral.getD2().getY()+quadrilateral.getH4(), quadrilateral.getPosition().getZ()+quadrilateral.getD2().getZ());
	}
	
	
	
	@Override
	public boolean intersect(SFVertex3f P, float ray, float h) {
		for (int i = 0; i < obstacles.size(); i++) {
			if(obstacles.get(i).intersect(P, ray, h)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @deprecated Use {@link shadow.world.SFOccluderSet#occludersCount()} instead
	 */
	@Override
	public int occludersCount() {
		return occluders.occludersCount();
	}

	/**
	 * @deprecated Use {@link shadow.world.SFOccluderSet#getOccluder(int)} instead
	 */
	@Override
	public SFOccluder getOccluder(int index) {
		return occluders.getOccluder(index);
	}
	
	@Override
	public boolean isObjectInCameraBounds(SFCamera camera) {
		return isVisible && SFRectangularObstacle.isParallelepipedInCameraBound(camera,
					new SFVertex3f(bounds.getBasePoint()),
					new SFVertex3f(bounds.getExtension()));
	}
	
	public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F){
		return isVisible && SFRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
				new SFVertex3f(bounds.getBasePoint()),
				new SFVertex3f(bounds.getExtension()));
	}
	
	public void addObstacle(SFObstacle obstacle){
		obstacles.add(obstacle);
	}
	
	public SFWalkable(SFQuadrilateralWalkable quadrilateral) {

		this.quadrilateral=quadrilateral;
		setup();
		
	}
	
	public void getBounds(SFVertex3f pos,SFVertex3f dim){
		
		pos.set3f(bounds.getBasePoint());
		dim.set3f(bounds.getExtension());
	}
	
	public float getX(float u,float v){
		return quadrilateral.getPosition().getX()+u*quadrilateral.getD1().getX()+v*quadrilateral.getD2().getX();
	}
	
	public float getY(float u,float v){
		return quadrilateral.getPosition().getY()+u*quadrilateral.getD1().getY()+v*quadrilateral.getD2().getY();
	}
	
	public float getZ(float u,float v){
		return quadrilateral.getPosition().getZ()+u*quadrilateral.getD1().getZ()+v*quadrilateral.getD2().getZ();
	}
	
	public float getH(float u,float v){
		return (1-u)*(1-v)*quadrilateral.getH1()+u*(1-v)*quadrilateral.getH2()+(1-u)*v*quadrilateral.getH3()+u*v*quadrilateral.getH4();
	}
	
	@Override
	public SFObstacle getObstacle(int index) {
		return obstacles.get(index);
	}
	
	@Override
	public int getObstaclesCount() {
		return obstacles.size();
	}
	
	public ArrayList<SFObstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<SFObstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public void get2DPlacementCoord(float x,float z,float coords[]){
		quadrilateral.get2DPlacementCoord(x, z, coords);
	}
	
	public static boolean areCoordsInside(float[] coords){
		return (coords[0]>=-0.001f && coords[0]<=1.001f) && 
				(coords[1]>=-0.001f && coords[1]<=1.001f);
	}
	
	public static boolean areParallelVector(
			float ax,float ay,float az,
			float bx,float by,float bz){
		float n_x=ay*bz-az*by;
		float n_y=az*bx-ax*bz;
		float n_z=ax*by-ay*bx;
		return (n_x*n_x+n_y*n_y+n_z*n_z)<0.001*(ax*ax+ay*ay+az*az);
	}
	
	public boolean intersectAnyObstacle(SFVertex3f P,float ray,float h){
		for (int i = 0; i < obstacles.size(); i++) {
			boolean b=obstacles.get(i).intersect(P, ray, h);
			if(b)
				return true;
		}
		return false;
	}
	
	public boolean isShadowWalkableConnected(SFWalkable walkable){
		SFVertex3f eds[][]=walkable.edges;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				float p_x=-this.edges[i][0].getX()+eds[j][0].getX();
				float p_y=-this.edges[i][0].getY()+eds[j][0].getY();
				float p_z=-this.edges[i][0].getZ()+eds[j][0].getZ();
					
				float k1=getParallelRap(p_x,p_y,p_z,
						this.edges[i][1].getX(),
						this.edges[i][1].getY(),
						this.edges[i][1].getZ());
				float k2=getParallelRap(eds[j][1].getX(), 
						eds[j][1].getY(),
						eds[j][1].getZ(),
						this.edges[i][1].getX(),
						this.edges[i][1].getY(),
						this.edges[i][1].getZ());
				
				if(k2!=0){
					boolean do_it=false;
					if(k1==0){
						float quad=p_x*p_x+p_y*p_y+p_z*p_z;
						if(quad<this.edges[i][1].dot3f(this.edges[i][1])*0.001f)
							do_it=true;
					}else{
						do_it=true;
					}	
					if (do_it) {
						if(k2>0){
							if(k1<1 && k1+k2>0)
								return true;
						}
						if(k2<0){
							if(k1>0 && k2+k1<1)
								return true;
						}
					}
				}
				
			}
		}
		return false;
	}

	/**
	 * Return 0 if vectors are not parallel, and
	 * 
	 * k1 such that (ax,ay,az)=k1 (bx,by,bz) if they are 
	 * 
	 * @param ax
	 * @param ay
	 * @param az
	 * @param bx
	 * @param by
	 * @param bz
	 * @return
	 */
	public static float getParallelRap(
			float ax,float ay,float az,
			float bx,float by,float bz){
		float n_x=ay*bz-az*by;
		float n_y=az*bx-ax*bz;
		float n_z=ax*by-ay*bx;
		if ((n_x*n_x+n_y*n_y+n_z*n_z)<0.001*(ax*ax+ay*ay+az*az)){
			if(bx!=0)
				return ax/bx;
			else if(by!=0)
				return ay/by;
			else if(bz!=0)
				return az/bz;
		}
		return 0;
	}
	
	
	public boolean intersect(SFVertex3f Q,SFVertex3f Dir,SFVertex3f Point){
		
		SFMatrix3f m=new SFMatrix3f(
				quadrilateral.getD1().getX(),quadrilateral.getD2().getX(),-Dir.getX(),
				quadrilateral.getD1().getY(),quadrilateral.getD2().getY(),-Dir.getY(),
				quadrilateral.getD1().getZ(),quadrilateral.getD2().getZ(),-Dir.getZ()
		);
		m=SFMatrix3f.getInverse(m);
		SFVertex3f Qp=new SFVertex3f(Q);
		Qp.subtract3f(quadrilateral.getPosition());
		SFVertex3f uvt=m.Mult(Qp);
		
		if(uvt.getX()>=0 && uvt.getX()<=1 && uvt.getY()>=0 && uvt.getY()<=1){
			Point.set3f(Q.getX()+uvt.getZ()*Dir.getX(),
					  Q.getY()+uvt.getZ()*Dir.getY(),
					  Q.getZ()+uvt.getZ()*Dir.getZ());
			return true;
		}
		
		return false;
	}
	
	

	public SFQuadrilateralWalkable getQuadrilateral() {
		return quadrilateral;
	}

	public void setQuadrilateral(SFQuadrilateralWalkable quadrilateral) {
		this.quadrilateral = quadrilateral;
	}

	public float getD1_2Dlength() {
		return d1_2Dlength;
	}

	public void setD1_2Dlength(float d1_2Dlength) {
		this.d1_2Dlength = d1_2Dlength;
	}

	public float getD2_2Dlength() {
		return d2_2Dlength;
	}

	public void setD2_2Dlength(float d2_2Dlength) {
		this.d2_2Dlength = d2_2Dlength;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
