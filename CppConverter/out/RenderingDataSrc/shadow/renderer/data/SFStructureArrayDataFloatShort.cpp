#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/geometry/data.SFFloatShort.h"

namespace sf{
class SFStructureArrayDataFloatShort extends SFStructureArrayData<SFFloatShort>{

	SFStructureArrayDataFloatShort() {
		super(new SFFloatShort());
	}
}
;
}
#endif
