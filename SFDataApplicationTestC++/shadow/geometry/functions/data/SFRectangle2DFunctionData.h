#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/data/SFFixedFloat16.h"
#include "shadow/geometry/functions/SFRectangle2DFunction.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFBinaryObject.h"

namespace sf{
class SFRectangle2DFunctionData : public SFDataAsset<SFSurfaceFunction>{

	SFBinaryObject<SFFixedFloat16>* x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16>* y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16>* w=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16>* h=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());

public:
	SFRectangle2DFunctionData();

	~SFRectangle2DFunctionData();

	SFRectangle2DFunction* buildResource();
	
	void updateResource(SFSurfaceFunction* resource);
};

}
#endif
