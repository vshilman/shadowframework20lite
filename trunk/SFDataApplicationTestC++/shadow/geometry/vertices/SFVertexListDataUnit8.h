#ifndef shadow_geometry_vertices_SFVertexListDataUnit8_H_
#define shadow_geometry_vertices_SFVertexListDataUnit8_H_

#include "shadow/geometry/data/SFFixedFloatUnit8.h"
#include "shadow/geometry/vertices/SFValueListData.h"

namespace sf{
class SFVertexListDataUnit8 : public SFValueListData{

	SFVertexListDataUnit8() :SFValueListData(new SFFixedFloatUnit8()){

	}

};
}
#endif
