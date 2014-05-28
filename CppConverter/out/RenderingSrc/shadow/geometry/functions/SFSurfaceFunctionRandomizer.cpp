#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/math/SFRandomizer.h"

class SFSurfaceFunctionRandomizer extends SFGenericSurfaceFunction implements SFSurfaceFunction{

//	SFRandomizer randomizer;

//	SFSurfaceFunctionRandomizer(int seed) {
//		super();
		this->randomizer=new SFRandomizer(seed);
	}

//	float getValue(float u, float v) {
//		return randomizer.randomFloat();
	}

//	void setSeed(int seed){
//		randomizer.setSeed(seed);
//		randomizer.reset();
	}

	
//	float getX(float u, float v) {
//		return u;
	}

	
//	float getY(float u, float v) {
//		return v;
	}

	
//	float getZ(float u, float v) {
//		return getValue(u, v);
	}


}
;
}
#endif
