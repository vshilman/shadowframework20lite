#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/geometry/data.SFFixedFloat16.h"

namespace sf{
class SFStructureArrayData16 extends SFStructureArrayData<SFFixedFloat16>{

	SFStructureArrayData16() {
		super(new SFFixedFloat16());
	}
}
;
}
#endif
