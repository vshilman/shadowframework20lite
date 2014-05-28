#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFViewTransform.h"

class SFParallelepipedWalkable implements SFIWalkable {

//	SFVertex3f posO;
//	SFVertex3f dirU;
//	SFVertex3f dirV;

	A,B,C,D;

//	SFParallelepipedWalkable(SFVertex3f position, SFVertex3f dirU,
//			SFVertex3f dirV) {
//		super();
		this->posO = position;
//		setDirs(dirU, dirV);
	}

//	void setDirs(SFVertex3f dirU, SFVertex3f dirV) {
		this->dirU = dirU;
		this->dirV = dirV;

//		float delta=dirU.getX()*dirV.getZ()-dirU.getZ()*dirV.getX();
//		float recDelta=1.0f/delta;
//		A=dirV.getZ()*recDelta;
//		B=-dirV.getX()*recDelta;
//		C=-dirU.getZ()*recDelta;
//		D=dirU.getX()*recDelta;
	}

	
//	boolean getCameraPointPosition(SFVertex3f position,SFViewTransform camera,float cameraX,float cameraY) {

//		float[] matrix=camera.extractTransform();

//		float R11=matrix[0]*dirU.getX()+matrix[4]*dirU.getY()+matrix[8]*dirU.getZ();
//		float R12=matrix[0]*dirV.getX()+matrix[4]*dirV.getY()+matrix[8]*dirV.getZ();
//		float R13=matrix[0]*posO.getX()+matrix[4]*posO.getY()+matrix[8]*posO.getZ()+matrix[12];

//		float R21=matrix[1]*dirU.getX()+matrix[5]*dirU.getY()+matrix[9]*dirU.getZ();
//		float R22=matrix[1]*dirV.getX()+matrix[5]*dirV.getY()+matrix[9]*dirV.getZ();
//		float R23=matrix[1]*posO.getX()+matrix[5]*posO.getY()+matrix[9]*posO.getZ()+matrix[13];

//		float R31=matrix[3]*dirU.getX()+matrix[7]*dirU.getY()+matrix[11]*dirU.getZ();
//		float R32=matrix[3]*dirV.getX()+matrix[7]*dirV.getY()+matrix[11]*dirV.getZ();
//		float R33=matrix[3]*posO.getX()+matrix[7]*posO.getY()+matrix[11]*posO.getZ()+matrix[15];

//		float A=R11-cameraX*R31;
//		float B=R12-cameraX*R32;
//		float C=R21-cameraY*R31;
//		float D=R22-cameraY*R32;

//		float E=cameraX*R33-R13;
//		float F=cameraY*R33-R23;

//		float recDelta=1.0f/(A*D-B*C);

//		float u=(E*D-F*B)*recDelta;
//		float v=(-E*C+F*A)*recDelta;

////		float vCx=(R11*u+R12*v+R13);
////		float vCy=(R21*u+R22*v+R23);
////		float vCw=(R31*u+R32*v+R33);
////
////		System.err.println("Verifica U"+u+" V "+v);
////		System.err.println("Verifica C"+(vCx/vCw)+" "+cameraX+" "+(vCy/vCw)+" "+cameraY);

//		return checkIn(position, u, v);
	}

//	/* (non-Javadoc)
//	 * @see shadow.lwc.SFIWalkable#isIn(shadow.lwc.SFAvatar)
//	 */
	
//	boolean checkIn(SFVertex3f position){

//		float x=position.getX()-this->posO.getX();
//		float z=position.getZ()-this->posO.getZ();

//		float u=(A*x+B*z);
//		float v=(C*x+D*z);

//		boolean isIn = checkIn(position, u, v);

//		return isIn;
	}

	
//	boolean checkOut(SFVertex3f position) {

//		float x=position.getX()-this->posO.getX();
//		float z=position.getZ()-this->posO.getZ();

//		float u=(A*x+B*z);
//		float v=(C*x+D*z);

//		boolean isOut = checkout(position, u, v);

//		return isOut;
	}

//	boolean checkout(SFVertex3f position, float u, float v) {
//		boolean isout=true;
//		if(u>=0 && u<=1 && v>=0 && v<=1){
//			float du=(u>=0.5f)?1-u:u;
//			float dv=(v>=0.5f)?1-v:v;
//			float delta=du*du-dv*dv;
//			if(delta>0){
//				if(v<=0.5){
//					v=0;
				}
//					v=1;
				}
			}
//				if(u<=0.5){
//					u=0;
				}
//					u=1;
				}
			}

//			isout=false;
		}

//		position.set3f(
//				this->posO.getX()+u*this->dirU.getX()+v*this->dirV.getX(), 
//				this->posO.getY()+u*this->dirU.getY()+v*this->dirV.getY(), 
//				this->posO.getZ()+u*this->dirU.getZ()+v*this->dirV.getZ());

//		return isout;
	}

//	boolean checkIn(SFVertex3f position, float u, float v) {
//		boolean isIn=true;
//		if(u<0){
//			u=0;
//			isIn=false;
		}
//		if(u>1){
//			u=1;
//			isIn=false;
		}
//		if(v<0){
//			v=0;
//			isIn=false;
		}
//		if(v>1){
//			v=1;
//			isIn=false;
		}

//		position.set3f(
//				this->posO.getX()+u*this->dirU.getX()+v*this->dirV.getX(), 
//				this->posO.getY()+u*this->dirU.getY()+v*this->dirV.getY(), 
//				this->posO.getZ()+u*this->dirU.getZ()+v*this->dirV.getZ());
//		return isIn;
	}

//	SFVertex3f getPosO() {
//		return posO;
	}

//	void setPosO(SFVertex3f posO) {
		this->posO = posO;
	}

//	SFVertex3f getDirU() {
//		return dirU;
	}

//	void setDirU(SFVertex3f dirU) {
		this->dirU = dirU;
	}

//	SFVertex3f getDirV() {
//		return dirV;
	}

//	void setDirV(SFVertex3f dirV) {
		this->dirV = dirV;
	}


}
;
}
#endif
