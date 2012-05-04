package shadow.world.temp;

import java.util.ArrayList;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;

public class ShadowWalkable implements ShadowObstaclesNode{

	private int drawingColor=0xffff0000;
	
	private ArrayList<ShadowObstacle> obstacles=new ArrayList<ShadowObstacle>();
	private ArrayList<ShadowOccluder> occluders=new ArrayList<ShadowOccluder>();
	
	private float Dmat[]=new float[4];
	
	private SFVertex3f position=new SFVertex3f();
	private SFVertex3f D1=new SFVertex3f();
	private SFVertex3f D2=new SFVertex3f();
	private float h1,h2,h3,h4;
	
	private boolean isVisible=true;
	private SFVertex3f edges[][];
	
	private float d1_2Dlength=0;
	private float d2_2Dlength=0;
	
	public ShadowWalkable(float posx,float posy,float posz, float d1x,float d1y,float d1z,
			float d2x,float d2y,float d2z) {
		super();
		setup(posx, posy, posz, d1x, d1y, d1z, d2x, d2y, d2z);
	}
	

	public SFNode scene;

	public SFNode getScene() {
		return scene;
	}

	public void setScene(SFNode scene) {
		this.scene = scene;
	}

	private void setup(float posx, float posy, float posz, float d1x,
			float d1y, float d1z, float d2x, float d2y, float d2z) {
		position.set3f(posx,posy,posz);
		D1.set3f(d1x,d1y,d1z);
		D2.set3f(d2x,d2y,d2z);
		
		//used by all calls to get2DPlacementCoord
		float delta=d1x*d2z-d1z*d2x;
		if(delta!=0){
			delta=1.0f/delta;
			Dmat[0]=d2z*delta;
			Dmat[1]=-d2x*delta;
			Dmat[2]=-d1z*delta;
			Dmat[3]=d1x*delta;
		}
		
		SFVertex3f pos2=new SFVertex3f(position);
		pos2.add3f(D1);
		SFVertex3f pos3=new SFVertex3f(position);
		pos3.add3f(D2);
		SFVertex3f eds[][]={
			{position,D1},
			{position,D2},
			{pos2,D2},
			{pos3,D1}
		};
		
		this.edges=eds;
		this.d1_2Dlength=(float)(Math.sqrt(D1.getX()*D1.getX()+D1.getZ()*D1.getZ()));
		this.d2_2Dlength=(float)(Math.sqrt(D2.getX()*D2.getX()+D2.getZ()*D2.getZ()));
		
		minX=position.getX();
		maxX=position.getX();
		minY=position.getY();
		maxY=position.getY();
		minZ=position.getZ();
		maxZ=position.getZ();

		checkBounds(position.getX(), position.getY()+h1, position.getZ());
		checkBounds(position.getX()+D1.getX(), position.getY()+D1.getY(), position.getZ()+D1.getZ());
		checkBounds(position.getX()+D1.getX(), position.getY()+D1.getY()+h2, position.getZ()+D1.getZ());
		checkBounds(position.getX()+D2.getX(), position.getY()+D2.getY(), position.getZ()+D2.getZ());
		checkBounds(position.getX()+D2.getX(), position.getY()+D2.getY()+h3, position.getZ()+D2.getZ());
		checkBounds(position.getX()+D2.getX()+D1.getX(), position.getY()+D2.getY()+D1.getY(), position.getZ()+D2.getZ()+D1.getZ());
		checkBounds(position.getX()+D2.getX(), position.getY()+D2.getY()+h4, position.getZ()+D2.getZ());
	}
	
	float minX,maxX,minY,maxY,minZ,maxZ;
	
	@Override
	public boolean intersect(SFVertex3f P, float ray, float h) {
		for (int i = 0; i < obstacles.size(); i++) {
			if(obstacles.get(i).intersect(P, ray, h)){
				return true;
			}
		}
		return false;
	}
	
	public void addOccluder(ShadowOccluder occluder){
		occluders.add(occluder);
	}

	@Override
	public int occludersCount() {
		return occluders.size();
	}

	@Override
	public ShadowOccluder getOccluder(int index) {
		return occluders.get(index);
	}
	
	public void checkBounds(float x,float y,float z){
		if(minX>x)
			minX=x;
		if(maxX<x)
			maxX=x;
		
		if(minY>y)
			minY=y;
		if(maxY<y)
			maxY=y;
		
		if(minZ>z)
			minZ=z;
		if(maxZ<z)
			maxZ=z;
	}
	
	@Override
	public boolean isObjectInCameraBounds(SFCamera camera) {
		return isVisible && ShadowRectangularObstacle.isParallelepipedInCameraBound(camera,
					new SFVertex3f(minX,minY,minZ),
					new SFVertex3f(maxX-minX,maxY-minY,maxZ-minZ));
	}
	
	public boolean isObjectOccluded(ShadowOccluder occluder,SFVertex3f F){
		return isVisible && ShadowRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
				new SFVertex3f(minX,minY,minZ),
				new SFVertex3f(maxX-minX,maxY-minY,maxZ-minZ));
	}
	
	public void addObstacle(ShadowObstacle obstacle){
		obstacles.add(obstacle);
	}
	
	public ShadowWalkable(float posx,float posy,float posz, float d1x,float d1y,float d1z,
			float d2x,float d2y,float d2z, 
			float h1, float h2, float h3, float h4) {

		this.h1 = h1;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		setup(posx, posy, posz, d1x, d1y, d1z, d2x, d2y, d2z);
	}
	
	public void getBounds(SFVertex3f pos,SFVertex3f dim){
		
		pos.set3f(minX,minY,minZ);
		dim.set3f(maxX-minX,maxY-minY,maxZ-minZ);
	}
	
	public float getX(float u,float v){
		return position.getX()+u*D1.getX()+v*D2.getX();
	}
	
	public float getY(float u,float v){
		return position.getY()+u*D1.getY()+v*D2.getY();
	}
	
	public float getZ(float u,float v){
		return position.getZ()+u*D1.getZ()+v*D2.getZ();
	}
	
	public float getH(float u,float v){
		return (1-u)*(1-v)*h1+u*(1-v)*h2+(1-u)*v*h3+u*v*h4;
	}
	
	@Override
	public ShadowObstacle getObstacle(int index) {
		return obstacles.get(index);
	}
	
	@Override
	public int getObstaclesCount() {
		return obstacles.size();
	}
	
	public ArrayList<ShadowObstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<ShadowObstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public void get2DPlacementCoord(float x,float z,float coords[]){
		x-=position.getX();
		z-=position.getZ();
		coords[0]=Dmat[0]*x+Dmat[1]*z;
		coords[1]=Dmat[2]*x+Dmat[3]*z;
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
	
	public boolean isShadowWalkableConnected(ShadowWalkable walkable){
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
	
	
	public void draw(){
		
//		for (int i = 0; i < occludersCount(); i++) {
//			getOccluder(i).draw(gl);
//		}
//
//		GLColors.color4f(gl, 0xffbbbbbb);
//		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX(),position.getY()+D2.getY(),position.getZ()+D2.getZ());
//			gl.SFVertex3f(position.getX()+D1.getX(),position.getY()+D1.getY(),position.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX()+D1.getX(),position.getY()+D2.getY()+D1.getY(),position.getZ()+D2.getZ()+D1.getZ());
//		gl.glEnd();
//		
//		GLColors.color4f(gl, drawingColor);
//		
//		gl.glBegin(GL2.GL_LINE_LOOP);
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+D1.getX(),position.getY()+D1.getY(),position.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX()+D1.getX(),position.getY()+D2.getY()+D1.getY(),position.getZ()+D2.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX(),position.getY()+D2.getY(),position.getZ()+D2.getZ());
//		gl.glEnd();
//		gl.glBegin(GL2.GL_LINE_LOOP);
//			gl.SFVertex3f(position.getX(),position.getY()+h1,position.getZ());
//			gl.SFVertex3f(position.getX()+D1.getX(),position.getY()+D1.getY()+h2,position.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX()+D1.getX(),position.getY()+D2.getY()+D1.getY()+h4,position.getZ()+D2.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX(),position.getY()+D2.getY()+h3,position.getZ()+D2.getZ());
//		gl.glEnd();
//		gl.glBegin(GL2.GL_LINES);
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX(),position.getY()+h1,position.getZ());
//			gl.SFVertex3f(position.getX()+D1.getX(),position.getY()+D1.getY(),position.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D1.getX(),position.getY()+D1.getY()+h2,position.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX()+D1.getX(),position.getY()+D2.getY()+D1.getY(),position.getZ()+D2.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX()+D1.getX(),position.getY()+D2.getY()+D1.getY()+h4,position.getZ()+D2.getZ()+D1.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX(),position.getY()+D2.getY(),position.getZ()+D2.getZ());
//			gl.SFVertex3f(position.getX()+D2.getX(),position.getY()+D2.getY()+h3,position.getZ()+D2.getZ());
//		gl.glEnd();
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
				D1.getX(),D2.getX(),-Dir.getX(),
				D1.getY(),D2.getY(),-Dir.getY(),
				D1.getZ(),D2.getZ(),-Dir.getZ()
		);
		m=SFMatrix3f.getInverse(m);
		SFVertex3f Qp=new SFVertex3f(Q);
		Qp.subtract3f(position);
		SFVertex3f uvt=m.Mult(Qp);
		
		if(uvt.getX()>=0 && uvt.getX()<=1 && uvt.getY()>=0 && uvt.getY()<=1){
			Point.set3f(Q.getX()+uvt.getZ()*Dir.getX(),
					  Q.getY()+uvt.getZ()*Dir.getY(),
					  Q.getZ()+uvt.getZ()*Dir.getZ());
			return true;
		}
		
		return false;
	}
	
	public SFVertex3f getPosition() {
		return position;
	}
	
	public void setPosition(SFVertex3f position) {
		this.position = position;
	}
	
	public SFVertex3f getD1() {
		return D1;
	}
	
	public void setD1(SFVertex3f d1) {
		D1 = d1;
	}
	
	public SFVertex3f getD2() {
		return D2;
	}
	public void setD2(SFVertex3f d2) {
		D2 = d2;
	}
	
	public float getH1() {
		return h1;
	}
	
	public void setH1(float h1) {
		this.h1 = h1;
	}
	
	public float getH2() {
		return h2;
	}
	
	public void setH2(float h2) {
		this.h2 = h2;
	}
	
	public float getH3() {
		return h3;
	}
	
	public void setH3(float h3) {
		this.h3 = h3;
	}
	
	public float getH4() {
		return h4;
	}
	
	public void setH4(float h4) {
		this.h4 = h4;
	}

	public int getDrawingColor() {
		return drawingColor;
	}

	public void setDrawingColor(int drawingColor) {
		this.drawingColor = drawingColor;
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
