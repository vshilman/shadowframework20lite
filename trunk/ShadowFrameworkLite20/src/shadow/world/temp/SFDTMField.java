package shadow.world.temp;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;



public class SFDTMField implements SFGenericWalkablesNet,SFObstaclesNode{

	//public class GLDTM{
	private ArrayList<SFObstacle> obstacles=new ArrayList<SFObstacle>();
	private DTMWalkable walkables[];

	//these are all the required data
	public float _dX;
	public float _dZ;
	public int dimW,dimH;
	public float x0;
	public float z0; 
	public float[] height;
	public float minHeight;
	public float maxHeight;

	public int drawingMode=0;
	public int color;
	
	public boolean entered;
	
	
//	double bMinX=0,bMaxX=0;
//	double bMinY=0,bMaxY=0;
//	double bMinZ=0,bMaxZ=0;
	
	public SFDTMField(float localStepX,float localStepY,int dimW,int dimH) {
		this._dX=localStepX;
		this._dZ=localStepY;
		this.dimW=dimW;
		this.dimH=dimH;
		height=new float[dimW*dimH];//ok, dimW e dimH sono proprio le dimensioni dei dati.
		for (int i = 0; i < height.length; i++) {
			height[i]=0;
		}
		walkables=new DTMWalkable[(dimW-2)*(dimH-2)];
		for(int i=1;i<dimH-1;i++){
			for(int j=1;j<dimW-1;j++){	
				walkables[j-1+(i-1)*(dimW-2)]=new DTMWalkable(j,i);
			}
		}
	}
	
	
	public boolean isEntered() {
		return entered;
	}



	public void setEntered(boolean entered) {
		this.entered = entered;
	}



	@Override
	public SFObstacle getObstacle(int index) {
		if(index<obstacles.size())
			return obstacles.get(index);
		else
			return walkables[index-obstacles.size()];
	}
	@Override
	public int getObstaclesCount() {
		return obstacles.size()+walkables.length;
	}

	@Override
	public boolean enter(SFVertex3f position, float ray) {
		
		if(position.getX()<x0+0.5f*_dX+ray)
			return false;
		if(position.getX()>x0+(dimW-1.5f)*_dX-ray)
			return false;
		
		if(position.getZ()<z0+0.5f*_dZ+ray)
			return false;
		if(position.getZ()>z0+(dimH-1.5f)*_dZ-ray)
			return false;
		
		return true;
	}
	
	@Override
	public boolean isIn(SFVertex3f position, float maxDistance) {
		
		if(position.getX()<x0+0.5f*_dX-maxDistance)
			return false;
		if(position.getX()>x0+(dimW-1.5f)*_dX+maxDistance)
			return false;
		
		if(position.getZ()<z0+0.5f*_dZ-maxDistance)
			return false;
		if(position.getZ()>z0+(dimH-1.5f)*_dZ+maxDistance)
			return false;
		
		return true;
	}
	
	@Override
	public boolean isObjectInCameraBounds(SFCamera camera) {
		
		return SFRectangularObstacle.isParallelepipedInCameraBound(camera,
				new SFVertex3f(x0+_dX*0.5f,minHeight,z0+_dZ*0.5f),
				new SFVertex3f(_dX*(dimW-2),maxHeight-minHeight,_dZ*(dimH-2)));
	}
	


	public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F){
		return SFRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
				new SFVertex3f(x0+_dX*0.5f,minHeight,z0+_dZ*0.5f),
				new SFVertex3f(_dX*(dimW-2),maxHeight-minHeight,_dZ*(dimH-2)));
	}
	
	@Override
	public boolean intersect(SFVertex3f P, float ray, float h) {
		float posx=x0+_dX*0.5f;
		float dimx=(dimW-2)*_dX;
		if(P.getX()+ray<posx || P.getX()-ray>posx+dimx){
			return false;
		}
		float posz=z0+_dZ*0.5f;
		float dimz=(dimH-2)*_dZ;
		if(P.getZ()+ray<posz || P.getZ()-ray>posz+dimz){
			return false;
		}
		float posy=minHeight;
		float dimy=maxHeight-minHeight;
		if(P.getY()+h<posy || P.getY()>posy+dimy){
			return false;
		}
		return false;
	}
	
	private ArrayList<SFPickable> pickable=new ArrayList<SFPickable>();
	
	public void addPickable(SFPickable pick){
		pickable.add(pick);
	}
	
	public SFPickable getPickable(SFVertex3f Q,SFVertex3f Dir){
		for (int i = 0; i < pickable.size(); i++) {
			SFPickable pick=pickable.get(i);
			if(SFRectangularObstacle.isIntersecting(pick.p, pick.dim, Q, Dir)){
				return pick;
			}
		}
		return null;
	}
	
	public void addObstacle(SFObstacle obstacle){
		obstacles.add(obstacle);
	}

	public ArrayList<SFObstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<SFObstacle> obstacles) {
		this.obstacles = obstacles;
	}
	
	@Override
	public int occludersCount() {
		return 0;
	}

	@Override
	public SFOccluder getOccluder(int index) {
		return null;
	}
	
	
	public boolean intersectAnyObstacle(SFVertex3f P,float ray,float h){
		for (int i = 0; i < obstacles.size(); i++) {
			boolean b=obstacles.get(i).intersect(P, ray, h);
			if(b)
				return true;
		}
		return false;
	}
	

	@Override
	public boolean canExit(int walkableIndex) {
		return true;
	}
	
	
	static BufferedImage im;
	int tobj1,tobj2;
	int tCode1,tCode2;
	public void init() {
		evalMinMaxHeight();
		
	}
	
	public void evalMinMaxHeight(){
		minHeight=height[0];
		maxHeight=height[0];
		System.err.println("height[0] " + height[0]);
		for(int i=1;i<height.length;i++){
			System.err.println("height[i] " + height[i]);
			if(height[i]<minHeight)
				minHeight=height[i];
			if(height[i]>maxHeight)
				maxHeight=height[i];
		}
	}
	
	@Override
	public float getY(int walkable, SFVertex3f position) {
		
		float xRel=(float)((position.getX()+_dX*0.5f-x0)/_dX);
		float zRel=(float)((position.getZ()+_dZ*0.5f-z0)/_dZ);
		
		int J=(int)(xRel);
		int I=(int)(zRel);
		
		float u=xRel-J;
		float v=zRel-I;

		float v1=getHeight(I-1, J-1);
		float v2=getHeight(I-1, J);
		float v3=getHeight(I-1, J+1);
		float v4=getHeight(I, J-1);
		float v5=getHeight(I, J);
		float v6=getHeight(I, J+1);
		float v7=getHeight(I+1, J-1);
		float v8=getHeight(I+1, J);
		float v9=getHeight(I+1, J+1);
		
		float y1=0.25f*(v1+v2+v4+v5);
		float y2=0.5f*(v2+v5);
		float y3=0.25f*(v2+v3+v5+v6);
		float y4=0.5f*(v4+v5);
		float y5=v5;
		float y6=0.5f*(v5+v6);
		float y7=0.25f*(v4+v5+v7+v8);
		float y8=0.5f*(v5+v8);
		float y9=0.25f*(v5+v6+v8+v9);
		
		float y=(1-v)*(1-v)*(y1*(1-u)*(1-u)+y2*2*u*(1-u)+y3*u*u)+
				2*v*(1-v)*(y4*(1-u)*(1-u)+y5*2*u*(1-u)+y6*u*u)+
				v*v*(y7*(1-u)*(1-u)+y8*2*u*(1-u)+y9*u*u);
		
		return y;
	}
	
	@Override
	public int findWalkable(SFVertex3f position) {
		return canWalk(0, position, 0, 0);//don't really care
	}
	
	@Override
	public void moveOnEdge(int walkable, SFVertex3f actualPosition,
			SFVertex3f destination, float ray, float height) {

		float w=(dimW-2)*_dX;
		float h=(dimH-2)*_dZ;
		float a=ray/w;
		float b=ray/h;
		float u_pos=(actualPosition.getX()-x0-_dX*0.5f)/(w);
		float v_pos=(actualPosition.getZ()-z0-_dZ*0.5f)/(h);
		float u_dst=(destination.getX()-x0-_dX*0.5f)/(w);
		float v_dst=(destination.getZ()-z0-_dZ*0.5f)/(h);
		
		float t1=1,t2=1;
		if(u_dst+a>1){//u_dest+b>1
			float u=1-a;
			t1=(u-u_pos)/(u_dst-u_pos);
		}else if(u_dst-a<0){
			float u=a;
			t1=(u-u_pos)/(u_dst-u_pos);
		}
		if(v_dst+b>1){//u_dest+b>1
			float v=1-b;
			t2=(v-v_pos)/(v_dst-v_pos);
		}else if(v_dst-b<0){
			float v=b;
			t2=(v-v_pos)/(v_dst-v_pos);
		}
		
		float u=u_pos*(1-t1)+u_dst*t1;
		float v=v_pos*(1-t2)+v_dst*t2;
		
		actualPosition.setX(x0+_dX*(0.5f)+u*w);
		actualPosition.setZ(z0+_dZ*(0.5f)+v*h);
		actualPosition.setY(getY(walkable, actualPosition));
	}
	
	@Override
	public int getWalkablesCount() {
		return (dimH-1)*(dimW-1);//TODO
	}
	
	@Override
	public int canWalk(int walkable, SFVertex3f position, float ray,
			float height) {
		
		float x=position.getX();
		float z=position.getZ();
		double w=(dimW-2)*_dX;
		double h=(dimH-2)*_dZ;
		
		if(x-ray<x0+_dX*0.5f)
			return -1;
		if(x+ray>x0+_dX*0.5f+w)
			return -1;
		if(z-ray<z0+_dZ*0.5f)
			return -1;
		if(z+ray>z0+_dZ*0.5f+h)
			return -1;

		if(intersectAnyObstacle(position,ray,height)){
			return -2;
		}	
		
		int J=(int)((x-x0)/_dX);
		int I=(int)((z-z0)/_dZ);
		
		return J+I*(dimW-1);
	}

	@Override
	public boolean intersect(int walkable, SFVertex3f Q, SFVertex3f Dir,
			SFVertex3f Point) {
		
		boolean found=false;
		SFVertex3f ret=new SFVertex3f();
		for(int i=1;i<dimH-1;i++){//indices of patch
			for(int j=1;j<dimW-1;j++){
				SFVertex3f v=findIntersectionOnPatch(Q, Dir, i, j);
				
				if(v!=null){
					if(!found){
						ret.set(v);
						found=true; //if v.getZ()>ret.getZ(). Don't think it is correct. 
					}else if(v.getZ()>ret.getZ()){//TODO: i don't agree with this condition. I'm going to make a math check. 
						ret.set(v);
					}
				}	
			}
		}
		if(found){
			Point.set(ret);
			return true;
		}
			
		return false;
	}
	
	//private int inters_i,inters_j;
	
	private float getHeight(int i,int j){
		
		if(i<0)
			i=0;
		else if(i>=dimH)
			i=dimH-1;
		if(j<0)
			j=0;
		else if(j>=dimW)
			j=dimW-1;
		
		return height[dimW*i+j];
		
	}

	private SFVertex3f findIntersectionOnPatch(SFVertex3f Q1, SFVertex3f Dir, int i,
			int j) {
		double Xmin=x0+(_dX*(j-0.5f));
		double Ymin=z0+(_dZ*(i-0.5f));
		
		double Xmax=Xmin+_dX;
		double Ymax=Ymin+_dZ;
		
		double tmin=0;
		double tmax=0;
		if(Dir.getX()!=0){
			double t1x=(Xmin-Q1.getX())/Dir.getX();
			double t2x=(Xmax-Q1.getX())/Dir.getX();
			if(t1x>t2x){ double t=t1x;t1x=t2x;t2x=t; }
			tmin=t1x;
			tmax=t2x;
			System.err.println("t1x " + t1x + " t2x " + t2x);
			if(Dir.getY()!=0){
				double t1y=(Ymin-Q1.getZ())/Dir.getZ();
				double t2y=(Ymax-Q1.getZ())/Dir.getZ();
				if(t1y>t2y){ double t=t1y;t1y=t2y;t2y=t; }
				if(tmin<t1y)tmin=t1y;
				if(tmax>t2y)tmax=t2y;
				System.err.println("t1y " + t1y + " t2y " + t2y);
				if(Dir.getZ()!=0){
					double t1z=(minHeight-Q1.getY())/Dir.getY();
					double t2z=(maxHeight-Q1.getY())/Dir.getY();
					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
					System.err.println("t1z " + t1z + " t2z " + t2z);
					if(tmin<t1z)tmin=t1z;
					if(tmax>t2z)tmax=t2z;
				}else{
					if(!(Q1.getZ()>minHeight && Q1.getZ()<maxHeight))
						return null;
				}
			}else{
				if(!(Q1.getY()>Ymin && Q1.getY()<Ymax))
					return null;
				if(Dir.getZ()!=0){
					double t1z=(minHeight-Q1.getZ())/Dir.getZ();
					double t2z=(maxHeight-Q1.getZ())/Dir.getZ();
					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
					if(tmin<t1z)tmin=t1z;
					if(tmax>t2z)tmax=t2z;
				}else{
					if(!(Q1.getZ()>=minHeight && Q1.getZ()<=maxHeight))
						return null;
				}
			}
		}else{
			if(!(Q1.getX()>Xmin && Q1.getX()<Xmax))
				return null;
			if(Dir.getY()!=0){
				double t1y=(Ymin-Q1.getY())/Dir.getY();
				double t2y=(Ymax-Q1.getY())/Dir.getY();
				if(t1y>t2y){ double t=t1y;t1y=t2y;t2y=t; }
				tmin=t1y;
				tmax=t2y;
				if(Dir.getZ()!=0){
					double t1z=(minHeight-Q1.getZ())/Dir.getZ();
					double t2z=(maxHeight-Q1.getZ())/Dir.getZ();
					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
					if(tmin<t1z)tmin=t1z;
					if(tmax>t2z)tmax=t2z;
				}else{
					if(!(Q1.getZ()>minHeight && Q1.getZ()<maxHeight))
						return null;
				}
			}else{
				if(!(Q1.getY()>Ymin && Q1.getY()<Ymax))
					return null;
				if(Dir.getZ()!=0){
					double t1z=(minHeight-Q1.getZ())/Dir.getZ();
					double t2z=(maxHeight-Q1.getZ())/Dir.getZ();
					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
					tmin=t1z;
					tmax=t2z;
				}else{
					if(!(Q1.getZ()>minHeight && Q1.getZ()<maxHeight))
						return null;
				}
			}
		}
		System.err.println("tmin " + tmin);
		System.err.println("tmax " + tmax);
		
		if(tmin<tmax){
			System.err.println("Enter ");
			float v1=getHeight(i-1, j-1);
			float v2=getHeight(i-1, j);
			float v3=getHeight(i-1, j+1);
			float v4=getHeight(i, j-1);
			float v5=getHeight(i, j);
			float v6=getHeight(i, j+1);
			float v7=getHeight(i+1, j-1);
			float v8=getHeight(i+1, j);
			float v9=getHeight(i+1, j+1);
			float z1=0.25f*(v1+v2+v4+v5);
			float z2=0.5f*(v2+v5);
			float z3=0.25f*(v2+v3+v5+v6);
			float z4=0.5f*(v4+v5);
			float z5=v5;
			float z6=0.5f*(v5+v6);
			float z7=0.25f*(v4+v5+v7+v8);
			float z8=0.5f*(v5+v8);
			float z9=0.25f*(v5+v6+v8+v9);
			
			//this is ok.
			double au=(Q1.getX()-Xmin)/(Xmax-Xmin);
			double bu=(Dir.getX())/(Xmax-Xmin);
			double av=(Q1.getZ()-Ymin)/(Ymax-Ymin);
			double bv=(Dir.getZ())/(Ymax-Ymin);
			
			double tMin=-1,tMax=2;
			if(bu!=0){
				double t1u=(double)(-au/bu);
				double t2u=(double)((1-au)/bu);
				if(bu>0){
					tMin=t1u;tMax=t2u;
				}else{
					tMin=t2u;tMax=t1u;
				}
				if(bv!=0){
					double t1v=(double)(-av/bv);
					double t2v=(double)((1-av)/bv);
					if(bv>0){
						if(t1v>tMin)tMin=t1v;
						if(t2v<tMax)tMax=t2v;
					}else{
						if(t2v>tMin)tMin=t2v;
						if(t1v<tMax)tMax=t1v;
					}
				}
			}else{
				double t1v=(double)(-av/bv);
				double t2v=(double)((1-av)/bv);
				if(bv>0){
					tMin=t1v;tMax=t2v;
				}else{
					tMin=t2v;tMax=t1v;
				}
			}
			System.err.println("tMin " + tMin + " tMax " + tMax);
			
			int DIVISIONS=10;
			float step=1.0f/DIVISIONS;
			
			float tInter=0;
			float zInter=0;
			boolean found=false;
			
			for (int k = 0; k <DIVISIONS; k++) {
				double ts=tmin+k*step*(tmax-tmin);
				double us=au+ts*bu;
				double vs=av+ts*bv;
				double usm=1-us;
				double vsm=1-vs;
				double tr=tmin+(k+1)*step*(tmax-tmin);
				double ur=au+tr*bu;
				double vr=av+tr*bv;
				double urm=1-ur;
				double vrm=1-vr;
				
				double zs=vsm*vsm*(usm*usm*z1+2*us*usm*z2+us*us*z3)+
						2*vs*vsm*(usm*usm*z4+2*us*usm*z5+us*us*z6)+
						vs*vs*(usm*usm*z7+2*us*usm*z8+us*us*z9);
				double zr=vrm*vrm*(urm*urm*z1+2*ur*urm*z2+ur*ur*z3)+
						2*vr*vrm*(urm*urm*z4+2*ur*urm*z5+ur*ur*z6)+
						vr*vr*(urm*urm*z7+2*ur*urm*z8+ur*ur*z9);
				
				double zA=Q1.getY()+Dir.getY()*ts;
				double zB=Q1.getY()+Dir.getY()*tr;
				
				double DELTA=(zB-zA)-(zr-zs);
				if(DELTA!=0){
					double T=(zs-zA)/(DELTA);
					if(T>=0 && T<=1){
						if(!found){
							found=true;
							tInter=(float)(T*(tr-ts)+ts);
							zInter=(float)(T*(zr-zs)+zs);
						}else{
							float z=(float)(T*(zr-zs)+zs);
							if(zInter>z){
								zInter=z;
								tInter=(float)T;
							}
						}
					}
				}
			}
		 
			if(found){
				SFVertex3f v=new SFVertex3f(Q1.getX()+tInter*Dir.getX(),Q1.getY()+tInter*Dir.getY(),Q1.getZ()+tInter*Dir.getZ());
				return v;
			}
		}
		
		return null;
	}
	

	public SFNode scene;

	public SFNode getScene() {
		return scene;
	}

	public void setScene(SFNode scene) {
		this.scene = scene;
	}
	
	private class DTMWalkable implements SFObstacle{
		
		int i,j;
		public boolean visible;
		
		public DTMWalkable(int j, int i) {
			super();
			this.j = j;
			this.i = i;
		}
		
		
		@Override
		public SFOccluder getOccluder(int index) {
			return null;
		}
		
		@Override
		public boolean intersect(SFVertex3f P, float ray, float h) {
			return false;
		}
		
		@Override
		public boolean isObjectInCameraBounds(SFCamera camera) {
			boolean b= SFRectangularObstacle.isParallelepipedInCameraBound(camera,
					new SFVertex3f(x0+_dX*0.5f*(2*j-1),minHeight,z0+_dZ*0.5f*(2*i-1)),
					new SFVertex3f(_dX,maxHeight-minHeight,_dZ));
			return b;
			
		}
		
		public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F){
			boolean b= SFRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
					new SFVertex3f(x0+_dX*0.5f*(2*j-1),minHeight,z0+_dZ*0.5f*(2*i-1)),
					new SFVertex3f(_dX,maxHeight-minHeight,_dZ));
			return b;
		}
		
		@Override
		public int occludersCount() {
			return 0;
		} 
		
		public SFNode scene;

		public SFNode getScene() {
			return null;
		}

		public void setScene(SFNode scene) {
			this.scene = scene;
		}
		
	}
}
