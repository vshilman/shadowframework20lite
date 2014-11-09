#ifndef shadow_renderer_SFTransformKeeper_H_
#define shadow_renderer_SFTransformKeeper_H_

#include "SFPipelineTransform3f.h"

namespace sf{

class SFTransformKeeper {

public:
	virtual ~SFTransformKeeper();

	virtual SFPipelineTransform3f* getTransform()=0;

};

}
#endif
