#ifndef shadow_renderer_SFILodSpace_H_
#define shadow_renderer_SFILodSpace_H_

#include "SFVertex3f.h"
#include "SFCamera.h"

namespace sf{

class SFILodSpace {

public:
	virtual ~SFILodSpace();
	virtual SFVertex3f getNearestPoint(SFCamera* camera)=0;

};

}
#endif
