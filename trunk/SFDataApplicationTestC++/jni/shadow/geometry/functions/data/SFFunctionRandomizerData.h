#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions/SFSurfaceFunctionRandomizer.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFInt.h"

namespace sf{
class SFFunctionRandomizerData : public SFDataAsset<SFSurfaceFunction>{

	SFInt* seed;

public:
	SFFunctionRandomizerData();
	~SFFunctionRandomizerData();
	
	SFSurfaceFunction* buildResource();
	
	void updateResource(SFSurfaceFunction* resource);
};

}
#endif
