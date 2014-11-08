#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions/SFCurvedTubeFunction.h"
#include "shadow/system/data/SFDataset.h"
#include "shadow/geometry/functions/data/SFTwoCurvesFunctionData.h"

namespace sf{
class SFCurvedTubeFunctionData : public SFTwoCurvesFunctionData{

public:
	SFDataset* generateNewDatasetInstance();
	
	SFCurvedTubeFunction* buildResource();
	
	void updateResource(SFSurfaceFunction* resource);

};

}
#endif
