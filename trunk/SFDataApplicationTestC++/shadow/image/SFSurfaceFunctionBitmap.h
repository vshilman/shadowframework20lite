#ifndef shadow_image_SFSurfaceFunctionBitmap_H_
#define shadow_image_SFSurfaceFunctionBitmap_H_

#include "SFSurfaceFunction.h"
#include "SFBitmap.h"

namespace sf{
class SFSurfaceFunctionBitmap : public SFBitmap{

  	SFSurfaceFunction* function;

public:
  	SFSurfaceFunctionBitmap(int width,int height,bool rgb,SFSurfaceFunction* function);

  	SFSurfaceFunction* getFunction();

  	void setFunction(SFSurfaceFunction* function);
	
  	void init();

};

}
#endif
