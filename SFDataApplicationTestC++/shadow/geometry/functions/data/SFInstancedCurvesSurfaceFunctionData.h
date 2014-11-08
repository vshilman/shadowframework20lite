#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves/data/SFCurvesDataKeeperSurface.h"
#include "shadow/geometry/curves/data/SFCurvesListKeeper.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFDataAssetObject.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFShortArray.h"

namespace sf{
class SFInstancedCurvesSurfaceFunctionData : public SFDataAsset<SFSurfaceFunction> {

	SFDataAssetObject<SFSurfaceFunction>* surfaceFunction;
	SFShortArray* indices;

public:
	SFInstancedCurvesSurfaceFunctionData();
	~SFInstancedCurvesSurfaceFunctionData();
	
	SFSurfaceFunction* buildResource();

	void updateResource(SFSurfaceFunction* resource);

};

}
#endif
