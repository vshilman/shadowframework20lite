#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions/SFRadialSurfaceFunction.h"
#include "shadow/system/data/SFDataset.h"
#include "shadow/geometry/functions/data/SFTwoCurvesFunctionData.h"

namespace sf{
class SFRadialSurfaceFunctionData : public SFTwoCurvesFunctionData{

public:
	SFDataset* generateNewDatasetInstance();

	SFRadialSurfaceFunction buildResource();
	
	void updateResource(SFSurfaceFunction* resource);

};

}
#endif
