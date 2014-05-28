#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveIndices.h"
#include "shadow/system/SFArray.h"

class SFGridGeometry extends SFMeshGeometry{

//	int Nx;
//	int Ny;
//	SFVertex3f position;
//	SFVertex3f directionX;
//	SFVertex3f directionY;
//	SFGridGeometry(SFPrimitive primitive) {
//		super();
//		setPrimitive(primitive);
	}

//	void build(SFVertex3f position,SFVertex3f directionX,SFVertex3f directionY,int Nx,int Ny){
		this->position=position;
		this->directionX=directionX;
		this->directionY=directionY;
		this->Nx=Nx;
		this->Ny=Ny;
//		init();
	}

	
//	void compile() {
//		super.compile();

//		int linesX=Nx+1;
//		int linesY=Ny+1;

//		SFArray<SFValuenf> values=getArray().getPrimitiveData(0);

//		int positionValues=values.generateElements(2*linesX);
//		int positionPrimitives=getArray().generateElements(linesX);
//		SFPrimitiveIndices indices=getArray().generateSample();
//		float dt=1.0f/Nx;
//		for (int i = 0; i < linesX; i++) {
//			float t=i*dt;
//			SFVertex3f v1=new SFVertex3f();
//			v1.set(position);
//			v1.addMult(t, directionX);
//			SFVertex3f v2=new SFVertex3f();
//			v2.set(v1);
//			values.setElement(positionValues+2*i, v1);
//			values.setElement(positionValues+2*i+1, v2);
//			indices.getPrimitiveIndices()[0]=positionValues+2*i;
//			indices.getPrimitiveIndices()[1]=positionValues+2*i+1;
//			getArray().setElement(positionPrimitives+i, indices);
		}

//		positionValues=values.generateElements(2*linesY);
//		positionPrimitives=getArray().generateElements(linesY);
//		dt=1.0f/Ny;
//		for (int i = 0; i < linesY; i++) {
//			float t=i*dt;
//			SFVertex3f v1=new SFVertex3f();
//			v1.set(position);
//			v1.addMult(t, directionY);
//			SFVertex3f v2=new SFVertex3f();
//			v2.set(v1);
//			v2.add(directionX);
//			values.setElement(positionValues+2*i, v1);
//			values.setElement(positionValues+2*i+1, v2);
//			indices.getPrimitiveIndices()[0]=positionValues+2*i;
//			indices.getPrimitiveIndices()[1]=positionValues+2*i+1;
//			getArray().setElement(positionPrimitives+i, indices);
		}
	}


}
;
}
#endif
