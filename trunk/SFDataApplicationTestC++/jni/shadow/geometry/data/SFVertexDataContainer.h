#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/math/SFValuenf.h"

namespace sf{

class SFVertexDataContainer{

public:
	virtual ~SFVertexDataContainer();

	virtual void getVertex(SFValuenf* write)=0;

	virtual void setVertex(SFValuenf* read)=0;
};
}
#endif
