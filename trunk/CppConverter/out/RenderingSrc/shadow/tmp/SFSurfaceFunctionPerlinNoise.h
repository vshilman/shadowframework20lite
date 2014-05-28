#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFSurfaceFunction.h"

class SFSurfaceFunctionPerlinNoise extends SFPerlinNoise{

//	SFSurfaceFunction bitmap;

//	SFSurfaceFunctionPerlinNoise(){
	}

//	SFSurfaceFunctionPerlinNoise(int width,int height,float[] weights,boolean rgb){

//		super();

//		setWidth((short)width);
//		setHeight((short)height);

		this->weights=weights;
		this->rgb=rgb;
	}


//	SFSurfaceFunction getBitmap() {
//		return bitmap;
	}

//	void setBitmap(SFSurfaceFunction bitmap) {
		this->bitmap = bitmap;
	}


	
//	protected float getOctaveValue(float u, float v, int octave) {

//		float U=u*octave;
//		U-=((int)U);
//		float V=v*octave;
//		V-=((int)V);

//		float value=bitmap.getZ(U, V);
//		return value*256;
	}


}
;
}
#endif
