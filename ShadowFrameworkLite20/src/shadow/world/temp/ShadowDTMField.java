package shadow.world.temp;


//
//public class ShadowDTMField implements ShadowGenericWalkablesNet,ShadowObstaclesNode{
//
//	//public class GLDTM{
//	private ArrayList<ShadowObstacle> obstacles=new ArrayList<ShadowObstacle>();
//	private DTMWalkable walkables[];
//
//	//these are all the required data
//	public float _dX;
//	public float _dZ;
//	public int dimW,dimH;
//	public float x0;
//	public float z0; 
//	public float[] height;
//	public float minHeight;
//	public float maxHeight;
//
//	public int drawingMode=0;
//	public int color;
//	
//	public boolean entered;
//	
//	
////	double bMinX=0,bMaxX=0;
////	double bMinY=0,bMaxY=0;
////	double bMinZ=0,bMaxZ=0;
//	
//	public ShadowDTMField(float localStepX,float localStepY,int dimW,int dimH) {
//		this._dX=localStepX;
//		this._dZ=localStepY;
//		this.dimW=dimW;
//		this.dimH=dimH;
//		height=new float[dimW*dimH];//ok, dimW e dimH sono proprio le dimensioni dei dati.
//		for (int i = 0; i < height.length; i++) {
//			height[i]=0;
//		}
//		walkables=new DTMWalkable[(dimW-2)*(dimH-2)];
//		for(int i=1;i<dimH-1;i++){
//			for(int j=1;j<dimW-1;j++){	
//				walkables[j-1+(i-1)*(dimW-2)]=new DTMWalkable(j,i);
//			}
//		}
//	}
//	
//	
//	public boolean isEntered() {
//		return entered;
//	}
//
//
//
//	public void setEntered(boolean entered) {
//		this.entered = entered;
//	}
//
//
//
//	@Override
//	public ShadowObstacle getObstacle(int index) {
//		if(index<obstacles.size())
//			return obstacles.get(index);
//		else
//			return walkables[index-obstacles.size()];
//	}
//	@Override
//	public int getObstaclesCount() {
//		return obstacles.size()+walkables.length;
//	}
//
//	@Override
//	public boolean enter(GLVertex3f position, float ray) {
//		
//		if(position.x<x0+0.5f*_dX+ray)
//			return false;
//		if(position.x>x0+(dimW-1.5f)*_dX-ray)
//			return false;
//		
//		if(position.z<z0+0.5f*_dZ+ray)
//			return false;
//		if(position.z>z0+(dimH-1.5f)*_dZ-ray)
//			return false;
//		
//		return true;
//	}
//	
//	@Override
//	public boolean isIn(GLVertex3f position, float maxDistance) {
//		
//		if(position.x<x0+0.5f*_dX-maxDistance)
//			return false;
//		if(position.x>x0+(dimW-1.5f)*_dX+maxDistance)
//			return false;
//		
//		if(position.z<z0+0.5f*_dZ-maxDistance)
//			return false;
//		if(position.z>z0+(dimH-1.5f)*_dZ+maxDistance)
//			return false;
//		
//		return true;
//	}
//	
//	@Override
//	public boolean isObjectInCameraBounds(ShadowCamera camera) {
//		
//		return ShadowRectangularObstacle.isParallelepipedInCameraBound(camera,
//				new GLVertex3f(x0+_dX*0.5f,minHeight,z0+_dZ*0.5f),
//				new GLVertex3f(_dX*(dimW-2),maxHeight-minHeight,_dZ*(dimH-2)));
//	}
//	
//
//
//	public boolean isObjectOccluded(ShadowOccluder occluder,GLVertex3f F){
//		return ShadowRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
//				new GLVertex3f(x0+_dX*0.5f,minHeight,z0+_dZ*0.5f),
//				new GLVertex3f(_dX*(dimW-2),maxHeight-minHeight,_dZ*(dimH-2)));
//	}
//	
//	@Override
//	public boolean intersect(GLVertex3f P, float ray, float h) {
//		float posx=x0+_dX*0.5f;
//		float dimx=(dimW-2)*_dX;
//		if(P.x+ray<posx || P.x-ray>posx+dimx){
//			return false;
//		}
//		float posz=z0+_dZ*0.5f;
//		float dimz=(dimH-2)*_dZ;
//		if(P.z+ray<posz || P.z-ray>posz+dimz){
//			return false;
//		}
//		float posy=minHeight;
//		float dimy=maxHeight-minHeight;
//		if(P.y+h<posy || P.y>posy+dimy){
//			return false;
//		}
//		return false;
//	}
//	
//	private ArrayList<ShadowPickable> pickable=new ArrayList<ShadowPickable>();
//	
//	public void addPickable(ShadowPickable pick){
//		pickable.add(pick);
//	}
//	
//	public ShadowPickable getPickable(GLVertex3f Q,GLVertex3f Dir){
//		for (int i = 0; i < pickable.size(); i++) {
//			ShadowPickable pick=pickable.get(i);
//			if(ShadowRectangularObstacle.isIntersecting(pick.p, pick.dim, Q, Dir)){
//				return pick;
//			}
//		}
//		return null;
//	}
//	
//	public void addObstacle(ShadowObstacle obstacle){
//		obstacles.add(obstacle);
//	}
//
//	public ArrayList<ShadowObstacle> getObstacles() {
//		return obstacles;
//	}
//
//	public void setObstacles(ArrayList<ShadowObstacle> obstacles) {
//		this.obstacles = obstacles;
//	}
//	
//	@Override
//	public int occludersCount() {
//		return 0;
//	}
//
//	@Override
//	public ShadowOccluder getOccluder(int index) {
//		return null;
//	}
//	
//	
//	public boolean intersectAnyObstacle(GLVertex3f P,float ray,float h){
//		for (int i = 0; i < obstacles.size(); i++) {
//			boolean b=obstacles.get(i).intersect(P, ray, h);
//			if(b)
//				return true;
//		}
//		return false;
//	}
//	
//	public void draw(GL2 gl) {
//		
//		
//	}
//
//	@Override
//	public boolean canExit(int walkableIndex) {
//		return true;
//	}
//	
//	public void drawSurface(GL2 gl,int j,int i,float z1,float z2,float z3,float z4,float z5,
//			float z6,float z7,float z8,float z9){
//		float dx=(float)(this._dX*0.5f);
//		float dy=(float)(this._dZ*0.5f);
//		float x=(float)(x0+dx*(2*(j-1)+1));
//		float y=(float)(z0+dy*(2*(i-1)+1));
//		
////		gl.glColor3f(0,0,1);
////		gl.glBegin(GL.GL_TRIANGLE_STRIP);
////			gl.glVertex3d(x,z1,y);
////			gl.glVertex3d(x,z4,y+dy);
////			gl.glVertex3d(x+dx,z2,y);
////			gl.glVertex3d(x+dx,z5,y+dy);
////			gl.glVertex3d(x+2*dx,z3,y);
////			gl.glVertex3d(x+2*dx,z6,y+dy);
////		gl.glEnd();
////		gl.glBegin(GL.GL_TRIANGLE_STRIP);
////			gl.glVertex3d(x,z4,y+dy);
////			gl.glVertex3d(x,z7,y+dy*2);
////			gl.glVertex3d(x+dx,z5,y+dy);
////			gl.glVertex3d(x+dx,z8,y+dy*2);
////			gl.glVertex3d(x+2*dx,z6,y+dy);
////			gl.glVertex3d(x+2*dx,z9,y+dy*2);
////		gl.glEnd();
//		
//		int divisions=10;
//		float step=1.0f/divisions;
//
//		gl.glColor3f(1,0,0.5f);
//		for (i = 0; i < divisions; i++) {
//			gl.glBegin(GL2.GL_TRIANGLE_STRIP);
//				for (j = 0; j <= divisions; j++) {
//					float u=j*step;
//					float v=i*step;
//					gl.glVertex3f(x+u*dx*2, 
//							(1-v)*(1-v)*(z1*(1-u)*(1-u)+z2*2*u*(1-u)+z3*u*u)+
//							2*v*(1-v)*(z4*(1-u)*(1-u)+z5*2*u*(1-u)+z6*u*u)+
//							v*v*(z7*(1-u)*(1-u)+z8*2*u*(1-u)+z9*u*u)
//						, y+v*dy*2);
//					v+=step;
//					gl.glVertex3f(x+u*dx*2, 
//							(1-v)*(1-v)*(z1*(1-u)*(1-u)+z2*2*u*(1-u)+z3*u*u)+
//							2*v*(1-v)*(z4*(1-u)*(1-u)+z5*2*u*(1-u)+z6*u*u)+
//							v*v*(z7*(1-u)*(1-u)+z8*2*u*(1-u)+z9*u*u)
//						, y+v*dy*2);
//					
//				}
//			gl.glEnd();
//		}
//	}
//	
//	static BufferedImage im;
//	int tobj1,tobj2;
//	int tCode1,tCode2;
//	public void init() {
//		evalMinMaxHeight();
//		
//	}
//	
//	public void evalMinMaxHeight(){
//		minHeight=height[0];
//		maxHeight=height[0];
//		System.err.println("height[0] " + height[0]);
//		for(int i=1;i<height.length;i++){
//			System.err.println("height[i] " + height[i]);
//			if(height[i]<minHeight)
//				minHeight=height[i];
//			if(height[i]>maxHeight)
//				maxHeight=height[i];
//		}
//	}
//	
//	@Override
//	public float getY(int walkable, GLVertex3f position) {
//		
//		float xRel=(float)((position.x+_dX*0.5f-x0)/_dX);
//		float zRel=(float)((position.z+_dZ*0.5f-z0)/_dZ);
//		
//		int J=(int)(xRel);
//		int I=(int)(zRel);
//		
//		float u=xRel-J;
//		float v=zRel-I;
//
//		float v1=getHeight(I-1, J-1);
//		float v2=getHeight(I-1, J);
//		float v3=getHeight(I-1, J+1);
//		float v4=getHeight(I, J-1);
//		float v5=getHeight(I, J);
//		float v6=getHeight(I, J+1);
//		float v7=getHeight(I+1, J-1);
//		float v8=getHeight(I+1, J);
//		float v9=getHeight(I+1, J+1);
//		
//		float y1=0.25f*(v1+v2+v4+v5);
//		float y2=0.5f*(v2+v5);
//		float y3=0.25f*(v2+v3+v5+v6);
//		float y4=0.5f*(v4+v5);
//		float y5=v5;
//		float y6=0.5f*(v5+v6);
//		float y7=0.25f*(v4+v5+v7+v8);
//		float y8=0.5f*(v5+v8);
//		float y9=0.25f*(v5+v6+v8+v9);
//		
//		float y=(1-v)*(1-v)*(y1*(1-u)*(1-u)+y2*2*u*(1-u)+y3*u*u)+
//				2*v*(1-v)*(y4*(1-u)*(1-u)+y5*2*u*(1-u)+y6*u*u)+
//				v*v*(y7*(1-u)*(1-u)+y8*2*u*(1-u)+y9*u*u);
//		
//		return y;
//	}
//	
//	@Override
//	public int findWalkable(GLVertex3f position) {
//		return canWalk(0, position, 0, 0);//don't really care
//	}
//	
//	@Override
//	public void moveOnEdge(int walkable, GLVertex3f actualPosition,
//			GLVertex3f destination, float ray, float height) {
//
//		float w=(dimW-2)*_dX;
//		float h=(dimH-2)*_dZ;
//		float a=ray/w;
//		float b=ray/h;
//		float u_pos=(actualPosition.x-x0-_dX*0.5f)/(w);
//		float v_pos=(actualPosition.z-z0-_dZ*0.5f)/(h);
//		float u_dst=(destination.x-x0-_dX*0.5f)/(w);
//		float v_dst=(destination.z-z0-_dZ*0.5f)/(h);
//		
//		float t1=1,t2=1;
//		if(u_dst+a>1){//u_dest+b>1
//			float u=1-a;
//			t1=(u-u_pos)/(u_dst-u_pos);
//		}else if(u_dst-a<0){
//			float u=a;
//			t1=(u-u_pos)/(u_dst-u_pos);
//		}
//		if(v_dst+b>1){//u_dest+b>1
//			float v=1-b;
//			t2=(v-v_pos)/(v_dst-v_pos);
//		}else if(v_dst-b<0){
//			float v=b;
//			t2=(v-v_pos)/(v_dst-v_pos);
//		}
//		
//		float u=u_pos*(1-t1)+u_dst*t1;
//		float v=v_pos*(1-t2)+v_dst*t2;
//		
//		actualPosition.x=x0+_dX*(0.5f)+u*w;
//		actualPosition.z=z0+_dZ*(0.5f)+v*h;
//		actualPosition.y=getY(walkable, actualPosition);
//	}
//	
//	@Override
//	public int getWalkablesCount() {
//		return (dimH-1)*(dimW-1);//TODO
//	}
//	
//	@Override
//	public int canWalk(int walkable, GLVertex3f position, float ray,
//			float height) {
//		
//		float x=position.x;
//		float z=position.z;
//		double w=(dimW-2)*_dX;
//		double h=(dimH-2)*_dZ;
//		
//		if(x-ray<x0+_dX*0.5f)
//			return -1;
//		if(x+ray>x0+_dX*0.5f+w)
//			return -1;
//		if(z-ray<z0+_dZ*0.5f)
//			return -1;
//		if(z+ray>z0+_dZ*0.5f+h)
//			return -1;
//
//		if(intersectAnyObstacle(position,ray,height)){
//			return -2;
//		}	
//		
//		int J=(int)((x-x0)/_dX);
//		int I=(int)((z-z0)/_dZ);
//		
//		return J+I*(dimW-1);
//	}
//
//	@Override
//	public boolean intersect(int walkable, GLVertex3f Q, GLVertex3f Dir,
//			GLVertex3f Point) {
//		
//		boolean found=false;
//		GLVertex3f ret=new GLVertex3f();
//		for(int i=1;i<dimH-1;i++){//indices of patch
//			for(int j=1;j<dimW-1;j++){
//				GLVertex3f v=findIntersectionOnPatch(Q, Dir, i, j);
//				
//				if(v!=null){
//					if(!found){
//						ret.set(v);
//						found=true; //if v.z>ret.z. Don't think it is correct. 
//					}else if(v.z>ret.z){//TODO: i don't agree with this condition. I'm going to make a math check. 
//						ret.set(v);
//					}
//				}	
//			}
//		}
//		if(found){
//			Point.set(ret);
//			return true;
//		}
//			
//		return false;
//	}
//	
//	//private int inters_i,inters_j;
//	
//	private float getHeight(int i,int j){
//		
//		if(i<0)
//			i=0;
//		else if(i>=dimH)
//			i=dimH-1;
//		if(j<0)
//			j=0;
//		else if(j>=dimW)
//			j=dimW-1;
//		
//		return height[dimW*i+j];
//		
//	}
//
//	private GLVertex3f findIntersectionOnPatch(GLVertex3f Q1, GLVertex3f Dir, int i,
//			int j) {
//		double Xmin=x0+(_dX*(j-0.5f));
//		double Ymin=z0+(_dZ*(i-0.5f));
//		
//		double Xmax=Xmin+_dX;
//		double Ymax=Ymin+_dZ;
//		
//		double tmin=0;
//		double tmax=0;
//		if(Dir.x!=0){
//			double t1x=(Xmin-Q1.x)/Dir.x;
//			double t2x=(Xmax-Q1.x)/Dir.x;
//			if(t1x>t2x){ double t=t1x;t1x=t2x;t2x=t; }
//			tmin=t1x;
//			tmax=t2x;
//			System.err.println("t1x " + t1x + " t2x " + t2x);
//			if(Dir.y!=0){
//				double t1y=(Ymin-Q1.z)/Dir.z;
//				double t2y=(Ymax-Q1.z)/Dir.z;
//				if(t1y>t2y){ double t=t1y;t1y=t2y;t2y=t; }
//				if(tmin<t1y)tmin=t1y;
//				if(tmax>t2y)tmax=t2y;
//				System.err.println("t1y " + t1y + " t2y " + t2y);
//				if(Dir.z!=0){
//					double t1z=(minHeight-Q1.y)/Dir.y;
//					double t2z=(maxHeight-Q1.y)/Dir.y;
//					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
//					System.err.println("t1z " + t1z + " t2z " + t2z);
//					if(tmin<t1z)tmin=t1z;
//					if(tmax>t2z)tmax=t2z;
//				}else{
//					if(!(Q1.z>minHeight && Q1.z<maxHeight))
//						return null;
//				}
//			}else{
//				if(!(Q1.y>Ymin && Q1.y<Ymax))
//					return null;
//				if(Dir.z!=0){
//					double t1z=(minHeight-Q1.z)/Dir.z;
//					double t2z=(maxHeight-Q1.z)/Dir.z;
//					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
//					if(tmin<t1z)tmin=t1z;
//					if(tmax>t2z)tmax=t2z;
//				}else{
//					if(!(Q1.z>=minHeight && Q1.z<=maxHeight))
//						return null;
//				}
//			}
//		}else{
//			if(!(Q1.x>Xmin && Q1.x<Xmax))
//				return null;
//			if(Dir.y!=0){
//				double t1y=(Ymin-Q1.y)/Dir.y;
//				double t2y=(Ymax-Q1.y)/Dir.y;
//				if(t1y>t2y){ double t=t1y;t1y=t2y;t2y=t; }
//				tmin=t1y;
//				tmax=t2y;
//				if(Dir.z!=0){
//					double t1z=(minHeight-Q1.z)/Dir.z;
//					double t2z=(maxHeight-Q1.z)/Dir.z;
//					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
//					if(tmin<t1z)tmin=t1z;
//					if(tmax>t2z)tmax=t2z;
//				}else{
//					if(!(Q1.z>minHeight && Q1.z<maxHeight))
//						return null;
//				}
//			}else{
//				if(!(Q1.y>Ymin && Q1.y<Ymax))
//					return null;
//				if(Dir.z!=0){
//					double t1z=(minHeight-Q1.z)/Dir.z;
//					double t2z=(maxHeight-Q1.z)/Dir.z;
//					if(t1z>t2z){ double t=t1z;t1z=t2z;t2z=t; }
//					tmin=t1z;
//					tmax=t2z;
//				}else{
//					if(!(Q1.z>minHeight && Q1.z<maxHeight))
//						return null;
//				}
//			}
//		}
//		System.err.println("tmin " + tmin);
//		System.err.println("tmax " + tmax);
//		
//		if(tmin<tmax){
//			System.err.println("Enter ");
//			float v1=getHeight(i-1, j-1);
//			float v2=getHeight(i-1, j);
//			float v3=getHeight(i-1, j+1);
//			float v4=getHeight(i, j-1);
//			float v5=getHeight(i, j);
//			float v6=getHeight(i, j+1);
//			float v7=getHeight(i+1, j-1);
//			float v8=getHeight(i+1, j);
//			float v9=getHeight(i+1, j+1);
//			float z1=0.25f*(v1+v2+v4+v5);
//			float z2=0.5f*(v2+v5);
//			float z3=0.25f*(v2+v3+v5+v6);
//			float z4=0.5f*(v4+v5);
//			float z5=v5;
//			float z6=0.5f*(v5+v6);
//			float z7=0.25f*(v4+v5+v7+v8);
//			float z8=0.5f*(v5+v8);
//			float z9=0.25f*(v5+v6+v8+v9);
//			
//			//this is ok.
//			double au=(Q1.x-Xmin)/(Xmax-Xmin);
//			double bu=(Dir.x)/(Xmax-Xmin);
//			double av=(Q1.z-Ymin)/(Ymax-Ymin);
//			double bv=(Dir.z)/(Ymax-Ymin);
//			
//			double tMin=-1,tMax=2;
//			if(bu!=0){
//				double t1u=(double)(-au/bu);
//				double t2u=(double)((1-au)/bu);
//				if(bu>0){
//					tMin=t1u;tMax=t2u;
//				}else{
//					tMin=t2u;tMax=t1u;
//				}
//				if(bv!=0){
//					double t1v=(double)(-av/bv);
//					double t2v=(double)((1-av)/bv);
//					if(bv>0){
//						if(t1v>tMin)tMin=t1v;
//						if(t2v<tMax)tMax=t2v;
//					}else{
//						if(t2v>tMin)tMin=t2v;
//						if(t1v<tMax)tMax=t1v;
//					}
//				}
//			}else{
//				double t1v=(double)(-av/bv);
//				double t2v=(double)((1-av)/bv);
//				if(bv>0){
//					tMin=t1v;tMax=t2v;
//				}else{
//					tMin=t2v;tMax=t1v;
//				}
//			}
//			System.err.println("tMin " + tMin + " tMax " + tMax);
//			
//			int DIVISIONS=10;
//			float step=1.0f/DIVISIONS;
//			
//			float tInter=0;
//			float zInter=0;
//			boolean found=false;
//			
//			for (int k = 0; k <DIVISIONS; k++) {
//				double ts=tmin+k*step*(tmax-tmin);
//				double us=au+ts*bu;
//				double vs=av+ts*bv;
//				double usm=1-us;
//				double vsm=1-vs;
//				double tr=tmin+(k+1)*step*(tmax-tmin);
//				double ur=au+tr*bu;
//				double vr=av+tr*bv;
//				double urm=1-ur;
//				double vrm=1-vr;
//				
//				double zs=vsm*vsm*(usm*usm*z1+2*us*usm*z2+us*us*z3)+
//						2*vs*vsm*(usm*usm*z4+2*us*usm*z5+us*us*z6)+
//						vs*vs*(usm*usm*z7+2*us*usm*z8+us*us*z9);
//				double zr=vrm*vrm*(urm*urm*z1+2*ur*urm*z2+ur*ur*z3)+
//						2*vr*vrm*(urm*urm*z4+2*ur*urm*z5+ur*ur*z6)+
//						vr*vr*(urm*urm*z7+2*ur*urm*z8+ur*ur*z9);
//				
//				double zA=Q1.y+Dir.y*ts;
//				double zB=Q1.y+Dir.y*tr;
//				
//				double DELTA=(zB-zA)-(zr-zs);
//				if(DELTA!=0){
//					double T=(zs-zA)/(DELTA);
//					if(T>=0 && T<=1){
//						if(!found){
//							found=true;
//							tInter=(float)(T*(tr-ts)+ts);
//							zInter=(float)(T*(zr-zs)+zs);
//						}else{
//							float z=(float)(T*(zr-zs)+zs);
//							if(zInter>z){
//								zInter=z;
//								tInter=(float)T;
//							}
//						}
//					}
//				}
//			}
//		 
//			if(found){
//				GLVertex3f v=new GLVertex3f(Q1.x+tInter*Dir.x,Q1.y+tInter*Dir.y,Q1.z+tInter*Dir.z);
//				return v;
//			}
//		}
//		
//		return null;
//	}
//	
//
//	public GLScene scene;
//
//	public GLScene getScene() {
//		return scene;
//	}
//
//	public void setScene(GLScene scene) {
//		this.scene = scene;
//	}
//	
//	private class DTMWalkable implements ShadowObstacle{
//		
//		int i,j;
//		public boolean visible;
//		
//		public DTMWalkable(int j, int i) {
//			super();
//			this.j = j;
//			this.i = i;
//		}
//		
//		@Override
//		public void draw(GL2 gl) {
//			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
//			gl.glColor3f(1,1,1);
//			float v1=getHeight(i-1, j-1);
//			float v2=getHeight(i-1, j);
//			float v3=getHeight(i-1, j+1);
//			float v4=getHeight(i, j-1);
//			float v5=getHeight(i, j);
//			float v6=getHeight(i, j+1);
//			float v7=getHeight(i+1, j-1);
//			float v8=getHeight(i+1, j);
//			float v9=getHeight(i+1, j+1);
//			
//			float z1=0.25f*(v1+v2+v4+v5);
//			float z2=0.5f*(v2+v5);
//			float z3=0.25f*(v2+v3+v5+v6);
//			float z4=0.5f*(v4+v5);
//			float z5=v5;
//			float z6=0.5f*(v5+v6);
//			float z7=0.25f*(v4+v5+v7+v8);
//			float z8=0.5f*(v5+v8);
//			float z9=0.25f*(v5+v6+v8+v9);
//			
//			drawSurface(gl, j, i, z1, z2, z3, z4, z5, z6, z7, z8, z9);
//			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
//		}
//		
//		@Override
//		public ShadowOccluder getOccluder(int index) {
//			return null;
//		}
//		
//		@Override
//		public boolean intersect(GLVertex3f P, float ray, float h) {
//			return false;
//		}
//		
//		@Override
//		public boolean isObjectInCameraBounds(ShadowCamera camera) {
//			boolean b= ShadowRectangularObstacle.isParallelepipedInCameraBound(camera,
//					new GLVertex3f(x0+_dX*0.5f*(2*j-1),minHeight,z0+_dZ*0.5f*(2*i-1)),
//					new GLVertex3f(_dX,maxHeight-minHeight,_dZ));
//			return b;
//			
//		}
//		
//		public boolean isObjectOccluded(ShadowOccluder occluder,GLVertex3f F){
//			boolean b= ShadowRectangularObstacle.isParallelepipedInOccluderShadow(F,occluder,
//					new GLVertex3f(x0+_dX*0.5f*(2*j-1),minHeight,z0+_dZ*0.5f*(2*i-1)),
//					new GLVertex3f(_dX,maxHeight-minHeight,_dZ));
//			return b;
//		}
//		
//		@Override
//		public int occludersCount() {
//			return 0;
//		} 
//		
//		public GLScene scene;
//
//		public GLScene getScene() {
//			return null;
//		}
//
//		public void setScene(GLScene scene) {
//			this.scene = scene;
//		}
//		
//	}
//}
