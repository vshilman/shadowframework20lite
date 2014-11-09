#ifndef shadow_renderer_SfPrimitiveArrayResource_H_
#define shadow_renderer_SfPrimitiveArrayResource_H_

#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFResource.h"

namespace sf{
class SfPrimitiveArrayResource : public SFGraphicsResource{

	SFPrimitiveArray* array;
  	SFResource resource;

public:
  	SfPrimitiveArrayResource(SFPrimitiveArray* array);

  	SFPrimitiveArray* getArray();

  	SFResource* getResource();
	
  	void destroy();
	
  	void init();
};

}
#endif
