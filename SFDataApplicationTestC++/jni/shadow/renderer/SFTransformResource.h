#ifndef shadow_renderer_SFTransformResource_H_
#define shadow_renderer_SFTransformResource_H_

#include "SFTransform3f.h"
#include "SFIResource.h"
#include "SFResource.h"

namespace sf{

class SFTransformResource : public SFTransform3f , public SFIResource{

	SFResource resource;

	SFResource* getResource();

};

}
#endif
