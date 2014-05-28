#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/geometry/data.SFFixedFloatUnit8.h"


namespace sf{
class SFStructureArrayDataUnit8 extends SFStructureArrayData<SFFixedFloatUnit8>{

	SFStructureArrayDataUnit8() {
		super(new SFFixedFloatUnit8());
	}
}
;
}
#endif
