#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFCamera.h"
#include "shadow/renderer/SFILodSpace.h"

class SFRectBound implements SFILodSpace{

//	float minX,maxX;
//	float minY,maxY;
//	float minZ,maxZ;

//	SFRectBound(float minX, float maxX, float minY, float maxY,
//			float minZ, float maxZ) {
//		super();
		this->minX = minX;
		this->maxX = maxX;
		this->minY = minY;
		this->maxY = maxY;
		this->minZ = minZ;
		this->maxZ = maxZ;
	}

	
//	SFVertex3f getNearestPoint(SFCamera camera) {

//		SFVertex3f dir=camera.getDir();

//		SFVertex3f nearest=new SFVertex3f();

//		if(dir.getX()<0){
//			nearest.setX(maxX);
		}
//			nearest.setX(minX);
		}
//		if(dir.getY()<0){
//			nearest.setY(maxY);
		}
//			nearest.setY(minY);
		}
//		if(dir.getZ()<0){
//			nearest.setZ(maxZ);
		}
//			nearest.setZ(minZ);
		}

//		return nearest;
	}
}
;
}
#endif
