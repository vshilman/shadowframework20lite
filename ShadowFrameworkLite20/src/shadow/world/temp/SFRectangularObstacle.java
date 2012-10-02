package shadow.world.temp;

import shadow.math.SFVertex3f;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;


public class SFRectangularObstacle implements SFObstacle {

	//private int drawingColor=0xff0000ff;

	private SFVertex3f position=new SFVertex3f();
	private SFVertex3f dim=new SFVertex3f();
	
	public SFRectangularObstacle(float posx,float posy,float posz, float d1x,float d1y,float d1z) {
		super();
		this.position.set3f(posx,posy,posz);
		this.dim.set3f(d1x,d1y,d1z);
	}
	
	private SFRectangularObstacle(){
		
	}

	/* (non-Javadoc)
	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowObstacle#intersect(shadow.math.SFVertex3f, float, float)
	 */
	public boolean intersect(SFVertex3f P,float ray,float h){
		
		if(P.getX()+ray<position.getX() || P.getX()-ray>position.getX()+dim.getX()){
			return false;
		}
		if(P.getZ()+ray<position.getZ() || P.getZ()-ray>position.getZ()+dim.getZ()){
			return false;
		}
		if(P.getY()+h<position.getY() || P.getY()>position.getY()+dim.getY()){
			return false;
		}
		
		return true;
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
	public SFOccluder getOccluder(int index) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see tutorials.rendering.tutorial5_twilight_town.control.ShadowObstacle#draw(javax.media.opengl.GL2)
	 */
	public void draw(){
//		GLColors.color4f(gl, drawingColor);
//		
//		gl.glBegin(GL2.GL_LINE_LOOP);
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY(),position.getZ()+dim.getZ());
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ()+dim.getZ());
//		gl.glEnd();
//		gl.glBegin(GL2.GL_LINE_LOOP);
//			gl.SFVertex3f(position.getX(),position.getY()+dim.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY()+dim.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY()+dim.getY(),position.getZ()+dim.getZ());
//			gl.SFVertex3f(position.getX(),position.getY()+dim.getY(),position.getZ()+dim.getZ());
//		gl.glEnd();
//		gl.glBegin(GL2.GL_LINES);
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX(),position.getY()+dim.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY()+dim.getY(),position.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY(),position.getZ()+dim.getZ());
//			gl.SFVertex3f(position.getX()+dim.getX(),position.getY()+dim.getY(),position.getZ()+dim.getZ());
//			gl.SFVertex3f(position.getX(),position.getY(),position.getZ()+dim.getZ());
//			gl.SFVertex3f(position.getX(),position.getY()+dim.getY(),position.getZ()+dim.getZ());
//		gl.glEnd();
	}
	
	@Override
	public boolean isObjectInCameraBounds(SFCamera camera) {
		return isParallelepipedInCameraBound(camera, position, dim);
	}
	
	@Override
	public boolean isObjectOccluded(SFOccluder occluder,SFVertex3f F) {
		return isParallelepipedInOccluderShadow(F,occluder, position, dim);
	}
	
	public static boolean isParallelepipedInOccluderShadow(SFVertex3f F,SFOccluder occ,SFVertex3f p,SFVertex3f dim){
		
		//when is a rect occluded? when the center of the rect is and there are not intersections 

		float rap=30;
		SFVertex3f[] vsOcc={
				new SFVertex3f(occ.getP().getX(),
							   occ.getP().getY(),
							   occ.getP().getZ()),
				new SFVertex3f(occ.getP().getX()+occ.getD2().getX(),
							   occ.getP().getY()+occ.getD2().getY(),
							   occ.getP().getZ()+occ.getD2().getZ()),
				new SFVertex3f(occ.getP().getX()+occ.getD2().getX()+occ.getD1().getX(),
							   occ.getP().getY()+occ.getD2().getY()+occ.getD1().getY(),
							   occ.getP().getZ()+occ.getD2().getZ()+occ.getD1().getZ()),
				new SFVertex3f(occ.getP().getX()+occ.getD1().getX(),
							   occ.getP().getY()+occ.getD1().getY(),
							   occ.getP().getZ()+occ.getD1().getZ()),
				
				new SFVertex3f(F.getX()+(occ.getP().getX()-F.getX())*rap,
							   F.getY()+(occ.getP().getY()-F.getY())*rap,
							   F.getX()+(occ.getP().getZ()-F.getZ())*rap),
				new SFVertex3f(F.getX()+(occ.getP().getX()+occ.getD2().getX()-F.getX())*rap,
							   F.getY()+(occ.getP().getY()+occ.getD2().getY()-F.getY())*rap,
							   F.getZ()+(occ.getP().getZ()+occ.getD2().getZ()-F.getZ())*rap),
				new SFVertex3f(F.getX()+(occ.getP().getX()+occ.getD2().getX()+occ.getD1().getX()-F.getX())*rap,
							   F.getY()+(occ.getP().getY()+occ.getD2().getY()+occ.getD1().getY()-F.getY())*rap,
							   F.getZ()+(occ.getP().getZ()+occ.getD2().getZ()+occ.getD1().getZ()-F.getZ())*rap),
				new SFVertex3f(F.getX()+(occ.getP().getX()+occ.getD1().getX()-F.getX())*rap,
							   F.getY()+(occ.getP().getY()+occ.getD1().getY()-F.getY())*rap,
							   F.getZ()+(occ.getP().getZ()+occ.getD1().getZ()-F.getZ())*rap),
				
		};
		
		
		float recDimx=1.0f/dim.getX();
		float recDimy=1.0f/dim.getY();
		float recDimz=1.0f/dim.getZ();
		boolean intersecting=(new SFRectangularObstacle()).isOccluded(p.getX(),p.getY(),p.getZ(), recDimx, recDimy, recDimz, 
				vsOcc);
		
		return intersecting;
	}
	
	
	public static boolean isParallelepipedInCameraBound(SFCamera cam,SFVertex3f p,SFVertex3f dim){
		
		SFVertex3f F=cam.getF();
		SFVertex3f Dir=cam.getDir();
		SFVertex3f Up=cam.getUp();
		SFVertex3f Left=cam.getLeft();
		

		float delta=(float)(Math.sqrt(Dir.dot3f(Dir)));
		float rap=(cam.getDistance())/delta;
		
		SFVertex3f[] vsCam={
			new SFVertex3f(F.getX()+Dir.getX()-Up.getX()-Left.getX(),F.getY()+Dir.getY()-Up.getY()-Left.getY(),F.getZ()+Dir.getZ()-Up.getZ()-Left.getZ()),
			new SFVertex3f(F.getX()+Dir.getX()+Up.getX()-Left.getX(),F.getY()+Dir.getY()+Up.getY()-Left.getY(),F.getZ()+Dir.getZ()+Up.getZ()-Left.getZ()),
			new SFVertex3f(F.getX()+Dir.getX()+Up.getX()+Left.getX(),F.getY()+Dir.getY()+Up.getY()+Left.getY(),F.getZ()+Dir.getZ()+Up.getZ()+Left.getZ()),
			new SFVertex3f(F.getX()+Dir.getX()-Up.getX()+Left.getX(),F.getY()+Dir.getY()-Up.getY()+Left.getY(),F.getZ()+Dir.getZ()-Up.getZ()+Left.getZ()),
			new SFVertex3f(F.getX()+(Dir.getX()-Up.getX()-Left.getX())*rap,F.getY()+(Dir.getY()-Up.getY()-Left.getY())*rap,F.getZ()+(Dir.getZ()-Up.getZ()-Left.getZ())*rap),
			new SFVertex3f(F.getX()+(Dir.getX()+Up.getX()-Left.getX())*rap,F.getY()+(Dir.getY()+Up.getY()-Left.getY())*rap,F.getZ()+(Dir.getZ()+Up.getZ()-Left.getZ())*rap),
			new SFVertex3f(F.getX()+(Dir.getX()+Up.getX()+Left.getX())*rap,F.getY()+(Dir.getY()+Up.getY()+Left.getY())*rap,F.getZ()+(Dir.getZ()+Up.getZ()+Left.getZ())*rap),
			new SFVertex3f(F.getX()+(Dir.getX()-Up.getX()+Left.getX())*rap,F.getY()+(Dir.getY()-Up.getY()+Left.getY())*rap,F.getZ()+(Dir.getZ()-Up.getZ()+Left.getZ())*rap)
		};
		
		float recDimx=1.0f/dim.getX();
		float recDimy=1.0f/dim.getY();
		float recDimz=1.0f/dim.getZ();
		boolean intersecting=(new SFRectangularObstacle()).hasIntersections(p.getX(),p.getY(),p.getZ(), recDimx, recDimy, recDimz, vsCam);
		
		return intersecting;
	}


	final int[][] poly={
			{0,1,2,3},
			{4,5,6,7},
			{0,1,4,5},
			{1,2,5,6},
			{2,3,6,7},
			{3,0,7,4}
	};
	
	final int[][] occ_poly={
			{0,1,2,3},
			{0,1,4,5},
			{1,2,5,6},
			{2,3,6,7},
			{3,0,7,4}
	};
	
	final SFVertex3f vb[]={
		new SFVertex3f(0,0,0),
		new SFVertex3f(1,0,0),
		new SFVertex3f(1,1,0),
		new SFVertex3f(0,1,0),
		new SFVertex3f(0,0,1),
		new SFVertex3f(1,0,1),
		new SFVertex3f(1,1,1),
		new SFVertex3f(0,1,1),
	};

	
	public boolean hasIntersections(float x,float y,float z,float recDx,float recDy,float recDz,
			SFVertex3f[] vsCam) {
		
		for (int i = 0; i < vsCam.length; i++) {
			vsCam[i].setX((vsCam[i].getX()-x)*recDx);
			vsCam[i].setY((vsCam[i].getY()-y)*recDy);
			vsCam[i].setZ((vsCam[i].getZ()-z)*recDz);
		}
		
		
		for (int i = 0; i < poly.length; i++) {
			if(areSeparated(vb, vsCam, i)){
				return false;
			}
		}
		
		for (int i = 0; i < poly.length; i++) {
			if(areSeparated(vsCam,vb, i)){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean areSeparated(SFVertex3f vb1[],SFVertex3f vb2[],int index1){
	
		/*
		 * Separating axis Theorem:
		 * if two polytopes are convex and they have no points in common,
		 * then there exist a face of the first or of the second which can 
		 * be used as separating plane.
		 */
		
		//Define a separating axis on one 
		SFVertex3f A=vb1[poly[index1][0]];
		SFVertex3f d1=new SFVertex3f(vb1[poly[index1][1]]);
		SFVertex3f d2=new SFVertex3f(vb1[poly[index1][2]]);
		d1.subtract3f(A);
		d2.subtract3f(A);
		SFVertex3f N=d1.cross(d2);
		
		boolean positiveVb1=false;
		boolean negativeVb1=false;
	
		for (int i = 0; i < vb1.length; i++) {
			boolean found=false;
			for (int j = 0; j < poly[index1].length; j++) {
				if(i==poly[index1][j])
					found=true;
			}
			if(!found){
				SFVertex3f v=vb1[i];
				float f=N.getX()*(v.getX()-A.getX())+N.getY()*(v.getY()-A.getY())+N.getZ()*(v.getZ()-A.getZ());
				
				if(f>0.00001f)
					positiveVb1=true;
				if(f<-0.00001f)
					negativeVb1=true;	
			}
		}
		
		
		//points of vb1 on both sides
		if(positiveVb1 && negativeVb1)
			return false;
	
		boolean positiveVb2=false;
		boolean negativeVb2=false;
	
		for (int i = 0; i < vb2.length; i++) {
			SFVertex3f v=vb2[i];
			float f=N.getX()*(v.getX()-A.getX())+N.getY()*(v.getY()-A.getY())+N.getZ()*(v.getZ()-A.getZ());
			if(f>0.00001f)
				positiveVb2=true;
			if(f<-0.00001f)
				negativeVb2=true;
		}

		//points of vb2 on both sides
		if(positiveVb2 && negativeVb2)
			return false;
		
		//points of vb1 and vb2 on the same side
		if(positiveVb1 && positiveVb2)
			return false;

		//points of vb1 and vb2 on the same side
		if(negativeVb2 && negativeVb1)
			return false;
		
		//points are on different sides.
		return true;
	}
	
	public boolean isOccluded(float x,float y,float z,float recDx,float recDy,float recDz,
			SFVertex3f[] vsCam) {

		for (int i = 0; i < vsCam.length; i++) {
			vsCam[i].setX((vsCam[i].getX()-x)*recDx);
			vsCam[i].setY((vsCam[i].getY()-y)*recDy);
			vsCam[i].setZ((vsCam[i].getZ()-z)*recDz);
		}
		
		
		for (int i = 0; i < occ_poly.length; i++) {
			if(!areSameSide(vsCam, vb, i)){
				return false;
			}
		}
		
		return true;
		
	}
	

	public boolean areSameSide(SFVertex3f vb1[],SFVertex3f vb2[],int index1){
	
		/*
		 * Separating axis Theorem:
		 * if two polytopes are convex and they have no points in common,
		 * then there exist a face of the first or of the second which can 
		 * be used as separating plane.
		 */
		
		//Define a separating axis on one 
		SFVertex3f A=vb1[occ_poly[index1][0]];
		SFVertex3f d1=new SFVertex3f(vb1[occ_poly[index1][1]]);
		SFVertex3f d2=new SFVertex3f(vb1[occ_poly[index1][2]]);
		d1.subtract3f(A);
		d2.subtract3f(A);
		SFVertex3f N=d1.cross(d2);
		
		boolean positiveVb1=false;
		boolean negativeVb1=false;
	
		for (int i = 0; i < vb1.length; i++) {
			boolean found=false;
			for (int j = 0; j < occ_poly[index1].length; j++) {
				if(i==occ_poly[index1][j])
					found=true;
			}
			if(!found){
				SFVertex3f v=vb1[i];
				float f=N.getX()*(v.getX()-A.getX())+N.getY()*(v.getY()-A.getY())+N.getZ()*(v.getZ()-A.getZ());
				
				if(f>0.00001f)
					positiveVb1=true;
				if(f<-0.00001f)
					negativeVb1=true;	
			}
		}
		
		//points of vb1 on both sides
		if(positiveVb1 && negativeVb1)
			return false;
	
		boolean positiveVb2=false;
		boolean negativeVb2=false;
	
		for (int i = 0; i < vb2.length; i++) {
			SFVertex3f v=vb2[i];
			float f=N.getX()*(v.getX()-A.getX())+N.getY()*(v.getY()-A.getY())+N.getZ()*(v.getZ()-A.getZ());
			if(f>0.00001f)
				positiveVb2=true;
			if(f<-0.00001f)
				negativeVb2=true;
		}

		//points of vb2 on both sides
		if(positiveVb2 && negativeVb2)
			return false;
		//Now: vb1 points are all on the same side, and vb2 too
		
		//points of vb1 and vb2 on the same side
		if(positiveVb1 && positiveVb2)
			return true;

		//points of vb1 and vb2 on the same side
		if(negativeVb2 && negativeVb1)
			return true;
		
		//points are on different sides or something didn't work.
		return false;
	}
	
	
	public static boolean isIntersecting(SFVertex3f p,SFVertex3f dim,SFVertex3f Q,SFVertex3f Dir){
		
		float recDx=1.0f/dim.getX();
		float recDy=1.0f/dim.getY();
		float recDz=1.0f/dim.getZ();
		SFVertex3f Qtmp=new SFVertex3f(
				(Q.getX()-p.getX())*recDx,
				(Q.getY()-p.getY())*recDy,
				(Q.getZ()-p.getZ())*recDz
		);
		SFVertex3f Dirtmp=new SFVertex3f(
				(Dir.getX())*recDx,
				(Dir.getY())*recDy,
				(Dir.getZ())*recDz
		);
		
		
		if(Dirtmp.getX()!=0){
			
			// i find intersections on x=0 and x=1
			float t_minx=(-Qtmp.getX())/(Dirtmp.getX());
			float t_maxx=(1-Qtmp.getX())/(Dirtmp.getX());
			if(t_maxx<t_minx){
				float c=t_maxx;
				t_maxx=t_minx;
				t_minx=c;
			}
			
			if(Dirtmp.getY()!=0){
				
				float t_miny=(-Qtmp.getY())/(Dirtmp.getY());
				float t_maxy=(1-Qtmp.getY())/(Dirtmp.getY());
				if(t_maxy<t_miny){
					float c=t_maxy;
					t_maxy=t_miny;
					t_miny=c;
				}
				
				if((t_maxx<t_miny) || (t_maxy<t_minx))
					return false;
				
				if(Dirtmp.getZ()!=0){
					float t_minz=(-Qtmp.getZ())/(Dirtmp.getZ());
					float t_maxz=(1-Qtmp.getZ())/(Dirtmp.getZ());
					if(t_maxz<t_minz){
						float c=t_maxz;
						t_maxz=t_minz;
						t_minz=c;
					}

					if((t_maxz<t_miny) || (t_maxy<t_minz)
						|| (t_maxz<t_minx) || (t_maxx<t_minz))
						return false;
					return true;
				}else if(Q.getZ()>=0 && Q.getZ()<=1){
					return true;
				}
			}else if(Q.getY()>=0 && Q.getY()<=1){
				if(Dirtmp.getZ()!=0){
					float t_minz=(-Qtmp.getZ())/(Dirtmp.getZ());
					float t_maxz=(1-Qtmp.getZ())/(Dirtmp.getZ());
					if(t_maxz<t_minz){
						float c=t_maxz;
						t_maxz=t_minz;
						t_minz=c;
					}
					if((t_maxz<t_minx) || (t_maxx<t_minz))
							return false;
					return true;
				}else if(Q.getZ()>=0 && Q.getZ()<=1){
					return true;
				}
			}
		}else if(Q.getX()>=0 && Q.getX()<=1){
			if(Dirtmp.getY()!=0){
				float t_miny=(-Qtmp.getY())/(Dirtmp.getY());
				float t_maxy=(1-Qtmp.getY())/(Dirtmp.getY());
				if(t_maxy<t_miny){
					float c=t_maxy;
					t_maxy=t_miny;
					t_miny=c;
				}
				
				if(Dirtmp.getZ()!=0){
					float t_minz=(-Qtmp.getZ())/(Dirtmp.getZ());
					float t_maxz=(1-Qtmp.getZ())/(Dirtmp.getZ());
					if(t_maxz<t_minz){
						float c=t_maxz;
						t_maxz=t_minz;
						t_minz=c;
					}
					if((t_maxz<t_miny) || (t_maxy<t_minz))
							return false;
					return true;
				}else if(Q.getZ()>=0 && Q.getZ()<=1){
					return true;
				}
			}else if(Q.getY()>=0 && Q.getY()<=1){
				if(Dirtmp.getZ()!=0){
					return true;
				}else if(Q.getZ()>=0 && Q.getZ()<=1){
					return true;
				}				
			}
		}
		
		return false;
	}
	
}

