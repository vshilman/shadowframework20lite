#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/math/SFValuenf.h"

class SFVertexDataContainer<T: SFValuenf>{

	void getVertex(T write);

	void setVertex(T read);
}
;
}
#endif
